package ChallengeBackend.QuintoImpacto.modelos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private long id;

    private String nombre, apellido;

    @OneToMany(mappedBy = "profesor",fetch = FetchType.EAGER)
    private Set<Curso> cursos = new HashSet<>();

    public Profesor() {
    }

    public Profesor(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;

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
        nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        apellido = apellido;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    public void addCurso(Curso curso){
        if(cursos.size()==0){
            curso.setProfesor(this);
            cursos.add(curso);
        }
        cursos.forEach(curso1 -> {
            if(!curso1.getHorario().equals(curso.getHorario())){
                curso.setProfesor(this);
                cursos.add(curso);
            }
        });

    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", Nombre='" + nombre + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", cursos=" + cursos +
                '}';
    }
}
