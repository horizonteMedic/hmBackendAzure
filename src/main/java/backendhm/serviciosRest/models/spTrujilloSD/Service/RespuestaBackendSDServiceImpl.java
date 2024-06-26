package backendhm.serviciosRest.models.spTrujilloSD.Service;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.RequestHistoriaClinicaOcupacionalDTO;
import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDatosPacienteDTO;
import backendhm.serviciosRest.models.spTrujilloSD.dto.RespuestaBackendDTOTSD;
import backendhm.serviciosRest.models.spTrujilloSD.entity.BackendEntityDatosPacienteSD;
import backendhm.serviciosRest.models.spTrujilloSD.entity.RespuestaBackendTSD;
import backendhm.serviciosRest.models.spTrujilloSD.repository.IBackendEntityDatosPacienteSDRepository;
import backendhm.serviciosRest.models.spTrujilloSD.repository.IRespuestaBackendSDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public RespuestaBackendDTO registroHistoriaClinicaOcupacional(RequestHistoriaClinicaOcupacionalDTO rdp) {
        RespuestaBackendTSD respuestaBackendTSD=respuestaBackendSDRepository.backendRegistroHistoriaClinica(rdp.getTipoOperacion(),rdp.getN_orden(), rdp.getCodPa(),rdp.getRazonEmpresa(), rdp.getRazonContrata(), rdp.getNomEx(),rdp.getAlturaPo(),rdp.getMineralPo(),rdp.getFechaAperturaPo(),
                rdp.getPrecioPo(),rdp.getEstadoEx(), rdp.getNomExamen(), rdp.getCargoDe(),rdp.getAreaO(),rdp.getN_medico(),rdp.getN_hora(),rdp.getTipoPago(),rdp.getN_fisttest(), rdp.getN_psicosen(), rdp.getN_testaltura(),
                rdp.getGrupoSan(),rdp.getGrupoFactorSan(),rdp.getCodClinica(), rdp.getVisualCompl(), rdp.getTrabCalientes(), rdp.getChk_covid1(), rdp.getChk_covid2(), rdp.getManipAlimentos(), rdp.getTextObserv1(),
                rdp.getTextObserv2(), rdp.getCodSede(), rdp.getTipoPruebaCovid(), rdp.getTipoPrueba(), rdp.getNombreHotel(),rdp.getProtocolo(),rdp.getPrecioAdic(), rdp.getAutoriza(), rdp.getN_operacion(), rdp.getHerraManuales(),
                rdp.getRxcDorsoLumbar(), rdp.getRxcKLumbar(), rdp.getRxcLumbosacra(), rdp.getRxcPlomos(), rdp.getMercurioo()).orElseThrow();

        return mapearDTOAZURE(respuestaBackendTSD);
    }


    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoEmpresasNP() {
        List<RespuestaBackendTSD> listadoEmpresas=respuestaBackendSDRepository.listadoEmpresasNP().orElseThrow();
        return listadoEmpresas.stream().map(this::mapearDTOAzure).collect(Collectors.toList());
    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoContratasNP() {
        List<RespuestaBackendTSD> listadoContratas=respuestaBackendSDRepository.listadoContratasNP().orElseThrow();
        return listadoContratas.stream().map(this::mapearDTOAzure).collect(Collectors.toList());

    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoMedicosNP() {
        List<RespuestaBackendTSD> listadoMedicos=respuestaBackendSDRepository.listadoMedicos().orElseThrow();
        return listadoMedicos.stream().map(this::mapearDTOAzure).collect(Collectors.toList());

    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoTipoPruebasNP() {
        List<RespuestaBackendTSD> listadoTipoPrueba=respuestaBackendSDRepository.listadoTipoPrueba().orElseThrow();
        return listadoTipoPrueba.stream().map(this::mapearDTOAzure).collect(Collectors.toList());

    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoCargo() {
        List<RespuestaBackendTSD> listadoCargo=respuestaBackendSDRepository.listadoCargo().orElseThrow();
        return listadoCargo.stream().map(this::mapearDTOAzure).collect(Collectors.toList());

    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoArea() {
        List<RespuestaBackendTSD> listadoArea=respuestaBackendSDRepository.listadoArea().orElseThrow();
        return listadoArea.stream().map(this::mapearDTOAzure).collect(Collectors.toList());

    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoTipoExamen() {
        List<RespuestaBackendTSD> listadoTipoExamen=respuestaBackendSDRepository.listadoTipoExamen().orElseThrow();
        return listadoTipoExamen.stream().map(this::mapearDTOAzure).collect(Collectors.toList());

    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoExplotacion() {
        List<RespuestaBackendTSD> listadoExplotacion=respuestaBackendSDRepository.listadoExplotacion().orElseThrow();
        return listadoExplotacion.stream().map(this::mapearDTOAzure).collect(Collectors.toList());

    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoMineral() {
        List<RespuestaBackendTSD> listadoMineral=respuestaBackendSDRepository.listadoMineral().orElseThrow();
        return listadoMineral.stream().map(this::mapearDTOAzure).collect(Collectors.toList());

    }

    @Override
    public List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoAltura() {
        List<RespuestaBackendTSD> listadoAltura=respuestaBackendSDRepository.listadoAltura().orElseThrow();
        return listadoAltura.stream().map(this::mapearDTOAzure).collect(Collectors.toList());
    }

    @Override
    public backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO precioExamen(String nameExamen) {
        RespuestaBackendTSD respuestaBackendTSD=respuestaBackendSDRepository.precioExamen(nameExamen).orElseThrow();
        return mapearDTOAzure(respuestaBackendTSD);
    }
    private backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO mapearDTOAzure(RespuestaBackendTSD respuestaBackendTSD){
        backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO
                respuestaBackendDTO= new backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO();
        respuestaBackendDTO.setId(respuestaBackendTSD.getId());
        respuestaBackendDTO.setMensaje(respuestaBackendTSD.getMensaje());

        return respuestaBackendDTO;
    }
    RespuestaBackendDTOTSD mapearDto(RespuestaBackendTSD respuestaBackendTSD){
        RespuestaBackendDTOTSD respuestaBackendDTOTSD=new RespuestaBackendDTOTSD();

        respuestaBackendDTOTSD.setId(respuestaBackendTSD.getId());
        respuestaBackendDTOTSD.setMensaje(respuestaBackendTSD.getMensaje());
        return  respuestaBackendDTOTSD;

    }

    private backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO mapearDTOAZURE(RespuestaBackendTSD respuestaBackendTSD){
        backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO respuestaBackendDTOTSD= new backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO();
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
}
