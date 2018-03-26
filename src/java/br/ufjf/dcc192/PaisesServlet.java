/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc192;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ice
 */
@WebServlet(name = "PaisesServlet", urlPatterns = {"/index.html"})
public class PaisesServlet extends HttpServlet {

    private Map<String, String> paises;
    private Map<String, List<String>> cores;

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
        this.paises = new HashMap<>();
        this.paises.put("Brasil", "Verde");
        this.paises.put("Estados Unidos", "Azul");
        this.paises.put("Russia", "Vermelho");
        this.paises.put("China", "Vermelho");
        this.paises.put("Japão", "Branco");

        this.cores = new HashMap<>();

        String agrupa = request.getParameter("agrupa");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Países</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet - " + request.getContextPath() + "</h1>");

            out.println("<DL>");
            this.paises.forEach((pais, cor) -> {
                out.println("<DT>" + pais + "</DT><DD>" + cor + "</DD>");
            });
            out.println("</DL>");
            out.println("<hr>");

            if ("cor".equals(agrupa)) {
                out.println("<DL>");
                this.paises.forEach((String pais, String cor) -> {
                    if (!this.cores.containsKey(cor)) {
                        ArrayList<String> aux = new ArrayList<>();
                        aux.add(pais);
                        this.cores.put(cor, aux);
                    } else {
                        this.cores.get(cor).add(pais);
                    }
                });
                this.cores.forEach((cor, paises) -> {
                    out.println("<DT>" + cor + "</DT>");
                    for (String pais : paises) {
                        out.println("<DD>" + pais + "</DD>");
                    }
                });
                out.println("</DL>");
            }

            out.println("<a href='" + request.getContextPath() + "?agrupa=cor'>Agrupar por cor</a>");

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
        processRequest(request, response);
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
