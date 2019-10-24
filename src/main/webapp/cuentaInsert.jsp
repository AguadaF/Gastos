<%-- 
    Document   : cuentaInsert
    Created on : 01/11/2018, 16:55:35
    Author     : Fernando Aguada  <fernandoaguada@protonmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <title>Gestión de Gastos</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Fernando Aguada">
        <link rel="icon" href="img/kompass.ico" type="image/x-icon" >
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-journal.min.css">        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">

        <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>

        <script>
            $().ready(function () {
                $("#cuenta").validate({
                    rules: {
                        descrip: {
                            required: true,
                            minlength: 5
                        },
                    },
                    messages: {
                        descrip: {
                            required: "Por favor ingrese una descripción",
                            minlength: "La descripcion debe tener al menos 5 caracteres"
                        },
                    }
                });
            });
        </script>         
    </head>
    <body>

        <div class="container bg-sucess" style="padding-top: 30px; padding-botton: 30px;">
            <h2>Carga Nuevas Cuentas</h2>

            <form action="Cuenta?action=INSERT" method="post" id="cuenta">

                <input type="hidden" id="action" name="action" value="${action}">

                <div class="form-group row">
                    <label for="id" class="col-sm-1 col-form-label">Id</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="id" value="${cuenta.id}"  readonly="">
                    </div>
                </div>               

                <div class="form-group row">
                    <label for="descrip" class="col-sm-1 col-form-label">Descripción</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="descrip" id="descrip">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="tipo" class="col-sm-1 col-form-label">Tipo</label>
                    <div class="col-sm-2">
                        <SELECT class="form-control" size=1 cols=2 NAME="tipo" id="tipo">
                            <OPTION value=1 selected="Ingreso"> Ingreso</option>
                            <OPTION value=2> Egreso</option>
                        </select>  
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-primary"><i class="far fa-thumbs-up"></i>Confirmar</button>  
                    </div>
                </div>

                <div class="form-group row">
                    <a href="<%=request.getContextPath()%>/Cuenta?action=LIST" id="cancel" name="cancel" class="btn btn-default">Volver Atras</a>
                </div>

            </form>
        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>