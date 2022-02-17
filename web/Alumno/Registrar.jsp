<%-- 
    Document   : Registrar
    Created on : 24-oct-2020, 20:18:15
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

<jsp:useBean id="gestorBDAlumno" scope="session" class="Alumno.GestorBDAlumno"/>
<jsp:setProperty name="gestorBDAlumno" property="alumnoIdSeleccionado" param="alumnoId"/>

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
                Alumno
            </h1>
            <form method="POST" 
                  action="/UTNAcademia/Alumno/Confirmacion.jsp" 
                  onsubmit="return validar();">
                <input type="text" 
                       name="alumnoId" 
                       hidden=""
                       value="${gestorBDAlumno.alumnoIdSeleccionado}"
                       class="form-control col-md-4"/>
                <br>
                <label for="alumnoNombreApellido">
                    <b>Nombre y Apellido</b>
                </label>
                <input type="text" 
                       name="alumnoNombreApellido" 
                       id="alumnoNombreApellido" 
                       value="${gestorBDAlumno.obtenerAlumnoXIdAlumno.alumnoNombreApellido}" 
                       class="form-control col-md-4" 
                       placeholder="Nombre y Apellido"
                       pattern="{2,20}" 
                       title="Debe contener entre 2 y 20 caracteres, solo letras"/>
                <br>
                <label for="alumnoEdad">
                    <b>Edad</b>
                </label>
                <input type="text" 
                       name="alumnoEdad" 
                       id="alumnoEdad" 
                       value="${gestorBDAlumno.obtenerAlumnoXIdAlumno.alumnoEdad}" 
                       class="form-control col-md-4" 
                       placeholder="Edad"
                       pattern="{1,3}"
                       oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                       title="Debe contener entre 1 y 3 números"/>
                <br>
                <label for="alumnoSexo">
                    <b>Sexo</b>
                </label>
                <select name="alumnoSexo"
                        id="alumnoSexo"
                        class="form-control col-md-4">
                    <option value="True"
                            <c:if test="${gestorBDAlumno.alumnoIdSeleccionado == 0}"> 
                                selected
                            </c:if>   
                            <c:if test="${gestorBDAlumno.obtenerAlumnoXIdAlumno.alumnoSexo}"> 
                                selected
                            </c:if>           
                            >
                        Hombre
                    </option>
                    <option value="False" 
                            <c:choose>
                                <c:when test="${gestorBDAlumno.alumnoIdSeleccionado == 0}">
                                </c:when>
                                <c:when test="${not gestorBDAlumno.obtenerAlumnoXIdAlumno.alumnoSexo}">
                                    selected
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                            >
                        Mujer
                    </option>
                </select>
                <br>
                <button type="submit" 
                        class="btn btn-success">
                    Registrar
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
        function validar() {
            var alumnoNombreApellido = document.getElementById('alumnoNombreApellido');
            if (alumnoNombreApellido.value === '') {
                alert('El Nombre y Apellido no debe estar vacio');
                return false;
            }

            var alumnoEdad = document.getElementById('alumnoEdad');
            if (alumnoEdad.value === '') {
                alert('La Edad no debe estar vacia');
                return false;
            }
        }
    </script>
</body>
</html>