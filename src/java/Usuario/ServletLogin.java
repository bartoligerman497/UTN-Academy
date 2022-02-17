package Usuario;

import Usuario.GestorBDUsuario;
import Usuario.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("cerrar")) {
            HttpSession sesion = request.getSession();
            sesion.removeAttribute("usuario");

            sesion.invalidate();

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/InicioPublico.jsp");
            rd.forward(request, response);
        }

        if (accion != null && accion.equals("ingresar")) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String usuarioNombre = request.getParameter("usuarioNombre");
            String usuarioContrasenia = request.getParameter("usuarioContrasenia");

            GestorBDUsuario gestorDBUsuario = new GestorBDUsuario();

            Usuario usuario = new Usuario(0, usuarioNombre, usuarioContrasenia);

            boolean existe = false;
            existe = gestorDBUsuario.ExisteUsuario(usuario);

            if (existe) {
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usuario);

                sesion.setMaxInactiveInterval(600);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Inicio.jsp");
                rd.forward(request, response);

            } else {
                boolean datosIncorrectos = true;
                request.getSession().setAttribute("datosIncorrectos", datosIncorrectos);
//            request.setAttribute("datosIncorrectos", datosIncorrectos);
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
//            rd.forward(request, response);
                response.sendRedirect("/UTNAcademia/Login.jsp");

            }
        } catch (Exception e) {
            boolean datosIncorrectos = true;
            request.getSession().setAttribute("datosIncorrectos", datosIncorrectos);

            response.sendRedirect("/UTNAcademia/Login.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
