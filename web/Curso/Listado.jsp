<%-- 
    Document   : Listado
    Created on : 24-oct-2020, 20:19:16
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

<jsp:useBean id="GestorBDCurso" scope="request" class="Curso.GestorBDCurso"/>

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
                Listado de Cursos
            </h1>
            <c:choose>
                <c:when test="${empty GestorBDCurso.cursoListado}">
                    <div class="alert alert-danger col-md-4" role="alert" >
                        Lista Vacía
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <thead>
                            <tr> 
                                <th scope="col">Id Curso</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Precio</th>
                                <th scope="col">Duración</th>
                                <th scope="col">Cupo</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${GestorBDCurso.cursoListado}" var="arrayListCursos">
                                <tr>
                                    <td>
                                        ${arrayListCursos.cursoId}
                                    </td>
                                    <td>
                                        ${arrayListCursos.cursoNombre}
                                    </td>
                                    <td>
                                        $${arrayListCursos.cursoPrecio}
                                    </td>
                                    <td>
                                        ${arrayListCursos.cursoTiempoMeses} Meses
                                    </td>
                                    <td>
                                        ${arrayListCursos.cursoCupo}
                                    </td>
                                    <td>               
                                        <form action="/UTNAcademia/Curso/Registrar.jsp" 
                                              method="POST">    
                                            <input type="text" 
                                                   name="cursoId" 
                                                   hidden=""
                                                   value="${arrayListCursos.cursoId}"
                                                   class="form-control col-md-4"/>
                                            <button type="submit" 
                                                    value="Editar"
                                                    class="btn btn-warning">
                                                Editar
                                            </button>
                                        </form>
                                        <!--
                                              <button type="button" 
                                                    class="btn btn-warning" 
                                                    onclick="window.location.href = '/UTNAcademia/Curso/Registrar.jsp?cursoId=${arrayListCursos.cursoId}'">
                                                     Editar
                                              </button>
                                        -->
                                    </td>
                                    <td>
                                        <form action="/UTNAcademia/ServletEliminarCurso?cursoId=${arrayListCursos.cursoId}" 
                                              method="POST"
                                              onsubmit="return validar();">               
                                            <button type="submit" 
                                                    value="Eliminar"
                                                    class="btn btn-danger">
                                                Eliminar
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
            <form action="/UTNAcademia/Curso/Registrar.jsp?cursoId=0" 
                  method="POST">               
                <button type="submit" 
                        value="Registrar"
                        class="btn btn-primary">
                    Registrar
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