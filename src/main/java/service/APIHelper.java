package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class APIHelper {

    private static final String API_KEY = "145e8f39714c40f2835a8623dcf7df3c";

    /**
     * Lấy tin tức chung về stock
     */
    public static String getNewsJSON() {
        return fetch("https://newsapi.org/v2/everything?q=stock&language=en&pageSize=20&sortBy=publishedAt&apiKey=" + API_KEY);
    }

    /**
     * Gộp nhiều symbol thành 1 query OR — chỉ tốn 1 request
     */
    public static String getNewsBySymbols(List<String> symbols) {
        if (symbols == null || symbols.isEmpty()) {
            return getNewsJSON();
        }
        try {
            int limit = Math.min(symbols.size(), 15);
            StringBuilder q = new StringBuilder();
            for (int i = 0; i < limit; i++) {
                if (i > 0) {
                    q.append(" OR ");
                }
                q.append(symbols.get(i));
            }
            String encoded = URLEncoder.encode(q.toString(), "UTF-8");
            String url = "https://newsapi.org/v2/everything?q=" + encoded
                    + "&language=en&sortBy=publishedAt&pageSize=30&apiKey=" + API_KEY;
            return fetch(url);
        } catch (Exception e) {
            e.printStackTrace();
            return getNewsJSON();
        }
    }

    /**
     * Lấy tin tức theo 1 symbol cụ thể
     */
    public static String getNewsBySymbol(String symbol) {
        if (symbol == null || symbol.trim().isEmpty()) {
            return getNewsJSON();
        }
        try {
            String encoded = URLEncoder.encode(symbol.trim(), "UTF-8");
            String url = "https://newsapi.org/v2/everything?q=" + encoded
                    + "&language=en&sortBy=publishedAt&pageSize=20&apiKey=" + API_KEY;
            return fetch(url);
        } catch (Exception e) {
            e.printStackTrace();
            return getNewsJSON();
        }
    }

    private static String fetch(String urlString) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection cn = (HttpURLConnection) url.openConnection();
            cn.setRequestMethod("GET");
            cn.setConnectTimeout(8000);
            cn.setReadTimeout(8000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(cn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
