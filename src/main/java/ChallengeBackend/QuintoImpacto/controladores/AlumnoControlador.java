package ChallengeBackend.QuintoImpacto.controladores;
import ChallengeBackend.QuintoImpacto.DTO.AlumnoDTO;
import ChallengeBackend.QuintoImpacto.modelos.Alumno;
import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.CursoAlumno;
import ChallengeBackend.QuintoImpacto.servicios.AlumnoServicio;
import ChallengeBackend.QuintoImpacto.servicios.CursoAlumnoServicio;
import ChallengeBackend.QuintoImpacto.servicios.CursoServicio;
import ChallengeBackend.QuintoImpacto.servicios.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AlumnoControlador {

    @Autowired
    private AlumnoServicio alumnoServicio;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CursoAlumnoServicio cursoAlumnoServicio;

    @Autowired
    private CursoServicio cursoServicio;

    @Autowired
    EmailService emailService;

    @GetMapping("/alumnos")
    public Set<AlumnoDTO> getCursos(){
        return alumnoServicio.getAlumnosDTO();
    }

    @PostMapping("/alumnos")
    public ResponseEntity<Object> registroAlumno(
            @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String contraseña){
        if (nombre.isEmpty() ){
            return new ResponseEntity<>("Falta el nombre", HttpStatus.FORBIDDEN);

        }
        if ( apellido.isEmpty()){
            return new ResponseEntity<>("Falta el apellido", HttpStatus.FORBIDDEN);

        }
        if (email.isEmpty() ){
            return new ResponseEntity<>("Falta el email", HttpStatus.FORBIDDEN);

        }
        if (contraseña.isEmpty()){
            return new ResponseEntity<>("Falta la contraseña", HttpStatus.FORBIDDEN);

        }
        if (alumnoServicio.getAlumnoByEmail(email) != null){
            return new ResponseEntity<>("Este email esta en uso", HttpStatus.FORBIDDEN);
        }
        Alumno alumno= new Alumno(nombre,apellido,email,passwordEncoder.encode(contraseña));
        alumnoServicio.saveAlumno(alumno);

        return new ResponseEntity<>(alumno.getCodigoActivacion() ,HttpStatus.CREATED);
    }

    @PostMapping("/alumno/mandar/email")
    public ResponseEntity<Object> sendEmailTool(
            @RequestParam String to,
            @RequestParam int code

    ) {
        String textMessage = "El codigo de validacion es                                                                                                           " +
                code;
        String subject = "Validacion Email";
        emailService.sendEmailTool(textMessage, to, subject);

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);

    }
    @PatchMapping("/alumno/validacion")
    public ResponseEntity<Object> validationClient(
            @RequestParam String email,
            @RequestParam int code
    ){
        Alumno alumno =  alumnoServicio.getAlumnoByEmail(email);
        if(alumno.getCodigoActivacion() == code){
            alumno.setEstadoAlumno(true);
            alumnoServicio.saveAlumno(alumno);
            return new ResponseEntity<>("Su email ah sido confirmado ya puede iniciar sesion",HttpStatus.ACCEPTED);
        }else return new ResponseEntity<>("El codigo no es valido",HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("/alumno/autenticado")
    public AlumnoDTO getAllBuyersDTO(Authentication authentication) {
        return new AlumnoDTO(alumnoServicio.getAlumnoByEmail(authentication.getName()));
    }
    @PatchMapping ("/alumno/nombre")
    public ResponseEntity<Object> cambiarNombre(@RequestParam String email,@RequestParam String nombre){
        Alumno alumno = alumnoServicio.getAlumnoByEmail(email);
        alumno.setNombre(nombre);
        alumnoServicio.saveAlumno(alumno);
        return new ResponseEntity<> ( "Apellido Nombre" , HttpStatus.OK);
    }
    @PatchMapping ("/alumno/apellido")
    public ResponseEntity<Object> cambiarApellido(@RequestParam String email,@RequestParam String apellido){
        Alumno alumno = alumnoServicio.getAlumnoByEmail(email);
        alumno.setApellido(apellido);
        alumnoServicio.saveAlumno(alumno);
        return new ResponseEntity<>("Apellido Cambiado",HttpStatus.OK);
    }
    @PatchMapping ("/alumno/email")
    public ResponseEntity<Object> cambiarEmail(@RequestParam String email,@RequestParam String email2){
        Alumno alumno = alumnoServicio.getAlumnoByEmail(email);
        alumno.setEmail(email2);
        alumnoServicio.saveAlumno(alumno);
        return new ResponseEntity<>( "Apellido Email",HttpStatus.OK);
    }
    @PatchMapping ("/alumno/contraseña")
    public ResponseEntity<Object> cambiarContraseña(@RequestParam String email,@RequestParam String contraseña){
        Alumno alumno = alumnoServicio.getAlumnoByEmail(email);
        alumno.setContraseña(passwordEncoder.encode(contraseña));
        alumnoServicio.saveAlumno(alumno);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping ("/alumno/curso")
    public ResponseEntity<Object> quitarCurso(@RequestParam String email,@RequestParam String curso){
        Alumno alumno = alumnoServicio.getAlumnoByEmail(email);
        alumno.getCursos().stream().forEach(cursoAlumno ->{
            if(cursoAlumno.getCurso().getNombre().equals(curso)){
                cursoAlumnoServicio.borrarCurso(cursoAlumno);
                alumno.getCursos().remove(cursoAlumno);
                alumnoServicio.saveAlumno(alumno);
                Curso curso1 = cursoServicio.getCursoByNombre(curso);
                cursoServicio.borrarCursoAlumno(curso1, cursoAlumno);

            }

        }) ;

        return new ResponseEntity<>("Curso Borrado",HttpStatus.OK);
    }

    @PatchMapping("/alumno/borrar")
    public ResponseEntity<?> borrarAlumno(@RequestParam Long id) {
        Alumno alumno = alumnoServicio.getAlumnoById(id);

        if ( alumno == null ) {
            return new ResponseEntity<>("El alumno no existe",HttpStatus.FORBIDDEN);
        }

        alumno.setEstadoAlumno(false);
        alumnoServicio.saveAlumno(alumno);
        return new ResponseEntity<>("Alumno borrado",HttpStatus.OK);
    }

}
