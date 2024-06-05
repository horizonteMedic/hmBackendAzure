package backendhm.serviciosRest.models.spTrujilloNP.services;


import backendhm.serviciosRest.models.spTrujilloNP.dto.*;
import backendhm.serviciosRest.models.spTrujilloNP.entity.MatrizAdministrativa;
import backendhm.serviciosRest.models.spTrujilloNP.entity.RespuestaBackendNP;
import backendhm.serviciosRest.models.spTrujilloNP.repository.IMatrizAdminRepository;
import backendhm.serviciosRest.models.spTrujilloNP.repository.IRespuestaBackendNPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional("trujilloNPTransactionManagerFactory")
public class RespuestaBackendServiceImpl implements IRespuestaBackendService{

    @Autowired
    private IRespuestaBackendNPRepository respuestaBackendNPRepository;

    @Autowired
    private IMatrizAdminRepository matrizAdminRepository;

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
        RespuestaBackendNP respuestaBackendNP=
                respuestaBackendNPRepository.registroHistoriaClinica(rdp.getN_orden(), rdp.getCodPa(),rdp.getRazonEmpresa(), rdp.getRazonContrata(), rdp.getNomEx(),rdp.getAlturaPo(),rdp.getMineralPo(),rdp.getFechaAperturaPo(),
                        rdp.getPrecioPo(),rdp.getEstadoEx(), rdp.getNomExamen(), rdp.getCargoDe(),rdp.getAreaO(),rdp.getN_medico(),rdp.getN_hora(),rdp.getTipoPago(),rdp.getN_fisttest(), rdp.getN_psicosen(), rdp.getN_testaltura(),
                        rdp.getColor(), rdp.getGrupoSan(),rdp.getGrupoFactorSan(),rdp.getCodClinica(), rdp.getVisualCompl(), rdp.getTrabCalientes(), rdp.getChk_covid1(), rdp.getChk_covid2(), rdp.getManipAlimentos(), rdp.getTextObserv1(),
                        rdp.getTextObserv2(), rdp.getCodSede(), rdp.getTipoPruebaCovid(), rdp.getTipoPrueba(), rdp.getNombreHotel(),rdp.getProtocolo(),rdp.getPrecioAdic(), rdp.getAutoriza(), rdp.getN_operacion(), rdp.getHerraManuales(),
                        rdp.getRxcDorsoLumbar(), rdp.getRxcKLumbar(), rdp.getRxcLumbosacra(), rdp.getRxcPlomos(), rdp.getMercurioo(), rdp.getReferencia()).orElseThrow();

            return mapearDTO(respuestaBackendNP);
    }

    @Override
    public RespuestaBackendDTO busquedaDniPorNOrden(long nOrden) {
        RespuestaBackendNP respuestaBackendNP=respuestaBackendNPRepository.busquedaDniPorNorden(nOrden).orElseThrow();

        return mapearDTO(respuestaBackendNP);
    }

    @Override
    public RespuestaBackendDTO busquedaDniPorReferencia(String referencia) {
        RespuestaBackendNP respuestaBackendNP=respuestaBackendNPRepository.busquedaDniPorReferencia(referencia).orElseThrow();

        return mapearDTO(respuestaBackendNP);
    }

    @Override
    public List<ResponseMatrizDTO> listadoMatrizAdministrativa(RequesMatrizDTO requesMatrizDTO) {
            List<MatrizAdministrativa> listadoMatrizAdmin=matrizAdminRepository.listadoMatrizAdmin(requesMatrizDTO.getRucContrata(),requesMatrizDTO.getFechaInicio(), requesMatrizDTO.getFechaFinal()).orElseThrow();

        return listadoMatrizAdmin.stream().map(this::mapearDTOMADM).collect(Collectors.toList());
    }

    private RespuestaBackendDTO mapearDTO(RespuestaBackendNP respuestaBackendNP){
        RespuestaBackendDTO respuestaBackendDTO=new RespuestaBackendDTO();

        respuestaBackendDTO.setId(respuestaBackendNP.getId());
        respuestaBackendDTO.setMensaje(respuestaBackendNP.getMensaje());

        return respuestaBackendDTO;
    }

    private ResponseMatrizDTO mapearDTOMADM(MatrizAdministrativa matrizAdministrativa){
        ResponseMatrizDTO responseMatrizDTO=new ResponseMatrizDTO();

        responseMatrizDTO.setEdad(matrizAdministrativa.getEdad());
        responseMatrizDTO.setDni(matrizAdministrativa.getDni());
        responseMatrizDTO.setCargo(matrizAdministrativa.getCargo());
        responseMatrizDTO.setEdadTexto(matrizAdministrativa.getEdadTexto());
        responseMatrizDTO.setAptitudEmo(matrizAdministrativa.getAptitudEmo());
        responseMatrizDTO.setObservacion(matrizAdministrativa.getObservacion());
        responseMatrizDTO.setFechaExamen(matrizAdministrativa.getFechaExamen());
        responseMatrizDTO.setApellidosNombres(matrizAdministrativa.getApellidosNombres());
        responseMatrizDTO.setFechaSolicitud(matrizAdministrativa.getFechaSolicitud());
        responseMatrizDTO.setFechaNacimiento(matrizAdministrativa.getFechaNacimiento());
        responseMatrizDTO.setRazonContrata(matrizAdministrativa.getRazonContrata());

        return responseMatrizDTO;

    }

}
