package backendhm.serviciosRest.models.azure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "entidad_dto")
public class EntidadDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_entidad")
    private Long id;

    @Column(name = "ruc", length = 11)
    private String ruc;

    @Column(name = "razon_social", length = 200)
    private String razonSocial;

}
