<%-- 
    Document   : verMascotasBean
    Created on : 13/11/2024, 08:35:30 PM
    Author     : CruzF
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.MascotasBean" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalles de la Mascota</title>
    
    <!-- Integración de Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 20px;
        }
        .container {
            background-color: #ffffff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
        }
        .detail-item {
            margin-bottom: 15px;
        }
        .detail-item strong {
            font-weight: bold;
        }
        .btn-back {
            display: block;
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Detalles de la Mascota</h1>
        
        <jsp:useBean id="mascotaBean" class="models.MascotasBean" scope="request" />

        <%
            if (mascotaBean != null) {
        %>
            <div class="detail-item">
                <p><strong>ID:</strong> <%= mascotaBean.getId() %></p>
            </div>
            <div class="detail-item">
                <p><strong>Nombre:</strong> <%= mascotaBean.getNombre() %></p>
            </div>
            <div class="detail-item">
                <p><strong>Especie:</strong> <%= mascotaBean.getEspecie() %></p>
            </div>
            <div class="detail-item">
                <p><strong>Peso:</strong> <%= mascotaBean.getPeso() %> kg</p>
            </div>
            <div class="detail-item">
                <p><strong>Fecha de Nacimiento:</strong> <%= mascotaBean.getF_nac() %></p>
            </div>
            <div class="detail-item">
                <p><strong>Sexo:</strong> <%= mascotaBean.getSexo() %></p>
            </div>
        <%
            } else {
        %>
            <p>No se encontró la información de la mascota.</p>
        <%
            }
        %>

        <div class="btn-back">
            <a href="mascota_servlet" class="btn btn-primary">Volver a la lista de mascotas</a>
        </div>
    </div>

    <!-- Script de Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
