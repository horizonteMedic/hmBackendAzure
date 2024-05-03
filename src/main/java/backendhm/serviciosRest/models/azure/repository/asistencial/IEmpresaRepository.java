package backendhm.serviciosRest.models.azure.repository.asistencial;

import backendhm.serviciosRest.models.azure.entity.asistencial.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa,String> {
    @Query(value = "select *from empresas where ruc_empresa=?;", nativeQuery=true)
    Optional<Empresa> busquedaEmpresaPorRuc(String rucEmpresa);

}
