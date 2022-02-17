<%-- 
    Document   : confirmacion
    Created on : 31-oct-2020, 22:11:55
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

<jsp:useBean id="programa" scope="session" class="Programa.Programa" />
<jsp:setProperty name="programa" property="*" />

<jsp:useBean id="gestorBDAlumno" scope="session" class="Alumno.GestorBDAlumno" />
<jsp:setProperty name="gestorBDAlumno" property="alumnoIdSeleccionado" param="programaAlumnoId" />

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
                Confirmar Programa
            </h1>
            <input type="text" 
                   name="cursoId" 
                   hidden=""
                   value="${curso.cursoId}" 
                   class="form-control col-md-4"/>
            <br>
            <b>Alumno</b><br>
            ${gestorBDAlumno.obtenerAlumnoXIdAlumno.alumnoNombreApellido}<br>
            <br>
            <b>Nombre</b><br>
            ${programa.programaNombre} <br>
            <br>
            <b>Fecha</b><br>
            ${programa.programaFecha} <br>
            <br>
            <form action="/UTNAcademia/ServletRegistrarPrograma" 
                  method="POST"
                  onsubmit="return validar();">               
                <button type="submit" 
                        vlalue="Agregar"
                        class="btn btn-success">
                    Registrar
                </button>
            </form>
            <br>
            <button type="button" 
                    class="btn btn-warning" 
                    onclick="window.location.href = '/UTNAcademia/Programa/Registrar.jsp?ProgramaId=${programa.programaId}'">
                Volver
            </button>
            <br>
            <br>
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