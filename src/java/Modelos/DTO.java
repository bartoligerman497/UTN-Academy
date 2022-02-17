/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author German
 */
public class DTO {

    private double sumTotalFacturadoPorCurso;

    private double sumTotalDescuentos;

    private double sumTotalDescuentosXAlumno;

    private int alumnoId;
    private String alumnoNombreApellido;
    private int alumnoEdad;
    private boolean alumnoSexo;
    private boolean alumnoBajaLogica;


    //era mejor hacer private Programa programa y listo pa
    private int programaId;
    private String programaNombre;
    private String programaFecha;
    private int programaCantidadDescargas;
    private boolean programaBajaLogica;

    private int cursoId;
    private String cursoNombre;
    private String cursoDescripcion;
    private double cursoPrecio;
    private int cursoTiempoMeses;
    private int cursoCupo;
    private boolean cursoBajaLogica;

    private int inscripcionCursoId;
    private double inscripcionCursoPrecio;
    private boolean inscripcionCursoBajaLogica;

    private int inscripcionId;
    private String inscripcionFecha;
    private double inscripcionPrecioInicial;
    private double inscripcionDescuento;
    private double inscripcionPrecioFinal;
    private double inscripcionPagoAlumno;
    private double inscripcionVuelto;

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------
    public double getSUMTotalFacturadoPorCurso() {
        return sumTotalFacturadoPorCurso;
    }

    public void setSUMTotalFacturadoPorCurso(double sumTotalFacturadoPorCurso) {
        this.sumTotalFacturadoPorCurso = sumTotalFacturadoPorCurso;
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------
    public double getSUMTotalDescuentos() {
        return sumTotalDescuentos;
    }

    public void setSUMTotalDescuentos(double sumTotalDescuentos) {
        this.sumTotalDescuentos = sumTotalDescuentos;
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------
    public double getSumTotalDescuentosXAlumno() {
        return sumTotalDescuentosXAlumno;
    }

    public void setSumTotalDescuentosXAlumno(double sumTotalDescuentosXAlumno) {
        this.sumTotalDescuentosXAlumno = sumTotalDescuentosXAlumno;
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------
    public int getAlumnoId() {
        return alumnoId;
    }

    public String getAlumnoNombreApellido() {
        return alumnoNombreApellido;
    }

    public int getAlumnoEdad() {
        return alumnoEdad;
    }

    public boolean isAlumnoSexo() {
        return alumnoSexo;
    }

    public boolean isAlumnoBajaLogica() {
        return alumnoBajaLogica;
    }

    //-------------------------------------------------------------------------------------------
    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public void setAlumnoNombreApellido(String alumnoNombreApellido) {
        this.alumnoNombreApellido = alumnoNombreApellido;
    }

    public void setAlumnoEdad(int alumnoEdad) {
        this.alumnoEdad = alumnoEdad;
    }

    public void setAlumnoSexo(boolean alumnoSexo) {
        this.alumnoSexo = alumnoSexo;
    }

    public void setAlumnoBajaLogica(boolean alumnoBajaLogica) {
        this.alumnoBajaLogica = alumnoBajaLogica;
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------
    public int getProgramaId() {
        return programaId;
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

    public boolean getProgramaBajaLogica() {
        return programaBajaLogica;
    }

    //-------------------------------------------------------------------------------------------
    public void setProgramaId(int programaId) {
        this.programaId = programaId;
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

    public void setProgramaBajaLogica(boolean programaBajaLigica) {
        this.programaBajaLogica = programaBajaLigica;
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------
    public int getCursoId() {
        return cursoId;
    }

    public String getCursoNombre() {
        return cursoNombre;
    }

    public String getCursoDescripcion() {
        return cursoDescripcion;
    }

    public double getCursoPrecio() {
        return cursoPrecio;
    }

    public int getCursoTiempoMeses() {
        return cursoTiempoMeses;
    }

    public int getCursoCupo() {
        return cursoCupo;
    }

    public boolean isCursoBajaLogica() {
        return cursoBajaLogica;
    }

    //-------------------------------------------------------------------------------------------
    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public void setCursoNombre(String cursoNombre) {
        this.cursoNombre = cursoNombre;
    }

    public void setCursoDescripcion(String cursoDescripcion) {
        this.cursoDescripcion = cursoDescripcion;
    }

    public void setCursoPrecio(double cursoPrecio) {
        this.cursoPrecio = cursoPrecio;
    }

    public void setCursoTiempoMeses(int cursoTiempoMeses) {
        this.cursoTiempoMeses = cursoTiempoMeses;
    }

    public void setCursoCupo(int cursoCupo) {
        this.cursoCupo = cursoCupo;
    }

    public void setCursoBajaLogica(boolean cursoBajaLogica) {
        this.cursoBajaLogica = cursoBajaLogica;
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------
    public int getInscripcionCursoId() {
        return inscripcionCursoId;
    }

    public double getInscripcionCursoPrecio() {
        return inscripcionCursoPrecio;
    }

    public boolean isInscripcionCursoBajaLogica() {
        return inscripcionCursoBajaLogica;
    }

    //-------------------------------------------------------------------------------------------
    public void setInscripcionCursoId(int inscripcionCursoId) {
        this.inscripcionCursoId = inscripcionCursoId;
    }

    public void setInscripcionCursoPrecio(double inscripcionCursoPrecio) {
        this.inscripcionCursoPrecio = inscripcionCursoPrecio;
    }

    public void setInscripcionCursoBajaLogica(boolean inscripcionCursoBajaLogica) {
        this.inscripcionCursoBajaLogica = inscripcionCursoBajaLogica;
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------
    public int getInscripcionId() {
        return inscripcionId;
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

    //-------------------------------------------------------------------------------------------
    public void setInscripcionId(int inscripcionId) {
        this.inscripcionId = inscripcionId;
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

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------
}
