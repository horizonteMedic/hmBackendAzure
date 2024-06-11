package backendhm.serviciosRest.models.spTrujilloSD.Service;

import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDatosPacienteDTO;
import backendhm.serviciosRest.models.spTrujilloSD.dto.RespuestaBackendDTOTSD;
import backendhm.serviciosRest.models.spTrujilloSD.entity.BackendEntityDatosPacienteSD;
import backendhm.serviciosRest.models.spTrujilloSD.entity.RespuestaBackendTSD;
import backendhm.serviciosRest.models.spTrujilloSD.repository.IBackendEntityDatosPacienteSDRepository;
import backendhm.serviciosRest.models.spTrujilloSD.repository.IRespuestaBackendSDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("trujilloSDTransactionManagerFactory")
public class RespuestaBackendSDServiceImpl implements IRespuestaBackendServiceSD{

    @Autowired
    private IRespuestaBackendSDRepository respuestaBackendSDRepository;

    @Autowired
    private IBackendEntityDatosPacienteSDRepository backendEntityDatosPacienteSDRepository;

    @Override
    public RequestDatosPacienteDTO busquedaDatosPacienteDNI(long dni) {
        return mapearDTODatosPaciente(backendEntityDatosPacienteSDRepository.busquedaDatosPacienteDNI(dni).orElseThrow());
    }

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

    private RequestDatosPacienteDTO mapearDTODatosPaciente(BackendEntityDatosPacienteSD bk){
        RequestDatosPacienteDTO respuestaBackendDTO=new RequestDatosPacienteDTO();

        respuestaBackendDTO.setCodPa(bk.getCodPa());
        respuestaBackendDTO.setCaserioPA(bk.getCaserioPA());
        respuestaBackendDTO.setApellidosPa(bk.getApellidosPa());
        respuestaBackendDTO.setCelPa(bk.getCelPa());
        respuestaBackendDTO.setDepartamentoPa(bk.getDepartamentoPa());
        respuestaBackendDTO.setDireccionPa(bk.getDireccionPa());
        respuestaBackendDTO.setDistritoPa(bk.getDistritoPa());
        respuestaBackendDTO.setEmailPa(bk.getEmailPa());
        respuestaBackendDTO.setFotoPa(bk.getFotoPa());
        respuestaBackendDTO.setEstadoCivilPa(bk.getEstadoCivilPa());
        respuestaBackendDTO.setNivelEstPa(bk.getNivelEstPa());
        respuestaBackendDTO.setFechaRegistroPa(bk.getFechaRegistroPa());
        respuestaBackendDTO.setFechaNaciminetoPa(bk.getFechaNaciminetoPa());
        respuestaBackendDTO.setHoraRegistroPa(bk.getHoraRegistroPa());
        respuestaBackendDTO.setCodAleatorioPa(bk.getCodAleatorioPa());
        respuestaBackendDTO.setLugarNacPa(bk.getLugarNacPa());
        respuestaBackendDTO.setOcupacionPa(bk.getOcupacionPa());
        respuestaBackendDTO.setSexoPa(bk.getSexoPa());
        respuestaBackendDTO.setTelCasaPa(bk.getTelCasaPa());
        respuestaBackendDTO.setTelTrabajoPa(bk.getTelTrabajoPa());
        respuestaBackendDTO.setTipoDoc(bk.getTipoDoc());
        respuestaBackendDTO.setNombresPa(bk.getNombresPa());
        respuestaBackendDTO.setCaserioPA(bk.getCaserioPA());


        return respuestaBackendDTO;
    }
}
