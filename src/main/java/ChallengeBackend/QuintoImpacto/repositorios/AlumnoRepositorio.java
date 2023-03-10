package ChallengeBackend.QuintoImpacto.repositorios;

import ChallengeBackend.QuintoImpacto.modelos.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AlumnoRepositorio extends JpaRepository<Alumno,Long> {
    Alumno findByEmail(String email);
}
