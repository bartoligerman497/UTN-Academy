/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Curso;

/**
 *
 * @author German
 */
public class Curso {

    private int cursoId;
    private String cursoNombre;
    private String cursoDescripcion;
    private double cursoPrecio;
    private int cursoTiempoMeses;
    private int cursoCupo;
    private boolean cursoBajaLogica;

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

    public Curso(int cursoId, String cursoNombre, String cursoDescripcion, double cursoPrecio, int cursoTiempoMeses, int cursoCupo, boolean cursoBajaLogica) {
        this.cursoId = cursoId;
        this.cursoNombre = cursoNombre;
        this.cursoDescripcion = cursoDescripcion;
        this.cursoPrecio = cursoPrecio;
        this.cursoTiempoMeses = cursoTiempoMeses;
        this.cursoCupo = cursoCupo;
        this.cursoBajaLogica = cursoBajaLogica;
    }

    public Curso() {

    }

    @Override
    public String toString() {
        return "cursoNombre=" + cursoNombre + ", cursoDescripcion=" + cursoDescripcion + ", cursoPrecio=" + cursoPrecio + ", cursoTiempoMeses=" + cursoTiempoMeses + ", cursoCupo=" + cursoCupo + ", cursoBajaLogica=" + cursoBajaLogica;
    }

}
