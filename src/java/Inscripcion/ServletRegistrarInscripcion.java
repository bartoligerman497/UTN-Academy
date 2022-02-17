/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inscripcion;

import Curso.Curso;
import Curso.GestorBDCurso;
import InscripcionCurso.GestorBDInscripcionCurso;
import InscripcionCurso.InscripcionCurso;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "ServletRegistrarInscripcion", urlPatterns = {"/ServletRegistrarInscripcion"})
public class ServletRegistrarInscripcion extends HttpServlet {

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
            out.println("<title>Servlet ServletRegistrarInscripcion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletRegistrarInscripcion at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);

        if (request.getSession().getAttribute("usuario") == null) {
            boolean datosIncorrectos = true;
            request.setAttribute("datosIncorrectos", datosIncorrectos);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
            rd.forward(request, response);
        }

        GestorBDCurso gestorBDCurso = new GestorBDCurso();

        Inscripcion inscripcion = (Inscripcion) request.getSession().getAttribute("inscripcion");
        int inscripcionAlumnoId = Integer.parseInt(request.getParameter("inscripcionAlumnoId"));
        int cantidadCursos = Integer.parseInt((String) request.getSession().getAttribute("cantidadCursos"));
        String inscripcionFecha = request.getParameter("inscripcionFecha");
        double inscripcionDescuento = Double.parseDouble(request.getParameter("inscripcionDescuento"));

        ArrayList<Curso> cursos = new ArrayList<>();
        int cursoId = 0;

        for (int i = 0; i < cantidadCursos; i++) {
            cursoId = Integer.parseInt(request.getParameter("inscripcionCursoId" + (i + 1)));

            gestorBDCurso.setCursoIdSeleccionado(cursoId);

            Curso curso = gestorBDCurso.getObtenerCursoXIdCurso();

            cursos.add(curso);
        }

        double inscripcionPrecioInicial = 0;
        for (Curso curso : cursos) {
            inscripcionPrecioInicial = inscripcionPrecioInicial + curso.getCursoPrecio();
        }

        inscripcionDescuento = inscripcionDescuento * inscripcionPrecioInicial / 100;

        double inscripcionPrecioFinal = inscripcionPrecioInicial - inscripcionDescuento;

        request.getSession().setAttribute("inscripcionId", inscripcion.getInscripcionId());
        request.getSession().setAttribute("inscripcionAlumnoId", inscripcionAlumnoId);
        request.getSession().setAttribute("cursos", cursos);
        request.getSession().setAttribute("inscripcionFecha", inscripcionFecha);
        request.getSession().setAttribute("inscripcionDescuento", inscripcionDescuento);
        request.getSession().setAttribute("inscripcionPrecioInicial", inscripcionPrecioInicial);
        request.getSession().setAttribute("inscripcionPrecioFinal", inscripcionPrecioFinal);

        response.sendRedirect("/UTNAcademia/Inscripcion/Confirmacion1.jsp");

//        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Inscripcion/Confirmacion1.jsp");
//        rd.forward(request, response);
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

        GestorBDInscripcion gestorBDInscripcion = new GestorBDInscripcion();
        GestorBDInscripcionCurso gestorBDInscripcionCurso = new GestorBDInscripcionCurso();

        int inscripcionId = (int) request.getSession().getAttribute("inscripcionId");
        int inscripcionAlumnoId = (int) request.getSession().getAttribute("inscripcionAlumnoId");
        ArrayList<Curso> arrayListCursos = (ArrayList<Curso>) request.getSession().getAttribute("cursos");
        String inscripcionFecha = (String) request.getSession().getAttribute("inscripcionFecha");
        double inscripcionDescuento = (Double) request.getSession().getAttribute("inscripcionDescuento");
        double inscripcionPrecioInicial = (Double) request.getSession().getAttribute("inscripcionPrecioInicial");
        double inscripcionPrecioFinal = (Double) request.getSession().getAttribute("inscripcionPrecioFinal");
        double inscripcionPagoCliente = Double.parseDouble((String) request.getSession().getAttribute("inscripcionPagoCliente"));
        double inscripcionVuelto = (Double) (request.getSession().getAttribute("inscripcionVuelto"));

        Inscripcion inscripcion = new Inscripcion(inscripcionId, inscripcionAlumnoId, inscripcionFecha, inscripcionPrecioInicial, inscripcionDescuento, inscripcionPrecioFinal, inscripcionPagoCliente, inscripcionVuelto);

        boolean exitoAgregarInscripcion = false;
        boolean exitoAgregarInscripcionCurso = false;

        exitoAgregarInscripcion = gestorBDInscripcion.AgregarInscripcion(inscripcion);

        int inscripcionÚltimoId = gestorBDInscripcion.ObtenerUltimoIDInscripcion();

        boolean exito = false;

        for (Curso curso : arrayListCursos) {
            exitoAgregarInscripcionCurso = gestorBDInscripcionCurso.AgregarInscripcionCurso(inscripcionÚltimoId, curso);
        }

        if (exitoAgregarInscripcion && exitoAgregarInscripcionCurso) {
            response.sendRedirect("/UTNAcademia/Inscripcion/Listado.jsp");
        }
    }

//
//            if (programa.getProgramaId() == 0) {
//                exito = gestorBDPrograma.AgregarPrograma(programa);
//            } else {
//                exito = gestorBDPrograma.ModificarPrograma(programa);
//            }
//            if (exito) {
//                response.sendRedirect("/UTNAcademia/Inscripcion/Listado.jsp");
//            }
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
