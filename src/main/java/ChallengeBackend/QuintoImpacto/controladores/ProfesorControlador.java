package ChallengeBackend.QuintoImpacto.controladores;

import ChallengeBackend.QuintoImpacto.DTO.ProfesorDTO;
import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.Profesor;
import ChallengeBackend.QuintoImpacto.servicios.CursoServicio;
import ChallengeBackend.QuintoImpacto.servicios.ProfesorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api")
public class ProfesorControlador {
    @Autowired
    private ProfesorServicio profesorServicio;

    @Autowired
    private CursoServicio cursoServicio;

    @GetMapping("/profesores")
    public Set<ProfesorDTO> getCursos(){
        return profesorServicio.getProfesoresDTO();
    }

    @PatchMapping("/profesor/nombre")
    public ResponseEntity<Object> cambiarNombre(@RequestParam Long id, @RequestParam String nombre){
        Profesor profesor = profesorServicio.getProfesorById(id);
        if(profesor == null){
            return new ResponseEntity<>("El profesor no existe",HttpStatus.FORBIDDEN);
        }
        profesor.setNombre(nombre);
        profesorServicio.saveProfesor(profesor);
        return new ResponseEntity<>("Profesor cambiado con exito",HttpStatus.OK);
    }
    @PatchMapping("/profesor/apellido")
    public ResponseEntity<Object> cambiarApellido(@RequestParam Long id, @RequestParam String apellido){
        Profesor profesor = profesorServicio.getProfesorById(id);
        if(profesor == null){
            return new ResponseEntity<>("El profesor no existe",HttpStatus.FORBIDDEN);
        }
        profesor.setApellido(apellido);
        profesorServicio.saveProfesor(profesor);
        return new ResponseEntity<>( "Profesor cambiado con exito",HttpStatus.OK);
    }
    @PostMapping("/profesor/nuevo")
    public ResponseEntity<Object> crearProfesor(@RequestParam String nombre, @RequestParam String apellido){
        if(nombre.isEmpty()){
            return new ResponseEntity<>("Falta el nombre del profesor", HttpStatus.FORBIDDEN);
        }
        if(apellido.isEmpty()){
            return new ResponseEntity<>("Falta el area del profesor", HttpStatus.FORBIDDEN);

        }
        Profesor profesor = new Profesor(nombre,apellido);
        profesorServicio.saveProfesor(profesor);
        return  new ResponseEntity<>("Profesor creado",HttpStatus.ACCEPTED);
    }

    @PatchMapping("/profesor/agregar/curso")
    public ResponseEntity<Object> agregarCurso(@RequestParam long idProfesor, @RequestParam long idCurso){
        Profesor profesor = profesorServicio.getProfesorById(idProfesor);
        Curso curso = cursoServicio.getCursoById(idCurso);
        if(profesor == null){
            return new ResponseEntity<>("No existe el profesor",HttpStatus.FORBIDDEN);
        }
        if(curso == null){
            return new ResponseEntity<>("No existe el curso",HttpStatus.FORBIDDEN);
        }
        if(profesor.getCursos().size()==0){
            profesor.addCurso(curso);
            return new ResponseEntity<>("Se agrego el curso al profesor",HttpStatus.ACCEPTED);
        }
        AtomicReference<Boolean> horarios = new AtomicReference<>(false);
        profesor.getCursos().forEach(curso1 -> {
            if(!curso1.getHorario().equals(curso.getHorario())){
                profesor.addCurso(curso);
                horarios.set(true);
            }
        });
        if(!horarios.get()){
            return new ResponseEntity<>("Ya tienes un curso en este horario",HttpStatus.FORBIDDEN);
        }else {
            return new ResponseEntity<>("Se agrego el curso al profesor",HttpStatus.ACCEPTED);
        }

    }
}
