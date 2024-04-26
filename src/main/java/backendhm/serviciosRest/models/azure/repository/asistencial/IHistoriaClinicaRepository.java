package backendhm.serviciosRest.models.azure.repository.asistencial;

import backendhm.serviciosRest.models.azure.entity.asistencial.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface IHistoriaClinicaRepository extends JpaRepository<HistoriaClinica,String> {

    @Query(value = "select CAST((COUNT(N_ORDEN)  +10001) AS TEXT) as n_orden from historia_clinica;", nativeQuery=true)
    String nroHistoriaClinicaRegistrar();
}
