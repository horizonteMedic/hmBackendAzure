package backendhm.serviciosRest.models.azure.entity.parametros;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "lista_parametros")
public class ListaParametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_lista_parametros")
    private Long id;

    @Column(nullable = false,length = 80)
    private String descripcion;

    @JsonBackReference
    @OneToMany(mappedBy="listaParametros", cascade= CascadeType.ALL,orphanRemoval = true)
    private Set<DetalleParametros> detalleParametros=new HashSet<>();
}
