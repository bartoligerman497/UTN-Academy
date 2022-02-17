/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InscripcionCurso;

import Curso.Curso;
import Modelos.DTO;
import Modelos.GestorBDConexion;
import ProgramaArchivo.ProgramaArchivo;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author German
 */
public class GestorBDInscripcionCurso {

    GestorBDConexion gestorBDConexion = new GestorBDConexion();
    ArrayList<InscripcionCurso> arrayListComboInscripcionCurso;

    private int inscripcionIdSeleccionada;

    public int getInscripcionIdSeleccionada() {
        return inscripcionIdSeleccionada;
    }

    public void setInscripcionIdSeleccionada(int inscripcionIdSeleccionada) {
        this.inscripcionIdSeleccionada = inscripcionIdSeleccionada;
    }

    public ArrayList<DTO> getObtenerInscripcionCursosXIdInscripcionDTO() {
        ArrayList<DTO> arrayListInscripcionCursosXIdInscripcionDTO = new ArrayList<>();
        Curso curso = null;

        try {
            gestorBDConexion.AbrirConexion();
            String sentenciaSQL
                    = "SELECT "
                    + "dbo.Inscripcion.IdInscripcion, "
                    + "dbo.Curso.Nombre, "
                    + "dbo.InscripcionCurso.Precio "
                    + "FROM dbo.Curso INNER JOIN "
                    + "dbo.InscripcionCurso ON dbo.Curso.IdCurso = dbo.InscripcionCurso.IdCurso INNER JOIN "
                    + "dbo.Inscripcion ON dbo.InscripcionCurso.IdInscripcion = dbo.Inscripcion.IdInscripcion "
                    + "where dbo.Inscripcion.IdInscripcion = ?";
            PreparedStatement preparedStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            //statement.setString(1, "");
            preparedStatement.setInt(1, inscripcionIdSeleccionada);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idInscripcion = resultSet.getInt(1);
                String cursoNombre = resultSet.getString(2);
                double inscripcionCursoPrecio = resultSet.getDouble(3);

                DTO dto = new DTO();

                dto.setInscripcionId(idInscripcion);
                dto.setCursoNombre(cursoNombre);
                dto.setInscripcionCursoPrecio(inscripcionCursoPrecio);

                arrayListInscripcionCursosXIdInscripcionDTO.add(dto);

            }
            resultSet.close();
            preparedStatement.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }

        return arrayListInscripcionCursosXIdInscripcionDTO;
    }

    public boolean AgregarInscripcionCurso(int ultimoIdInscripcion, Curso curso) {
        boolean b = false;
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();
            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(
                    "INSERT INTO "
                    + "InscripcionCurso"
                    + "("
                    + "idInscripcion, "
                    + "IdCurso, "
                    + "precio, "
                    + "BajaLogica"
                    + ") "
                    + "VALUES (?,?,?,?)"
            );

            stmt.setInt(1, ultimoIdInscripcion);
            stmt.setInt(2, curso.getCursoId());
            stmt.setDouble(3, curso.getCursoPrecio());
            stmt.setBoolean(4, true);

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

    public boolean DesabilitarAlumnoDelCurso(int cursoId, int inscripcionId) {
        int filasAfectadas = 0;

        try {
            gestorBDConexion.AbrirConexion();

            //Mal porque desabilita todas las inscripcionescurso de ese alumno en ese curso, no tengo el idInscripcion
            //            String sentenciaSQL
            //                    = "Update "
            //                    + "InscripcionCurso "
            //                    + "	set dbo.InscripcionCurso.BajaLogica = 0 "
            //                    + "	FROM            dbo.Alumno INNER JOIN "
            //                    + "     dbo.Inscripcion ON dbo.Alumno.IdAlumno = dbo.Inscripcion.IdAlumno INNER JOIN "
            //                    + "       dbo.InscripcionCurso ON dbo.Inscripcion.IdInscripcion = dbo.InscripcionCurso.IdInscripcion "
            //                    + "	where dbo.InscripcionCurso.IdCurso = ? and dbo.Alumno.IdAlumno= ?";
            String sentenciaSQL
                    = "Update "
                    + "InscripcionCurso "
                    + "	set dbo.InscripcionCurso.BajaLogica = 0 "
                    + "	where dbo.InscripcionCurso.IdCurso = ? and "
                    + "dbo.InscripcionCurso.IdInscripcion = ?";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);

            stmt.setInt(1, cursoId);
            stmt.setInt(2, inscripcionId);

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
}
