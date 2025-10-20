package org.test.servicio;

import java.util.Date;
import java.util.List;

import org.test.entidad.MovimientoEntidad;

import org.test.repositorio.BaseRepositorio;
import org.test.repositorio.MovimientoRepositorio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MovimientoServicio extends BaseSevicio<MovimientoEntidad, Long> {

    @Inject
    MovimientoRepositorio movimientoRepositorio;

    @Override
    public BaseRepositorio<MovimientoEntidad, Long> entidad(){
        return movimientoRepositorio;
    }

    
    @SuppressWarnings("unchecked")
    @Transactional
    public String movimientos(MovimientoEntidad m){
        String mensaje="";
        Date fechaActual = new Date();
    
        Query sql = movimientoRepositorio.getEntityManager().createQuery(" Select p  from MovimientoEntidad p where p.idCuenta =: cuenta order by p.fecha DESC");
    
        sql.setParameter("cuenta", m.getIdCuenta());
        
        Double saldo;
        List<MovimientoEntidad> movimiento= sql.getResultList();
        if(movimiento.size()==0){
            guardar(m);  
        }else {
            saldo =0.0;
            if (m.getTipoMovimiento().equals("CREDITO") && movimiento.get(0).getValor() >= 0) {
                saldo=movimiento.get(0).getSaldo() + m.getValor();
                m.setSaldo(saldo);
                m.setFecha(fechaActual);
                movimientoRepositorio.getEntityManager().persist(m);
    
            } else {
                if ( movimiento.get(0).getSaldo() > m.getValor() ) {
                   saldo=movimiento.get(0).getSaldo() - m.getValor(); 
                    m.setSaldo(saldo);
                    m.setFecha(fechaActual);
                    movimientoRepositorio.getEntityManager().persist(m);
                    } else {
                        if (movimiento.get(0).getValor() ==0.0 ) {
                          mensaje="Saldo no disponible";  
                        }
                        mensaje="Saldo insuficiente"; 
                    }
    
                } 
    
        }
            return mensaje; 
    }

    @SuppressWarnings("unchecked")
    public List<MovimientoEntidad> reporte( Long  idCuenta, Date desde, Date hasta){    

    Query sql = movimientoRepositorio.getEntityManager().createQuery(" Select p  from MovimientoEntidad p where p.idCuenta.idCuenta =:idCuenta and p.fecha BETWEEN :fechaInicio AND :fechaFin order by p.idMovimiento ");
    
    sql.setParameter("idCuenta", idCuenta);
    sql.setParameter("fechaInicio", desde);
    sql.setParameter("fechaFin", hasta);
    
    List<MovimientoEntidad> movimientos = sql.getResultList();
       
    return movimientos; 
    }



}
