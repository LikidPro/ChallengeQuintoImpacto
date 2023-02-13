package ChallengeBackend.QuintoImpacto.DTO;

import ChallengeBackend.QuintoImpacto.modelos.CursoAlumno;

public class CursoAlumnoDTO {
    private long id;

    public CursoAlumnoDTO(CursoAlumno cursoAlumno) {
        this.id = cursoAlumno.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
