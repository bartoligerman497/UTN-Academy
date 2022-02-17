<%-- 
    Document   : Registrar
    Created on : 24-oct-2020, 20:21:38
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

<jsp:useBean id="programa" scope="session" class="Programa.Programa"/>
<jsp:setProperty name="programa" property="*" />

<jsp:useBean id="GestorBDProgramaArchivo" scope="session" class="ProgramaArchivo.GestorBDProgramaArchivo"/> 
<jsp:setProperty name="GestorBDProgramaArchivo" property="programaIdSeleccionado" param="programaId" />

<jsp:useBean id="gestorBDPrograma" scope="session" class="Programa.GestorBDPrograma" />
<jsp:setProperty name="gestorBDPrograma" property="programaIdSeleccionado" param="programaId" />

<jsp:useBean id="gestorBDAlumno" scope="session" class="Alumno.GestorBDAlumno" />

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
                Programa
            </h1>
            <c:if test="${error}">
                <div class="alert alert-danger col-md-4" role="alert" >
                    Datos Incorrectos. Intente Nuevamente. <br>
                </div>
            </c:if>
            <form method="POST" 
                  action="/UTNAcademia/Programa/Confirmacion.jsp" 
                  onsubmit="return validarPrograma();">
                <input type="text" 
                       name="programaId" 
                       hidden=""
                       value="${programa.programaId}"
                       class="form-control col-md-4"/>
                <br>
                <label for="alumnoId">
                    <b>Alumno</b>
                </label>     
                <br>
                <select name="programaAlumnoId"
                        id="programaAlumnoId"
                        class="form-control col-md-4"
                        <c:if test="${empty gestorBDAlumno.cargarComboAlumnos}">
                            disabled
                        </c:if>   
                        >
                    <c:forEach  items="${gestorBDAlumno.cargarComboAlumnos}"
                                var="arrayListComboAlumnos">
                        <c:if test="${not empty gestorBDAlumno.cargarComboAlumnos}"> 
                            <option value="${arrayListComboAlumnos.alumnoId}"
                                    <c:if test="${gestorBDPrograma.obtenerProgramaXIdPrograma.programaAlumnoId ==  arrayListComboAlumnos.alumnoId}"> 
                                        selected
                                    </c:if>   
                                    >
                                ${arrayListComboAlumnos.alumnoNombreApellido}
                            </option>
                        </c:if>   
                    </c:forEach>
                </select>
                <br>
                <label for="programaNombre">
                    <b>Nombre</b>
                </label>          
                <input type="text" 
                       name="programaNombre" 
                       id="programaNombre" 
                       value="${gestorBDPrograma.obtenerProgramaXIdPrograma.programaNombre}" 
                       class="form-control col-md-4" 
                       placeholder="Nombre"
                       pattern="{2,20}" 
                       title="Debe contener entre 2 y 20 caracteres, solo letras"/>
                <br>
                <label for="programaFecha">
                    <b>Fecha</b>
                </label>
                <input type="text" 
                       name="programaFecha" 
                       id="programaFecha" 
                       value="${gestorBDPrograma.obtenerProgramaXIdPrograma.programaFecha}" 
                       class="form-control col-md-4" 
                       placeholder="Fecha"
                       pattern="{2,10}" 
                       title="Debe contener entre 2 y 20 caracteres, solo numeros"/>
                <br>
                <button type="submit" 
                        class="btn btn-success">
                    Registrar
                </button>
            </form>
            <br>
            <br>
            <c:choose>
                <c:when test="${empty GestorBDProgramaArchivo.obtenerArchivoXIdPrograma && programa.programaId != 0}">
                    <div class="alert alert-danger col-md-6" 
                         role="alert">
                        El programa no tiene un archivo 
                    </div>    
                    <form method="POST" 
                          action="/UTNAcademia/ServletRegistrarProgramaArchivo" 
                          enctype="multipart/form-data" 
                          onsubmit="return validarRegistrarArchivo();">
                        <input type="text" 
                               name="programaId" 
                               hidden="" 
                               value="${programa.programaId}">
                        <input  type="file" 
                                id="programaArchivoRuta"
                                name="programaArchivoRuta"
                                value="${GestorBDProgramaArchivo.obtenerArchivoXIdPrograma.programaArchivoRuta}">
                        <br>
                        <br>
                        <button type="submit" 
                                class="btn btn-primary">
                            Cargar Archivo
                        </button>
                        <br>
                        <br>
                    </form>
                </c:when>
                <c:when test="${not empty GestorBDProgramaArchivo.obtenerArchivoXIdPrograma && programa.programaId != 0}">
                    <div class="alert alert-success col-md-6" 
                         role="alert">
                        El programa cuenta con un archivo
                    </div>  
                    <form method="POST" 
                          action="/UTNAcademia/ServletEliminarProgramaArchivo"
                          onsubmit="return validarEliminarArchivo();">
                        <input type="text" 
                               name="programaArchivoId" 
                               hidden=""
                               value="${GestorBDProgramaArchivo.obtenerArchivoXIdPrograma.programaArchivoId}">
                        <input  type="text" 
                                name="programaArchivoRuta"
                                hidden=""
                                value="${GestorBDProgramaArchivo.obtenerArchivoXIdPrograma.programaArchivoRuta}">
                        <button type="submit" 
                                class="btn btn-danger">
                            Eliminar Archivo
                        </button>
                        <br>
                        <br>
                    </form>
                </c:when>
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
        function validarRegistrarArchivo() {
            var programaArchivoRuta = document.getElementById('programaArchivoRuta');
            if (document.getElementById("programaArchivoRuta").files.length == 0) {
                alert("Debe Ingresar una Archivo");
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
            var programaAlumnoId = document.getElementById('programaAlumnoId');
            if (programaAlumnoId.value === '' || programaAlumnoId.value === null) {
                alert('Debe Seleccionar un Alumno');
                return false;
            }

            var programaNombre = document.getElementById('programaNombre');
            if (programaNombre.value === '') {
                alert('El Nombre no debe estar vacio');
                return false;
            }

            var programaFecha = document.getElementById('programaFecha');
            if (programaFecha.value === '') {
                alert('La Fecha no debe estar vacia');
                return false;
            }
        }
    </script>
</body>
</html>

