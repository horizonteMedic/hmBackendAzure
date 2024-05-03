package backendhm.serviciosRest.models.azure.repository.sistemasArchivos;

import backendhm.serviciosRest.models.azure.entity.sistemaArchivos.UsuarioEmpresaOContrata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmpresaContrataRepository extends JpaRepository<UsuarioEmpresaOContrata,Long> {


}
