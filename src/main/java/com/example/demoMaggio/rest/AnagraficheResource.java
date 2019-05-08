package com.example.demoMaggio.rest;

import java.net.URI;
import java.util.List;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.example.demoMaggio.business.AnagraficaStore;
import com.example.demoMaggio.entity.Anagrafica;

@Path("/anagrafiche")
public class AnagraficheResource {

	@Inject
	AnagraficaStore store;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Anagrafica> getAll() {
		return store.findAll();
	}
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Anagrafica find(@PathParam("id") Integer id) {
        return store.findId(id);
    }

    // @Produces(MediaType.valueOf(TEXT_PLAIN))
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(Anagrafica c, @Context UriInfo uriInfo) {
        Anagrafica saved = store.save(c);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("/" + saved.getId())
                .build();
        return Response.ok(uri).build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public void update(@PathParam("id") Integer id, Anagrafica c) {
        c.setId(id);
        store.save(c);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        store.remove(id);
    }	
	
}
