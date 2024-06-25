package backendhm.serviciosRest.models.spTrujilloNP.services;


import backendhm.serviciosRest.models.azure.dtos.Ocupacional.RequestHistoriaClinicaOcupacionalDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.*;
import backendhm.serviciosRest.models.spTrujilloNP.entity.BackendEntityDatosPaciente;
import backendhm.serviciosRest.models.spTrujilloNP.entity.MatrizAdministrativa;
import backendhm.serviciosRest.models.spTrujilloNP.entity.ResponseMatrizSalud;
import backendhm.serviciosRest.models.spTrujilloNP.entity.RespuestaBackendNP;
import backendhm.serviciosRest.models.spTrujilloNP.repository.IBackendEntityDatosPacienteRepository;
import backendhm.serviciosRest.models.spTrujilloNP.repository.IMatrizAdminRepository;
import backendhm.serviciosRest.models.spTrujilloNP.repository.IMatrizSaludRepository;
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

    @Autowired
    private IMatrizSaludRepository matrizSaludRepository;

    @Autowired
    private IBackendEntityDatosPacienteRepository backendEntityDatosPacienteRepository;

    @Override
    public RespuestaBackendDTO registrarDatosPaciente(RequestDatosPacienteDTO rdp) {
        RespuestaBackendNP respuestaBackend=
                respuestaBackendNPRepository.registroDatosPacienteHuamachuco(rdp.getCodPa(),rdp.getNombresPa(),rdp.getFechaNaciminetoPa(),rdp.getSexoPa(),rdp.getEmailPa(),rdp.getLugarNacPa(),rdp.getNivelEstPa(),
                        rdp.getOcupacionPa(),rdp.getEstadoCivilPa(),rdp.getDireccionPa(),rdp.getDepartamentoPa(),rdp.getProvinciaPa(),rdp.getDistritoPa(),rdp.getCaserioPA(),rdp.getFotoPa(),rdp.getCodAleatorioPa(),
                        rdp.getTelCasaPa(),rdp.getTelTrabajoPa(),rdp.getCelPa(),rdp.getFechaRegistroPa(),rdp.getApellidosPa(),rdp.getHoraRegistroPa(),rdp.getTipoDoc()).orElseThrow();

        return mapearDTO(respuestaBackend);
    }

    @Override
    public RequestDatosPacienteDTO busquedaDatosPacienteDNI(long dni) {
        return mapearDTODatosPaciente(backendEntityDatosPacienteRepository.busquedaDatosPacienteDNI(dni).orElseThrow());
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
    public backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO registroHistoriaClinicaOcupacional(RequestHistoriaClinicaOcupacionalDTO rdp) {
        RespuestaBackendNP respuestaBackendNP=
                respuestaBackendNPRepository.backendRegistroHistoriaClinica(rdp.getTipoOperacion(),rdp.getN_orden(), rdp.getCodPa(),rdp.getRazonEmpresa(), rdp.getRazonContrata(), rdp.getNomEx(),rdp.getAlturaPo(),rdp.getMineralPo(),rdp.getFechaAperturaPo(),
                        rdp.getPrecioPo(),rdp.getEstadoEx(), rdp.getNomExamen(), rdp.getCargoDe(),rdp.getAreaO(),rdp.getN_medico(),rdp.getN_hora(),rdp.getTipoPago(),rdp.getN_fisttest(), rdp.getN_psicosen(), rdp.getN_testaltura(),
                         rdp.getGrupoSan(),rdp.getGrupoFactorSan(),rdp.getCodClinica(), rdp.getVisualCompl(), rdp.getTrabCalientes(), rdp.getChk_covid1(), rdp.getChk_covid2(), rdp.getManipAlimentos(), rdp.getTextObserv1(),
                        rdp.getTextObserv2(), rdp.getCodSede(), rdp.getTipoPruebaCovid(), rdp.getTipoPrueba(), rdp.getNombreHotel(),rdp.getProtocolo(),rdp.getPrecioAdic(), rdp.getAutoriza(), rdp.getN_operacion(), rdp.getHerraManuales(),
                        rdp.getRxcDorsoLumbar(), rdp.getRxcKLumbar(), rdp.getRxcLumbosacra(), rdp.getRxcPlomos(), rdp.getMercurioo()).orElseThrow();


        return mapearDTOAZURE(respuestaBackendNP);
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

    @Override
    public List<ResponseMatrizSaludDTO> listadoMatrizSalud(RequesMatrizDTO requesMatrizDTO) {
        List<ResponseMatrizSalud> listadoMatrizSalud=matrizSaludRepository.listadoMatrizSalud(requesMatrizDTO.getRucContrata(),requesMatrizDTO.getFechaInicio(), requesMatrizDTO.getFechaFinal()).orElseThrow();
        return listadoMatrizSalud.stream().map(this::mapearDTOMSALUD).collect(Collectors.toList());
    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoEmpresasNP() {
        List<RespuestaBackendNP> listadoEmpresas=respuestaBackendNPRepository.listadoEmpresasNP().orElseThrow();
        return listadoEmpresas.stream().map(this::mapearDTOAzure).collect(Collectors.toList());
    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoContratasNP() {
        List<RespuestaBackendNP> listadoContratas=respuestaBackendNPRepository.listadoContratasNP().orElseThrow();
        return listadoContratas.stream().map(this::mapearDTOAzure).collect(Collectors.toList());
    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoMedicosNP() {
        List<RespuestaBackendNP> listadoMedicos=respuestaBackendNPRepository.listadoMedicos().orElseThrow();
        return listadoMedicos.stream().map(this::mapearDTOAzure).collect(Collectors.toList());
    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoTipoPruebasNP() {
        List<RespuestaBackendNP> listadoTipoPruebas=respuestaBackendNPRepository.listadoTipoPrueba().orElseThrow();
        return listadoTipoPruebas.stream().map(this::mapearDTOAzure).collect(Collectors.toList());
    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoCargo() {
        List<RespuestaBackendNP> listadoCargo=respuestaBackendNPRepository.listadoCargo().orElseThrow();
        return listadoCargo.stream().map(this::mapearDTOAzure).collect(Collectors.toList());    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoArea() {
        List<RespuestaBackendNP> listadoArea=respuestaBackendNPRepository.listadoArea().orElseThrow();
        return listadoArea.stream().map(this::mapearDTOAzure).collect(Collectors.toList());
    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoTipoExamen() {
        List<RespuestaBackendNP> listadoTipoExamen=respuestaBackendNPRepository.listadoTipoExamen().orElseThrow();
        return listadoTipoExamen.stream().map(this::mapearDTOAzure).collect(Collectors.toList());
    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoExplotacion() {
        List<RespuestaBackendNP> listadoExplotacion=respuestaBackendNPRepository.listadoExplotacion().orElseThrow();
        return listadoExplotacion.stream().map(this::mapearDTOAzure).collect(Collectors.toList());
    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoMineral() {
        List<RespuestaBackendNP> listadoMineral=respuestaBackendNPRepository.listadoMineral().orElseThrow();
        return listadoMineral.stream().map(this::mapearDTOAzure).collect(Collectors.toList());
    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoAltura() {
        List<RespuestaBackendNP> listadoAltura=respuestaBackendNPRepository.listadoAltura().orElseThrow();
        return listadoAltura.stream().map(this::mapearDTOAzure).collect(Collectors.toList());
    }

    @Override
    public backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO precioExamen(String nameExamen) {
        RespuestaBackendNP respuestaBackendNP=respuestaBackendNPRepository.precioExamen(nameExamen).orElseThrow();

        return mapearDTOAzure(respuestaBackendNP);
    }

    private backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO mapearDTOAzure(RespuestaBackendNP respuestaBackendNP){
        backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO
                respuestaBackendDTO= new backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO();
        respuestaBackendDTO.setId(respuestaBackendNP.getId());
        respuestaBackendDTO.setMensaje(respuestaBackendNP.getMensaje());

        return respuestaBackendDTO;
    }
    private RespuestaBackendDTO mapearDTO(RespuestaBackendNP respuestaBackendNP){
        RespuestaBackendDTO respuestaBackendDTO=new RespuestaBackendDTO();

        respuestaBackendDTO.setId(respuestaBackendNP.getId());
        respuestaBackendDTO.setMensaje(respuestaBackendNP.getMensaje());

        return respuestaBackendDTO;
    }

    private backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO mapearDTOAZURE(RespuestaBackendNP respuestaBackendNP){
        backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO respuestaBackendDTO= new backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO();
        respuestaBackendDTO.setId(respuestaBackendNP.getId());
        respuestaBackendDTO.setMensaje(respuestaBackendNP.getMensaje());

        return respuestaBackendDTO;

    }
    private RequestDatosPacienteDTO mapearDTODatosPaciente(BackendEntityDatosPaciente bk){
        RequestDatosPacienteDTO respuestaBackendDTO=new RequestDatosPacienteDTO();

        respuestaBackendDTO.setCodPa(bk.getCodPa());
        respuestaBackendDTO.setCaserioPA(bk.getCaserioPA());
        respuestaBackendDTO.setApellidosPa(bk.getApellidosPa());
        respuestaBackendDTO.setCelPa(bk.getCelPa());
        respuestaBackendDTO.setDepartamentoPa(bk.getDepartamentoPa());
        respuestaBackendDTO.setProvinciaPa(bk.getProvinciaPa());
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

    private ResponseMatrizDTO mapearDTOMADM(MatrizAdministrativa matrizAdministrativa){
        ResponseMatrizDTO responseMatrizDTO=new ResponseMatrizDTO();

        responseMatrizDTO.setId(String.valueOf(matrizAdministrativa.getId()));
        responseMatrizDTO.setEdad(matrizAdministrativa.getEdadTexto());
        responseMatrizDTO.setDni(matrizAdministrativa.getDni());
        responseMatrizDTO.setCargo(matrizAdministrativa.getCargo());
        responseMatrizDTO.setAptitudEmo(matrizAdministrativa.getAptitudEmo());
        responseMatrizDTO.setObservacion(matrizAdministrativa.getObservacion());
        responseMatrizDTO.setFechaExamen(matrizAdministrativa.getFechaExamen());
        responseMatrizDTO.setApellidosNombres(matrizAdministrativa.getApellidosNombres());
        responseMatrizDTO.setFechaSolicitud(matrizAdministrativa.getFechaSolicitud());
        responseMatrizDTO.setFechaNacimiento(matrizAdministrativa.getFechaNacimiento());
        responseMatrizDTO.setRazonContrata(matrizAdministrativa.getRazonContrata());
        if(responseMatrizDTO.getAptitudEmo().toUpperCase().contains("INTERCONSULTA"))
        {
        responseMatrizDTO.setObservacion(responseMatrizDTO.getAptitudEmo());
        }
        else
        {
        responseMatrizDTO.setObservacion(" ");
        }
        return responseMatrizDTO;

    }

    private ResponseMatrizSaludDTO mapearDTOMSALUD(ResponseMatrizSalud responseMatrizSalud){
        ResponseMatrizSaludDTO responseMatrizSaludDTO=new ResponseMatrizSaludDTO();

        responseMatrizSaludDTO.setFechaSolicitud(responseMatrizSalud.getFechaSolicitud());
        responseMatrizSaludDTO.setApellidosNombres(responseMatrizSalud.getApellidosNombres());
        responseMatrizSaludDTO.setDni(responseMatrizSalud.getDni());
        responseMatrizSaludDTO.setFechaNacimiento(responseMatrizSalud.getFechaNacimiento());
        responseMatrizSaludDTO.setEdad(responseMatrizSalud.getEdad());
        responseMatrizSaludDTO.setRazonContrata(responseMatrizSalud.getRazonContrata());
        responseMatrizSaludDTO.setCargo(responseMatrizSalud.getCargo());
        responseMatrizSaludDTO.setTipoTrabajo(responseMatrizSalud.getTipotrabajo());
        responseMatrizSaludDTO.setCarnetVacunacion(responseMatrizSalud.getCarnet());
        responseMatrizSaludDTO.setAptitudEmo(responseMatrizSalud.getAptitud());
        responseMatrizSaludDTO.setFechaEvalacuacion(responseMatrizSalud.getFechadeevaluacion());
        responseMatrizSaludDTO.setPeso(responseMatrizSalud.getPeso());
        responseMatrizSaludDTO.setTalla(responseMatrizSalud.getTalla());
        responseMatrizSaludDTO.setImc(responseMatrizSalud.getImc());
        responseMatrizSaludDTO.setDxPeso(responseMatrizSalud.getDxpeso());
        responseMatrizSaludDTO.setGlucosa(responseMatrizSalud.getGlucosa());
        responseMatrizSaludDTO.setColesterol(responseMatrizSalud.getColesterol());
        responseMatrizSaludDTO.setTrigliceridos(responseMatrizSalud.getTrigliceridos());
        responseMatrizSaludDTO.setDxOftalmo(responseMatrizSalud.getDxoftalmo());
        responseMatrizSaludDTO.setDxAudiometria(responseMatrizSalud.getDxaudio());
        responseMatrizSaludDTO.setRestricciones(responseMatrizSalud.getRestriccionesaptitud());
        responseMatrizSaludDTO.setClinica("HORIZONTE MEDIC");
        responseMatrizSaludDTO.setTelefono("969603777");
        responseMatrizSaludDTO.setFechaValidacion(" ");

        return responseMatrizSaludDTO;

    }

}
