package ChallengeBackend.QuintoImpacto.repositorios;

import ChallengeBackend.QuintoImpacto.modelos.Curso;
import ChallengeBackend.QuintoImpacto.modelos.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProfesorRepositorio extends JpaRepository<Profesor,Long> {
    Profesor findByNombre(String nombre);
}
