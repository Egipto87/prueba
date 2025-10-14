package org.test.servicio;

import org.test.entidad.CuentaEntidad;
import org.test.repositorio.BaseRepositorio;
import org.test.repositorio.CuentaRepositorio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CuentaServicio extends BaseSevicio<CuentaEntidad, Long> {

    @Inject
    CuentaRepositorio cuentaRepositorio;

    @Override
    public BaseRepositorio<CuentaEntidad, Long> entidad(){
        return cuentaRepositorio;
    }



}
