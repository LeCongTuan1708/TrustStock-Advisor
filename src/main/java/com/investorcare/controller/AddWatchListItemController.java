/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.investorcare.controller;

import com.investorcare.model.WatchListItem;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author quyt2
 */
public class AddWatchListItemController extends HttpServlet {

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

        String watchListIdStr = request.getParameter("watchListId");
        // Xui rủi mà lỗi thì đá về danh sách thư mục gốc
        String url = "MainController?action=watch-list";

        try {
            String assetIdStr = request.getParameter("assetId");

            if (watchListIdStr != null && assetIdStr != null) {
                int watchListId = Integer.parseInt(watchListIdStr);
                int assetId = Integer.parseInt(assetIdStr);


                WatchListItem item = new WatchListItem();
                item.setWatchListId(watchListId);
                item.setAssetId(assetId);

                // Gọi DAO Insert
                com.investorcare.dao.WatchListItemDAO dao = new com.investorcare.dao.WatchListItemDAO();
                boolean check = dao.insert(item);

                if (check) {
                    // Thành công thì dùng Redirect đá về trang Chi Tiết Thư Mục cũ để nó load lại data
                    url = "MainController?action=watchlist-item&selectedId=" + watchListId;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Bẻ lái (PRG Pattern chống F5 nè)
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
