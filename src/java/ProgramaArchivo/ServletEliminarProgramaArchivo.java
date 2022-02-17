package ProgramaArchivo;

import Programa.Programa;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletEliminarHabitacionImagen", urlPatterns = {"/ServletEliminarProgramaArchivo"})
public class ServletEliminarProgramaArchivo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("usuario") == null) {
            boolean datosIncorrectos = true;
            request.setAttribute("datosIncorrectos", datosIncorrectos);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
            rd.forward(request, response);
        }

        try {
            GestorBDProgramaArchivo gestorBDProgramaArchivo = new GestorBDProgramaArchivo();

            int programaArchivoId = Integer.parseInt(request.getParameter("programaArchivoId"));
            String programaArchivoRuta = request.getParameter("programaArchivoRuta");

            //**********************************AQUÍ********************************************
            Path imagesPath = Paths.get("C:\\Users\\German\\Google Drive\\@UTN\\4to Cuatrimestre\\LAB4 2020\\UTNAcademia\\web\\Programa\\" + programaArchivoRuta);
            //Path imagesPath = Paths.get("C:\\Users\\barto\\Google Drive\\@UTN\\TP Laboratorio 4\\2W1_110218\\UTNMotel\\web\\" + descripcion);
            //Path imagesPath = Paths.get("E:\\TP Laboratorio 4\\2W1_110218\\UTNMotel\\web\\" + descripcion);

            Files.delete(imagesPath);

            boolean exito = gestorBDProgramaArchivo.EliminarArchivo(programaArchivoId);

            Programa programa = (Programa) request.getSession().getAttribute("programa");

            if (exito) {
                response.sendRedirect("/UTNAcademia/Programa/Registrar.jsp?programaId=" + programa.getProgramaId());
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
