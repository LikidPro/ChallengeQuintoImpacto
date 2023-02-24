package ChallengeBackend.QuintoImpacto.controladores;

import ChallengeBackend.QuintoImpacto.DTO.CursoDTO;
import ChallengeBackend.QuintoImpacto.modelos.Alumno;
import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.Horario;
import ChallengeBackend.QuintoImpacto.modelos.Profesor;
import ChallengeBackend.QuintoImpacto.servicios.CursoServicio;
import ChallengeBackend.QuintoImpacto.servicios.ProfesorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class CursoControlador {
    @Autowired
    private CursoServicio cursoServicio;

    @Autowired
    private ProfesorServicio profesorServicio;

    @GetMapping("/cursos")
    public Set<CursoDTO> getCursos(){
        return cursoServicio.getCursosDTO();
    }

    @PatchMapping("/curso/area")
    public ResponseEntity<Object> cambiarArea(@RequestParam String nombre, @RequestParam String area){
        Curso curso = cursoServicio.getCursoByNombre(nombre);
        curso.setNombre(area);
        cursoServicio.saveCurso(curso);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/curso/horario")
    public ResponseEntity<Object> cambiarHorario(@RequestParam String nombre, @RequestParam Horario horario){
        Curso curso = cursoServicio.getCursoByNombre(nombre);
        curso.setHorario(horario);
        cursoServicio.saveCurso(curso);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/curso/profesor")
    public ResponseEntity<Object> cambiarProfesor(@RequestParam String nombre, @RequestParam Long profesorId){
        Curso curso = cursoServicio.getCursoByNombre(nombre);
        Profesor profesor = profesorServicio.getProfesorById(profesorId);
        curso.setProfesor(profesor);
        cursoServicio.saveCurso(curso);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/curso/profesor/borrar")
    public ResponseEntity<Object> borrarProfesor(@RequestParam String nombre){
        Curso curso = cursoServicio.getCursoByNombre(nombre);
        curso.setProfesor(null);
        cursoServicio.saveCurso(curso);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/curso/nombre")
    public ResponseEntity<Object> cambiarNombre(@RequestParam String nombre, @RequestParam String nombre2){
        Curso curso = cursoServicio.getCursoByNombre(nombre);
        curso.setNombre(nombre2);
        cursoServicio.saveCurso(curso);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/curso/nuevo")
    public ResponseEntity<Object> crearCurso(@RequestParam String nombre, @RequestParam String area, @RequestParam Horario horario,  @RequestParam(required = false) Long profesorId){
        if(nombre.isEmpty()){
            return new ResponseEntity<>("Falta el nombre del curso", HttpStatus.FORBIDDEN);
        }
        if(area.isEmpty()){
            return new ResponseEntity<>("Falta el area del curso", HttpStatus.FORBIDDEN);

        }
        if(horario == null){
            return new ResponseEntity<>("Falta el horario del curso", HttpStatus.FORBIDDEN);
        }
        if(profesorId == null){
            Curso curso = new Curso(nombre,area,horario);
            cursoServicio.saveCurso(curso);
            return new ResponseEntity<>("Curso creado sin profesor",HttpStatus.ACCEPTED);
        }
        Profesor profesor = profesorServicio.getProfesorById(profesorId);
        Curso curso = new Curso(nombre,area,horario,profesor);
        cursoServicio.saveCurso(curso);
        return  new ResponseEntity<>("Curso creado",HttpStatus.ACCEPTED);

    }
}
