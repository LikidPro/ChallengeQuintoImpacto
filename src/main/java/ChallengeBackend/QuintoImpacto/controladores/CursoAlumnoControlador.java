package ChallengeBackend.QuintoImpacto.controladores;

import ChallengeBackend.QuintoImpacto.modelos.Alumno;
import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.CursoAlumno;
import ChallengeBackend.QuintoImpacto.servicios.AlumnoServicio;
import ChallengeBackend.QuintoImpacto.servicios.CursoAlumnoServicio;
import ChallengeBackend.QuintoImpacto.servicios.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CursoAlumnoControlador {
    @Autowired
    private AlumnoServicio alumnoServicio;
    @Autowired
    private CursoServicio cursoServicio;
    @Autowired
    private CursoAlumnoServicio cursoAlumnoServicio;

    @PostMapping("/curso/inscripcion")
    public ResponseEntity<Object> inscripcionAlumnoCurso(
            @RequestParam String curso,
            Authentication authentication
            ){
        LocalDate localDate = LocalDate.now();
        Alumno alumno = alumnoServicio.getAlumnoByEmail(authentication.getName());
        if(alumno != null){
        if (alumno.getCursos().stream().filter(cursoAlumno -> cursoAlumno.getCurso().getNombre().equals(curso)).collect(Collectors.toSet()).size()==1){

            return new ResponseEntity<>("Ya estas inscripto a este curso",HttpStatus.FORBIDDEN);
        }

            Curso curso1= cursoServicio.getCursoByNombre(curso);
        CursoAlumno cursoAlumno = new CursoAlumno(alumno,curso1,localDate);
        cursoAlumnoServicio.saveCursoAlumno(cursoAlumno);
        }else {
            return new ResponseEntity<>("No esta registrado", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Inscripto al curso",HttpStatus.ACCEPTED);
    }


}
