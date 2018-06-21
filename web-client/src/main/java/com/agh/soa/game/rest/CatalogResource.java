package com.agh.soa.game.rest;

import com.agh.soa.Category;
import com.agh.soa.CategoryType;
import com.agh.soa.Element;
import com.agh.soa.game.negotiator.ContentNegotiator;
import remote.RemoteCategoryService;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Path("/catalog")
public class CatalogResource {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!remote.RemoteCategoryService")
    private RemoteCategoryService remoteCategoryService;

    @Path("{categoryId}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCategories(@Context HttpServletRequest request, @PathParam("categoryId") Integer id, final @Context SecurityContext securityContext){
        List<Category> categories = securityContext.isUserInRole("Administrator") ?
        remoteCategoryService.getAllCategoriesByType(id) : Collections.emptyList();
        Locale locale = request.getLocale();
        if (locale.equals(Locale.FRENCH)) {
            categories.forEach(ContentNegotiator::translate);
        }
        GenericEntity<List<Category>> entity = new GenericEntity<List<Category>>(categories){};
        return categories.isEmpty() ? Response.status(403).build() : Response.status(200).entity(entity).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCategoryTypes(){
        List<CategoryType> types = remoteCategoryService.getCategoryTypes();
        GenericEntity<List<CategoryType>> entity = new GenericEntity<List<CategoryType>>(types){};
        return Response.ok().entity(entity).build();
    }

    @POST
    @Path("{typeId}/{categoryId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createElement(@PathParam("typeId") Integer typeId, @PathParam("categoryId") Integer categoryId, Element element){
        element.setCategoriesByCategoryId(remoteCategoryService.getAllCategoriesByType(typeId)
                .stream().filter( category -> category.getId() == categoryId).findAny().get());
        element.setElementTypesByTypeId(remoteCategoryService.getElementTypeById(typeId));
        remoteCategoryService.createElement(element);
        return Response.status(201).entity(element).build();
    }
}
