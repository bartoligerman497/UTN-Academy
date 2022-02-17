/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inscripcion;

/**
 *
 * @author German
 */
public class Inscripcion {

    private int inscripcionId;
    private int inscipcionAlumnoId;
    private String inscripcionFecha;
    private double inscripcionPrecioInicial;
    private double inscripcionDescuento;
    private double inscripcionPrecioFinal;
    private double inscripcionPagoAlumno;
    private double inscripcionVuelto;

    public int getInscripcionId() {
        return inscripcionId;
    }

    public int getInscipcionAlumnoId() {
        return inscipcionAlumnoId;
    }

    public String getInscripcionFecha() {
        return inscripcionFecha;
    }

    public double getInscripcionPrecioInicial() {
        return inscripcionPrecioInicial;
    }

    public double getInscripcionDescuento() {
        return inscripcionDescuento;
    }

    public double getInscripcionPrecioFinal() {
        return inscripcionPrecioFinal;
    }

    public double getInscripcionPagoAlumno() {
        return inscripcionPagoAlumno;
    }

    public double getInscripcionVuelto() {
        return inscripcionVuelto;
    }

    public void setInscripcionId(int inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public void setInscipcionAlumnoId(int inscipcionAlumnoId) {
        this.inscipcionAlumnoId = inscipcionAlumnoId;
    }

    public void setInscripcionFecha(String inscripcionFecha) {
        this.inscripcionFecha = inscripcionFecha;
    }

    public void setInscripcionPrecioInicial(double inscripcionPrecioInicial) {
        this.inscripcionPrecioInicial = inscripcionPrecioInicial;
    }

    public void setInscripcionDescuento(double inscripcionDescuento) {
        this.inscripcionDescuento = inscripcionDescuento;
    }

    public void setInscripcionPrecioFinal(double inscripcionPrecioFinal) {
        this.inscripcionPrecioFinal = inscripcionPrecioFinal;
    }

    public void setInscripcionPagoAlumno(double inscripcionPagoAlumno) {
        this.inscripcionPagoAlumno = inscripcionPagoAlumno;
    }

    public void setInscripcionVuelto(double inscripcionVuelto) {
        this.inscripcionVuelto = inscripcionVuelto;
    }

    public Inscripcion(int inscripcionId, int inscipcionAlumnoId, String inscripcionFecha, double inscripcionPrecioInicial, double inscripcionDescuento, double inscripcionPrecioFinal, double inscripcionPagoAlumno, double inscripcionVuelto) {
        this.inscripcionId = inscripcionId;
        this.inscipcionAlumnoId = inscipcionAlumnoId;
        this.inscripcionFecha = inscripcionFecha;
        this.inscripcionPrecioInicial = inscripcionPrecioInicial;
        this.inscripcionDescuento = inscripcionDescuento;
        this.inscripcionPrecioFinal = inscripcionPrecioFinal;
        this.inscripcionPagoAlumno = inscripcionPagoAlumno;
        this.inscripcionVuelto = inscripcionVuelto;
    }

    public Inscripcion() {

    }

    @Override
    public String toString() {
        return "Inscripcion{" + "inscripcionId=" + inscripcionId + ", inscipcionAlumnoId=" + inscipcionAlumnoId + ", inscripcionFecha=" + inscripcionFecha + ", inscripcionPrecioInicial=" + inscripcionPrecioInicial + ", inscripcionDescuento=" + inscripcionDescuento + ", inscripcionPrecioFinal=" + inscripcionPrecioFinal + ", inscripcionPagoAlumno=" + inscripcionPagoAlumno + ", inscripcionVuelto=" + inscripcionVuelto + '}';
    }

}
