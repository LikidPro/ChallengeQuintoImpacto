package ChallengeBackend.QuintoImpacto.servicios;

import ChallengeBackend.QuintoImpacto.DTO.ProfesorDTO;
import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.Profesor;

import java.util.Set;

public interface ProfesorServicio {

    public Set<ProfesorDTO> getProfesoresDTO();

    public Profesor getProfesorByNombre(String nombre);

    public void saveProfesor(Profesor profesor);
}
