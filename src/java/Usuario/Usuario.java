/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

/**
 *
 * @author German
 */
public class Usuario {

    private int usuarioId;
    private String usuarioNombre;
    private String usuarioContraseina;

    public int getUsuarioId() {
        return usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public String getUsuarioContraseina() {
        return usuarioContraseina;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public void setUsuarioContraseina(String usuarioContraseina) {
        this.usuarioContraseina = usuarioContraseina;
    }

    public Usuario(int usuarioId, String usuarioNombre, String usuarioContraseina) {
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.usuarioContraseina = usuarioContraseina;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuarioId=" + usuarioId + ", usuarioNombre=" + usuarioNombre + ", usuarioContraseina=" + usuarioContraseina + '}';
    }

}
