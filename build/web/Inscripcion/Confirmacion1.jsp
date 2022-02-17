<%-- 
    Document   : Confirmacion
    Created on : 01-nov-2020, 15:55:34
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

<jsp:useBean id="gestorBDAlumno" scope="session" class="Alumno.GestorBDAlumno" />
<jsp:setProperty name="gestorBDAlumno" property="alumnoIdSeleccionado" value="${inscripcionAlumnoId}" />

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
                Ingresar Pago
            </h1>
            <input type="text" 
                   name="cursoId" 
                   hidden=""
                   value="${inscripcionId}" 
                   class="form-control col-md-4"/>
            <br>
            <b>Fecha de Inscripcion</b><br>
            ${inscripcionFecha}<br>
            <br>
            <b>Alumno Seleccionado</b><br>
            ${gestorBDAlumno.obtenerAlumnoXIdAlumno.alumnoNombreApellido}<br>
            <br>
            <b>Cursos Seleccionado/s</b><br>
            <br>
            <c:forEach items="${cursos}" var="curso">
                ${curso.cursoNombre} <br>
                $${curso.cursoPrecio} <br>
                <br>
            </c:forEach>
            Precio Inicial <br>
            $${inscripcionPrecioInicial}<br>
            <br>
            <b>Descuento</b><br>
            $${inscripcionDescuento}<br>
            <br>
            <b>Precio Final</b><br>
            $${inscripcionPrecioFinal} <br>
            <br>
            <label for="inscripcionPagoCliente">
                <b>Pago Cliente</b>
            </label>
            <form action="/UTNAcademia/Inscripcion/Confirmacion2.jsp" 
                  method="POST"
                  onsubmit="return validar();">  
                <input type="text" 
                       name="inscripcionPagoCliente" 
                       id="inscripcionPagoCliente" 
                       value="" 
                       class="form-control col-md-4" 
                       placeholder="Pago Cliente"
                       pattern="{1,3}"
                       oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                       title="Debe contener entre 1 y 10 caracteres, solo números y hasta 1 decimal"/>
                <br>
                <button type="submit" 
                        vlalue="Agregar"
                        class="btn btn-primary">
                    Continuar
                </button>
            </form>
            <br>
            <button type="button" 
                    class="btn btn-warning" 
                    onclick="window.location.href = '/UTNAcademia/Inscripcion/Registrar.jsp?cantidadCursos=${cantidadCursos}'">
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
            var inscripcionPagoCliente = document.getElementById('inscripcionPagoCliente');
            if (inscripcionPagoCliente.value === '') {
                alert('Debe ingresar el Pago del Cliente');
                return false;
            }
            
            if (inscripcionPagoCliente.value < ${inscripcionPrecioFinal}) {
                alert('El pago del cliente debe ser superior al precio final');
                return false;
            }
        }
    </script>
</body>
</html>