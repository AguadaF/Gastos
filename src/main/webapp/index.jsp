<%-- 
    Document   : menu
    Created on : 16/10/2018, 06:17:41
    Author     : Fernando Aguada  <fernandoaguada@protonmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <title>Gestión de Gastos</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="img/kompass.ico" type="image/x-icon" >
        <link rel="stylesheet" type="text/css" href="css/bootstrap-journal.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/menu.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Cuentas</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/Cuenta?action=LIST">Cuentas</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/Cuenta?action=LISTAR" target="_blank">Listado</a>
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Movimientos</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/movimientos.jsp">Movimientos</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/movimientosLista1.jsp">Reporte de Movimientos por Fecha</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/movimientosLista2.jsp">Reporte de Movimientos por Cuenta</a>                            
                        </div>
                    </li>
                    
                      <li class="nav-item">
                        <a class="nav-link"  href="<%=request.getContextPath()%>/Logout" role="button" aria-haspopup="true" aria-expanded="false">Salir</a>
                    </li>

                </ul>
            </div>  
        </nav>
        <br>

        <div class="container">                
        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>