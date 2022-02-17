/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alumno;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.GestorBDConexion;
import java.sql.CallableStatement;
import java.util.ArrayList;

/**
 *
 * @author German
 */
public class GestorBDAlumno {

    GestorBDConexion gestorBDConexion = new GestorBDConexion();

    ArrayList<Alumno> arrayListComboAlumnos;
    ArrayList<Alumno> arrayListAlumnos;

    private int alumnoIdSeleccionado;

    public int getAlumnoIdSeleccionado() {
        return alumnoIdSeleccionado;
    }

    public void setAlumnoIdSeleccionado(int alumnoIdSeleccionado) {
        this.alumnoIdSeleccionado = alumnoIdSeleccionado;
    }

    public ArrayList<Alumno> getAlumnoListado() {
        arrayListAlumnos = new ArrayList<Alumno>();

        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL = "{call SpAlumnoListado}";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            //statement.setString(1, "");

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int idAlumno = resultSet.getInt(1);
                String nomApe = resultSet.getString(2);
                int edad = resultSet.getInt(3);
                boolean sexo = resultSet.getBoolean(4);
                boolean bajaLogica = true;

                Alumno alumno = new Alumno(idAlumno, nomApe, edad, sexo, bajaLogica);

                arrayListAlumnos.add(alumno);
            }

            resultSet.close();
            callableStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        return arrayListAlumnos;
    }

    public Alumno getObtenerAlumnoXIdAlumno() {
        Alumno alumno = null;

        try {
            gestorBDConexion.AbrirConexion();
            String sentenciaSQL = "{call SpAlumnoObtenerXAlumnoId(?)}";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            //statement.setString(1, "");
            callableStatement.setInt(1, alumnoIdSeleccionado);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                int alumnoId = resultSet.getInt(1);
                String alumnoNombreApellido = resultSet.getString(2);
                int alumnoEdad = resultSet.getInt(3);
                boolean alumnoSexo = resultSet.getBoolean(4);
                boolean alumnoBajaLogica = true;

                alumno = new Alumno(alumnoId, alumnoNombreApellido, alumnoEdad, alumnoSexo, alumnoBajaLogica);

            }
            resultSet.close();
            callableStatement.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }

        return alumno;
    }

    public boolean AgregarAlumno(Alumno alumno) {
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "INSERT INTO "
                    + "Alumno"
                    + "("
                    + "NombreApellido, "
                    + "Edad,"
                    + "Sexo, "
                    + "BajaLogica"
                    + ") "
                    + "VALUES (?,?,?,?)";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setString(1, alumno.getAlumnoNombreApellido());
            stmt.setInt(2, alumno.getAlumnoEdad());
            stmt.setBoolean(3, alumno.isAlumnoSexo());
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

    public boolean ModificarAlumno(Alumno alumno) {
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "UPDATE "
                    + "Alumno "
                    + "SET "
                    + "NombreApellido = ?, "
                    + "Edad = ?, "
                    + "Sexo = ? "
                    + "where IdAlumno = ?";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setString(1, alumno.getAlumnoNombreApellido());
            stmt.setInt(2, alumno.getAlumnoEdad());
            stmt.setBoolean(3, alumno.isAlumnoSexo());
            stmt.setInt(4, alumno.getAlumnoId());

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

    public boolean EliminarAlumno(int idAlumno) {
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "Update "
                    + "Alumno "
                    + "Set BajaLogica = 0 "
                    + "WHERE IdAlumno = ?";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setInt(1, idAlumno);

            filasAfectadas = stmt.executeUpdate();
            stmt.close();

        } catch (SQLException exc) {
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

    public ArrayList<Alumno> getCargarComboAlumnos() {

        arrayListComboAlumnos = new ArrayList<>();

        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL = "{call SpAlumnoListado}";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int idAlumno = resultSet.getInt(1);
                String nomApe = resultSet.getString(2);
                int edad = resultSet.getInt(3);
                boolean sexo = resultSet.getBoolean(4);
                boolean bajaLogica = true;

                Alumno alumno = new Alumno(idAlumno, nomApe, edad, sexo, bajaLogica);

                arrayListComboAlumnos.add(alumno);
            }

            resultSet.close();
            callableStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        return arrayListComboAlumnos;
    }
    //CargarListaTodosLosAlumnos
}
