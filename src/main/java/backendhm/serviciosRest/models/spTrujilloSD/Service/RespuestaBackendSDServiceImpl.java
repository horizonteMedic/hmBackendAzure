package backendhm.serviciosRest.models.spTrujilloSD.Service;

import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDatosPacienteDTO;
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
    public RespuestaBackendDTOTSD registrarDatosPaciente(RequestDatosPacienteDTO rdp) {
        RespuestaBackendTSD respuestaBackendTSD=respuestaBackendSDRepository.registroDatosPacienteServidorSD(rdp.getCodPa(),rdp.getNombresPa(),rdp.getFechaNaciminetoPa(),rdp.getSexoPa(),rdp.getEmailPa(),rdp.getLugarNacPa(),rdp.getNivelEstPa(),
                rdp.getOcupacionPa(),rdp.getEstadoCivilPa(),rdp.getDireccionPa(),rdp.getDepartamentoPa(),rdp.getProvinciaPa(),rdp.getDistritoPa(),rdp.getCaserioPA(),rdp.getFotoPa(),rdp.getCodAleatorioPa(),
                rdp.getTelCasaPa(),rdp.getTelTrabajoPa(),rdp.getCelPa(),rdp.getFechaRegistroPa(),rdp.getApellidosPa(),rdp.getHoraRegistroPa(),rdp.getTipoDoc()).orElseThrow();

        return mapearDto(respuestaBackendTSD);
    }

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
