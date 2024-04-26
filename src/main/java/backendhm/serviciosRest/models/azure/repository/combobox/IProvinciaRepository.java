package backendhm.serviciosRest.models.azure.repository.combobox;

import backendhm.serviciosRest.models.azure.entity.combobox.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProvinciaRepository extends JpaRepository<Provincia,String> {

    @Query(value = "select *from ubigeo_provincia where id_departamento_ubigeo=?;", nativeQuery=true)
    Optional<List<Provincia>> listadoProvinciasPorDepartamento(String idDepartamento);
}
