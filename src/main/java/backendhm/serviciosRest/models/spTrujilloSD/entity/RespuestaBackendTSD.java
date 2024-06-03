package backendhm.serviciosRest.models.spTrujilloSD.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "respuesta_backend")
public class RespuestaBackendTSD implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_resp")
    private Long id;

    @Column(length = 200)
    private String mensaje;
}
