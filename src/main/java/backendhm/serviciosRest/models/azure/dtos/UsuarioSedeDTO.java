package backendhm.serviciosRest.models.azure.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSedeDTO {

    private long idUsuarioSede;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    @Column(name ="user_registro",length = 18)
    private String userRegistro;

    private long id_sede;

    private long id_user;

    private String username;

    private String sede;
}
