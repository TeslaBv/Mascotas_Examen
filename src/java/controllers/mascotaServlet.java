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
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "mascotaServlet", urlPatterns = {"/mascota_servlet"})
public class mascotaServlet extends HttpServlet {

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
            out.println("<title>Servlet mascotaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mascotaServlet at " + request.getContextPath() + "</h1>");
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

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        ConnectionBD conexion = new ConnectionBD();
        String idParam = request.getParameter("id");

        try {
            conn = conexion.getConnectionBD();

            if (idParam != null) { // Si se recibe un ID
                int id = Integer.parseInt(idParam);

                String sql = "SELECT * FROM mascotas WHERE id_mascota = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String especie = rs.getString("especie");
                    double peso = rs.getDouble("peso");
                    java.sql.Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                    String sex = rs.getString("sexo");

                    // Convertir la cadena de sexo a un valor de enum Sexo
                    MascotasBean.Sexo sexo = MascotasBean.Sexo.valueOf(sex.toUpperCase());

                    // Crear una instancia de MascotaBean con los datos obtenidos
                    MascotasBean mascota = new MascotasBean(id, nombre, especie, peso, fechaNacimiento, sexo);
                    System.out.println("---------- "+mascota.getNombre());
                    // Establecer el objeto mascota en el request
                    request.setAttribute("mascotaBean", mascota);
                }

                rs.close();
                ps.close();

                // Redirigir a verMascotasBean.jsp
                request.getRequestDispatcher("jsp/verMascotasBean.jsp").forward(request, response);

            } else { // Si no se recibe un ID, mostrar la lista completa
                String sql = "SELECT * FROM mascotas";
                statement = conn.createStatement();
                rs = statement.executeQuery(sql);

                List<MascotasBean> mascotas = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt("id_mascota");
                    String nombre = rs.getString("nombre");
                    String especie = rs.getString("especie");
                    double peso = rs.getDouble("peso");
                    java.sql.Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                    String sex = rs.getString("sexo");

                    MascotasBean.Sexo sexo = MascotasBean.Sexo.valueOf(sex.toUpperCase());
                    MascotasBean mascota = new MascotasBean(id, nombre, especie, peso, fechaNacimiento, sexo);
                    mascotas.add(mascota);
                }

                rs.close();
                statement.close();

                request.setAttribute("mascotas", mascotas);

                // Redirigir a la lista de mascotas
                request.getRequestDispatcher("jsp/verMascotas.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        System.out.println("Entro al doPost");
        ConnectionBD conexion = new ConnectionBD();

        // Obtener los parámetros del formulario 
        String nombre = request.getParameter("nombre");
        String especie = request.getParameter("especie");
        String wPeso = request.getParameter("peso");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String sexo = request.getParameter("sexo");

        java.sql.Date fNac = java.sql.Date.valueOf(fechaNacimiento);
        Double peso = Double.parseDouble(wPeso);

        try {
            String sql = "INSERT INTO mascotas (nombre, especie, peso, fecha_nacimiento,sexo) VALUES (?, ?, ?, ?, ?)";
            conn = conexion.getConnectionBD();
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, especie);
            ps.setDouble(3, peso);
            ps.setDate(4, fNac);
            ps.setString(5, sexo);

            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Libro insertado correctamente");
                response.sendRedirect(request.getContextPath() + "/index.html");
            } else {

            }
        } catch (Exception e) {
        }

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
