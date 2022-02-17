/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alumno;

/**
 *
 * @author German
 */
public class Alumno {

    private int alumnoId;
    private String alumnoNombreApellido;
    private int alumnoEdad;
    private boolean alumnoSexo;
    private boolean alumnoBajaLogica;

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

    public Alumno(int alumnoId, String alumnoNombreApellido, int alumnoEdad, boolean alumnoSexo, boolean alumnoBajaLogica) {
        this.alumnoId = alumnoId;
        this.alumnoNombreApellido = alumnoNombreApellido;
        this.alumnoEdad = alumnoEdad;
        this.alumnoSexo = alumnoSexo;
        this.alumnoBajaLogica = alumnoBajaLogica;
    }

    public Alumno() {

    }

    @Override
    public String toString() {
        return "Id =" + alumnoId + ", Nombre y Apellido =" + alumnoNombreApellido;
    }

}
