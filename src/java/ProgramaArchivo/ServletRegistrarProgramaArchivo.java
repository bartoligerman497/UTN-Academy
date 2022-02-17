package ProgramaArchivo;

import ProgramaArchivo.GestorBDProgramaArchivo;
import ProgramaArchivo.ProgramaArchivo;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "ServletModificarHabitacionImagen", urlPatterns = {"/ServletRegistrarProgramaArchivo"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5,
        //          **********************************AQUÍ********************************************
        location = "C:\\Users\\German\\Google Drive\\@UTN\\4to Cuatrimestre\\LAB4 2020\\UTNAcademia\\web\\Programa\\Archivos")
//        location = "E:\\TP Laboratorio 4\\2W1_110218\\UTNMotel\\web\\ImagenesHabitaciones")

public class ServletRegistrarProgramaArchivo extends HttpServlet {

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

        String archivoNombre = "";

        for (Part part : request.getParts()) {
            String fileName = getFileName(part);
            if (!fileName.isEmpty()) {
                archivoNombre = fileName;
                part.write(fileName);
            }
        }

        String programaArchivoRuta = "Archivos/" + archivoNombre;
        int programaId = Integer.parseInt(request.getParameter("programaId"));

        ProgramaArchivo programaArchivo = new ProgramaArchivo(0, programaId, programaArchivoRuta, true);

        GestorBDProgramaArchivo gestorBDProgramaArchivo = new GestorBDProgramaArchivo();

        boolean exito = gestorBDProgramaArchivo.AgregarArchivo(programaArchivo);

        if (exito) {
            response.sendRedirect("/UTNAcademia/Programa/Registrar.jsp?programaId=" + programaId);
        }
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "";
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
