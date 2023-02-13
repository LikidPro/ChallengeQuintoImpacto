package ChallengeBackend.QuintoImpacto.servicios;

import ChallengeBackend.QuintoImpacto.DTO.CursoDTO;
import ChallengeBackend.QuintoImpacto.modelos.Alumno;
import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.CursoAlumno;

import java.util.List;
import java.util.Set;

public interface CursoServicio {

    public Set<CursoDTO> getCursosDTO();

    public Curso getCursoByNombre(String nombre);

    public void saveCurso(Curso curso);

    public void borrarCursoAlumno(Curso curso,CursoAlumno cursoAlumno);


}
