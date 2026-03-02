/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.investorcare.controller;

import com.investorcare.dao.WatchListItemDAO;
import com.investorcare.model.User;
import com.investorcare.model.WatchListItem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author quyt2
 */
public class WatchListItemController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        String url = "MainController?action=watch-list";
        
        try {
            WatchListItemDAO dao = new WatchListItemDAO();  
            User loginUser = (User) session.getAttribute("LOGIN_USER");
            String selectedIdStr = request.getParameter("selectedId");
            
            if (loginUser != null && "User".equalsIgnoreCase(loginUser.getRole())) {
                if (selectedIdStr != null && !selectedIdStr.isEmpty()) {
                    
                    int watchListId = Integer.parseInt(selectedIdStr);

                    WatchListItemDAO itemDao = new WatchListItemDAO();

                    List<WatchListItem> listItems = itemDao.getItemsByWatchListId(watchListId);

                    request.setAttribute("LIST_WATCHLIST_ITEM", listItems);

                    request.setAttribute("CURRENT_WATCHLIST_ID", watchListId);

                    url = "watchListItem.jsp";
                }
            } else {
                url = "login.jsp";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);

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
