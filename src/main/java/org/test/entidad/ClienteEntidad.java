package org.test.entidad;


import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cliente", schema = "ventas")
@Data
public class ClienteEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "estado")
    private Boolean estado;
    
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private PersonaEntidad personaEntidad;
}
