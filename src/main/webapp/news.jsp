<%--
    Document   : news
    Created on : Mar 13, 2026, 7:37:23 AM
    Author     : khait
--%>
<%@page import="com.investorcare.model.News"%>
<%@page import="com.investorcare.model.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>TrustStock — News</title>
        <link rel="stylesheet" href="style_dashboard.css">
        <link rel="stylesheet" href="style_news.css">
    </head>
    <body>
        <%
            User acc = (User) session.getAttribute("LOGIN_USER");
            if (acc == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            List<News> newsList = (List<News>) request.getAttribute("newsList");
        %>

        <!-- ===== NAVBAR ===== -->
        <nav class="navbar">
            <div class="navbar-brand">
                <div class="navbar-brand-icon">📈</div>
                InvestorCare
            </div>
            <div class="navbar-right">
                <span class="navbar-greeting">Welcome, <strong><%= acc.getUsername()%></strong></span>
                <div class="navbar-avatar"><%= acc.getUsername().substring(0, 1).toUpperCase()%></div>
                <a href="MainController?action=logout" class="navbar-logout">Logout</a>
            </div>
        </nav>

        <!-- ===== SUB NAV ===== -->
        <div class="nav-tabs">
            <a href="MainController?action=dashboard" class="tab-link"><span class="icon">🏠</span> Dashboard</a>
            <a href="MainController?action=news" class="tab-link active"><span class="icon">📰</span> News</a>
        </div>

        <!-- ===== CONTENT ===== -->
        <div class="content">

            <!-- PAGE HEADER -->
            <div class="news-page-header">
                <div>
                    <div class="news-page-title">
                        <span class="news-page-icon">📰</span>
                        Market News
                    </div>
                    <div class="news-page-subtitle">Latest financial & stock market updates</div>
                </div>
                <div class="news-live-badge">
                    <span class="news-live-dot"></span> Live Feed
                </div>
            </div>

            <!-- FEATURED + GRID -->
            <%
                if (newsList != null && !newsList.isEmpty()) {
                    News featured = newsList.get(0);
            %>

            <!-- FEATURED ARTICLE -->
            <a href="<%= featured.getUrl() %>" target="_blank" class="news-featured-link">
                <div class="news-featured">
                    <div class="news-featured-image-wrap">
                        <% if (featured.getImage() != null && !featured.getImage().isEmpty()) { %>
                        <img src="<%= featured.getImage() %>" alt="<%= featured.getTitle() %>"
                             class="news-featured-image" onerror="this.style.display='none';this.parentElement.classList.add('no-image')">
                        <% } %>
                        <div class="news-featured-overlay"></div>
                        <span class="news-tag news-tag-featured">⭐ Featured</span>
                    </div>
                    <div class="news-featured-body">
                        <span class="news-tag">📊 Market</span>
                        <div class="news-featured-title"><%= featured.getTitle() %></div>
                        <div class="news-featured-desc"><%= featured.getDescription() != null ? featured.getDescription() : "" %></div>
                        <div class="news-featured-cta">Read full story <span>→</span></div>
                    </div>
                </div>
            </a>

            <!-- NEWS GRID -->
            <div class="news-grid">
                <%
                    for (int i = 1; i < newsList.size(); i++) {
                        News n = newsList.get(i);
                        String imgSrc = (n.getImage() != null && !n.getImage().isEmpty()) ? n.getImage() : "";
                %>
                <a href="<%= n.getUrl() %>" target="_blank" class="news-card-link">
                    <div class="news-card">
                        <% if (!imgSrc.isEmpty()) { %>
                        <div class="news-card-img-wrap">
                            <img src="<%= imgSrc %>" alt="<%= n.getTitle() %>"
                                 class="news-card-img"
                                 onerror="this.parentElement.style.display='none'">
                        </div>
                        <% } %>
                        <div class="news-card-body">
                            <span class="news-tag">📰 News</span>
                            <div class="news-card-title"><%= n.getTitle() %></div>
                            <div class="news-card-desc">
                                <%= n.getDescription() != null && n.getDescription().length() > 120
                                    ? n.getDescription().substring(0, 120) + "…"
                                    : (n.getDescription() != null ? n.getDescription() : "") %>
                            </div>
                            <div class="news-card-footer">
                                <span class="news-read-more">Read more →</span>
                            </div>
                        </div>
                    </div>
                </a>
                <%  } %>
            </div>

            <% } else { %>
            <!-- EMPTY STATE -->
            <div class="news-empty">
                <div class="news-empty-icon">📭</div>
                <div class="news-empty-title">No news available</div>
                <div class="news-empty-sub">Check back later for the latest market updates.</div>
                <a href="MainController?action=news" class="btn btn-dark" style="text-decoration:none;margin-top:16px;">🔄 Refresh</a>
            </div>
            <% } %>

        </div><!-- /.content -->
    </body>
</html>