package org.test.servicio;

import java.util.List;

import org.test.entidad.PersonaEntidad;
import org.test.repositorio.BaseRepositorio;
import org.test.repositorio.PersonaRepositorio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Query;

@ApplicationScoped
public class PersonaServicio extends BaseSevicio<PersonaEntidad, Long> {
    @Inject
   PersonaRepositorio personaRepositorio;

    @Override
    protected BaseRepositorio<PersonaEntidad, Long> entidad() {
        return personaRepositorio;
    }

    @SuppressWarnings("unchecked")
    public List<PersonaEntidad> listarPorNombre(String nombre){

    Query sql = personaRepositorio.getEntityManager().createQuery(" Select p  from PersonaEntidad p where p.nombre =: nombre");

    sql.setParameter("nombre", nombre);

    List<PersonaEntidad> personas= sql.getResultList();
        return personas; 
    }

}
