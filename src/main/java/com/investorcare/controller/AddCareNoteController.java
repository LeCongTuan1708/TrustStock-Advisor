/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.investorcare.controller;

import com.investorcare.dao.CareNoteDAO;
import com.investorcare.model.CareNote;
import com.investorcare.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author quyt2
 */
public class AddCareNoteController extends HttpServlet {

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
        // Hỗ trợ gõ tiếng Việt có dấu cho Title và Content
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        String url = "MainController?action=care-note-list";

        try {
            User loginUser = (User) session.getAttribute("LOGIN_USER");

            if (loginUser != null && "User".equalsIgnoreCase(loginUser.getRole())) {

                String assetIdStr = request.getParameter("assetId");
                String title = request.getParameter("title");
                String content = request.getParameter("content");

                if (assetIdStr != null && title != null && content != null && !title.trim().isEmpty()) {

                    int assetId = Integer.parseInt(assetIdStr);

                    CareNote careNote = new CareNote();
                    careNote.setUserId(loginUser.getUserId()); 
                    careNote.setAssetId(assetId);
                    careNote.setTitle(title);
                    careNote.setContent(content);

                    CareNoteDAO dao = new CareNoteDAO();
                    boolean check = dao.insert(careNote);

                    if (check) {
                        response.sendRedirect(url);
                        return;
                    }
                }
            } else {
                url = "login.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(url);
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
