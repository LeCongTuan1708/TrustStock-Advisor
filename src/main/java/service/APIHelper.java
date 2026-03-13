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
     * Lấy tin tức chung về stock (fallback, không filter symbol)
     */
    public static String getNewsJSON() {
        String urlString = "https://newsapi.org/v2/everything?q=stock&language=en&pageSize=20&apiKey=" + API_KEY;
        return fetch(urlString);
    }

    /**
     * Lấy tin tức liên quan đến danh sách symbols từ DB. Gộp tất cả thành 1
     * query: q=AAPL OR TSLA OR NVDA ... Chỉ tốn 1 request dù có bao nhiêu
     * symbol.
     *
     * @param symbols danh sách symbol từ AssetDAO.getAllAssets()
     */
    public static String getNewsBySymbols(List<String> symbols) {
        if (symbols == null || symbols.isEmpty()) {
            return getNewsJSON();
        }

        try {
            // Giới hạn 15 symbol để query không quá dài
            int limit = Math.min(symbols.size(), 15);
            StringBuilder q = new StringBuilder();
            for (int i = 0; i < limit; i++) {
                if (i > 0) {
                    q.append(" OR ");
                }
                q.append(symbols.get(i));
            }

            String encoded = URLEncoder.encode(q.toString(), "UTF-8");
            String urlString = "https://newsapi.org/v2/everything"
                    + "?q=" + encoded
                    + "&language=en"
                    + "&sortBy=publishedAt"
                    + "&pageSize=30"
                    + "&apiKey=" + API_KEY;

            return fetch(urlString);

        } catch (Exception e) {
            e.printStackTrace();
            return getNewsJSON(); // fallback
        }
    }

    /**
     * HTTP GET helper dùng chung
     */
    private static String fetch(String urlString) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection cn = (HttpURLConnection) url.openConnection();
            cn.setRequestMethod("GET");
            cn.setConnectTimeout(8000);
            cn.setReadTimeout(8000);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(cn.getInputStream()));
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
