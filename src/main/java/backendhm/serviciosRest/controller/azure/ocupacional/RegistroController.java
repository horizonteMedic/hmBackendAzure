package backendhm.serviciosRest.controller.azure.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.RequestHistoriaClinicaOcupacionalDTO;
import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.ArchivoServidorDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDatosPacienteDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestNOrdenOcupacionalDTO;
import backendhm.serviciosRest.models.spTrujilloNP.services.IRespuestaBackendService;
import backendhm.serviciosRest.models.spTrujilloSD.Service.IRespuestaBackendServiceSD;
import backendhm.serviciosRest.models.spTrujilloSD.dto.RespuestaBackendDTOTSD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v01/ct/registroPacientes")
@CrossOrigin
public class RegistroController {

    //
    @Autowired
    private IRespuestaBackendService respuestaBackendService;

    @Autowired
    private IRespuestaBackendServiceSD respuestaBackendServiceSD;


    @PostMapping("/datosPaciente/{sedeNomenc}")
    public ResponseEntity<RespuestaBackendDTO> datosP(@PathVariable(name = "sedeNomenc") String sedeNomenc, @RequestBody RequestDatosPacienteDTO rdp) {
            RespuestaBackendDTO respuestaBackendDTO=new RespuestaBackendDTO();

        if (sedeNomenc.contains("T-NP")||sedeNomenc.contains("HNCY")) {
            backendhm.serviciosRest.models.spTrujilloNP.dto.RespuestaBackendDTO respuestaBackendDTONP=respuestaBackendService.registrarDatosPaciente(rdp);
            respuestaBackendDTO.setId(respuestaBackendDTONP.getId());
            respuestaBackendDTO.setMensaje(respuestaBackendDTONP.getMensaje());
            return new ResponseEntity<>(respuestaBackendDTO, HttpStatus.OK);
        }
        else {
            RespuestaBackendDTOTSD respuestaBackendDTOTSD=respuestaBackendServiceSD.registrarDatosPaciente(rdp);
            respuestaBackendDTO.setId(respuestaBackendDTOTSD.getId());
            respuestaBackendDTO.setMensaje(respuestaBackendDTOTSD.getMensaje());

            return new ResponseEntity<>(respuestaBackendDTO, HttpStatus.OK);
        }
    }

    @GetMapping("/datosPaciente/{sedeNomenc}/{dni}")
    public ResponseEntity<RequestDatosPacienteDTO> busquedaDatosPacienteDNI(@PathVariable String sedeNomenc, @PathVariable Long dni) {

        if (sedeNomenc.contains("T-NP")||sedeNomenc.contains("HNCY")) {

            return ResponseEntity.ok(respuestaBackendService.busquedaDatosPacienteDNI(dni));
        }
        else {


            return ResponseEntity.ok(respuestaBackendServiceSD.busquedaDatosPacienteDNI(dni));

        }

    }

    @PostMapping("/historiaClinicaOcupacional")
    public ResponseEntity<RespuestaBackendDTO> registrarHistoriaClinicaOcupacional(@RequestBody RequestHistoriaClinicaOcupacionalDTO rdp) {
        return new ResponseEntity<>(respuestaBackendService.registroHistoriaClinicaOcupacional(rdp),HttpStatus.OK);

    }


}
