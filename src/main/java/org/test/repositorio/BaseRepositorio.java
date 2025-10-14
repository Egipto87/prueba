package org.test.repositorio;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Data;

@Data
@ApplicationScoped
public abstract class BaseRepositorio<T, I> implements PanacheRepositoryBase<T, I> {

}
