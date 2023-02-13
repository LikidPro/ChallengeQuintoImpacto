package ChallengeBackend.QuintoImpacto;

import ChallengeBackend.QuintoImpacto.modelos.Alumno;
import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.CursoAlumno;
import ChallengeBackend.QuintoImpacto.modelos.Profesor;
import ChallengeBackend.QuintoImpacto.repositorios.AlumnoRepositorio;
import ChallengeBackend.QuintoImpacto.repositorios.CursoAlumnoRepositorio;
import ChallengeBackend.QuintoImpacto.repositorios.CursoRepositorio;
import ChallengeBackend.QuintoImpacto.repositorios.ProfesorRepositorio;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoriesTest {
    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    @Autowired
    private CursoAlumnoRepositorio cursoAlumnoRepositorio;

    @Autowired
    private CursoRepositorio cursoRepositorio;

    @Autowired
    private ProfesorRepositorio profesorRepositorio;

    @Test
    public void existenAlumnos(){
        List<Alumno> alumnos = alumnoRepositorio.findAll();
        assertThat(alumnos,is(not(empty())));

    }

    @Test
    public void existenPropiedadAlumnos(){
        List<Alumno> alumnos = alumnoRepositorio.findAll();
        assertThat(alumnos, hasItem(hasProperty("email", is("admin@admin.com"))));

    }
    @Test
    public void existenCursoAlumnos(){
        List<CursoAlumno> cursosAlumnos = cursoAlumnoRepositorio.findAll();
        assertThat(cursosAlumnos,is(not(empty())));

    }

    @Test
    public void existenPropiedadCursoAlumnos(){
        List<CursoAlumno> cursosAlumnos = cursoAlumnoRepositorio.findAll();
        assertThat(cursosAlumnos, hasItem(hasProperty("curso", is(5))));

    }
    @Test
    public void existeCurso(){
        List<Curso> accounts = cursoRepositorio.findAll();
        assertThat(accounts,is(not(empty())));

    }

    @Test
    public void existenPropiedadCurso(){
        List<Curso> accounts = cursoRepositorio.findAll();
        assertThat(accounts, hasItem(hasProperty("horario", is("MAÑANA"))));

    }
    @Test
    public void existenProfesores(){
        List<Profesor> clients = profesorRepositorio.findAll();
        assertThat(clients,is(not(empty())));

    }

    @Test
    public void existenPropiedadesProfesores(){
        List<Profesor> clients = profesorRepositorio.findAll();
        assertThat(clients, hasItem(hasProperty("nombre", is("David"))));

    }



}
