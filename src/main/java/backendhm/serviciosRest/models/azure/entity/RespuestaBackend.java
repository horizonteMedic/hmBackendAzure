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
@Table(name = "respuesta_backend")
public class RespuestaBackend implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_resp")
    private Long id;

    @Column(length = 200)
    private String mensaje;
}
