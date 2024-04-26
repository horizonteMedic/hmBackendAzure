package backendhm.serviciosRest.models.azure.entity.combobox;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ubigeo_departamento")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_departamento_ubigeo", length = 2)
    private String id;

    @Column(name = "nombre_departamento", length = 50)
    private String nombre;

}
