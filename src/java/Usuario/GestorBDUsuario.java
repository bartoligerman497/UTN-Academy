package Usuario;

import Modelos.GestorBDConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorBDUsuario {

    GestorBDConexion gestorBDConexion = new GestorBDConexion();

    public boolean ExisteUsuario(Usuario usuario) {
        boolean existe = false;
        try {
            gestorBDConexion.AbrirConexion();
            String sql = "select * from Usuario WHERE nombre = ? AND contrasenia = ?";
            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sql);
            stmt.setString(1, usuario.getUsuarioNombre());
            stmt.setString(2, usuario.getUsuarioContraseina());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                existe = true;
            }
            rs.close();
            stmt.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            gestorBDConexion.CerrarConexion();
        }

        return existe;
    }

//    public void NuevoUsuario(Usuario usuario) throws SQLException{
//        try {
//            gestorBDConexion.AbrirConexion();
//
//            String sql = "insert into Usuario (nombre, contrasenia) values (?,?)";
//
//            PreparedStatement stmt = gestorBDConexion.getConexion().prepareStatement(sql);
//            stmt.setString(1, usuario.getnombre());
//            stmt.setString(2, usuario.getcontrasenia());
//            ResultSet rs = stmt.executeQuery();
//
//        } catch (Exception exc) {
//            exc.printStackTrace();
//        } finally {
//            gestorBDConexion.CerrarConexion();
//        }
//    }
}
