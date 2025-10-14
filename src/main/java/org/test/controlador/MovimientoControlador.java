package org.test.controlador;

import java.util.Date;
import java.util.List;

import org.test.entidad.MovimientoEntidad;
import org.test.servicio.MovimientoServicio;

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

@Path("/movimiento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovimientoControlador {

    @Inject
    MovimientoServicio servicio;

        @GET
    public List<MovimientoEntidad> obtenerTodas() {
        return servicio.buscarTodos();
    }
   

    @GET
    @Path("/{id}")
    public MovimientoEntidad obtenerPorId(@PathParam("id") Long id) {
        return servicio.buscarPorId(id);
    }
    
    @GET
    @Path("/reporte")
    public List<MovimientoEntidad> reporte( @PathParam("id") Long id, @PathParam("inicio") Date desde, @PathParam("fin") Date hasta) {
        return servicio.reporte(id, desde, hasta);
    }
    
    @POST
    public MovimientoEntidad crear(MovimientoEntidad entidad) {
        return servicio.guardar(entidad);
    }
     @POST
     @Path("/transaccion")
    public Response movimiento(MovimientoEntidad entidad) {
        String mensaje = servicio.movimientos(entidad);
        if (!servicio.movimientos(entidad).isEmpty()) {
           return Response.ok(mensaje).build(); 
        }
            return Response.ok("Registrado").build(); 
               
    }
    
    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        servicio.eliminar(id);
    }

    @PUT 
    @Path("/{id}")   
    public Response actualizar(@PathParam("id") Long id, MovimientoEntidad entidad) {        
       MovimientoEntidad actualizar= servicio.buscarPorId(id);
        if (actualizar == null) {
            Log.info("La entidad con id " + id + " No actualizado");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        servicio.actualizar(entidad);
        Log.info("La entidad con id " + id + " actualizado");
        return Response.ok("Actualizado").build();
    }

}
