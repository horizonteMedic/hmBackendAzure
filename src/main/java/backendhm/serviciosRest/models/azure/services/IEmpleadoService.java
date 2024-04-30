package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.DatosEmpleadoDTO;
import backendhm.serviciosRest.models.azure.dtos.EmpleadoDTO;

import java.util.List;

public interface IEmpleadoService {
    EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO);

    List<EmpleadoDTO> listadoEmpleados();
    EmpleadoDTO obtenerEmpleadoPorID(long id);

    DatosEmpleadoDTO obtenerDatosEmpleadoPorNroDoc(long nroDoc);

    EmpleadoDTO actualizarEmpleado(EmpleadoDTO empleadoDTO, long id);
    void eliminarEmpleado(long id);
}
