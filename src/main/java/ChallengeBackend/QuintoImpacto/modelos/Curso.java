package ChallengeBackend.QuintoImpacto.modelos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private long id;

    private String nombre, area;

    private Horario horario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    @OneToMany(mappedBy = "curso",fetch = FetchType.EAGER)
    private Set<CursoAlumno> cursoAlumnos = new HashSet<>();

    public Curso() {
    }

    public Curso(String nombre, String area, Horario horario, Profesor profesor) {
        this.nombre = nombre;
        this.area = area;
        this.horario = horario;
        this.profesor = profesor;

    }
    public Curso(String nombre, String area, Horario horario) {
        this.nombre = nombre;
        this.area = area;
        this.horario = horario;


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

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Set<CursoAlumno> getCursosAlumnos() {
        return cursoAlumnos;
    }

    public void setCursosAlumnos(Set<CursoAlumno> cursoAlumnos) {
        this.cursoAlumnos = cursoAlumnos;
    }
    public void addCurso(CursoAlumno cursoAlumno){
        cursoAlumno.setCurso(this);
        cursoAlumnos.add(cursoAlumno);

    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", area='" + area + '\'' +
                ", horario=" + horario +
                ", profesor=" + profesor +
                ", cursoAlumnos=" + cursoAlumnos +
                '}';
    }
}
