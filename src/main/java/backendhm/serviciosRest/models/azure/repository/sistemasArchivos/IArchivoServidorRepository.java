package backendhm.serviciosRest.models.azure.repository.sistemasArchivos;

import backendhm.serviciosRest.models.azure.entity.sistemaArchivos.ArchivosServidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IArchivoServidorRepository extends JpaRepository<ArchivosServidor,Long> {

    @Query(value = "select *from archivos_servidores where historia_clinica=? and id_tipo_archivo=?;", nativeQuery=true)
    Optional<ArchivosServidor> detalleArchivoServidor(String hc, long ta);

    @Query(value = "select ars.* from archivos_servidores as ars where ars.historia_clinica=? and \n" +
            "\t ars.id_tipo_archivo in (select taa.id_tipo_archivo_asignar from tipo_archivo_asignado as taa inner join usuario_rol as ur\n" +
            "\t on taa.id_rol=ur.id_rol where ur.id_user=? );", nativeQuery=true)
    Optional<List<ArchivosServidor>> listadoArchivosPorHCYIdUser(String hc, long idUser);
}
