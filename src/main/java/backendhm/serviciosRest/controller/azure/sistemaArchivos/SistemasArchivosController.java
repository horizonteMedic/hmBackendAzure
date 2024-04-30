package backendhm.serviciosRest.controller.azure.sistemaArchivos;

import backendhm.serviciosRest.models.azure.dtos.SedePorUserDTO;
import backendhm.serviciosRest.models.azure.services.sistemaArchivos.IHistorialPacienteSPNPService;
import backendhm.serviciosRest.models.spTrujilloNP.dto.DetalleHistorialPacienteMultiservidorDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.HistorialPacienteMultiservidorDto;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDetalleHistorialPacienteMultiservidorDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestHistorialPacienteMultiservidor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/sistemaArchivos")
@CrossOrigin
public class SistemasArchivosController {

    @Autowired
    private IHistorialPacienteSPNPService historialPacienteSPNPService;

    @PostMapping("/listadoHistorialPacientesConFiltros")
    public ResponseEntity<List<HistorialPacienteMultiservidorDto>> multiservidores(@Valid @RequestBody RequestHistorialPacienteMultiservidor requestHistorialPacienteSPTNP) {

        return new ResponseEntity<>(historialPacienteSPNPService.listadoHistorialPaciente(requestHistorialPacienteSPTNP), HttpStatus.OK);

    }


    @PostMapping("/detalleListadoHistorialPacientesConFiltros")
    public ResponseEntity<List<DetalleHistorialPacienteMultiservidorDTO>> detalleMultiservidores(@Valid @RequestBody RequestDetalleHistorialPacienteMultiservidorDTO requestDetalleHistorialPacienteMultiservidorDTO) {

        return new ResponseEntity<>(historialPacienteSPNPService.detalleHistoruialUsuario(requestDetalleHistorialPacienteMultiservidorDTO), HttpStatus.OK);

    }


    @GetMapping("/sedePorUsuario/{username}")
    public ResponseEntity<List<SedePorUserDTO>> obtenerSedeOorUsuario(@PathVariable(name = "username") String username) {

        return ResponseEntity.ok(historialPacienteSPNPService.listadoSedesPorUsuario(username));
    }
}
