package com.agh.soa.game.rest;

import com.agh.soa.Category;
import com.agh.soa.CategoryType;
import com.agh.soa.Element;
import remote.RemoteCategoryService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Collections;
import java.util.List;

@Path("/catalog")
public class CatalogResource {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!remote.RemoteCategoryService")
    private RemoteCategoryService remoteCategoryService;

    @Path("{categoryId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories(@PathParam("categoryId") Integer id, final @Context SecurityContext securityContext){
        List<Category> categories = securityContext.isUserInRole("Administrator") ?
        remoteCategoryService.getAllCategoriesByType(id) : Collections.emptyList();
        return categories.isEmpty() ? Response.status(403).build() : Response.status(200).entity(categories).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryType> getCategoryTypes(){
        return remoteCategoryService.getCategoryTypes();
    }

    @POST
    @Consumes("application/json")
    public Response createElement(Element element){
        remoteCategoryService.createElement(element);
        String result = "Element added " + element;
        return Response.status(201).entity(result).build();
    }
}
