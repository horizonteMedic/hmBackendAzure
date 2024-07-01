package backendhm.serviciosRest.models.spTrujilloNP.controller;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.BackendHistoriaOcupacionalDTO;
import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ResponseMatrizArchivosDTO;
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

    @PostMapping("/matrizSalud")
    public ResponseEntity<List<ResponseMatrizSaludDTO>> obtenerListadoMatrizSalud(@RequestBody RequesMatrizDTO requesMatrizDTO){
        return  ResponseEntity.ok(respuestaBackendService.listadoMatrizSalud(requesMatrizDTO));
    }

    @GetMapping("/listadoHistorialOcupacional/{sedeNomenc}")
    public ResponseEntity<List<BackendHistoriaOcupacionalDTO>> listadoHistoriaOcupacional(@PathVariable String sedeNomenc){
        return  ResponseEntity.ok(respuestaBackendService.listadoHistoriaOcupacionalSede(sedeNomenc));
    }


    @GetMapping("/matrizArchivos")
    public ResponseEntity<List<ResponseMatrizArchivosDTO>> listadoArchivos(){
       // System.out.println("Entro a matriz de archivos");
        return  ResponseEntity.ok(respuestaBackendService.listadoMatrizArchivos());
    }

}
