<%-- 
    Document   : Listado
    Created on : 01-nov-2020, 13:00:11
    Author     : German
--%>

<%
    HttpSession sesion = request.getSession();
    Object usuario = sesion.getAttribute("usuario");

    if (usuario == null) {
        response.sendRedirect("/UTNAcademia/ServletLogin?accion=cerrar");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="gestorBDInscripcion" scope="session" class="Inscripcion.GestorBDInscripcion"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            UTN Academia
        </title>
        <link rel="shortcut icon" 
              href="http://localhost:8080/UTNAcademia/favicon.ico" 
              type="image/x-icon">
        <link rel="stylesheet" 
              href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" 
              crossorigin="anonymous">
    </head>
    <body>
        <%@include file="/Menu.jsp" %>
    <center>
        <div class="container-fluid">
            <h1 class="mt-4">
                Listado de Inscripciones
            </h1>
            <c:choose>
                <c:when test="${empty gestorBDInscripcion.inscripcionListadoDTO}">
                    <div class="alert alert-danger col-md-4" role="alert" >
                        Lista Vacía
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <thead>
                            <tr scope="row"> 
                                <th scope="col">Id Inscripcion</th>
                                <th scope="col">Alumno</th>
                                <th scope="col">Fecha</th>
                                <th scope="col">Precio Inicial</th>
                                <th scope="col">Descuento</th>
                                <th scope="col">Precio Final</th>
                                <th scope="col">Pago Alumno</th>
                                <th scope="col">Vuelto</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${gestorBDInscripcion.inscripcionListadoDTO}" var="arrayListInscripcioListadoDTO">
                                <tr>
                                    <td>
                                        ${arrayListInscripcioListadoDTO.inscripcionId}
                                    </td>
                                    <td>
                                        ${arrayListInscripcioListadoDTO.alumnoNombreApellido}
                                    </td>
                                    <td>
                                        ${arrayListInscripcioListadoDTO.inscripcionFecha}
                                    </td>
                                    <td>
                                        ${arrayListInscripcioListadoDTO.inscripcionPrecioInicial}
                                    </td>
                                    <td>
                                        ${arrayListInscripcioListadoDTO.inscripcionDescuento}
                                    </td>
                                    <td>
                                        ${arrayListInscripcioListadoDTO.inscripcionPrecioFinal}
                                    </td>
                                    <td>
                                        ${arrayListInscripcioListadoDTO.inscripcionPagoAlumno}
                                    </td>
                                    <td>
                                        ${arrayListInscripcioListadoDTO.inscripcionVuelto}
                                    </td>
                                    <td>
                                        <form action="/UTNAcademia/Inscripcion/VerCursos.jsp?inscripcionId=${arrayListInscripcioListadoDTO.inscripcionId}" 
                                              method="POST"
                                              onsubmit="return validar();">               
                                            <button type="submit" 
                                                    value="VerCursos"
                                                    class="btn btn-primary">
                                                Ver Cursos
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
            <button type="button" 
                    value="registrar"
                    class="btn btn-primary" 
                    onclick="window.location.href = '/UTNAcademia/Inscripcion/SeleccionarCantidadCursos.jsp'">
                Registrar
            </button>
        </div>
    </center>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" 
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" 
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" 
            crossorigin="anonymous">
    </script>
    <script>
    </script>
</body>
</html>
