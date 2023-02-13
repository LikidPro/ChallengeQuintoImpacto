package ChallengeBackend.QuintoImpacto.controladores;

import ChallengeBackend.QuintoImpacto.DTO.CursoDTO;
import ChallengeBackend.QuintoImpacto.DTO.ProfesorDTO;
import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.Profesor;
import ChallengeBackend.QuintoImpacto.servicios.ProfesorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class ProfesorControlador {
    @Autowired
    private ProfesorServicio profesorServicio;

    @GetMapping("/profesores")
    public Set<ProfesorDTO> getCursos(){
        return profesorServicio.getProfesoresDTO();
    }

    @PatchMapping("/profesor/nombre")
    public ResponseEntity<Object> cambiarNombre(@RequestParam String nombre, @RequestParam String nombre2){
        Profesor profesor = profesorServicio.getProfesorByNombre(nombre);
        profesor.setNombre(nombre2);
        profesorServicio.saveProfesor(profesor);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/profesor/apellido")
    public ResponseEntity<Object> cambiarApellido(@RequestParam String nombre, @RequestParam String apellido){
        Profesor profesor = profesorServicio.getProfesorByNombre(nombre);
        profesor.setApellido(apellido);
        profesorServicio.saveProfesor(profesor);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
