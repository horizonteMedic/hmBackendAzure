package backendhm.serviciosRest.models.azure.entity;

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
@Table(name = "historial_usuarios")
public class HistorialUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_historial_usuario")
    private Long id;

    @Column(name = "tipo_documento", length = 30)
    private String tipoDocumento;

    @Column(name = "num_documento")
    private long numDocumento;

    @Column(length = 50)
    private String apellidos;

    @Column(length = 50)
    private String nombres;

    @Column(length = 20)
    private String usuario;

    @Column(length = 50)
    private String rol;

    private Boolean vigente;

    @Column(name = "id_user")
    private long idUser;

    @Column(name = "id_user_rol")
    private long idUserRol;

    @Column(name = "id_empleado")
    private long idEmpleado;
}
