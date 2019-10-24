
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Fernando Aguada">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/kompass.ico" type="image/x-icon" >                
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-journal.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" >    
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script>
            $().ready(function () {
                $("#login").validate({
                    rules: {
                        txtUsuario: "required",
                        txtPassword: "required"
                    },
                    messages: {
                        txtUsuario: "Por favor Ingrese el Nombre de Usuario !",
                        txtPassword: "Por Favor Ingrese la Contraseña !"
                    }
                });
            });
        </script>         
    </head>
    <body>
    <center><h1>Recurso Protegido</h1></center>
    <div class="jumbotron boxlogin">
    <center><font color="red">El Usuario y/o Password son Incorrectos !!!</font></center>
        
        <form name="form" id="form" action="j_security_check" method="post">
            <div class="form-group">
                <input type="text" name="j_username" placeholder="Nombre de Usuario" class="form-control">
            </div>  
            <div class="form-group">
                <input type="password" name="j_password" placeholder="Contraseña" class="form-control">
            </div>             

            <button class="btn btn-lg btn-primary btn-block"  type="submit"><i class="fa fa-home"></i> Ingresar</button>  

        </form>        
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
