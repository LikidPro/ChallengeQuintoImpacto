package ChallengeBackend.QuintoImpacto.repositorios;

import ChallengeBackend.QuintoImpacto.modelos.Alumno;
import ChallengeBackend.QuintoImpacto.modelos.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CursoRepositorio extends JpaRepository<Curso,Long> {
    Curso findByNombre(String nombre);
}
