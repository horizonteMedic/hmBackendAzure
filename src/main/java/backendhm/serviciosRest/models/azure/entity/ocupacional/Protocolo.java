package backendhm.serviciosRest.models.azure.entity.ocupacional;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "protocolo_hm")
public class Protocolo implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_protocolo")
    private long idProtocolo;

    @Column(name = "nombre_protocolo", length = 300)
    private String nombreProtocolo;

    @Column(name = "ruc_empresa")
    private long rucEmpresa;

    private Boolean estado;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    @Column(name = "user_registro", length = 20)
    private String userRegistro;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_actualizacion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;

    @Column(name = "user_actualizacion", length = 20)
    private String userActualizacion;

    @Column(length = 12)
    private String precio;

    private String observacion;

    @JsonBackReference
    @OneToMany(mappedBy="protocolo_hm", cascade= CascadeType.ALL,orphanRemoval = true)
    private Set<ServicioProtocolo> detalleParametros=new HashSet<>();

}
