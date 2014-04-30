package com.matt9949.jerseyrestexample.resource;

import com.matt9949.jerseyrestexample.bean.Cat;
import com.matt9949.jerseyrestexample.service.CatService;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("/v1/cats")
public class CatResource {

    private CatService catService;

    @Context
    private UriInfo uriInfo;

    public CatResource() {
        this.catService = new CatService();
    }

    CatResource(CatService catService, UriInfo uriInfo){
        this.catService = catService;
        this.uriInfo = uriInfo;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response postCat(final Cat cat){

        String catId = catService.createCat(cat);

        final URI location = UriBuilder.fromUri(uriInfo.getAbsolutePath()).path(catId).build();

        return Response.created(location)
                       .entity(null)
                       .build();
    }

    @GET
    @Path("/{catId}")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response getCat(@PathParam("catId") final String catId){

        Cat cat = catService.retrieveCat(catId);

        return Response.status(Response.Status.OK)
                       .entity(cat)
                       .build();
    }

    @PUT
    @Path("/{catId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response updateCat(@PathParam("catId") final String catId, final Cat cat){

        catService.updateCat(catId, cat);

        final URI location = UriBuilder.fromUri(uriInfo.getAbsolutePath()).path(catId).build();

        return Response.noContent()
                       .header("Location", location)
                       .entity(null)
                       .build();
    }

    @DELETE
    @Path("/{catId}")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response deleteCat(@PathParam("catId") final String catId){

        catService.deleteCat(catId);

        return Response.noContent()
                .build();
    }

}
