<%-- 
    Document   : VerCursos
    Created on : 02-nov-2020, 0:24:18
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

<jsp:useBean id="gestorBDInscripcionCurso" scope="request" class="InscripcionCurso.GestorBDInscripcionCurso"/>
<jsp:setProperty name="gestorBDInscripcionCurso" property="inscripcionIdSeleccionada" param="inscripcionId"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            UTN Academia
        </title>
        <link rel="shortcut icon" 
              href="http://localhost:8080/UTNAcademia/favicon.ico" 
              type="image/x-icon"/>
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
                Cursos Elegidos
            </h1>
            <c:choose>
                <c:when test="${empty gestorBDInscripcionCurso.obtenerInscripcionCursosXIdInscripcionDTO}">
                    <div class="alert alert-danger col-md-4" role="alert" >
                        Lista Vacía
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <thead>
                            <tr> 
                                <th scope="col">Id Inscripcion</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Precio</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${ gestorBDInscripcionCurso.obtenerInscripcionCursosXIdInscripcionDTO}" var="arrayListDTO">
                                <tr>
                                    <td>
                                        ${arrayListDTO.inscripcionId}
                                    </td>
                                    <td>
                                        ${arrayListDTO.cursoNombre}
                                    </td>
                                    <td>
                                        $${arrayListDTO.inscripcionCursoPrecio}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
            <form action="/UTNAcademia/Inscripcion/Listado.jsp" 
                  method="POST">               
                <button type="submit" 
                        value="Volver"
                        class="btn btn-warning">
                    Volver
                </button>
            </form>
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