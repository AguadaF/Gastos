<%-- 
    Document   : errorcuenta
    Created on : 25/10/2018, 19:35:05
    Author     : Fernando Aguada  <fernandoaguada@protonmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title></title
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Fernando Aguada">
        <link rel="icon" href="img/kompass.ico" type="image/x-icon" >
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

        <style type="text/css">
            .bs-example{
                margin: 20px;
            }
        </style>        

        <script>
            function goBack() {
                window.history.back()
            }
        </script>
    </head>

</head>
<body>
    <form>
        <br/>
        <br/>
        <br/>

        <div class="container col-lg-7 mx-auto bs-example">
            <div class="alert-danger card mb-3" >
                <div class="card-header"><c:out value="${requestScope['javax.servlet.error.message']}" /></div>
                <div class="card-body">
                    <h4 class="card-title">Detalle del Error</h4>
                    <p class="card-text"><strong>Exception:</strong><c:out value="${requestScope['javax.servlet.error.exception']}" /></p>
                    <p class="card-text"><strong>Exception type:</strong><c:out value="${requestScope['javax.servlet.error.exception_type']}" /></p>
                    <p class="card-text"><strong>equest URI:</strong><c:out value="${requestScope['javax.servlet.error.request_uri']}" /></p>
                    <p class="card-text"><strong>Servlet name:</strong><c:out value="${requestScope['javax.servlet.error.servlet_name']}" /></p>
                    <p class="card-text"><strong>Status code:</strong> <c:out value="${requestScope['javax.servlet.error.status_code']}" /></p></p>
                </div>
            </div>
        </div>
        <div class="mx-auto col-sm-1">
            <button type="button" class="btn btn-primary" onclick="goBack()"><i class="fa fa-home"></i>Volver</button>
        </div>
       
    </form>
</body>
</html>
