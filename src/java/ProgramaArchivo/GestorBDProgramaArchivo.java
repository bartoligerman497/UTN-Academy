package ProgramaArchivo;

import ProgramaArchivo.ProgramaArchivo;
import Modelos.GestorBDConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestorBDProgramaArchivo {

    GestorBDConexion gestorBDConexion = new GestorBDConexion();

    private int programaIdSeleccionado;

    public int getProgramaIdSeleccionado() {
        return programaIdSeleccionado;
    }

    public void setProgramaIdSeleccionado(int programaIdSeleccionado) {
        this.programaIdSeleccionado = programaIdSeleccionado;
    }

    public boolean EliminarArchivo(int id) {
        int result = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL = "DELETE FROM ProgramaArchivo WHERE IDProgramaArchivo = ?";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setInt(1, id);

            result = stmt.executeUpdate();
            stmt.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean AgregarArchivo(ProgramaArchivo programaArchivo) {
        boolean b = false;
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "INSERT INTO "
                    + "ProgramaArchivo("
                    + "IdPrograma, "
                    + "Ruta,"
                    + "BajaLogica) "
                    + "VALUES (?,?,?)";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setInt(1, programaArchivo.getProgramaArchivoProgramaId());
            stmt.setString(2, programaArchivo.getProgramaArchivoRuta());
            stmt.setBoolean(3, true);

            filasAfectadas = stmt.executeUpdate();
            stmt.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }

        if (filasAfectadas > 0) {
            return b = true;
        } else {
            return b;
        }
    }

    public ProgramaArchivo getObtenerArchivoXIdPrograma() throws SQLException {
        ProgramaArchivo programaArchivo = null;

        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "select "
                    + "idProgramaArchivo, "
                    + "idPrograma, "
                    + "Ruta, "
                    + "BajaLogica "
                    + "from ProgramaArchivo "
                    + "where idPrograma = ?";
            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setInt(1, programaIdSeleccionado);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int programaArchivoId = rs.getInt("idProgramaArchivo");
                int programaId = rs.getInt("idPrograma");
                String programaArchivoRuta = rs.getString("Ruta");
                boolean programaBajaLogica = rs.getBoolean("bajaLogica");

                programaArchivo = new ProgramaArchivo(programaArchivoId, programaId, programaArchivoRuta, programaBajaLogica);
            }

            rs.close();
            stmt.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        return programaArchivo;
    }
}
