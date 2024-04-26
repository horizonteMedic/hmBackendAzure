package backendhm.serviciosRest.models.azure.services.combobox;

import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.dtos.combobox.DepartamentoDTO;
import backendhm.serviciosRest.models.azure.entity.combobox.Departamento;
import backendhm.serviciosRest.models.azure.repository.combobox.IDepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService{

    @Autowired
    IDepartamentoRepository departamentoRepository;

    @Override
    public DepartamentoDTO crearDepartamento(DepartamentoDTO departamentoDTO) {
        Departamento departamento=mapearEntidad(departamentoDTO);

        Departamento nuevoDepartamento=departamentoRepository.save(departamento);
        DepartamentoDTO departamentoRespuesta=mapearDTO(nuevoDepartamento);

        return departamentoRespuesta;
    }

    @Override
    public List<DepartamentoDTO> listadoDepartamentos() {
        List<Departamento> listadoDepartamentos=departamentoRepository.findAll();
        return listadoDepartamentos.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public DepartamentoDTO obtenerDepartamentoPorID(String id) {
        Departamento departamento=departamentoRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("Departamento","id",Long.parseLong(id)));
        return mapearDTO(departamento);
    }

    @Override
    public DepartamentoDTO actualizarDepartamento(DepartamentoDTO departamentoDTO, String id) {
        Departamento departamento=departamentoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Departamento","id",Long.parseLong(id)));
        Departamento departamentoActual=
                departamentoRepository.save(actualizarDepartamentoEntidad(departamentoDTO,departamento));

        return mapearDTO(departamentoActual);
    }

    @Override
    public void eliminarDepartamento(String id) {
        Departamento departamento=departamentoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Departamento","id",Long.parseLong(id)));

        departamentoRepository.delete(departamento);
    }

    private DepartamentoDTO mapearDTO(Departamento departamento){
        DepartamentoDTO departamentoDTO=new DepartamentoDTO();

        departamentoDTO.setId(departamento.getId());
        departamentoDTO.setNombre(departamento.getNombre());

        return departamentoDTO;
    }

    private Departamento mapearEntidad(DepartamentoDTO departamentoDTO){
        Departamento departamento=new Departamento();

        departamento.setId(departamentoDTO.getId());
        departamento.setNombre(departamentoDTO.getNombre());

        return departamento;
    }

    private Departamento actualizarDepartamentoEntidad(DepartamentoDTO  departamentoDTO,Departamento departamento){
        departamento.setNombre(departamentoDTO.getNombre());

        return departamento;
    }

}
