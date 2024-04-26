package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.OpcionesInterfazDTO;

import java.util.List;

public interface IOpcionInterfazService {
    OpcionesInterfazDTO crearOpcionInterfaz(OpcionesInterfazDTO opcionesInterfazDTO);
    List<OpcionesInterfazDTO> listadoOpcionesInterfaz();
    OpcionesInterfazDTO obtenerOpcionInterdazPorID(long id);
    OpcionesInterfazDTO actualizarOpcionInterfaz(OpcionesInterfazDTO opcionesInterfazDTO, long id);
    void eliminarOpcionInterfaz(long id);
}
