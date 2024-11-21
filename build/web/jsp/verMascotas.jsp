<%-- 
    Document   : verMascotas
    Created on : 13/11/2024, 08:07:18 PM
    Author     : CruzF
--%>
<%@page import="models.MascotasBean"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Mascotas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                max-width: 90%;
                margin-top: 40px;
            }
            h1 {
                color: #333;
                text-align: center;
                margin-bottom: 30px;
            }
            .table {
                background-color: #fff;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            th {
                background-color: #007bff;
                color: white;
                text-align: center;
            }
            td {
                text-align: center;
            }
            .button-container {
                display: flex;
                flex-direction: column;
                align-items: center;
                gap: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Lista de Mascotas</h1>

            <div class="row">
                <div class="col-md-12">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Especie</th>
                                <th>Peso (kg)</th>
                                <th>Fecha de Nacimiento</th>
                                <th>Sexo</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<MascotasBean> mascotas = (List<MascotasBean>) request.getAttribute("mascotas");
                                if (mascotas != null && !mascotas.isEmpty()) {
                                    for (MascotasBean mascota : mascotas) {
                            %>
                            <tr>
                                <td><%= mascota.getId()%></td>
                                <td><%= mascota.getNombre()%></td>
                                <td><%= mascota.getEspecie()%></td>
                                <td><%= mascota.getPeso()%></td>
                                <td><%= mascota.getF_nac()%></td>
                                <td><%= mascota.getSexo()%></td>
                                <td><div class="button-container" style="display: flow">
                                        <a type="button" class="btn btn-primary" href="mascota_servlet?id=<%= mascota.getId()%>">Ver en Bean</a>
                                        <a type="button" class="btn btn-secondary" href="mascotas_xml?id=<%= mascota.getId()%>">Ver en XML</a>
                                        <a href="mascotas_xml?id=<%= mascota.getId()%>" class="btn btn-success" download="<%= mascota.getId()%>mascota<%= mascota.getNombre()%>.xml">Descargar XML</a>
                                    </div>
                                </td>
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr>
                                <td colspan="6" class="text-center">No hay mascotas registradas</td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
