package ChallengeBackend.QuintoImpacto.DTO;

import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.CursoAlumno;
import ChallengeBackend.QuintoImpacto.modelos.Horario;
import ChallengeBackend.QuintoImpacto.modelos.Profesor;
import ChallengeBackend.QuintoImpacto.repositorios.CursoAlumnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CursoDTO {

    private long id;

    private String nombre, area;

    private Horario horario;

    private String profesor;


    private Set<AlumnoDTO> alumnoDTOS = new HashSet<>();

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.nombre = curso.getNombre();
        this.area = curso.getArea();
        this.horario = curso.getHorario();
        if(curso.getProfesor()== null){
            this.profesor = "No asignado";
        }else{

          this.profesor =  curso.getProfesor().getNombre() + " " + curso.getProfesor().getApellido();

        }
        this.alumnoDTOS = curso.getCursosAlumnos().stream().map(cursoAlumno -> new AlumnoDTO(cursoAlumno.getAlumno())).collect(Collectors.toSet());
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public Set<AlumnoDTO> getAlumnoDTOS() {
        return alumnoDTOS;
    }

    public void setAlumnoDTOS(Set<AlumnoDTO> alumnoDTOS) {
        this.alumnoDTOS = alumnoDTOS;
    }
}
