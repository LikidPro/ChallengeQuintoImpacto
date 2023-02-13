package ChallengeBackend.QuintoImpacto.servicios;

import ChallengeBackend.QuintoImpacto.modelos.Alumno;
import ChallengeBackend.QuintoImpacto.modelos.CursoAlumno;

public interface CursoAlumnoServicio {

    public void saveCursoAlumno(CursoAlumno cursoAlumno);

    public  CursoAlumno findByNombreCurso(String curso);

    public void borrarCurso(CursoAlumno cursoAlumno);

    public void saveCurso(CursoAlumno cursoAlumno);

}
