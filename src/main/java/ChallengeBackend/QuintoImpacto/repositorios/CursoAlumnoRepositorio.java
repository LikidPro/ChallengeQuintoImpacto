package ChallengeBackend.QuintoImpacto.repositorios;

import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.CursoAlumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CursoAlumnoRepositorio extends JpaRepository<CursoAlumno,Long> {
  CursoAlumno findByCursoNombre(String curso);
}