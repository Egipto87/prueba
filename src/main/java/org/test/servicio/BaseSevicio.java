package org.test.servicio;

import java.util.List;

import org.test.repositorio.BaseRepositorio;

import jakarta.transaction.Transactional;

public abstract class BaseSevicio<T, I> {

    protected abstract BaseRepositorio<T, I> entidad();

    @Transactional
    public T guardar(T entidad) {
        entidad().persist(entidad);
        return entidad;
    }

    @Transactional
    public T actualizar(T entidad) {
        entidad().getEntityManager().merge(entidad);
        return entidad;
    }

    @Transactional
    public void eliminar(I id) {
        entidad().deleteById(id);
    }

    public List<T> buscarTodos() {
        return entidad().listAll();
    }

     public T buscarPorId(I id) {
        return entidad().findById(id);
    }
   
}
