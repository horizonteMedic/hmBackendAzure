package backendhm.serviciosRest.models.spTrujilloNP.services;


import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDatosPacienteDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestNOrdenOcupacionalDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RespuestaBackendDTO;
import backendhm.serviciosRest.models.spTrujilloNP.entity.RespuestaBackendNP;
import backendhm.serviciosRest.models.spTrujilloNP.repository.IRespuestaBackendNPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("trujilloNPTransactionManagerFactory")
public class RespuestaBackendServiceImpl implements IRespuestaBackendService{

    @Autowired
    private IRespuestaBackendNPRepository respuestaBackendNPRepository;

    @Override
    public RespuestaBackendDTO registrarDatosPaciente(RequestDatosPacienteDTO rdp) {
        RespuestaBackendNP respuestaBackend=
                respuestaBackendNPRepository.registroDatosPacienteHuamachuco(rdp.getCodPa(),rdp.getNombresPa(),rdp.getFechaNaciminetoPa(),rdp.getSexoPa(),rdp.getEmailPa(),rdp.getLugarNacPa(),rdp.getNivelEstPa(),
                        rdp.getOcupacionPa(),rdp.getEstadoCivilPa(),rdp.getDireccionPa(),rdp.getDepartamentoPa(),rdp.getProvinciaPa(),rdp.getDistritoPa(),rdp.getCaserioPA(),rdp.getFotoPa(),rdp.getCodAleatorioPa(),
                        rdp.getTelCasaPa(),rdp.getTelTrabajoPa(),rdp.getCelPa(),rdp.getFechaRegistroPa(),rdp.getApellidosPa(),rdp.getHoraRegistroPa(),rdp.getTipoDoc()).orElseThrow();

        return mapearDTO(respuestaBackend);
    }

    @Override
    public RespuestaBackendDTO registroHistoriaClinica(RequestNOrdenOcupacionalDTO rdp) {
        System.out.println("el objeto dto de registro clinica:"+rdp);
        RespuestaBackendNP respuestaBackendNP=
                respuestaBackendNPRepository.registroHistoriaClinica(rdp.getN_orden(), rdp.getCodPa(),rdp.getRazonEmpresa(), rdp.getRazonContrata(), rdp.getNomEx(),rdp.getAlturaPo(),rdp.getMineralPo(),rdp.getFechaAperturaPo(),
                        rdp.getPrecioPo(),rdp.getEstadoEx(), rdp.getNomExamen(), rdp.getCargoDe(),rdp.getAreaO(),rdp.getN_medico(),rdp.getN_hora(),rdp.getTipoPago(),rdp.getN_fisttest(), rdp.getN_psicosen(), rdp.getN_testaltura(),
                        rdp.getColor(), rdp.getGrupoSan(),rdp.getGrupoFactorSan(),rdp.getCodClinica(), rdp.getVisualCompl(), rdp.getTrabCalientes(), rdp.getChk_covid1(), rdp.getChk_covid2(), rdp.getManipAlimentos(), rdp.getTextObserv1(),
                        rdp.getTextObserv2(), rdp.getCodSede(), rdp.getTipoPruebaCovid(), rdp.getTipoPrueba(), rdp.getNombreHotel(),rdp.getProtocolo(),rdp.getPrecioAdic(), rdp.getAutoriza(), rdp.getN_operacion(), rdp.getHerraManuales(),
                        rdp.getRxcDorsoLumbar(), rdp.getRxcKLumbar(), rdp.getRxcLumbosacra(), rdp.getRxcPlomos(), rdp.getMercurioo(), rdp.getReferencia()).orElseThrow();

            return mapearDTO(respuestaBackendNP);
    }

    private RespuestaBackendDTO mapearDTO(RespuestaBackendNP respuestaBackendNP){
        RespuestaBackendDTO respuestaBackendDTO=new RespuestaBackendDTO();

        respuestaBackendDTO.setId(respuestaBackendNP.getId());
        respuestaBackendDTO.setMensaje(respuestaBackendNP.getMensaje());

        return respuestaBackendDTO;
    }


}
