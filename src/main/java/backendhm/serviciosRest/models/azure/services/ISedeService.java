package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.SedeDTO;

import java.util.List;

public interface ISedeService {
    SedeDTO crearSede(SedeDTO sedeDTO);
    List<SedeDTO> listadoSedes();

    List<SedeDTO> listadoSedesHabilitados();

    SedeDTO obtenerSedePorID(long id);
    SedeDTO actualizarSede(SedeDTO sedeDTO, long id);
    void eliminarSede(long id);
}
