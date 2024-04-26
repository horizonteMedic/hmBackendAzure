package backendhm.serviciosRest.models.azure.entity.asistencial;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "contratas")
public class Contrata implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ruc_contrata")
    private String id;

    @Column(name = "razon_contrata")
    private String razonContrata;

    @Column(length = 255,name = "direccion_contrata")
    private String direccionContrata;

    @Column(length = 50,name = "telefono_contrata")
    private String telefonoContrata;

    @Column(name = "responsable_contrata")
    private String responsableContrata;

    @Column(name = "email_contrata")
    private String emailContrata;
}
