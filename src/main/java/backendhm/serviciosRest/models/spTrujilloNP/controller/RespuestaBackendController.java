package backendhm.serviciosRest.models.spTrujilloNP.controller;

import backendhm.serviciosRest.models.spTrujilloNP.dto.*;
import backendhm.serviciosRest.models.spTrujilloNP.services.IRespuestaBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/st/registros")
@CrossOrigin
public class RespuestaBackendController {

    @Autowired
    private IRespuestaBackendService respuestaBackendService;

    @PostMapping("/datosPacienteNP")
    public ResponseEntity<RespuestaBackendDTO> datosP(@RequestBody RequestDatosPacienteDTO rdp) {

            return new ResponseEntity<>(respuestaBackendService.registrarDatosPaciente(rdp),HttpStatus.OK);

    }

    @PostMapping("/historiaClinica")
    public ResponseEntity<RespuestaBackendDTO> registrarHistoriaC(@RequestBody RequestNOrdenOcupacionalDTO rdp) {
        return new ResponseEntity<>(respuestaBackendService.registroHistoriaClinica(rdp),HttpStatus.OK);

    }
    @PostMapping("/matrizAdministrativa")
    public ResponseEntity<List<ResponseMatrizDTO>> obtenerListadoMatrizAdministrativo(@RequestBody RequesMatrizDTO requesMatrizDTO){
        return  ResponseEntity.ok(respuestaBackendService.listadoMatrizAdministrativa(requesMatrizDTO));
    }
}
