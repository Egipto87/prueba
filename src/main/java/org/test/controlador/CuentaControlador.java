package org.test.controlador;

import java.util.Date;
import java.util.List;

import org.test.entidad.CuentaEntidad;
import org.test.entidad.MovimientoEntidad;
import org.test.servicio.CuentaServicio;
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

@Path("/cuenta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CuentaControlador {

    @Inject
    CuentaServicio servicio;

    @Inject
    MovimientoServicio servicioM;
    
    @GET
    public List<CuentaEntidad> obtenerTodas() {
        return servicio.buscarTodos();
    }
   
    @GET
    @Path("/{id}")
    public CuentaEntidad obtenerPorId(@PathParam("id") Long id) {
        return servicio.buscarPorId(id);
    }     
    
    @POST
    public Response crear(CuentaEntidad entidad) {
                
        Date fechaActual = new Date();
        CuentaEntidad cuenta= servicio.guardar(entidad);
        if (cuenta== null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        MovimientoEntidad movimiento = new MovimientoEntidad();
        movimiento.setIdCuenta(cuenta);
        movimiento.setTipoMovimiento("CREDITO");
        movimiento.setFecha(fechaActual);
        movimiento.setSaldo(cuenta.getSaldoInicial());
        movimiento.setValor(cuenta.getSaldoInicial());
        servicioM.movimientos(movimiento);
        return  Response.ok("Cuenta Creada").build();
    }
    
    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        servicio.eliminar(id);
    }

    @PUT 
    @Path("/{id}")   
    public Response actualizar(@PathParam("id") Long id, CuentaEntidad entidad) {        
       CuentaEntidad actualizar= servicio.buscarPorId(id);
        if (actualizar == null) {
            Log.info("La entidad con id " + id + " No actualizado");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        servicio.actualizar(entidad);
        Log.info("La entidad con id " + id + " actualizado");
        return Response.ok("Actualizado").build();
    }
}
