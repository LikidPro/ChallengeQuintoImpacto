package ChallengeBackend.QuintoImpacto.modelos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private long id;

    private String nombre, apellido, email, contraseña;

    private int codigoActivacion;

    private boolean estadoAlumno;


    @OneToMany(mappedBy = "alumno",fetch = FetchType.EAGER)
    private Set<CursoAlumno> cursos = new HashSet<>();

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, String email, String contraseña, int codigoActivacion, boolean estadoAlumno) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
        this.codigoActivacion = codigoActivacion;
        this.estadoAlumno = estadoAlumno;

    }
    public Alumno(String nombre, String apellido, String email, String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
        this.codigoActivacion = (int) ((Math.random()* (99999 - 10000))+ 10000);
        this.estadoAlumno = false;

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

    public Set<CursoAlumno> getCursos() {
        return cursos;
    }

    public void setCursos(Set<CursoAlumno> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", codigoActivacion=" + codigoActivacion +
                ", estadoAlumno=" + estadoAlumno +
                ", cursos=" + cursos +
                '}';
    }
}

