package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.RolAsignadoDTO;

import java.util.List;

public interface IRolAsignadoService {
    RolAsignadoDTO crearRolAsignado(RolAsignadoDTO rolAsignadoDTO);
    List<RolAsignadoDTO> listadoRol();
    List<RolAsignadoDTO> listadoRolesPorIDROL( long idRol);
    RolAsignadoDTO obtenerRolPorID(long id);
    RolAsignadoDTO actualizarRol(RolAsignadoDTO rolAsignadoDTO, long id);
    void eliminarRolAsignado(long id);
}
