package org.test.servicio;

import org.test.entidad.ClienteEntidad;

import org.test.repositorio.BaseRepositorio;
import org.test.repositorio.ClienteRepositorio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClienteServicio extends BaseSevicio<ClienteEntidad, Long> {

    @Inject
     ClienteRepositorio clienteRepositorio;

    @Override
    public BaseRepositorio<ClienteEntidad, Long> entidad() {
        return clienteRepositorio;
    }
    
}
