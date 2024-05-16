package backendhm.serviciosRest.models.azure.repository.sistemasArchivos;

import backendhm.serviciosRest.models.azure.entity.TipoArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITipoArchivoRepository extends JpaRepository<TipoArchivo,Long> {


    @Query(value = "select ta.* from tipo_archivo as ta where ta.estado=true and ta.id_tipo_archivo in(\n" +
            "\t select taa.id_tipo_archivo_asignar from tipo_archivo_asignado as taa inner join usuario_rol as ur\n" +
            "\t on taa.id_rol=ur.id_rol where ur.id_user=? );", nativeQuery=true)
    Optional<List<TipoArchivo>> listadoTipoArchivosConFiltroIdUser(long idUser);

}
