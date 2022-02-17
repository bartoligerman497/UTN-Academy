<%-- 
    Document   : SeleccionarCantidadCursos
    Created on : 01-nov-2020, 13:37:12
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
                Registrar Inscripcion
            </h1>
            <h3 class="mt-4">
                <ins>
                    ¿A cuantos cursos desea registrarse el Alumno?
                </ins>
            </h3>
            <form method="POST" 
                  action="/UTNAcademia/Inscripcion/Registrar.jsp" 
                  onsubmit="return validarCantidad();">
                <input type="text" 
                       name="inscripcionId"
                       hidden=""
                       value="0"
                       class="form-control col-md-4"/>
                <br>
                <label for="cantidadCursos">
                    <b>Cantidad</b>
                </label>
                <input type="text" 
                       name="cantidadCursos" 
                       id="cantidadCursos" 
                       value="" 
                       class="form-control col-md-4" 
                       placeholder="Cantidad"
                       pattern="{2,10}" 
                       title="Debe contener entre 2 y 20 caracteres, solo numeros"/>
                <br>
                <button type="submit" 
                        class="btn btn-primary">
                    Siguiente
                </button>
            </form>
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
        function validarCantidad() {
            var cantidadCursos = document.getElementById('cantidadCursos');
            if (cantidadCursos.value === '' || cantidadCursos.value == 0) {
                alert('La cantidad no debe estar vacia');
                return false;
            }
        }
    </script>
</body>
</html>

