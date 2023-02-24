package ChallengeBackend.QuintoImpacto.DTO;

import ChallengeBackend.QuintoImpacto.modelos.Alumno;
import ChallengeBackend.QuintoImpacto.modelos.CursoAlumno;
import ChallengeBackend.QuintoImpacto.repositorios.AlumnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AlumnoDTO {


    private long id;

    private String nombre, apellido, email, contraseña;

    private int codigoActivacion;

    private boolean estadoAlumno;

    private Set<CursoAlumnoDTO> cursos = new HashSet<>();

    public AlumnoDTO(Alumno alumno) {
        this.id = alumno.getId();
        this.nombre = alumno.getNombre();
        this.apellido = alumno.getApellido();
        this.email = alumno.getEmail();
        this.contraseña = alumno.getContraseña();
        this.codigoActivacion = alumno.getCodigoActivacion();
        this.estadoAlumno = alumno.isEstadoAlumno();
        this.cursos = alumno.getCursos().stream().map(cursoAlumno -> new CursoAlumnoDTO(cursoAlumno)).collect(Collectors.toSet());
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getCodigoActivacion() {
        return codigoActivacion;
    }

    public void setCodigoActivacion(int codigoActivacion) {
        this.codigoActivacion = codigoActivacion;
    }

    public boolean isEstadoAlumno() {
        return estadoAlumno;
    }

    public void setEstadoAlumno(boolean estadoAlumno) {
        this.estadoAlumno = estadoAlumno;
    }

    public Set<CursoAlumnoDTO> getCursos() {return cursos;}

    public void setCursos(Set<CursoAlumnoDTO> cursos) {this.cursos = cursos;}
}
