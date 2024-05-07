package backendhm.serviciosRest.models.azure.repository.sistemasArchivos;

import backendhm.serviciosRest.models.azure.entity.sistemaArchivos.UsuarioEmpresaOContrata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEmpresaContrataRepository extends JpaRepository<UsuarioEmpresaOContrata,Long> {

    @Query(value = "select uec.* from usuario_empresa_contrada as uec where uec.id_user=?;", nativeQuery=true)
    Optional<List<UsuarioEmpresaOContrata>> listadoEmpresasContratasPorIdUser(long idUser);

}
