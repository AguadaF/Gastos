<%-- 
    Document   : cuentas
    Created on : 18/10/2018, 06:13:12
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
        <link rel="icon" href="img/kompass.ico" type="image/x-icon" >

        <link rel="stylesheet" type="text/css" href="css/bootstrap-journal.min.css">        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dataTables.bootstrap4.min.css">

        <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dataTables.bootstrap4.min.js"></script>

        <!-- https://datatables.net/examples/styling/bootstrap4 -->      
        <script language="javascript" type="text/javascript">
            $(document).ready(function () {
                $('#tmovimiento').DataTable({
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
                        },
                        "oAria": {
                            "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                            "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                        }
                    }
                });
            });
        </script>                

    </head>
    <body>

        <div class="container" style="padding-top: 30px; padding-botton: 30px;">
            <h2>ABM de Cuentas</h2>            

            <div class="panel panel-default">

                <div class="panel-body">
                    <a class="btn btn-info" href="Cuenta?action=INSERT" role="button">Nueva Cuenta</a>
                    <a class="btn btn-info float-right" href="<%=request.getContextPath()%>/index.jsp" role="button">Menu Principal</a>

                    <table id="tmovimiento" class="table table-bordered table-hover">                                   
                        <thead>
                            <tr>
                                <th class="left">Id</th>
                                <th>Descripcion</th>
                                <th>Tipo</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${cuentas}" var="cuenta">
                                <tr>
                                    <th class="left">${cuenta.id}</th>
                                    <th>${cuenta.nombre}</th>
                                    <th>
                                        <c:if test="${cuenta.tipo==1}">
                                            Ingreso
                                        </c:if>
                                        <c:if test="${cuenta.tipo==2}">
                                            Egreso
                                        </c:if>
                                    </th>

                                    <td>
                                        <a class="btn btn-info" href="Cuenta?action=EDIT&id=${cuenta.id}" role="button"><i class="fas fa-user-edit"></i></a>
                                        <a class="btn btn-danger" href="Cuenta?action=DELETE&id=${cuenta.id}" role="button" onclick="return confirm('Confirma Eliminar la Cuenta?')"><i class="fas fa-trash-alt"></i></a>
                                    </td>

                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>       
                </div>

            </div>
        </div>

        <jsp:include page="footer.jsp" />
       
    </body>
</html>
