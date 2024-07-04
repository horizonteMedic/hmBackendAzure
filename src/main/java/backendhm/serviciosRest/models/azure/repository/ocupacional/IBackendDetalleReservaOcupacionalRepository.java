package backendhm.serviciosRest.models.azure.repository.ocupacional;

import backendhm.serviciosRest.models.azure.entity.ocupacional.BackendDetalleReservaOcupacionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBackendDetalleReservaOcupacionalRepository extends JpaRepository<BackendDetalleReservaOcupacionalEntity,Long> {

    @Query(value = "select row_number() over() as id_resp,shw.nombre_sede as sede, co.dni, \n" +
            "\tcase  when co.ruc_empresa is null then 'N/A' else emp.razon_empresa end as empresa,\n" +
            "\tcase  when co.ruc_contrata is null then 'N/A' else cnt.razon_contrata end as contrata,\n" +
            "\tco.user_registro as programador, co.fecha_reserva as fecha\n" +
            "\tfrom cita_ocupacional as co inner join sede_hm_web as shw \n" +
            "\ton co.nomensede=shw.codigo_sede left join empresas  as emp on \n" +
            "\tcast(co.ruc_empresa as text)= emp.ruc_empresa\n" +
            "\tleft join contratas as cnt on cast(co.ruc_contrata as text)=cnt.ruc_contrata\t\n" +
            "where co.nomensede=? and cast(co.fecha_reserva as text)=? and co.user_registro=?", nativeQuery=true)
    Optional<List<BackendDetalleReservaOcupacionalEntity>> litaDetalleReservaPorFiltros(String nameSede, String FechaReserva, String userName);

}
