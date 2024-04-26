package backendhm.serviciosRest.models.azure.repository.combobox;

import backendhm.serviciosRest.models.azure.entity.combobox.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartamentoRepository extends JpaRepository<Departamento,String> {
}
