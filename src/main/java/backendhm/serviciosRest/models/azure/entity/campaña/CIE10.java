package backendhm.serviciosRest.models.azure.entity.campaña;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "desktop_cie10")
public class CIE10 {

    @Id
    @Column(length = 10)
    private String codigo;

    @Column(length = 400)
    private  String descripcion;
}
