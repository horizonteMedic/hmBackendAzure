package backendhm.serviciosRest.models.spTrujilloNP.controller;

import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDatosPacienteDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestNOrdenOcupacionalDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RespuestaBackendDTO;
import backendhm.serviciosRest.models.spTrujilloNP.services.IRespuestaBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v01/st/registros")
@CrossOrigin
public class RespuestaBackendController {

    @Autowired
    private IRespuestaBackendService respuestaBackendService;

    @PostMapping("/datosPacienteNP")
    public ResponseEntity<RespuestaBackendDTO> datosP(@RequestBody RequestDatosPacienteDTO rdp) {

            return new ResponseEntity<>(respuestaBackendService.registrarDatosPaciente(rdp),HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/historiaClinica")
    public ResponseEntity<RespuestaBackendDTO> registrarHistoriaC(@RequestBody RequestNOrdenOcupacionalDTO rdp) {
        System.out.println("en el controller:"+rdp);
        return new ResponseEntity<>(respuestaBackendService.registroHistoriaClinica(rdp),HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
