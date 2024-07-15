package backendhm.serviciosRest.models.azure.repository.combobox;

import backendhm.serviciosRest.models.azure.entity.combobox.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDepartamentoRepository extends JpaRepository<Departamento,String> {
    @Query(value = "select id_departamento_ubigeo, UPPER(nombre_departamento) AS nombre_departamento from ubigeo_departamento", nativeQuery=true)
    Optional<List<Departamento>> listadoDeparatamentos();
}
