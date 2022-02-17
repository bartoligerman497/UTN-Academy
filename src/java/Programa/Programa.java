/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programa;

/**
 *
 * @author German
 */
public class Programa {

    private int programaId;
    private int programaAlumnoId;
    private String programaNombre;
    private String programaFecha;
    private int programaCantidadDescargas;
    private boolean programaBajaLogica;

    public int getProgramaId() {
        return programaId;
    }

    public int getProgramaAlumnoId() {
        return programaAlumnoId;
    }

    public String getProgramaNombre() {
        return programaNombre;
    }

    public String getProgramaFecha() {
        return programaFecha;
    }

    public int getProgramaCantidadDescargas() {
        return programaCantidadDescargas;
    }

    public boolean isProgramaBajaLogica() {
        return programaBajaLogica;
    }

    public void setProgramaId(int programaId) {
        this.programaId = programaId;
    }

    public void setProgramaAlumnoId(int programaAlumnoId) {
        this.programaAlumnoId = programaAlumnoId;
    }

    public void setProgramaNombre(String programaNombre) {
        this.programaNombre = programaNombre;
    }

    public void setProgramaFecha(String programaFecha) {
        this.programaFecha = programaFecha;
    }

    public void setProgramaCantidadDescargas(int programaCantidadDescargas) {
        this.programaCantidadDescargas = programaCantidadDescargas;
    }

    public void setProgramaBajaLogica(boolean programaBajaLogica) {
        this.programaBajaLogica = programaBajaLogica;
    }

    public Programa(int programaId, int programaAlumnoId, String programaNombre, String programaFecha, int programaCantidadDescargas, boolean programaBajaLogica) {
        this.programaId = programaId;
        this.programaAlumnoId = programaAlumnoId;
        this.programaNombre = programaNombre;
        this.programaFecha = programaFecha;
        this.programaCantidadDescargas = programaCantidadDescargas;
        this.programaBajaLogica = programaBajaLogica;
    }

    public Programa() {

    }

    @Override
    public String toString() {
        return "Programa{" + "programaId=" + programaId + ", programaNombre=" + programaNombre + ", programaFecha=" + programaFecha + ", programaCantidadDescargas=" + programaCantidadDescargas + ", programaBajaLogica=" + programaBajaLogica + '}';
    }
}
