package backendhm.serviciosRest.models.spTrujilloSD.Service;

import backendhm.serviciosRest.models.spTrujilloSD.dto.RespuestaBackendDTOTSD;
import backendhm.serviciosRest.models.spTrujilloSD.entity.RespuestaBackendTSD;
import backendhm.serviciosRest.models.spTrujilloSD.repository.IRespuestaBackendSDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("trujilloSDTransactionManagerFactory")
public class RespuestaBackendSDServiceImpl implements IRespuestaBackendServiceSD{

    @Autowired
    private IRespuestaBackendSDRepository respuestaBackendSDRepository;

    @Override
    public RespuestaBackendDTOTSD busquedaDniPorNOrden(long nOrden) {
        RespuestaBackendTSD respuestaBackendTSD=respuestaBackendSDRepository.busquedaDniPorNorden(nOrden).orElseThrow();
        return mapearDto(respuestaBackendTSD);
    }

    RespuestaBackendDTOTSD mapearDto(RespuestaBackendTSD respuestaBackendTSD){
        RespuestaBackendDTOTSD respuestaBackendDTOTSD=new RespuestaBackendDTOTSD();

        respuestaBackendDTOTSD.setId(respuestaBackendTSD.getId());
        respuestaBackendDTOTSD.setMensaje(respuestaBackendTSD.getMensaje());
        return  respuestaBackendDTOTSD;

    }
}
