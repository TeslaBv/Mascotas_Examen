/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import configuration.ConnectionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.MascotasBean;

/**
 *
 * @author CruzF
 */
@WebServlet(name = "mascotas_xml", urlPatterns = {"/mascotas_xml"})
public class mascotas_xml extends HttpServlet {

    private static final long serialVersionUID = 1L;

    Connection conn;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet mascotas_xml</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mascotas_xml at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");

        try {
            // Conexión a la base de datos
            ConnectionBD connectionBD = new ConnectionBD();
            Connection connection = connectionBD.getConnectionBD();

            int id = Integer.parseInt(idStr);
            MascotasBean mascota = null;

            // Consulta SQL para obtener los detalles de la mascota por ID
            String sql = "SELECT * FROM mascotas WHERE id_mascota = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Crear el objeto MascotasBean con los datos obtenidos
                mascota = new MascotasBean();
                mascota.setId(rs.getInt("id_mascota"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setF_nac(rs.getDate("fecha_nacimiento"));

                // Obtener el valor del sexo de la base de datos
                String sex = rs.getString("sexo");

                // Verificar si el sexo es "macho" o "hembra" y asignar el valor adecuado
                if (sex != null) {
                    if (sex.equalsIgnoreCase("macho")) {
                        mascota.setSexo(MascotasBean.Sexo.MACHO);
                    } else if (sex.equalsIgnoreCase("hembra")) {
                        mascota.setSexo(MascotasBean.Sexo.HEMBRA);
                    } 
                }
            }

            rs.close();
            ps.close();
            connection.close();

            // Verificar si se encontró la mascota
            if (mascota != null) {
                response.setContentType("application/xml");
                PrintWriter out = response.getWriter();

                // Generar el XML con los detalles de la mascota
                out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                out.println("<mascota>");
                out.println("<id> " + mascota.getId() + " </id>");
                out.println("<nombre> " + mascota.getNombre() + " </nombre>");
                out.println("<especie> " + mascota.getEspecie() + " </especie>");
                out.println("<peso> " + mascota.getPeso() + " Kilogramos </peso>");
                out.println("<fecha_nacimiento> " + mascota.getF_nac() + " </fecha_nacimiento>");
                out.println("<sexo> " + mascota.getSexo() + " </sexo>");
                out.println("</mascota>");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Mascota no encontrada");
            }
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar XML.");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
