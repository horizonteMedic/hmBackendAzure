package backendhm.serviciosRest.controller.azure.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ProtocoloDTO;
import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ServicioDTO;
import backendhm.serviciosRest.models.azure.services.ocupacional.IProtocoloService;
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
}
