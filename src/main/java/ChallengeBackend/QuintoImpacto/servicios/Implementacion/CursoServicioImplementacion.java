package ChallengeBackend.QuintoImpacto.servicios.Implementacion;

import ChallengeBackend.QuintoImpacto.DTO.CursoDTO;
import ChallengeBackend.QuintoImpacto.modelos.Alumno;
import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.CursoAlumno;
import ChallengeBackend.QuintoImpacto.repositorios.CursoRepositorio;
import ChallengeBackend.QuintoImpacto.servicios.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CursoServicioImplementacion implements CursoServicio {
    @Autowired
    private CursoRepositorio cursoRepositorio;
    @Override
    public Set<CursoDTO> getCursosDTO() {
        return cursoRepositorio.findAll().stream().map(curso -> new CursoDTO(curso)).collect(Collectors.toSet());
    }

    @Override
    public Curso getCursoByNombre(String nombre) {
        return cursoRepositorio.findByNombre(nombre);
    }

    @Override
    public void saveCurso(Curso curso) {
        cursoRepositorio.save(curso);
    }

    @Override
    public void borrarCursoAlumno(Curso curso,CursoAlumno cursoAlumno) {
       curso.getCursosAlumnos().remove(cursoAlumno);
       cursoRepositorio.save(curso);
    }


}
