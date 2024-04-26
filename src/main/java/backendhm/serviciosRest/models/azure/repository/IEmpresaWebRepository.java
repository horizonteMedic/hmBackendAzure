package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.EmpresaWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IEmpresaWebRepository extends JpaRepository<EmpresaWeb,Long> {

}
