<%-- 
    Document   : Registrar
    Created on : 01-nov-2020, 13:14:53
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

<c:set var = "cantidadCursos" scope = "session" value = "${param.cantidadCursos}"/>

<jsp:useBean id="inscripcion" scope="session" class="Inscripcion.Inscripcion"/>
<jsp:setProperty name="inscripcion" property="inscripcionId" param="inscripcionId"/>

<jsp:useBean id="gestorBDCurso" scope="session" class="Curso.GestorBDCurso"/>

<jsp:useBean id="gestorBDAlumno" scope="session" class="Alumno.GestorBDAlumno"/>

<jsp:useBean id="gestorBDInscripcion" scope="session" class="Inscripcion.GestorBDInscripcion"/>

<jsp:useBean id="inscripcionCurso" scope="session" class="InscripcionCurso.InscripcionCurso"/>

<jsp:useBean id="gestorBDInscripcionCurso" scope="session" class="InscripcionCurso.GestorBDInscripcionCurso"/>

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
                Registrar Inscripción
            </h1>
            <form method="GET" 
                  action="/UTNAcademia/ServletRegistrarInscripcion" 
                  onsubmit="return validarPrograma();">
                <input type="text" 
                       name="inscripcionId" 
                       hidden=""
                       value="${inscripcion.inscripcionId}"
                       class="form-control col-md-4"/>
                <br>
                <label for="inscripcionAlumnoId">
                    <b>Alumno</b>
                </label>     
                <br>
                <select name="inscripcionAlumnoId"
                        id="inscripcionAlumnoId"
                        class="form-control col-md-4">
                    <c:forEach  items="${gestorBDAlumno.cargarComboAlumnos}"
                                var="arrayListComboAlumnos">
                        <c:if test="${not empty gestorBDAlumno.cargarComboAlumnos}"> 
                            <option value="${arrayListComboAlumnos.alumnoId}">
                                ${arrayListComboAlumnos.alumnoNombreApellido}
                            </option>
                        </c:if>   
                    </c:forEach>
                </select>
                <br>
                <label for="">
                    <b>Curso/s</b>
                </label>     
                <br>
                <c:forEach var = "i" begin = "1" end = "${param.cantidadCursos}">
                    <select name="inscripcionCursoId${i}"
                            id="inscripcionCursoId${i}"
                            class="form-control col-md-4">
                        <c:forEach  items="${gestorBDCurso.cursoListado}"
                                    var="array">
                            <c:if test="${not empty gestorBDCurso.cursoListado}"> 
                                <option value="${array.cursoId}">
                                    ${array.cursoNombre} $${array.cursoPrecio}
                                </option>
                            </c:if>   
                        </c:forEach>
                    </select>
                    <br>
                </c:forEach>
                <label for="inscripcionFecha">
                    <b>Fecha</b>
                </label>
                <input type="text" 
                       name="inscripcionFecha" 
                       id="inscripcionFecha" 
                       value="" 
                       class="form-control col-md-4" 
                       placeholder="Fecha"
                       pattern="{2,10}" 
                       title="Debe contener entre 2 y 20 caracteres, solo numeros"/>
                <br>
                <label for="inscripcionDescuento">
                    <b>Descuento (en %)</b>
                </label>
                <input type="text" 
                       name="inscripcionDescuento" 
                       id="inscripcionDescuento" 
                       value="" 
                       class="form-control col-md-4" 
                       placeholder="Descuento"
                       pattern="{1,3}"
                       oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                       title="Debe contener entre 1 y 10 caracteres, solo números y hasta 1 decimal"/>
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
        function validarRegistrarArchivo() {
            var programaArchivoRuta = document.getElementById('programaArchivoRuta');
            if (document.getElementById("programaArchivoRuta").files.length == 0) {
                alert("Debe ingresar una Archivo");
                return false;
            }
            return true;
        }
        function validarEliminarArchivo() {
            if (confirm("Esta seguro?")) {
                return true;
            } else {
                return false;
            }
        }
        function validarPrograma() {
            var inscripcionAlumnoId = document.getElementById('inscripcionAlumnoId');
            if (inscripcionAlumnoId.value === '' || inscripcionAlumnoId.value === null) {
                alert('Debe Seleccionar un Alumno');
                return false;
            }
            var inscripcionCursoId1 = document.getElementById('inscripcionCursoId1');
            if (inscripcionCursoId1.value === '' || inscripcionCursoId1.value === null) {
                alert('Debe Seleccionar un Curso');
                return false;
            }
            var inscripcionFecha = document.getElementById('inscripcionFecha');
            if (inscripcionFecha.value === '') {
                alert('La Fecha no debe estar vacia');
                return false;
            }
            var inscripcionDescuento = document.getElementById('inscripcionDescuento');
            if (inscripcionDescuento.value === '') {
                alert('El descuento no debe estar vacio');
                return false;
            }
        }
    </script>
</body>
</html>

