/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InscripcionCurso;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author German
 */
@WebServlet(name = "ServletInscripcionCursoDesabilirarAlumnoDelCurso", urlPatterns = {"/ServletInscripcionCursoDesabilitarAlumnoDelCurso"})
public class ServletInscripcionCursoDesabilitarAlumnoDelCurso extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletInscripcionCursoDesabilirarAlumnoDelCurso</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletInscripcionCursoDesabilirarAlumnoDelCurso at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        if (request.getSession().getAttribute("usuario") == null) {
            boolean datosIncorrectos = true;
            request.setAttribute("datosIncorrectos", datosIncorrectos);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
            rd.forward(request, response);
        }

        int alumnoId = Integer.parseInt(request.getParameter("alumnoId"));
        int cursoId = Integer.parseInt(request.getParameter("cursoId"));
        int inscripcionId = Integer.parseInt(request.getParameter("inscripcionId"));

        GestorBDInscripcionCurso gestorBDInscripcionCurso = new GestorBDInscripcionCurso();

        boolean exito = gestorBDInscripcionCurso.DesabilitarAlumnoDelCurso(cursoId, inscripcionId);

        if (exito) {
            response.sendRedirect("/UTNAcademia/Consultas/AlumnosXCurso/Listado.jsp?cursoId=" + cursoId);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}