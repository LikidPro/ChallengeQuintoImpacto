package ChallengeBackend.QuintoImpacto.servicios;

import ChallengeBackend.QuintoImpacto.DTO.AlumnoDTO;
import ChallengeBackend.QuintoImpacto.DTO.ProfesorDTO;
import ChallengeBackend.QuintoImpacto.modelos.Alumno;

import java.util.Set;

public interface AlumnoServicio {

    public Set<AlumnoDTO> getAlumnosDTO();

    public Alumno getAlumnoByEmail(String email);

    public Alumno getAlumnoById(Long id);

    public void saveAlumno(Alumno alumno);

}
