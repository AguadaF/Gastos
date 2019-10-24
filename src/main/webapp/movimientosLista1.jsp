<%-- 
    Document   : movimientoslista1
    Created on : 17/01/2019, 18:40:42
    Author     : Fernando Aguada  <fernandoaguada@protonmail.com>
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <title>Listado de Movimientos</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Fernando Aguada">
        <link rel="icon" href="img/kompass.ico" type="image/x-icon" >
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-journal.min.css"> 
        <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dataTables.bootstrap4.min.css">

        <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dataTables.bootstrap4.min.js"></script>
    </head>
    <body>

        <div class="container bg-sucess" style="padding-top: 30px; padding-botton: 30px;">
            <h2>Reporte de Movimientos por Fecha</h2>
            <form action="Movimien?action=LISTADO1" method="post" target="_blank">

                <div class="form-group row">
                    <label for="defec"  class="col-sm-2 col-form-label">Desde Fecha</label>
                    <div class="col-sm-3">
                        <input type="date" class="form-control" name="defec" id="defec" value="">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="hafec"  class="col-sm-2 col-form-label">Hasta Fecha</label>
                    <div class="col-sm-3">
                        <input type="date" class="form-control" name="hafec" id="hafec" value="">
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

    </body>

</html>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        var date = new Date();

        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear();

        if (month < 10)
            month = "0" + month;
        if (day < 10)
            day = "0" + day;

        var today = year + "-" + month + "-" + day;
        $("#defec").attr("value", today);
        $("#hafec").attr("value", today);

    });
</script> 