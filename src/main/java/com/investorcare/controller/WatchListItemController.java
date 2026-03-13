/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.investorcare.controller;

import com.investorcare.dao.WatchListItemDAO;
import com.investorcare.dao.PriceBarDAO;
import com.investorcare.model.User;
import com.investorcare.model.WatchListItem;
import com.investorcare.model.PriceBar;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        String url = "MainController?action=watch-list";
        try {
            User loginUser = (User) session.getAttribute("LOGIN_USER");
            String selectedIdStr = request.getParameter("selectedId");
            
            if (loginUser != null && "User".equalsIgnoreCase(loginUser.getRole())) {
                if (selectedIdStr != null && !selectedIdStr.isEmpty()) {
                    
                    int watchListId = Integer.parseInt(selectedIdStr);
                    com.investorcare.dao.WatchListDAO wlDao = new com.investorcare.dao.WatchListDAO();
                    String watchListName = "Danh mục #" + watchListId;
                    watchListName = wlDao.getWatchListNameById(watchListId);
                    WatchListItemDAO itemDao = new WatchListItemDAO();
                    List<WatchListItem> listItems = itemDao.getItemsByWatchListId(watchListId);
                    request.setAttribute("LIST_WATCHLIST_ITEM", listItems);
                    request.setAttribute("CURRENT_WATCHLIST_ID", watchListId);
                    request.setAttribute("WATCHLIST_NAME", watchListName);
                    // ==============================================================
                    // BẮT ĐẦU MÓC GIÁ TỪ DATABASE LÊN 
                    // ==============================================================
                    Map<Integer, PriceBar> latestPrices = new HashMap<>();
                    PriceBarDAO priceBarDAO = new PriceBarDAO();

                    if (listItems != null) {
                        for (WatchListItem item : listItems) {
                            if (item.getAsset() != null) {
                                int assetId = item.getAsset().getAssetId();
                                try {
                                    // Lấy dòng giá mới nhất của từng mã cổ phiếu
                                    PriceBar latest = priceBarDAO.getLatest(assetId);
                                    if (latest != null) {
                                        latestPrices.put(assetId, latest);
                                    }
                                } catch (Exception ex) {
                                    System.out.println(">>> Lỗi lấy giá cho Asset " + assetId + ": " + ex.getMessage());
                                }
                            }
                        }
                    }
                    
                    // Ném nguyên cái Map giá trị này sang JSP với tên "LATEST_PRICES"
                    request.setAttribute("LATEST_PRICES", latestPrices);
                    // ==================================================== ==========
                    // KẾT THÚC MÓC GIÁ
                    // ==============================================================

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}