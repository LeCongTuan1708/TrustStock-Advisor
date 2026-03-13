package com.investorcare.controller;

import com.investorcare.dao.AssetDAO;
import com.investorcare.model.Asset;
import com.investorcare.model.News;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.APIHelper;

@WebServlet(name = "StockNewsController", urlPatterns = {"/StockNewsController"})
public class StockNewsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<News> list = new ArrayList<>();
        String filterSymbol = request.getParameter("symbol"); // null nếu xem all

        try {
            String json;

            if (filterSymbol != null && !filterSymbol.trim().isEmpty()) {
                // Xem tin tức theo 1 symbol cụ thể
                json = APIHelper.getNewsBySymbol(filterSymbol.trim().toUpperCase());
            } else {
                // Xem tất cả — lấy symbols từ DB
                AssetDAO assetDAO = new AssetDAO();
                List<Asset> assets = assetDAO.getAllAssets();
                List<String> symbols = assets.stream()
                        .map(Asset::getSymbol)
                        .collect(Collectors.toList());
                json = APIHelper.getNewsBySymbols(symbols);
            }

            if (json == null || json.isEmpty()) throw new Exception("Empty API response");

            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
            JsonArray articles = obj.getAsJsonArray("articles");

            for (int i = 0; i < articles.size(); i++) {
                JsonObject article = articles.get(i).getAsJsonObject();
                String title = getString(article, "title");
                String desc  = getString(article, "description");
                String url   = getString(article, "url");
                String image = getString(article, "urlToImage");

                if (title.equals("[Removed]") || url.isEmpty() || url.equals("[Removed]")) continue;
                list.add(new News(title, desc, url, image));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("newsList", list);
        request.setAttribute("filterSymbol", filterSymbol); // để news.jsp biết đang filter gì
        request.getRequestDispatcher("news.jsp").forward(request, response);
    }

    private String getString(JsonObject obj, String key) {
        if (!obj.has(key) || obj.get(key).isJsonNull()) return "";
        JsonElement el = obj.get(key);
        return el.isJsonPrimitive() ? el.getAsString() : "";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { processRequest(request, response); }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { processRequest(request, response); }

    @Override
    public String getServletInfo() { return "Stock News Controller"; }
}