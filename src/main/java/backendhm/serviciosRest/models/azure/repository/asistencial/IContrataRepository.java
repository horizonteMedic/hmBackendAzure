package backendhm.serviciosRest.models.azure.repository.asistencial;

import backendhm.serviciosRest.models.azure.entity.asistencial.Contrata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContrataRepository extends JpaRepository<Contrata,String> {
    @Query(value = "select * from contratas where ruc_contrata IN (select uec.ruc from usuario_empresa_contrada as uec inner join usuario as us on uec.id_user=us.id_user\n" +
            "\t where us.username=? and tipo=?);", nativeQuery=true)
    List<Contrata> PorUsernameContrata(String userName, String tipoEmpCont);
}
