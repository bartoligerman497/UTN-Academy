/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InscripcionCurso;

/**
 *
 * @author German
 */
public class InscripcionCurso {

    private int inscripcionCursoId;
    private int inscripcionCursoInscripcionId;
    private int inscripcionCursoCursoId;
    private double inscripcionCursoPrecio;
    private boolean inscripcionCursoBajaLogica;

    public int getInscripcionCursoId() {
        return inscripcionCursoId;
    }

    public int getInscripcionCursoInscripcionId() {
        return inscripcionCursoInscripcionId;
    }

    public int getInscripcionCursoCursoId() {
        return inscripcionCursoCursoId;
    }

    public double getInscripcionCursoPrecio() {
        return inscripcionCursoPrecio;
    }

    public boolean isInscripcionCursoBajaLogica() {
        return inscripcionCursoBajaLogica;
    }

    public void setInscripcionCursoId(int inscripcionCursoId) {
        this.inscripcionCursoId = inscripcionCursoId;
    }

    public void setInscripcionCursoInscripcionId(int inscripcionCursoInscripcionId) {
        this.inscripcionCursoInscripcionId = inscripcionCursoInscripcionId;
    }

    public void setInscripcionCursoCursoId(int inscripcionCursoCursoId) {
        this.inscripcionCursoCursoId = inscripcionCursoCursoId;
    }

    public void setInscripcionCursoPrecio(double inscripcionCursoPrecio) {
        this.inscripcionCursoPrecio = inscripcionCursoPrecio;
    }

    public void setInscripcionCursoBajaLogica(boolean inscripcionCursoBajaLogica) {
        this.inscripcionCursoBajaLogica = inscripcionCursoBajaLogica;
    }

    public InscripcionCurso(int inscripcionCursoId, int inscripcionCursoInscripcionId, int inscripcionCursoCursoId, double inscripcionCursoPrecio, boolean inscripcionCursoBajaLogica) {
        this.inscripcionCursoId = inscripcionCursoId;
        this.inscripcionCursoInscripcionId = inscripcionCursoInscripcionId;
        this.inscripcionCursoCursoId = inscripcionCursoCursoId;
        this.inscripcionCursoPrecio = inscripcionCursoPrecio;
        this.inscripcionCursoBajaLogica = inscripcionCursoBajaLogica;
    }

    @Override
    public String toString() {
        return "InscripcionCurso{" + "inscripcionCursoId=" + inscripcionCursoId + ", inscripcionCursoInscripcionId=" + inscripcionCursoInscripcionId + ", inscripcionCursoCursoId=" + inscripcionCursoCursoId + ", inscripcionCursoPrecio=" + inscripcionCursoPrecio + ", inscripcionCursoBajaLogica=" + inscripcionCursoBajaLogica + '}';
    }

    public InscripcionCurso() {

    }

}
