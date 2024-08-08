package backendhm.serviciosRest.models.spTrujilloNP.controller;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.BackendHistoriaOcupacionalDTO;
import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ConsultaReservaDTO;
import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ResponseMatrizArchivosDTO;
import backendhm.serviciosRest.models.azure.dtos.asistencial.ConsultaReniecDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.EmpleadoTipoDocDTO;
import backendhm.serviciosRest.models.azure.services.IArchivoServidorService;
import backendhm.serviciosRest.models.azure.services.asistencial.IConsumoApisService;
import backendhm.serviciosRest.models.azure.services.ocupacional.ICitaOcupacionalService;
import backendhm.serviciosRest.models.spTrujilloNP.dto.*;
import backendhm.serviciosRest.models.spTrujilloNP.services.IRespuestaBackendService;
import jakarta.validation.Valid;
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

    @Autowired
    private ICitaOcupacionalService citaOcupacionalService;

    @Autowired
    private IArchivoServidorService archivoServidorService;
    @Autowired
    private IConsumoApisService consumoApisService;


    @GetMapping("/detalleArchivoEmpleado/{dni}/{tipoArchivo}")
    public ResponseEntity<EmpleadoTipoDocDTO> obtenerArchivoEmpleadoDetalle(@PathVariable long dni, @PathVariable String tipoArchivo) {

        return ResponseEntity.ok(archivoServidorService.detalleArchivoEmpleado(dni,tipoArchivo));
    }

    @PostMapping("/registrarArchivoEmpleado")
    public ResponseEntity<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> registrarActualzarArchivoEmpleado(@Valid @RequestBody EmpleadoTipoDocDTO empleadoTipoDocDTO) {

        return new ResponseEntity<>(archivoServidorService.registrarActualizarArchivoEmpleado(empleadoTipoDocDTO), HttpStatus.CREATED);

    }

    @GetMapping("/consumoApis/{dni}")
    public ResponseEntity<ConsultaReniecDTO> consumoApis(@PathVariable String dni){
        return  ResponseEntity.ok(consumoApisService.consumoApis(dni));
    }

    @GetMapping("/consultaReserva/{dni}")
    public ResponseEntity<ConsultaReservaDTO> listadoHistoriaOcupacional(@PathVariable Long dni){
        return  ResponseEntity.ok(citaOcupacionalService.consultarReservaDatosPaciente(dni));
    }

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
