package backendhm.serviciosRest.controller.azure.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ContratoProtocoloDTO;
import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ProtocoloDTO;
import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ServicioDTO;
import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ServicioProtocoloDTO;
import backendhm.serviciosRest.models.azure.services.ocupacional.IContratoProtocoloService;
import backendhm.serviciosRest.models.azure.services.ocupacional.IProtocoloService;
import backendhm.serviciosRest.models.azure.services.ocupacional.IServicioProtocoloService;
import backendhm.serviciosRest.models.azure.services.ocupacional.IServicioService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/ocupacional")
@CrossOrigin
public class OcupacionalController {


    @Autowired
    private IServicioService servicioService;

    @Autowired
    private IProtocoloService protocoloService;

    @Autowired
    private IServicioProtocoloService servicioProtocoloService;

    @Autowired
    private IContratoProtocoloService contratoProtocoloService;
    private static JSONObject json=null;


    @GetMapping("/servicios")
    public ResponseEntity<List<ServicioDTO>> obtenerListadoServicios(){

        return  ResponseEntity.ok(servicioService.listadoServicios());
    }

    @GetMapping("/servicios/{id}")
    public ResponseEntity<ServicioDTO> obtenerServicioPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(servicioService.obtenerServicioPorID(id));
    }


    @PostMapping("/servicios")
    public ResponseEntity<ServicioDTO> guardarServicios(@Valid @RequestBody ServicioDTO servicioDTO) {

        return new ResponseEntity<>(servicioService.crearServicio(servicioDTO), HttpStatus.CREATED);

    }

    @PutMapping("/servicios/{id}")
    public ResponseEntity<ServicioDTO> actualizarServicio(@Valid @RequestBody ServicioDTO servicioDTO,
                                                  @PathVariable(name = "id") long id) {

        ServicioDTO servicioActualizado = servicioService.actualizarServicio(servicioDTO, id);
        return new ResponseEntity<>(servicioActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/servicios/{id}")
    public ResponseEntity<String> eliminarServicio(@PathVariable(name = "id") long id) {
        servicioService.eliminarServicio(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }




    @GetMapping("/protocolos")
    public ResponseEntity<List<ProtocoloDTO>> obtenerListadoProtocolos(){

        return  ResponseEntity.ok(protocoloService.listadoProtocolos());
    }

    @GetMapping("/protocolos/{id}")
    public ResponseEntity<ProtocoloDTO> obtenerProtocoloPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(protocoloService.obtenerProtocoloPorID(id));
    }


    @PostMapping("/protocolos")
    public ResponseEntity<ProtocoloDTO> guardarProtocolos(@Valid @RequestBody ProtocoloDTO protocoloDTO) {

        return new ResponseEntity<>(protocoloService.crearProtocolo(protocoloDTO), HttpStatus.CREATED);

    }

    @PutMapping("/protocolos/{id}")
    public ResponseEntity<ProtocoloDTO> actualizarProtocolo(@Valid @RequestBody ProtocoloDTO protocoloDTO,
                                                          @PathVariable(name = "id") long id) {

        ProtocoloDTO protocoloActualizado = protocoloService.actualizarProtocolo(protocoloDTO, id);
        return new ResponseEntity<>(protocoloActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/protocolos/{id}")
    public ResponseEntity<String> eliminarProtocolo(@PathVariable(name = "id") long id) {
        protocoloService.eliminarProtocolo(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }







    @GetMapping("/servicioProtocolos")
    public ResponseEntity<List<ServicioProtocoloDTO>> obtenerListadoServiciosProtocolos(){

        return  ResponseEntity.ok(servicioProtocoloService.listadoServiciosProtocolos());
    }

    @GetMapping("/servicioProtocolos/{id}")
    public ResponseEntity<ServicioProtocoloDTO> obtenerServicioProtocoloPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(servicioProtocoloService.obtenerServicioProtocoloPorID(id));
    }


    @PostMapping("/servicioProtocolos")
    public ResponseEntity<ServicioProtocoloDTO> guardarServicioProtocolos(@Valid @RequestBody ServicioProtocoloDTO servicioProtocoloDTO) {

        return new ResponseEntity<>(servicioProtocoloService.crearServiciosProtocolo(servicioProtocoloDTO), HttpStatus.CREATED);

    }

    @PutMapping("/servicioProtocolos/{id}")
    public ResponseEntity<ServicioProtocoloDTO> actualizarServicioProtocolo(@Valid @RequestBody ServicioProtocoloDTO servicioProtocoloDTO,
                                                            @PathVariable(name = "id") long id) {

        ServicioProtocoloDTO servicioProtocoloDTOActualizado = servicioProtocoloService.actualizarServiciProtocolo(servicioProtocoloDTO, id);
        return new ResponseEntity<>(servicioProtocoloDTOActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/servicioProtocolos/{id}")
    public ResponseEntity<String> eliminarServicioProtocolo(@PathVariable(name = "id") long id) {
        servicioProtocoloService.eliminarServicioProtocolo(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }






    @GetMapping("/contrataProtocolos")
    public ResponseEntity<List<ContratoProtocoloDTO>> obtenerListadoContratosProtocolos(){

        return  ResponseEntity.ok(contratoProtocoloService.listadoContratosProtocolos());
    }

    @GetMapping("/contrataProtocolos/{id}")
    public ResponseEntity<ContratoProtocoloDTO> obtenerContratoProtocoloPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(contratoProtocoloService.obtenerContratoProtocoloPorID(id));
    }


    @PostMapping("/contrataProtocolos")
    public ResponseEntity<ContratoProtocoloDTO> guardarContratoProtocolos(@Valid @RequestBody ContratoProtocoloDTO contratoProtocoloDTO) {

        return new ResponseEntity<>(contratoProtocoloService.crearContratoProtocolo(contratoProtocoloDTO), HttpStatus.CREATED);

    }

    @PutMapping("/contrataProtocolos/{id}")
    public ResponseEntity<ContratoProtocoloDTO> actualizarContratoProtocolo(@Valid @RequestBody ContratoProtocoloDTO contratoProtocoloDTO,
                                                                            @PathVariable(name = "id") long id) {

        ContratoProtocoloDTO contratoProtocoloDTOActualizado = contratoProtocoloService.actualizarContratoProtocolo(contratoProtocoloDTO, id);
        return new ResponseEntity<>(contratoProtocoloDTOActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/contrataProtocolos/{id}")
    public ResponseEntity<String> eliminarContratoProtocolo(@PathVariable(name = "id") long id) {
        contratoProtocoloService.eliminarContratoProtocolo(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }
}
