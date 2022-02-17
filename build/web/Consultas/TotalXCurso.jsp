<%-- 
    Document   : TotalXCurso
    Created on : 24-oct-2020, 20:22:42
    Author     : German
--%>

<%
    HttpSession sesion = request.getSession();
    Object usuario = sesion.getAttribute("usuario");

    if (usuario == null) {
        response.sendRedirect("/UTNAcademia/ServletLogin?accion=cerrar");
    }
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="Consultas" scope="request" class="Consultas.GestorBDConsultas"/>

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
                Total Facturado por Curso
            </h1>
            <c:choose>
                <c:when test="${empty Consultas.spTotalFacturadoPorCadaCurso}">
                    <div class="alert alert-danger col-md-4" role="alert" >
                        Lista Vacía
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <thead>
                            <tr> 
                                <th scope="col">
                                    Id Curso
                                </th>
                                <th scope="col">
                                    Nombre
                                </th>
                                <th scope="col">
                                    Total Facturado
                                </th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${Consultas.spTotalFacturadoPorCadaCurso}" var="arrayListDTO">
                                <tr>
                                    <td>
                                        ${arrayListDTO.cursoId}
                                    </td>
                                    <td>
                                        ${arrayListDTO.cursoNombre}
                                    </td>
                                    <td>
                                        $${arrayListDTO.SUMTotalFacturadoPorCurso}
                                    </td>
                                    <td>
                                        <button type="button" 
                                                class="btn btn-warning" 
                                                onclick="window.location.href = '/UTNAcademia/Curso/Registrar.jsp?cursoId=${arrayListDTO.cursoId}'">
                                            Editar
                                        </button>
                                    </td>
                                    <td>
                                        <form action="/UTNAcademia/ServletEliminarCurso?cursoId=${arrayListDTO.cursoId}" 
                                              method="POST"
                                              onsubmit="return validar();">               
                                            <button type="submit" 
                                                    vlalue="Agregar"
                                                    class="btn btn-danger">
                                                Eliminar
                                            </button>
                                        </form>
                                    </td> </tr>
                                </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
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
        function validar() {
            if (confirm("Esta seguro?")) {
                return true;
            } else {
                return false;
            }
        }
    </script>
</body>
</html>
