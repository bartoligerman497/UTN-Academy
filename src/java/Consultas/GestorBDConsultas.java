/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import Modelos.DTO;
import Modelos.GestorBDConexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author German Bartoli
 */
public class GestorBDConsultas {

    ArrayList<DTO> arrayListDTO;

    GestorBDConexion gestorBDConexion = new GestorBDConexion();

    private int cursoSeleccionado;

    public void setCursoSeleccionado(int cursoSeleccionado) {
        this.cursoSeleccionado = cursoSeleccionado;
    }

    public GestorBDConsultas() {

    }

    public ArrayList<DTO> getSpTotalFacturadoPorCadaCurso() {
        arrayListDTO = new ArrayList<>();

        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL = "{call SpConsultasTotalFacturadoPorCurso}";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            //statement.setString(1, "");

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                // int idStock = rs.getInt(Nombre);

                int cursoId = resultSet.getInt(1);
                String cursoNombre = resultSet.getString(2);
                double sumTotalFacturadoPorCurso = resultSet.getDouble(3);

                DTO dto = new DTO();

                dto.setCursoId(cursoId);
                dto.setCursoNombre(cursoNombre);
                dto.setSUMTotalFacturadoPorCurso(sumTotalFacturadoPorCurso);

                arrayListDTO.add(dto);
            }

            resultSet.close();
            callableStatement.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        return arrayListDTO;
    }

    public double getSpTotalDescuentos() {
        double SUMTotalDescuentos = 0;

        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL = "{call SpConsultasTotalDescuentos}";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            //statement.setString(1, "");

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                // int idStock = rs.getInt(Nombre);

                SUMTotalDescuentos = resultSet.getDouble(1);
            }

            resultSet.close();
            callableStatement.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        return SUMTotalDescuentos;
    }

    public ArrayList<DTO> getSpAlumnosCursoSeleccionado() {
        arrayListDTO = new ArrayList<>();

        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL = "{call SpConsultasAlumnosCursoSeleccionado(?)}";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            callableStatement.setInt(1, cursoSeleccionado);
            //statement.setString(1, "");

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                // int idStock = rs.getInt(Nombre);

                int inscripcionId = resultSet.getInt(1);
                int alumnoId = resultSet.getInt(2);
                String alumnoNombreApellido = resultSet.getString(3);
                int alumnoEdad = resultSet.getInt(4);
                boolean alumnoSexo = resultSet.getBoolean(5);

                DTO dto = new DTO();

                dto.setInscripcionId(inscripcionId);
                dto.setAlumnoId(alumnoId);
                dto.setAlumnoNombreApellido(alumnoNombreApellido);
                dto.setAlumnoEdad(alumnoEdad);
                dto.setAlumnoSexo(alumnoSexo);

                arrayListDTO.add(dto);
            }

            resultSet.close();
            callableStatement.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        return arrayListDTO;
    }

    public ArrayList<DTO> getSp5Programas() {
        arrayListDTO = new ArrayList<>();

        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL = "{call [dbo].[SpConsultas5Programas]}";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            //statement.setString(1, "");

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                // int idStock = rs.getInt(Nombre);

                int programaId = resultSet.getInt(1);
                String programaNombre = resultSet.getString(2);
                String programaFecha = resultSet.getString(3);
                int programaCantidadDescargas = resultSet.getInt(4);
                boolean programaBajaLogica = resultSet.getBoolean(5);

                int alumnoId = resultSet.getInt(6);
                String alumnoNombreApellido = resultSet.getString(7);

                DTO dto = new DTO();

                dto.setProgramaId(programaId);
                dto.setProgramaNombre(programaNombre);
                dto.setProgramaFecha(programaFecha);
                dto.setProgramaCantidadDescargas(programaCantidadDescargas);
                dto.setProgramaBajaLogica(programaBajaLogica);

                dto.setAlumnoId(alumnoId);
                dto.setAlumnoNombreApellido(alumnoNombreApellido);

                arrayListDTO.add(dto);
            }

            resultSet.close();
            callableStatement.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        return arrayListDTO;
    }

    public boolean getEstadoSp5Programas() {

        boolean bandera = true;

        ArrayList<DTO> arrayListDTOPrograma = getSp5Programas();

        for (DTO dto : arrayListDTOPrograma) {
            if (dto.getProgramaBajaLogica()) {
                bandera = false;
            }
        }

        return bandera;
    }

    public ArrayList<DTO> getSpAlumnosConDescuento() {
        arrayListDTO = new ArrayList<>();

        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL = "{call SpConsultasAlumnosConDescuento}";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            //statement.setString(1, "");

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                // int idStock = rs.getInt(Nombre);

                int alumnoId = resultSet.getInt(1);
                String alumnoNombreApellido = resultSet.getString(2);
                int alumnoEdad = resultSet.getInt(3);
                boolean alumnoSexo = resultSet.getBoolean(4);
                double sumTotalDescuentosXAlumno = resultSet.getDouble(5);

                DTO dto = new DTO();

                dto.setAlumnoId(alumnoId);
                dto.setAlumnoNombreApellido(alumnoNombreApellido);
                dto.setAlumnoEdad(alumnoEdad);
                dto.setAlumnoSexo(alumnoSexo);
                dto.setSumTotalDescuentosXAlumno(sumTotalDescuentosXAlumno);

                arrayListDTO.add(dto);
            }

            resultSet.close();
            callableStatement.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        return arrayListDTO;
    }
}
