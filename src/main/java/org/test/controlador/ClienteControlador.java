package org.test.controlador;

import java.util.List;

import org.test.entidad.ClienteEntidad;
import org.test.servicio.ClienteServicio;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteControlador {

    @Inject
    ClienteServicio servicio;
    
    @GET
    public List<ClienteEntidad> obtenerTodas() {
        return servicio.buscarTodos();
    }
   

    @GET
    @Path("/{id}")
    public ClienteEntidad obtenerPorId(@PathParam("id") Long id) {
        return servicio.buscarPorId(id);
    }     
    
    @POST
    public ClienteEntidad crear(ClienteEntidad entidad) {
        return servicio.guardar(entidad);
    }
    
    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        servicio.eliminar(id);
    }

    @PUT 
    @Path("/{id}")   
    public Response actualizar(@PathParam("id") Long id, ClienteEntidad entidad) {        
       ClienteEntidad actualizar= servicio.buscarPorId(id);
        if (actualizar == null) {
            Log.info("Cliente con id " + id + " No actualizado");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        servicio.actualizar(entidad);
        Log.info("Clionete con id " + id + " actualizado");
        return Response.ok("Actualizado").build();
    }
}
