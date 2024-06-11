package backendhm.serviciosRest.models.spTrujilloNP.repository;

import backendhm.serviciosRest.models.spTrujilloNP.entity.BackendEntityDatosPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBackendEntityDatosPacienteRepository extends JpaRepository<BackendEntityDatosPaciente,Long> {
    @Query(value = "select cod_pa,nombres_pa,CAST(fecha_nacimiento_pa as text),CAST (sexo_pa AS TEXT),email_pa,lugar_nac_pa, nivel_est_pa, ocupacion_pa, estado_civil_pa, direccion_pa,departamento_pa, provincia_pa,\n" +
            "\tdistrito_pa,caserio_pa, cast(foto_pa as text), cod_aleatorio_pa,tel_casa_pa, tel_trabajo_pa, cel_pa, CAST(fecha_registro_pa as text), apellidos_pa, CAST(hora_registro_pa as text), tipo_doc\n" +
            "  from datos_paciente where cod_pa=?", nativeQuery=true)
    Optional<BackendEntityDatosPaciente> busquedaDatosPacienteDNI(long dni);
}
