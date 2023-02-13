package ChallengeBackend.QuintoImpacto.servicios.Implementacion;

import ChallengeBackend.QuintoImpacto.DTO.AlumnoDTO;
import ChallengeBackend.QuintoImpacto.modelos.Alumno;
import ChallengeBackend.QuintoImpacto.repositorios.AlumnoRepositorio;
import ChallengeBackend.QuintoImpacto.servicios.AlumnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlumnoServicioImplementacion implements AlumnoServicio {

    @Autowired
    private AlumnoRepositorio alumnoRepositorio;
    @Override
    public Set<AlumnoDTO> getAlumnosDTO() {
        return alumnoRepositorio.findAll().stream().map(alumno -> new AlumnoDTO(alumno)).collect(Collectors.toSet());
    }

    @Override
    public Alumno getAlumnoByEmail(String email) {
        return alumnoRepositorio.findByEmail(email);
    }

    @Override
    public void saveAlumno(Alumno alumno) {alumnoRepositorio.save(alumno);}


}
