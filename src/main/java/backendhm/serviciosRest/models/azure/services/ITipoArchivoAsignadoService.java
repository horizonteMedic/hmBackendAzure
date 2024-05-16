package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.TipoArchivoAsignadoDTO;

import java.util.List;

public interface ITipoArchivoAsignadoService {
    TipoArchivoAsignadoDTO crearTipoArchivoAsignado(TipoArchivoAsignadoDTO tipoArchivoAsignadoDTO);
    List<TipoArchivoAsignadoDTO> listadoTipoArchivoAsignado();
    List<TipoArchivoAsignadoDTO> listadoTipoArchivoAsignadoPorIDROL( long idRol);
    TipoArchivoAsignadoDTO obtenerTipoArchivoAsignadoPorIDTipoAsignado(long id);
    TipoArchivoAsignadoDTO actualizarTipoArchivoAsignado(TipoArchivoAsignadoDTO tipoArchivoAsignadoDTO, long id);
    void eliminarTipoArchivoAsignado(long id);
}
