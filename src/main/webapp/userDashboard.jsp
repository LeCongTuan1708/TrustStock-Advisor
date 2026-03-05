
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.investorcare.model.Asset"%>
<%@page import="com.investorcare.model.Alert"%>

<%
    com.investorcare.model.User acc
            = (com.investorcare.model.User) session.getAttribute("account");

    if (acc == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Asset> assets = (List<Asset>) request.getAttribute("assets");

    List<Alert> alerts
            = (List<Alert>) request.getAttribute("alerts");

    Integer unreadCount
            = (Integer) request.getAttribute("unreadCount");

    if (unreadCount == null)
        unreadCount = 0;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Dashboard - TrustStock</title>
        <link rel="stylesheet" href="css/dashboard.css">
    </head>

    <body>

        <!-- ===== NAVBAR ===== -->
        <nav class="navbar">

            <div class="navbar-brand">
                <div class="navbar-brand-icon">📈</div>
                TrustStock
            </div>

            <div class="navbar-right">
                <span class="navbar-greeting">
                    Welcome, <strong><%= acc.getUsername()%></strong>
                </span>

                <div class="navbar-avatar">
                    <%= acc.getUsername().substring(0, 1).toUpperCase()%>
                </div>

                <a href="MainController?action=logout"
                   class="navbar-logout">Logout</a>
            </div>

        </nav>


        <!-- ===== NAVIGATION TABS ===== -->
        <div class="nav-tabs">

            <a href="#account" class="tab-link active">Account</a>
            <a href="#market" class="tab-link">Market</a>
            <a href="#portfolio" class="tab-link">Portfolio</a>
            <a href="#watchlist" class="tab-link">Watchlist</a>

            <a href="#alerts" class="tab-link">
                Alerts
                <% if (unreadCount > 0) {%>
                <span class="badge"><%= unreadCount%></span>
                <% }%>
            </a>

            <a href="#carenote" class="tab-link">Care Note</a>

        </div>


        <!-- ===== MAIN CONTENT ===== -->
        <div class="content">


            <!-- ===== ACCOUNT ===== -->
            <section id="account" class="section-card">

                <h2>Account Information</h2>

                <p><strong>Username:</strong> <%= acc.getUsername()%></p>
                <p><strong>Email:</strong> <%= acc.getEmail()%></p>

            </section>



            <!-- ===== MARKET ===== -->
            <section id="market" class="section-card">

                <h2>Market</h2>

                <table class="table">

                    <tr>
                        <th>Symbol</th>
                        <th>Name</th>
                        <th>Exchange</th>
                        <th>Status</th>
                    </tr>

                    <%
                        if (assets != null) {
                            for (Asset a : assets) {
                    %>

                    <tr>
                        <td><%= a.getSymbol()%></td>
                        <td><%= a.getName()%></td>
                        <td><%= a.getExchange()%></td>
                        <td><%= a.getStatus()%></td>
                    </tr>

                    <%
                            }
                        }
                    %>

                </table>

            </section>



            <!-- ===== PORTFOLIO ===== -->
            <section id="portfolio" class="section-card">

                <h2>Portfolio</h2>

                <p>Your portfolio will appear here.</p>

            </section>



            <!-- ===== WATCHLIST ===== -->
            <section id="watchlist" class="section-card">

                <h2>Watchlist</h2>

                <ul>
                    <li>NVDA</li>
                    <li>META</li>
                    <li>VNM</li>
                </ul>

            </section>



            <!-- ===== ALERTS ) ===== -->
            <section id="alerts" class="section-card">

                <h2>Alerts</h2>

                <%
                    if (alerts != null && !alerts.isEmpty()) {

                        for (Alert a : alerts) {
                %>

                <div class="alert-item">

                    <div class="alert-header">

                        <span class="alert-severity <%= a.getSeverity().toLowerCase()%>">
                            <%= a.getSeverity()%>
                        </span>

                        <span class="alert-time">
                            <%= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
                    .format(a.getTimestamp())%>
                        </span>

                    </div>

                    <div class="alert-message">
                        <%= a.getMessage()%>
                    </div>

                </div>

                <%
                    }

                } else {
                %>

                <p>No alerts.</p>

                <%
                    }
                %>

            </section>



            <!-- ===== CARE NOTE ===== -->
            <section id="carenote" class="section-card">

                <h2>Care Note</h2>

                <p>Notes and recommendations will appear here.</p>

            </section>


        </div>



        <!-- ===== SCRIPT ===== -->
        <script>

            (function () {

                const navbar = document.querySelector('.navbar');
                const navTabs = document.querySelector('.nav-tabs');

                function getOffset() {

                    return (navbar ? navbar.offsetHeight : 64)
                            + (navTabs ? navTabs.offsetHeight : 48)
                            + 8;

                }

                document.querySelectorAll('.nav-tabs .tab-link')
                        .forEach(link => {

                            link.addEventListener('click', function (e) {

                                e.preventDefault();

                                const id = this.getAttribute('href').replace('#', '');

                                const target = document.getElementById(id);

                                if (!target)
                                    return;

                                const top =
                                        target.getBoundingClientRect().top
                                        + window.scrollY
                                        - getOffset();

                                window.scrollTo({
                                    top: Math.max(0, top),
                                    behavior: 'smooth'
                                });

                                document.querySelectorAll('.nav-tabs .tab-link')
                                        .forEach(l => l.classList.remove('active'));

                                this.classList.add('active');

                            });

                        });

            })();

        </script>


    </body>
</html>
```
