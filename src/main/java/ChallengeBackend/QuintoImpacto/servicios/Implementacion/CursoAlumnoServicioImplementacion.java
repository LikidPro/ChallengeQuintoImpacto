package ChallengeBackend.QuintoImpacto.servicios.Implementacion;

import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.CursoAlumno;
import ChallengeBackend.QuintoImpacto.repositorios.CursoAlumnoRepositorio;
import ChallengeBackend.QuintoImpacto.repositorios.CursoRepositorio;
import ChallengeBackend.QuintoImpacto.servicios.CursoAlumnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CursoAlumnoServicioImplementacion implements CursoAlumnoServicio {
    @Autowired
    private CursoAlumnoRepositorio cursoAlumnoRepositorio;

    @Autowired
    private CursoRepositorio cursoRepositorio;
    @Override
    public void saveCursoAlumno(CursoAlumno cursoAlumno) {
        cursoAlumnoRepositorio.save(cursoAlumno);

    }

    @Override
    public CursoAlumno findByNombreCurso(String curso) {
        return cursoAlumnoRepositorio.findByCursoNombre(curso);
    }

    @Override
    public void borrarCurso(CursoAlumno cursoAlumno) {
        cursoAlumnoRepositorio.delete(cursoAlumno);
    }

    @Override
    public void saveCurso(CursoAlumno cursoAlumno) {
        cursoAlumnoRepositorio.save(cursoAlumno);
    }
}
