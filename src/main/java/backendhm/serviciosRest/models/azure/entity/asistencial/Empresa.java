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
@Table(name = "empresas")
public class Empresa implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ruc_empresa")
    private String id;

    @Column(name = "razon_empresa")
    private String razonEmpresa;

    @Column(length = 255,name = "direccion_empresa")
    private String direccionEmpresa;

    @Column(length = 50,name = "telefono_empresa")
    private String telefonoEmpresa;

    @Column(name = "responsable_empresa")
    private String responsableEmpresa;

    @Column(name = "email_empresa")
    private String emailEmpresa;

    private String apiToken;
}
