package com.agh.soa.game.rest;

import com.agh.soa.Category;
import com.agh.soa.CategoryType;
import com.agh.soa.Element;
import com.agh.soa.game.negotiator.ContentNegotiator;
import remote.RemoteCategoryService;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
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
    public List<Category> getCategories(@Context HttpServletRequest request, @PathParam("categoryId") Integer id, final @Context SecurityContext securityContext){
        List<Category> categories = remoteCategoryService.getAllCategoriesByType(id);
//        List<Category> categories = securityContext.isUserInRole("Administrator") ?
//        remoteCategoryService.getAllCategoriesByType(id) : Collections.emptyList();
//        return categories.isEmpty() ? Response.status(403).build() : Response.status(200).entity(categories).build();
        Locale locale = request.getLocale();
        if (locale.equals(Locale.FRENCH))
            categories.forEach(ContentNegotiator::translate);
        return categories;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<CategoryType> getCategoryTypes(){
        return remoteCategoryService.getCategoryTypes();
    }

    @POST
    @Path("{typeId}/{categoryId}")
    @Consumes("application/json")
    public Response createElement(@PathParam("typeId") Integer typeId, @PathParam("categoryId") Integer categoryId, Element element){
        element.setCategoriesByCategoryId(remoteCategoryService.getAllCategoriesByType(typeId)
                .stream().filter( category -> category.getId() == categoryId).findAny().get());
        element.setElementTypesByTypeId(remoteCategoryService.getElementTypeById(typeId));
        remoteCategoryService.createElement(element);
        return Response.status(201).entity(element).build();
    }
}
