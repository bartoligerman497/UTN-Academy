<%-- 
    Document   : Listado
    Created on : 24-oct-2020, 20:19:47
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

<jsp:useBean id="curso" scope="request" class="Curso.Curso"/>
<jsp:setProperty name="curso" property="cursoId" param="cursoId"/>

<jsp:useBean id="gestorBDCurso" scope="request" class="Curso.GestorBDCurso"/>
<jsp:setProperty name="gestorBDCurso" property="cursoIdSeleccionado" param="cursoId"/>

<jsp:useBean id="Consultas" scope="request" class="Consultas.GestorBDConsultas"/>
<jsp:setProperty name="Consultas" property="cursoSeleccionado" param="cursoId"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>UTN Academia</title>

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
                <ins>Alumnos del Curso:</ins><br>
                ${gestorBDCurso.obtenerCursoXIdCurso.cursoNombre}<br>    
            </h1>
            <%--
            <%= request.getParameter("cursoNombre")%> -->
            ${param.cursoNombre} 
            --%>
            <c:choose>
                <c:when test="${empty Consultas.spAlumnosCursoSeleccionado}">
                    <div class="alert alert-danger col-md-4" role="alert" >
                        Lista Vacía
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <thead>
                            <tr> 
                                <th scope="col">Id</th>
                                <th scope="col">Nombre y Apellido</th>
                                <th scope="col">Edad</th>
                                <th scope="col">Sexo</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${Consultas.spAlumnosCursoSeleccionado}" var="arrayListDTO">
                                <tr>
                                    <td>
                                        ${arrayListDTO.alumnoId}
                                    </td>
                                    <td>
                                        ${arrayListDTO.alumnoNombreApellido}
                                    </td>
                                    <td>
                                        ${arrayListDTO.alumnoEdad}
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${arrayListDTO.alumnoSexo}">
                                                Hombre
                                            </c:when>    
                                            <c:otherwise>
                                                Mujer
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <button type="button" 
                                                class="btn btn-warning" 
                                                onclick="window.location.href = '/UTNAcademia/Alumno/Registrar.jsp?alumnoId=${arrayListDTO.alumnoId}'">
                                            Editar Alumno
                                        </button>
                                    </td>
                                    <td>
                                        <form action="/UTNAcademia/ServletInscripcionCursoDesabilitarAlumnoDelCurso?alumnoId=${arrayListDTO.alumnoId}&cursoId=${curso.cursoId}&inscripcionId=${arrayListDTO.inscripcionId}" 
                                              method="POST"
                                              onsubmit="return validar();">               
                                            <button type="submit" 
                                                    vlalue="Deabilitar"
                                                    class="btn btn-danger">
                                                Eliminar del Curso
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
        <button type="button" 
                class="btn btn-primary" 
                onclick="window.location.href = '/UTNAcademia/Consultas/AlumnosXCurso/SeleccionarCurso.jsp'">
            Volver
        </button>
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