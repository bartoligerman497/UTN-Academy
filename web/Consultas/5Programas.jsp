<%-- 
    Document   : 5Programas
    Created on : 24-oct-2020, 20:22:58
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

<jsp:useBean id="gestorBDPrograma" scope="session" class="Programa.GestorBDPrograma"/>

<jsp:useBean id="gestorBDAlumno" scope="session" class="Alumno.GestorBDAlumno"/>

<jsp:useBean id="gestorBDProgramaArchivo" scope="session" class="ProgramaArchivo.GestorBDProgramaArchivo"/>

<jsp:useBean id="gestorBDConsultas" scope="session" class="Consultas.GestorBDConsultas"/>

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
                TOP 5 Programas
            </h1>
            <c:choose>
                <c:when test="${empty gestorBDConsultas.sp5Programas}">
                    <div class="alert alert-danger col-md-4" role="alert" >
                        Lista Vacía
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <thead>
                            <tr scope="row"> 
                                <th scope="col">Id</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Creador</th>
                                <th scope="col">Fecha de Subida</th>
                                <th scope="col">Cantidad Descargas</th>
                                <th scope="col">Estado</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${gestorBDConsultas.sp5Programas}" var="arrayListDTO">
                                <tr
                                    <c:choose>
                                        <c:when test="${arrayListDTO.programaBajaLogica}">
                                            bgcolor="Aquamarine"
                                        </c:when>    
                                        <c:otherwise>
                                            bgcolor="Gainsboro"
                                        </c:otherwise>
                                    </c:choose>
                                    >
                                    <td>
                                        ${arrayListDTO.programaId}
                                    </td>
                                    <td>
                                        ${arrayListDTO.programaNombre}
                                    </td>
                                    <td>
                                        ${arrayListDTO.alumnoNombreApellido}
                                    </td>
                                    <td>
                                        ${arrayListDTO.programaFecha}
                                    </td>
                                    <td>
                                        ${arrayListDTO.programaCantidadDescargas}
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${arrayListDTO.programaBajaLogica}">
                                                Habilitado
                                            </c:when>    
                                            <c:otherwise>
                                                Deshabilitado
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <jsp:setProperty name="gestorBDProgramaArchivo" property="programaIdSeleccionado" value="${arrayListDTO.programaId}" />
                                        <c:choose>
                                            <c:when test="${empty gestorBDProgramaArchivo.obtenerArchivoXIdPrograma.programaArchivoRuta}">
                                                No Disponible
                                            </c:when>
                                            <c:otherwise>
                                                <a href="\UTNAcademia\Programa/${gestorBDProgramaArchivo.obtenerArchivoXIdPrograma.programaArchivoRuta}"
                                                   onclick="window.location.href = '/UTNAcademia/ServletProgramaContadorDescarga?programaId=${arrayListDTO.programaId}&opcion=1'"
                                                   download>
                                                    Descargar
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <button 
                                            type="button" 
                                            class="btn btn-warning"  
                                            onclick="window.location.href = '/UTNAcademia/Programa/Registrar.jsp?programaId=${arrayListDTO.programaId}'">
                                            Editar
                                        </button>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${arrayListDTO.programaBajaLogica}">
                                                <form action="/UTNAcademia/ServletProgramaHabilitarDesabilitar?programaId=${arrayListDTO.programaId}&habilitarDesabilitar=0&opcion=1" 
                                                      method="POST"
                                                      onsubmit="return validar();">               
                                                    <button type="submit" 
                                                            vlalue=""
                                                            class="btn btn-danger">
                                                        Deshabilitar
                                                    </button>
                                                </form>
                                            </c:when>    
                                            <c:otherwise>
                                                <form action="/UTNAcademia/ServletProgramaHabilitarDesabilitar?programaId=${arrayListDTO.programaId}&habilitarDesabilitar=1&opcion=1" 
                                                      method="POST">               
                                                    <button type="submit" 
                                                            vlalue="Habilitar"
                                                            class="btn btn-success">
                                                        Habilitar
                                                    </button>
                                                </form>
                                            </c:otherwise>
                                        </c:choose>
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
                    onclick="window.location.href = '/UTNAcademia/Programa/Registrar.jsp?programaId=0'">
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
