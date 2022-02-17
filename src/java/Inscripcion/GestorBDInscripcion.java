/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inscripcion;

import Curso.Curso;
import Modelos.DTO;
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
public class GestorBDInscripcion {

    GestorBDConexion gestorBDConexion = new GestorBDConexion();

    ArrayList<Inscripcion> arrayListInscripcio;

    public ArrayList<DTO> getInscripcionListadoDTO() {
        ArrayList<DTO> arrayListInscripcioListadoDTO = new ArrayList<>();

        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "SELECT   "
                    + "dbo.Inscripcion.IdInscripcion, "
                    + "dbo.Alumno.NombreApellido, "
                    + "dbo.Inscripcion.Fecha, "
                    + "dbo.Inscripcion.PrecioInicial, "
                    + "dbo.Inscripcion.Descuento, "
                    + "dbo.Inscripcion.PrecioFinal, "
                    + "dbo.Inscripcion.PagoAlumno, "
                    + "dbo.Inscripcion.Vuelto "
                    + "FROM "
                    + "dbo.Alumno INNER JOIN "
                    + "dbo.Inscripcion ON dbo.Alumno.IdAlumno = dbo.Inscripcion.IdAlumno";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            //statement.setString(1, "");

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int inscripcionId = resultSet.getInt(1);
                String alumnoNombreApellido = resultSet.getString(2);
                String inscripcionFecha = resultSet.getString(3);
                double inscripcionPrecioInicial = resultSet.getDouble(4);
                double inscripcionDescuento = resultSet.getDouble(5);
                double inscripcionPrecioFinal = resultSet.getDouble(6);
                double inscripcionPagoAlumno = resultSet.getDouble(7);
                double inscripcionVuelto = resultSet.getDouble(8);

                DTO dto = new DTO();

                dto.setInscripcionId(inscripcionId);
                dto.setAlumnoNombreApellido(alumnoNombreApellido);
                dto.setInscripcionFecha(inscripcionFecha);
                dto.setInscripcionPrecioInicial(inscripcionPrecioInicial);
                dto.setInscripcionDescuento(inscripcionDescuento);
                dto.setInscripcionPrecioFinal(inscripcionPrecioFinal);
                dto.setInscripcionPagoAlumno(inscripcionPagoAlumno);
                dto.setInscripcionVuelto(inscripcionVuelto);

                arrayListInscripcioListadoDTO.add(dto);
            }

            resultSet.close();
            callableStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        return arrayListInscripcioListadoDTO;
    }

    public boolean AgregarInscripcion(Inscripcion inscripcion) {
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "INSERT INTO "
                    + "Inscripcion"
                    + "("
                    + "idAlumno, "
                    + "Fecha,"
                    + "PrecioInicial, "
                    + "Descuento,"
                    + "PrecioFinal,"
                    + "PagoAlumno,"
                    + "Vuelto"
                    + ") "
                    + "VALUES (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            preparedStatement.setInt(1, inscripcion.getInscipcionAlumnoId());
            preparedStatement.setString(2, inscripcion.getInscripcionFecha());
            preparedStatement.setDouble(3, inscripcion.getInscripcionPrecioInicial());
            preparedStatement.setDouble(4, inscripcion.getInscripcionDescuento());
            preparedStatement.setDouble(5, inscripcion.getInscripcionPrecioFinal());
            preparedStatement.setDouble(6, inscripcion.getInscripcionPagoAlumno());
            preparedStatement.setDouble(7, inscripcion.getInscripcionVuelto());

            filasAfectadas = preparedStatement.executeUpdate();
            preparedStatement.close();

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

    public int ObtenerUltimoIDInscripcion() {
        int ultimoIDInscripcion = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "SELECT "
                    + "MAX(IdInscripcion) "
                    + "FROM Inscripcion;";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ultimoIDInscripcion = rs.getInt(1);
            }
            rs.close();
            stmt.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }

        return ultimoIDInscripcion;
    }
}
