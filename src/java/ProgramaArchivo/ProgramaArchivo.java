/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramaArchivo;

/**
 *
 * @author German
 */
public class ProgramaArchivo {

    private int programaArchivoId;

    private int programaArchivoProgramaId;
    private String programaArchivoRuta;
    private boolean programaArchivoBajaLogica;

    public int getProgramaArchivoId() {
        return programaArchivoId;
    }

    public int getProgramaArchivoProgramaId() {
        return programaArchivoProgramaId;
    }

    public String getProgramaArchivoRuta() {
        return programaArchivoRuta;
    }

    public boolean isProgramaArchivoBajaLogica() {
        return programaArchivoBajaLogica;
    }

    public void setProgramaArchivoId(int programaArchivoId) {
        this.programaArchivoId = programaArchivoId;
    }

    public void setProgramaArchivoProgramaId(int programaArchivoProgramaId) {
        this.programaArchivoProgramaId = programaArchivoProgramaId;
    }

    public void setProgramaArchivoRuta(String programaArchivoRuta) {
        this.programaArchivoRuta = programaArchivoRuta;
    }

    public void setProgramaArchivoBajaLogica(boolean programaArchivoBajaLogica) {
        this.programaArchivoBajaLogica = programaArchivoBajaLogica;
    }

    public ProgramaArchivo(int programaArchivoId, int programaArchivoProgramaId, String programaArchivoRuta, boolean programaArchivoBajaLogica) {
        this.programaArchivoId = programaArchivoId;
        this.programaArchivoProgramaId = programaArchivoProgramaId;
        this.programaArchivoRuta = programaArchivoRuta;
        this.programaArchivoBajaLogica = programaArchivoBajaLogica;
    }

    public ProgramaArchivo() {

    }

    @Override
    public String toString() {
        return "ProgramaArchivo{" + "programaArchivoId=" + programaArchivoId + ", programaArchivoProgramaId=" + programaArchivoProgramaId + ", programaArchivoRuta=" + programaArchivoRuta + ", programaArchivoBajaLogica=" + programaArchivoBajaLogica + '}';
    }

}
