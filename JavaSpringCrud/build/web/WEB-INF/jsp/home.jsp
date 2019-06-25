<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
        <title>Ejemplo de JDBCTemplates</title>
    </head>
    <body>
        <div class="container">
            <h1>Ejemplo de JDBC</h1>
            <div class="row">                
                <p>
                    <a href="add.htm" class="btn btn-success"> Agregar</a>
                </p>
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>E-Mail</th>
                            <th>Teléfono</th>
                            <th>Acciones</th>
                        </tr>                    
                    </thead>
                    <tbody>
                        <c:forEach items="${datos}" var="dato">
                            <tr>
                                <td><c:out value="${dato.id}" /></td>
                                <td><c:out value="${dato.nombre}" /></td>
                                <td><c:out value="${dato.correo}" /></td>
                                <td><c:out value="${dato.telefono}" /></td>
                                <td>
                                    <a href="<c:url value="edit.htm?id=${dato.id}"/>"><span class="glyphicon glyphicon-pencil" aria-hidden="true">Editar</span></a>
                                    <a href="<c:url value="delete.htm?id=${dato.id}"/>"><span class="glyphicon glyphicon-trash" aria-hidden="true">Eliminar</span></a>
    
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
            </div>
        </div>
    </body>
</html>
