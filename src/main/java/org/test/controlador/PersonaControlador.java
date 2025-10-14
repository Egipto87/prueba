package org.test.controlador;

import java.util.List;

import org.test.entidad.PersonaEntidad;
import org.test.servicio.PersonaServicio;

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

@Path("/persona")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaControlador {


    @Inject
    PersonaServicio servicio;

   @GET
    public List<PersonaEntidad> obtenerTodas() {
        return servicio.buscarTodos();
    } 

    @GET
    @Path("/{id}")
    public PersonaEntidad obtenerPorId(@PathParam("id") Long id) {
        return servicio.buscarPorId(id);
    }

    @GET
    @Path("/nombre/{nombre}")
    public List<PersonaEntidad> obtenerPorNombre(@PathParam("nombre") String nombre) {
        return  servicio.listarPorNombre(nombre);
    }
    
    
    @POST
    public PersonaEntidad crear(PersonaEntidad entidad) {
        return servicio.guardar(entidad);
    }
    
    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        servicio.eliminar(id);
    }

    @PUT 
    @Path("/{id}")   
    public Response actualizar(@PathParam("id") Long id, PersonaEntidad entidad) {        
       PersonaEntidad actualizar = servicio.buscarPorId(id);
        if (actualizar == null) {
            Log.info("La entida con id " + id + " No actualizado");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        servicio.actualizar(entidad);
        Log.info("La entidad con id " + id + " actualizado");
        return Response.ok("Actualizado").build();
    }

}
