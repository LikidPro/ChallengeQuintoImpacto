package ChallengeBackend.QuintoImpacto.DTO;

import ChallengeBackend.QuintoImpacto.modelos.CursoAlumno;

import java.time.LocalDate;

public class CursoAlumnoDTO {


    private String curso;
    private LocalDate fecha;


    public CursoAlumnoDTO(CursoAlumno cursoAlumno) {
        this.curso=cursoAlumno.getCurso().getNombre();
        this.fecha = cursoAlumno.getLocalDate();
    }


    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
