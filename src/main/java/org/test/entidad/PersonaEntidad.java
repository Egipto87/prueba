package org.test.entidad;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table(name = "persona", schema = "ventas")
@Data
public class PersonaEntidad {   
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "genero")
    private String genero;

    @Column(name = "fecha_nacimiento")    
    private Date fechaNacimiento;

    @Column(name = "identificacion")
    private String identificacion;

    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "telefono")
    private String telefono;

    @Column(name = "estado")
    private Boolean estado;    
    
}
