package backendhm.serviciosRest.controller.azure.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.*;
import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;
import backendhm.serviciosRest.models.azure.services.ocupacional.*;
import backendhm.serviciosRest.models.spTrujilloNP.services.IRespuestaBackendService;
import backendhm.serviciosRest.models.spTrujilloSD.Service.IRespuestaBackendServiceSD;
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
    private IRespuestaBackendServiceSD respuestaBackendServiceSD;

    @Autowired
    private IRespuestaBackendService respuestaBackendServiceNP;

    @Autowired
    private IServicioService servicioService;

    @Autowired
    private IProtocoloService protocoloService;

    @Autowired
    private IServicioProtocoloService servicioProtocoloService;

    @Autowired
    private IContratoProtocoloService contratoProtocoloService;

    @Autowired
    private ICitaOcupacionalService citaOcupacionalService;
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

    @GetMapping("/protocolos/busquedaPorName/{name}")
    public ResponseEntity<ProtocoloDTO> obtenerProtocoloPorID(@PathVariable(name = "name") String name) {

        return ResponseEntity.ok(protocoloService.busquedaProtocoloPorName(name));
    }






    @GetMapping("/servicioProtocolos")
    public ResponseEntity<List<ServicioProtocoloDTO>> obtenerListadoServiciosProtocolos(){

        return  ResponseEntity.ok(servicioProtocoloService.listadoServiciosProtocolos());
    }

    @GetMapping("/servicioProtocolos/{id}")
    public ResponseEntity<ServicioProtocoloDTO> obtenerServicioProtocoloPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(servicioProtocoloService.obtenerServicioProtocoloPorID(id));
    }

    @GetMapping("/servicioProtocolos/busquedaPorIdProtocolo/{idProtocolo}")
    public ResponseEntity<List<ServicioProtocoloDTO>> obtenerServicioProtocoloPorIDProtocolo(@PathVariable(name = "idProtocolo") long idProtocolo) {

        return ResponseEntity.ok(servicioProtocoloService.busquedaServicioProtocoloPorIDProtocolo(idProtocolo));
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

    @GetMapping("/contrataProtocolos/busquedaIdProtocolo/{idProtocolo}")
    public ResponseEntity<List<ContratoProtocoloDTO>>obtenerContratoProtocoloPorIDProtocolo(@PathVariable(name = "idProtocolo") long idProtocolo) {

        return ResponseEntity.ok(contratoProtocoloService.busquedaContrataProtocoloPorIDProtocolo(idProtocolo));
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





    @GetMapping("/citaOcupacional")
    public ResponseEntity<List<CitaOcupacionalDTO>> obtenerListadoCitadoOcupacional(){

        return  ResponseEntity.ok(citaOcupacionalService.listadoCitaOcupacional());
    }

    @GetMapping("/citaOcupacional/{id}")
    public ResponseEntity<CitaOcupacionalDTO> obtenerCitaOcupacionalPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(citaOcupacionalService.obtenerCitaOcupacionalPorID(id));
    }


    @PostMapping("/citaOcupacional")
    public ResponseEntity<CitaOcupacionalDTO> guardarCitaOcupacional(@Valid @RequestBody CitaOcupacionalDTO citaOcupacionalDTO) {

        return new ResponseEntity<>(citaOcupacionalService.crearCitaOcupacional(citaOcupacionalDTO), HttpStatus.CREATED);

    }

    @PutMapping("/citaOcupacional/{id}")
    public ResponseEntity<CitaOcupacionalDTO> actualizarCitaOcupacional(@Valid @RequestBody CitaOcupacionalDTO citaOcupacionalDTO,
                                                          @PathVariable(name = "id") long id) {

        CitaOcupacionalDTO citaOcupacionalDTOActualizada = citaOcupacionalService.actualizarCitaOcupacional(citaOcupacionalDTO, id);
        return new ResponseEntity<>(citaOcupacionalDTOActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/citaOcupacional/{id}")
    public ResponseEntity<String> eliminarCitaOcupacional(@PathVariable(name = "id") long id) {
        citaOcupacionalService.eliminarCitaOcupacional(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }


    @GetMapping("/listadoEmpresasMutisucursal/{nomenSede}")
    public ResponseEntity<List<RespuestaBackendDTO>>listadoEmpresas(@PathVariable(name = "nomenSede") String nomenSede) {

        if(nomenSede.contains("T-SD") ) {
            return ResponseEntity.ok(respuestaBackendServiceSD.listadoEmpresasNP());

        }
        else
            return ResponseEntity.ok(respuestaBackendServiceNP.listadoEmpresasNP());

    }

    @GetMapping("/listadoContratasMutisucursal/{nomenSede}")
    public ResponseEntity<List<RespuestaBackendDTO>>listadoContratas(@PathVariable(name = "nomenSede") String nomenSede) {

        if(nomenSede.contains("T-SD") ) {
            return ResponseEntity.ok(respuestaBackendServiceSD.listadoContratasNP());

        }
        else
            return ResponseEntity.ok(respuestaBackendServiceNP.listadoContratasNP());
    }

    @GetMapping("/listadoNombreMedicosMutisucursal/{nomenSede}")
    public ResponseEntity<List<RespuestaBackendDTO>>listadoNombreMedicos(@PathVariable(name = "nomenSede") String nomenSede) {

        if(nomenSede.contains("T-SD") ) {
            return ResponseEntity.ok(respuestaBackendServiceSD.listadoMedicosNP());

        }
        else
            return ResponseEntity.ok(respuestaBackendServiceNP.listadoMedicosNP());
    }

    @GetMapping("/listadoTipoPruebaMutisucursal/{nomenSede}")
    public ResponseEntity<List<RespuestaBackendDTO>>listadoTipo(@PathVariable(name = "nomenSede") String nomenSede) {

        if(nomenSede.contains("T-SD") ) {
            return ResponseEntity.ok(respuestaBackendServiceSD.listadoTipoPruebasNP());

        }
        else
            return ResponseEntity.ok(respuestaBackendServiceNP.listadoTipoPruebasNP());
    }

    @GetMapping("/listadoCargoMutisucursal/{nomenSede}")
    public ResponseEntity<List<RespuestaBackendDTO>>listadoCargo(@PathVariable(name = "nomenSede") String nomenSede) {

        if(nomenSede.contains("T-SD") ) {
            return ResponseEntity.ok(respuestaBackendServiceSD.listadoCargo());

        }
        else
            return ResponseEntity.ok(respuestaBackendServiceNP.listadoCargo());
    }

    @GetMapping("/listadoAreaMutisucursal/{nomenSede}")
    public ResponseEntity<List<RespuestaBackendDTO>>listadoArea(@PathVariable(name = "nomenSede") String nomenSede) {

        if(nomenSede.contains("T-SD") ) {
            return ResponseEntity.ok(respuestaBackendServiceSD.listadoArea());

        }
        else
            return ResponseEntity.ok(respuestaBackendServiceNP.listadoArea());
    }

    @GetMapping("/listadoTipoExamenMutisucursal/{nomenSede}")
    public ResponseEntity<List<RespuestaBackendDTO>>listadoTipoExamen(@PathVariable(name = "nomenSede") String nomenSede) {

        if(nomenSede.contains("T-SD") ) {
            return ResponseEntity.ok(respuestaBackendServiceSD.listadoTipoExamen());

        }
        else
            return ResponseEntity.ok(respuestaBackendServiceNP.listadoTipoExamen());
    }

    @GetMapping("/listadoExplotacionMutisucursal/{nomenSede}")
    public ResponseEntity<List<RespuestaBackendDTO>>listadoExplotacion(@PathVariable(name = "nomenSede") String nomenSede) {

        if(nomenSede.contains("T-SD") ) {
            return ResponseEntity.ok(respuestaBackendServiceSD.listadoExplotacion());

        }
        else
            return ResponseEntity.ok(respuestaBackendServiceNP.listadoExplotacion());
    }

    @GetMapping("/listadoMineralMutisucursal/{nomenSede}")
    public ResponseEntity<List<RespuestaBackendDTO>>listadoMineral(@PathVariable(name = "nomenSede") String nomenSede) {

        if(nomenSede.contains("T-SD") ) {
            return ResponseEntity.ok(respuestaBackendServiceSD.listadoMineral());

        }
        else
            return ResponseEntity.ok(respuestaBackendServiceNP.listadoMineral());
    }


    @GetMapping("/listadoAlturaMutisucursal/{nomenSede}")
    public ResponseEntity<List<RespuestaBackendDTO>>listadoAltura(@PathVariable(name = "nomenSede") String nomenSede) {

        if(nomenSede.contains("T-SD") ) {
            return ResponseEntity.ok(respuestaBackendServiceSD.listadoAltura());

        }
        else
            return ResponseEntity.ok(respuestaBackendServiceNP.listadoAltura());
    }


    @GetMapping("/PrecioExamenMutisucursal/{nomenSede}/{nombreExamen}")
    public ResponseEntity<RespuestaBackendDTO>precioExamen(@PathVariable String nomenSede, @PathVariable String nombreExamen) {

        if(nomenSede.contains("T-SD") ) {
            return ResponseEntity.ok(respuestaBackendServiceSD.precioExamen(nombreExamen));

        }
        else
            return ResponseEntity.ok(respuestaBackendServiceNP.precioExamen(nombreExamen));
    }
}
