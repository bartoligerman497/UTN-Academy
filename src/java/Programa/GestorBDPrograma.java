/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programa;

import Curso.Curso;
import Modelos.GestorBDConexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author German
 */
public class GestorBDPrograma {

    GestorBDConexion gestorBDConexion = new GestorBDConexion();

    ArrayList<Programa> arrayListComboPrograma;
    ArrayList<Programa> arrayListPrograma;

    private int programaIdSeleccionado;

    public int getProgramaIdSeleccionado() {
        return programaIdSeleccionado;
    }

    public void setProgramaIdSeleccionado(int programaIdSeleccionado) {
        this.programaIdSeleccionado = programaIdSeleccionado;
    }

    public ArrayList<Programa> getProgramaListado() {
        arrayListPrograma = new ArrayList<>();

        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL = "{call SpProgramaListado}";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            //statement.setString(1, "");

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int programaId = resultSet.getInt(1);
                int programaAlumnoId = resultSet.getInt(2);
                String programaNombre = resultSet.getString(3);
                String programaFecha = resultSet.getString(4);
                int programaCantidadDescargas = resultSet.getInt(5);
                boolean programaBajaLogica = resultSet.getBoolean(6);

                Programa programa = new Programa(programaId, programaAlumnoId, programaNombre, programaFecha, programaCantidadDescargas, programaBajaLogica);

                arrayListPrograma.add(programa);
            }

            resultSet.close();
            callableStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        return arrayListPrograma;
    }

    public boolean getEstadoProgramaListado() {

        boolean bandera = true;

        ArrayList<Programa> arrayListProgramas = getProgramaListado();

        for (Programa arrayListPrograma : arrayListProgramas) {
            if (arrayListPrograma.isProgramaBajaLogica()) {
                bandera = false;
            }
        }

        return bandera;
    }

    public Programa getObtenerProgramaXIdPrograma() {
        Programa programa = null;

        try {
            gestorBDConexion.AbrirConexion();
            String sentenciaSQL = "{call SpProgramaObtenerXProgramaId(?)}";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            //statement.setString(1, "");
            callableStatement.setInt(1, programaIdSeleccionado);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                int programaAlumnoId = resultSet.getInt(2);

                String programaNombre = resultSet.getString(3);
                String programaFecha = resultSet.getString(4);
                int programaCantidadDescargas = resultSet.getInt(5);
                boolean programaBajaLogica = resultSet.getBoolean(6);

                programa = new Programa(programaIdSeleccionado, programaAlumnoId, programaNombre, programaFecha, programaCantidadDescargas, programaBajaLogica);

            }
            resultSet.close();
            callableStatement.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }

        return programa;
    }

    public boolean AgregarPrograma(Programa programa) {
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "INSERT INTO "
                    + "Programa"
                    + "("
                    + "IdAlumno, "
                    + "Nombre,"
                    + "Fecha, "
                    + "CantidadDescargas,"
                    + "BajaLogica"
                    + ") "
                    + "VALUES (?,?,?,?,?)";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setInt(1, programa.getProgramaAlumnoId());
            stmt.setString(2, programa.getProgramaNombre());
            stmt.setString(3, programa.getProgramaFecha());
            stmt.setInt(4, programa.getProgramaCantidadDescargas());
            stmt.setBoolean(5, true);

            filasAfectadas = stmt.executeUpdate();
            stmt.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }

        if (filasAfectadas > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ModificarPrograma(Programa programa) {
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "UPDATE "
                    + "Programa "
                    + "SET "
                    + "IdAlumno = ?, "
                    + "Nombre = ?, "
                    + "Fecha = ? "
                    + "where IdPrograma = ?";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setInt(1, programa.getProgramaAlumnoId());
            stmt.setString(2, programa.getProgramaNombre());
            stmt.setString(3, programa.getProgramaFecha());
            stmt.setInt(4, programa.getProgramaId());

            filasAfectadas = stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        if (filasAfectadas > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ModificarProgramaContador(int programaId) {
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "UPDATE "
                    + "Programa "
                    + "SET "
                    + "CantidadDescargas = CantidadDescargas + 1 "
                    + "where IdPrograma = ?";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setInt(1, programaId);

            filasAfectadas = stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        if (filasAfectadas > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean HabilitarPrograma(int idPrograma) {
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "Update "
                    + "Programa "
                    + "Set BajaLogica = 1 "
                    + "WHERE IdPrograma = ?";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setInt(1, idPrograma);

            filasAfectadas = stmt.executeUpdate();
            stmt.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        if (filasAfectadas > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean DesabilitarPrograma(int idPrograma) {
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "Update "
                    + "Programa "
                    + "Set BajaLogica = 0 "
                    + "WHERE IdPrograma = ?";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setInt(1, idPrograma);

            filasAfectadas = stmt.executeUpdate();
            stmt.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        if (filasAfectadas > 0) {
            return true;
        } else {
            return false;
        }
    }
}
