<%-- 
    Document   : movimientos
    Created on : 03/11/2018, 12:38:34
    Author     : Fernando Aguada  <fernandoaguada@protonmail.com>
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <title>Gestión de Gastos</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Fernando Aguada">
        <link rel="icon" href="${pageContext.request.contextPath}/img/kompass.ico" type="image/x-icon" >
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-journal.min.css"> 
        <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dataTables.bootstrap4.min.css">

        <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dataTables.bootstrap4.min.js"></script>
        !<%--Para Ordenar Correctamente por Fecha el DataTables --%>
        <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script>
        <script src="//cdn.datatables.net/plug-ins/1.10.19/sorting/datetime-moment.js"></script>

    </head>
    <body>

        <div class="container" style="padding-top: 30px; padding-botton: 30px;">
            <h2>ABM de Movimientos</h2>
            <a class="btn btn-info" href="Movimien?action=INSERT" role="button">Nuevo Movimiento</a>
            <a class="btn btn-info float-right" href="<%=request.getContextPath()%>/index.jsp" role="button">Menu Principal</a>
           
            <div class="panel panel-default">

                <div class="panel-body">
                    <table id="tablamovimiento" class="table table-striped table-bordered" style="width:100%">
                        <thead>
                            <tr>
                                <th>Fecha</th>
                                <th>Cuenta</th>
                                <th>Importe</th>
                                <th>Concepto</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody id="movimien_data">                     
                        </tbody>
                    </table>       
                </div>

            </div>
        </div>

        <jsp:include page="footer.jsp" />

        <script language="javascript" type="text/javascript">
            $(document).ready(function () {
                $.fn.dataTable.moment('DD/MM/YYYY');
                var tabla = $('#tablamovimiento').DataTable({
                    //serverSide: true,    //server side only works well with type "POST" !!!
                    ajax: {
                        method: "POST",
                        url: "Movimien?action=LIST",
                        dataSrc: "datos"
                    },
                    language: {
                        "sProcessing": "Procesando...",
                        "sLengthMenu": "Mostrar _MENU_ registros",
                        "sZeroRecords": "No se encontraron resultados",
                        "sEmptyTable": "Ningún dato disponible en esta tabla",
                        "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                        "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
                        "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
                        "sInfoPostFix": "",
                        "sSearch": "Buscar:",
                        "sUrl": "",
                        "sInfoThousands": ",",
                        "sLoadingRecords": "Cargando...",
                        "oPaginate": {
                            "sFirst": "Primero",
                            "sLast": "Último",
                            "sNext": "Siguiente",
                            "sPrevious": "Anterior"
                        }
                    },
                    columns: [
                        {data: "fecha"},
                        {data: "nombre"},
                        {data: "importe"},
                        {data: "concepto"},
                        {data: "acciones"},
                    ],
                    order: [[0, 'desc']]
                });
            });

        </script>             
    </body>
</html>
