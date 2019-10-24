<%-- 
    Document   : movimienEdit
    Created on : 27/11/2018, 16:55:13
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/select2.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/select2-bootstrap.min.css">        

        <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
    </head>
    <body>

        <div class="container bg-sucess" style="padding-top: 30px; padding-botton: 30px;">
            <h2>Modifica Movimientos</h2>
            <form action="Movimien?action=UPDATE" method="post">

                <input type="hidden" name="id" value="${movimien.id}">                
                <input type="hidden" name="cuenta" id="cuenta" value="${movimien.cuenta.id}">                

                <div class="form-group row">
                    <label for="fecha"  class="col-sm-1 col-form-label">Fecha</label>
                    <div class="col-sm-2">
                        <input type="date" class="form-control" name="fecha" id="fecha" value="${movimien.fecha}">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="cuenta"  class="col-sm-1 col-form-label">Cuenta</label>
                    <div class="col-sm-3">
                        <select id="cbocuenta" class="form-control border" name="cbocuenta">
                        </select>
                    </div>
                </div>                    

                <div class="form-group row">
                    <label for="importe"  class="col-sm-1 col-form-label">Importe</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="importe" id="importe"  value="${movimien.importe}">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="conce"  class="col-sm-1 col-form-label">Concepto</label>                    
                    <div class="col-sm-3">                    
                        <input type="text" class="form-control" name="conce" id="conce" value="${movimien.concepto}">
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-primary"><i class="far fa-thumbs-up"></i>Confirmar</button>  
                    </div>
                </div>

                <div class="form-group row">
                    <a href="<%=request.getContextPath()%>/movimientos.jsp" id="cancel" name="cancel" class="btn btn-default">Volver Atras</a>
                </div>

            </form>
        </div>

        <jsp:include page="footer.jsp" />

        <script language="javascript" type="text/javascript">
            function Cuenta() {
                $('#cbocuenta').empty();
                $('#cbocuenta').select2({
                    theme:"bootstrap",
                    width:"100%"
                });
                $('#cbocuenta').append("<option>Cargando ...</option>");
                $.ajax({
                    type: "POST",
                    url: "CuentaBusca",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (data) {
                        $('#cbocuenta').empty();
                        $.each(data.aaData, function (i, data) {
                            $('#cbocuenta').append('<option value="' + data.id + '">' + data.nombre + '</option>');
                        });
                        $("#cbocuenta").val($('#cuenta').val());
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert("some error");
                    }
                });
            }

            $(document).ready(function () {
                Cuenta();
            });
        </script>       


    </body>
</html>
