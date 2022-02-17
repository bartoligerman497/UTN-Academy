/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Curso;

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
public class GestorBDCurso {

    GestorBDConexion gestorBDConexion = new GestorBDConexion();

    ArrayList<Curso> arrayListComboCursos;
    ArrayList<Curso> arrayListCursos;

    private int cursoIdSeleccionado;

    public int getCursoIdSeleccionado() {
        return cursoIdSeleccionado;
    }

    public void setCursoIdSeleccionado(int cursoIdSeleccionado) {
        this.cursoIdSeleccionado = cursoIdSeleccionado;
    }

    public ArrayList<Curso> getCursoListado() {
        arrayListCursos = new ArrayList<>();

        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL = "{call SpCursoListado}";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            //statement.setString(1, "");

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int cursoId = resultSet.getInt(1);
                String cursoNombre = resultSet.getString(2);
                String cursoDescripcion = resultSet.getString(3);
                double cursoPrecio = resultSet.getDouble(4);
                int cursoTiempoMeses = resultSet.getInt(5);
                int cursoCupo = resultSet.getInt(6);
                boolean cursoBajaLogica = true;

                Curso curso = new Curso(cursoId, cursoNombre, cursoDescripcion, cursoPrecio, cursoTiempoMeses, cursoCupo, cursoBajaLogica);

                arrayListCursos.add(curso);
            }

            resultSet.close();
            callableStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }
        return arrayListCursos;
    }

    public Curso getObtenerCursoXIdCurso() {
        Curso curso = null;

        try {
            gestorBDConexion.AbrirConexion();
            String sentenciaSQL = "{call SpCursoObtenerXCursoId(?)}";
            CallableStatement callableStatement = gestorBDConexion.getConexion().prepareCall(sentenciaSQL);
            //statement.setString(1, "");
            callableStatement.setInt(1, cursoIdSeleccionado);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                String cursoNombre = resultSet.getString(2);
                String cursoDescripcion = resultSet.getString(3);
                double cursoPrecio = resultSet.getDouble(4);
                int cursoTiempoMeses = resultSet.getInt(5);
                int cursoCupo = resultSet.getInt(6);
                boolean cursoBajaLogica = true;

                curso = new Curso(cursoIdSeleccionado, cursoNombre, cursoDescripcion, cursoPrecio, cursoTiempoMeses, cursoCupo, cursoBajaLogica);

            }
            resultSet.close();
            callableStatement.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }

        return curso;
    }

    public boolean AgregarCurso(Curso curso) {
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "INSERT INTO "
                    + "Curso"
                    + "("
                    + "Nombre, "
                    + "Descripcion,"
                    + "Precio, "
                    + "TiempoMeses,"
                    + "Cupo,"
                    + "BajaLogica"
                    + ") "
                    + "VALUES (?,?,?,?,?,?)";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setString(1, curso.getCursoNombre());
            stmt.setString(2, curso.getCursoDescripcion());
            stmt.setDouble(3, curso.getCursoPrecio());
            stmt.setInt(4, curso.getCursoTiempoMeses());
            stmt.setInt(5, curso.getCursoCupo());
            stmt.setBoolean(6, true);

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

    public boolean ModificarCurso(Curso curso) {
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "UPDATE "
                    + "Curso "
                    + "SET "
                    + "Nombre = ?, "
                    + "Descripcion = ?, "
                    + "Precio = ?, "
                    + "TiempoMeses = ?, "
                    + "Cupo = ? "
                    + "where IdCurso = ?";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setString(1, curso.getCursoNombre());
            stmt.setString(2, curso.getCursoDescripcion());
            stmt.setDouble(3, curso.getCursoPrecio());
            stmt.setInt(4, curso.getCursoTiempoMeses());
            stmt.setInt(5, curso.getCursoCupo());
            stmt.setInt(6, curso.getCursoId());

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

    public boolean EliminarCurso(int idCurso) {
        int filasAfectadas = 0;
        try {
            gestorBDConexion.AbrirConexion();

            String sentenciaSQL
                    = "Update "
                    + "Curso "
                    + "Set BajaLogica = 0 "
                    + "WHERE IdCurso = ?";

            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sentenciaSQL);
            stmt.setInt(1, idCurso);

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
