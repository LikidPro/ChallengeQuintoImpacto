package ChallengeBackend.QuintoImpacto.servicios.Implementacion;

import ChallengeBackend.QuintoImpacto.DTO.ProfesorDTO;
import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.Profesor;
import ChallengeBackend.QuintoImpacto.repositorios.ProfesorRepositorio;
import ChallengeBackend.QuintoImpacto.servicios.ProfesorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfesorServicioImplementacion implements ProfesorServicio {

    @Autowired
    private ProfesorRepositorio profesorRepositorio;
    @Override
    public Set<ProfesorDTO> getProfesoresDTO() {
        return profesorRepositorio.findAll().stream().map(profesor -> new ProfesorDTO(profesor)).collect(Collectors.toSet());
    }

    @Override
    public Profesor getProfesorByNombre(String nombre) {
        return profesorRepositorio.findByNombre(nombre);
    }

    @Override
    public void saveProfesor(Profesor profesor) {
        profesorRepositorio.save(profesor);
    }
}
