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
    <center>
        <div class="container-fluid">
            <h1 class="mt-4">
                Login
            </h1>
        </div>
        <br>
        <form method="POST" 
              action="/UTNAcademia/ServletLogin">

            <c:if test="${datosIncorrectos}">
                <div class="alert alert-danger col-md-4" role="alert" >
                    Datos Incorrectos. Intente Nuevamente. <br>
                </div>
            </c:if>
            <label for="usuarioNombre">
                Usuario
            </label>   
            <input type="text" 
                   id="usuarioNombre" 
                   class="form-control col-md-4"  
                   name="usuarioNombre" 
                   placeholder="Usuario"
                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]){8,20}"
                   title="Debe contener al entre 8 y 20 caracteres, sólo números o letras"
                   />
            <br>
            <br>
            <label for="usuarioContrasenia">
                Contraseña
            </label>  
            <input type="password" 
                   id="usuarioContrasenia" 
                   class="form-control col-md-4" 
                   name="usuarioContrasenia"
                   placeholder="Contraseña"
                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]){8,20}"
                   title="Debe contener al menos un número, una letra mayúscula, una letra minúscula y más de 8 caracteres"
                   />
            <br>
            <button type="submit" 
                    class="btn btn-primary">
                Ingresar
            </button>
            <br>
            <br>
            <button type="button" 
                    class="btn btn-warning" 
                    onclick="window.location.href = '/UTNAcademia/InicioPublico.jsp'">
                Volver
            </button>
        </form>
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
    </script>
</body>
</html>