<%-- 
    Document   : verMascotasXML
    Created on : 20/11/2024, 11:37:19 AM
    Author     : CruzF
--%>

<%@page contentType="application/xml" pageEncoding="UTF-8"%>
<%@page import="models.MascotasBean"%>
<%@page import="configuration.ConnectionBD"%>
<%@page import="java.sql.*"%>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    MascotasBean mascota = null;

    try {
        // Establecer conexión a la base de datos
        ConnectionBD conn = new ConnectionBD();
        Connection connection = conn.getConnectionBD();

        // Consulta SQL para obtener los datos de la mascota por su ID
        String query = "SELECT * FROM Mascota WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            // Crear un objeto MascotasBean con los datos obtenidos
            mascota = new MascotasBean();
            mascota.setId(rs.getInt("id"));
            mascota.setNombre(rs.getString("nombre"));
            mascota.setEspecie(rs.getString("especie"));
            mascota.setPeso(rs.getDouble("peso"));
            mascota.setF_nac(rs.getDate("fecha_nacimiento"));

            // Obtener el sexo y asignarlo
            String sex = rs.getString("sexo");
            if (sex != null) {
                if (sex.equalsIgnoreCase("macho")) {
                    mascota.setSexo(MascotasBean.Sexo.MACHO);
                } else if (sex.equalsIgnoreCase("hembra")) {
                    mascota.setSexo(MascotasBean.Sexo.HEMBRA);
                }
            }
        }

        connection.close();
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    }

    // Si se encontró la mascota, generar el XML
    if (mascota != null) {
%>
<?xml version="1.0" encoding="UTF-8"?>
<Mascota>
    <ID><%= mascota.getId()%></ID>
    <Nombre><%= mascota.getNombre()%></Nombre>
    <Especie><%= mascota.getEspecie()%></Especie>
    <Peso><%= mascota.getPeso()%></Peso>
    <FechaNacimiento><%= mascota.getF_nac()%></FechaNacimiento>
    <Sexo><%= mascota.getSexo()%></Sexo>
</Mascota>
<%
} else {
%>
<?xml version="1.0" encoding="UTF-8"?>
<Error>No se encontró la mascota.</Error>
    <%
        }
    %>
