package com.investorcare.controller;

import com.investorcare.dao.CareNoteDAO;
import com.investorcare.dao.AssetDAO;
import com.investorcare.model.CareNote;
import com.investorcare.model.Asset;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ViewCareNoteController", urlPatterns = {"/ViewCareNoteController"})
public class ViewCareNoteController extends HttpServlet {

    // Nơi chuyển hướng cuối cùng chính là đây!
    private static final String SUCCESS = "careNoteDetail.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        
        try {
            // 1. Lấy noteId từ URL
            String noteIdStr = request.getParameter("noteId");
            int noteId = Integer.parseInt(noteIdStr);
            
            // 2. Gọi DAO để lấy dữ liệu CareNote từ Database
            CareNoteDAO noteDao = new CareNoteDAO();
            CareNote note = noteDao.getCareNoteById(noteId); // Thay bằng hàm tương ứng của bạn
            
            // 3. Gọi DAO lấy danh sách Asset để hiển thị tên Ticker (nếu cần)
            AssetDAO assetDao = new AssetDAO();
            List<Asset> listAsset = assetDao.getAllAssets(); // Thay bằng hàm tương ứng của bạn
            
            if (note != null) {
                // 4. Đẩy dữ liệu lên request để JSP hứng lấy
                request.setAttribute("CARE_NOTE", note);
                request.setAttribute("LIST_ASSET", listAsset);
                
                // 5. CHUYỂN HƯỚNG SANG TRANG DETAIL Ở ĐÂY 👇
                url = SUCCESS; 
            } else {
                request.setAttribute("ERROR", "Không tìm thấy nhật ký này!");
            }
            
        } catch (Exception e) {
            log("Error at ViewCareNoteController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

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
}