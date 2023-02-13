package ChallengeBackend.QuintoImpacto;

import ChallengeBackend.QuintoImpacto.modelos.*;
import ChallengeBackend.QuintoImpacto.repositorios.AlumnoRepositorio;
import ChallengeBackend.QuintoImpacto.repositorios.CursoAlumnoRepositorio;
import ChallengeBackend.QuintoImpacto.repositorios.CursoRepositorio;
import ChallengeBackend.QuintoImpacto.repositorios.ProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class QuintoImpactoApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(QuintoImpactoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(AlumnoRepositorio alumnoRepositorio, CursoAlumnoRepositorio cursoAlumnoRepositorio, CursoRepositorio cursoRepositorio, ProfesorRepositorio profesorRepositorio){
		return args ->{
//			Creando Profesores de Prueba
			Profesor profesor = new Profesor("Paula","Martinez");
			Profesor profesor2 = new Profesor("Jesus","N");
			Profesor profesor3 = new Profesor("David","Garcia");
			Profesor profesor4 = new Profesor("Albert","Einstein");
//          Guardando Profesores
			profesorRepositorio.save(profesor);
			profesorRepositorio.save(profesor2);
			profesorRepositorio.save(profesor3);
			profesorRepositorio.save(profesor4);
//			Creando Cursos de Prueba
			Curso curso = new Curso("JAVA","Programacion", Horario.MAÑANA, profesor2);
			Curso curso2 = new Curso("Spring","Programacion", Horario.TARDE, profesor2);
			Curso curso3 = new Curso("Musica","Arte", Horario.TARDE, profesor3);
			Curso curso4 = new Curso("Psicologia","Sociales", Horario.NOCHE, profesor);
			Curso curso5 = new Curso("Fisica","Ciencias", Horario.NOCHE, profesor4);
			Curso curso6 = new Curso("Relatividad","Ciencias", Horario.MAÑANA, profesor4);
//			Guardando Cursos
			cursoRepositorio.save(curso);
			cursoRepositorio.save(curso2);
			cursoRepositorio.save(curso3);
			cursoRepositorio.save(curso4);
			cursoRepositorio.save(curso5);
			cursoRepositorio.save(curso6);
//			Creando Administrador
			Alumno alumno = new Alumno("Admin","Admin","admin@admin.com",passwordEncoder.encode("1234"),1234,true);
//			Guardando Administrador
			alumnoRepositorio.save(alumno);
//			Creando Alumnos Prueba
			Alumno alumno2 = new Alumno("Adrian","Lopez","adrian@Lopez.com",passwordEncoder.encode("1234"),1234,true);
			Alumno alumno3 = new Alumno("Tomaz","Suarez","Tomas@Suarez.com",passwordEncoder.encode("1234"),1234,true);
			Alumno alumno4 = new Alumno("Fer","Torres","Fer@Torres.com",passwordEncoder.encode("1234"),1234,true);
//          Guardando Alumnos Prueba
			alumnoRepositorio.save(alumno2);
			alumnoRepositorio.save(alumno3);
			alumnoRepositorio.save(alumno4);
//			Creando CursoAlumno
			CursoAlumno cursoAlumno = new CursoAlumno(alumno2,curso);
			CursoAlumno cursoAlumno2 = new CursoAlumno(alumno3,curso);
			CursoAlumno cursoAlumno3 = new CursoAlumno(alumno4,curso);
			CursoAlumno cursoAlumno4 = new CursoAlumno(alumno2,curso2);
			CursoAlumno cursoAlumno5 = new CursoAlumno(alumno2,curso3);
			cursoAlumnoRepositorio.save(cursoAlumno);
			cursoAlumnoRepositorio.save(cursoAlumno2);
			cursoAlumnoRepositorio.save(cursoAlumno3);
			cursoAlumnoRepositorio.save(cursoAlumno4);
			cursoAlumnoRepositorio.save(cursoAlumno5);
		};
}
	}