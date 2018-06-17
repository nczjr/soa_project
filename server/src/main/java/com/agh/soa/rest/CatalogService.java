package com.agh.soa.rest;

import com.agh.soa.Category;
import com.agh.soa.CategoryType;
import com.agh.soa.Element;
import com.agh.soa.dao.CategoryDAO;
import com.agh.soa.dao.ElementDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/catalog")
public class CatalogService {

    @Inject
    CategoryDAO categoryDAO;

    @Inject
    ElementDAO elementDAO;

    @Path("{categoryId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getCategories(@PathParam("categoryId") Integer id){
        return categoryDAO.findByCategoryType(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryType> getCategoryTypes(){
        return categoryDAO.findCategoryTypes();
    }

    @POST
    @Consumes("application/json")
    public Response createElement(Element element){
        elementDAO.create(element);
        String result = "Element added " + element;
        return Response.status(201).entity(result).build();
    }
}
