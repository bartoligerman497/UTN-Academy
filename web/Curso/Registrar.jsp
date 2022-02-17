<%-- 
    Document   : Registrar
    Created on : 24-oct-2020, 20:21:14
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

<jsp:useBean id="gestorBDCurso" scope="session" class="Curso.GestorBDCurso"/>
<jsp:setProperty name="gestorBDCurso" property="cursoIdSeleccionado" param="cursoId"/>

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
                Curso
            </h1>
            <form method="POST" 
                  action="/UTNAcademia/Curso/Confirmacion.jsp" 
                  onsubmit="return validar();">
                <input type="text" 
                       name="cursoId" 
                       hidden=""
                       value="${gestorBDCurso.cursoIdSeleccionado}"
                       class="form-control col-md-4"/>
                <br>
                <label for="cursoNombre">
                    <b>Nombre</b>
                </label>          
                <input type="text" 
                       name="cursoNombre" 
                       id="cursoNombre" 
                       value="${gestorBDCurso.obtenerCursoXIdCurso.cursoNombre}" 
                       class="form-control col-md-4" 
                       placeholder="Nombre"
                       pattern="{2,20}" 
                       title="Debe contener entre 2 y 20 caracteres, solo letras"/>
                <br>
                <label for="cursoDescripcion">
                    <b>Descripcion</b>
                </label>
                <textarea type="textarea" 
                          name="cursoDescripcion" 
                          id="cursoDescripcion" 
                          class="form-control col-md-4" 
                          placeholder="Descripcion"
                          pattern="{2,500}"
                          rows="10" cols="50"
                          title="Debe contener entre 2 y 500 caracteres, solo letras"
                          />${gestorBDCurso.obtenerCursoXIdCurso.cursoDescripcion}</textarea>
                <br>
                <label for="cursoPrecio">
                    <b>Precio</b>
                </label>
                <input type="text" 
                       name="cursoPrecio" 
                       id="cursoPrecio" 
                       value="${gestorBDCurso.obtenerCursoXIdCurso.cursoPrecio}" 
                       class="form-control col-md-4" 
                       placeholder="Precio"
                       pattern="{1,10}"
                       oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                       title="Debe contener entre 1 y 10 caracteres, solo números y hasta 1 decimal"/>
                <br>
                <label for="cursoTiempoMeses">
                    <b>Tiempo (en meses)</b>
                </label>
                <input type="text" 
                       name="cursoTiempoMeses" 
                       id="cursoTiempoMeses" 
                       value="${gestorBDCurso.obtenerCursoXIdCurso.cursoTiempoMeses}" 
                       class="form-control col-md-4" 
                       placeholder="Tiempo (en meses)"
                       pattern="{1,3}"
                       oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                       title="Debe contener entre 1 y 3 caracteres, solo números"/>
                <br>
                <label for="cursoCupo">
                    <b>Cupo</b>
                </label>
                <input type="text" 
                       name="cursoCupo" 
                       id="cursoCupo" 
                       value="${gestorBDCurso.obtenerCursoXIdCurso.cursoCupo}" 
                       class="form-control col-md-4" 
                       placeholder="Cupo"
                       pattern="{1,3}"
                       oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                       title="Debe contener entre 1 y 3 caracteres, solo números"/>
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
            var cursoNombre = document.getElementById('cursoNombre');
            if (cursoNombre.value === '') {
                alert('El Nombre no debe estar vacio');
                return false;
            }

            var cursoDescripcion = document.getElementById('cursoDescripcion');
            if (cursoDescripcion.value === '') {
                alert('La Descripción no debe estar vacia');
                return false;
            }

            var cursoPrecio = document.getElementById('cursoPrecio');
            if (cursoPrecio.value === '') {
                alert('El Precio no debe estar vacio');
                return false;
            }

            var cursoTiempoMeses = document.getElementById('cursoTiempoMeses');
            if (cursoTiempoMeses.value === '') {
                alert('El Tiempo no debe estar vacio');
                return false;
            }
            var cursoCupo = document.getElementById('cursoCupo');
            if (cursoCupo.value === '') {
                alert('El Cupo no debe estar vacio');
                return false;
            }
        }
    </script>
</body>
</html>