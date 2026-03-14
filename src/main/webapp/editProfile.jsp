<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.investorcare.model.User"%>
<%
    User acc = (User) session.getAttribute("LOGIN_USER");
    if (acc == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String error = (String) request.getAttribute("ERROR");
    String success = (String) request.getAttribute("SUCCESS");
    String editEmail = request.getParameter("editEmail");
    boolean isEditEmail = "true".equals(editEmail);
%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile — TrustStock</title>
        <link rel="stylesheet" href="editProfile.css">
    </head>
    <body>

        <!-- ===== NAVBAR ===== -->
        <nav class="navbar">
            <div class="navbar-brand">
                <div class="navbar-brand-icon">📈</div>
                TrustStock
            </div>
            <div class="navbar-right">
                <a href="MainController?action=dashboard" class="navbar-back">← Back to Dashboard</a>
                <span class="navbar-greeting">Welcome, <strong><%= acc.getUsername()%></strong></span>
                <div class="navbar-avatar"><%= acc.getUsername().substring(0, 1).toUpperCase()%></div>
                <a href="MainController?action=logout" class="navbar-logout">Log out</a>
            </div>
        </nav>

        <!-- ===== PAGE WRAPPER ===== -->
        <div class="page-wrapper">
            <div class="profile-container">

                <!-- ===== PROFILE HERO ===== -->
                <div class="profile-hero">
                    <div class="hero-bg-glow"></div>
                    <div class="hero-content">
                        <div class="hero-avatar">
                            <span><%= acc.getUsername().substring(0, 1).toUpperCase()%></span>
                            <div class="avatar-ring"></div>
                        </div>
                        <div class="hero-info">
                            <h1><%= acc.getUsername()%></h1>
                            <div class="hero-meta">
                                <span class="badge badge-active">● Active</span>
                                <% if (acc.getEmail() != null && !acc.getEmail().isEmpty()) {%>
                                <span class="hero-email">✉️ <%= acc.getEmail()%></span>
                                <% }%>
                            </div>
                        </div>
                    </div>

                    <!-- Stats row -->
                    <div class="hero-stats">
                        <div class="hero-stat">
                            <div class="hero-stat-label">Member Since</div>
                            <div class="hero-stat-value"><%= (acc.getCreated_at() != null) ? acc.getCreated_at().substring(0, 10) : "N/A"%></div>
                        </div>
                        <div class="hero-stat-divider"></div>
                        <div class="hero-stat">
                            <div class="hero-stat-label">Last Login</div>
                            <div class="hero-stat-value"><%= (acc.getLastLogin() != null) ? acc.getLastLogin() : "N/A"%></div>
                        </div>
                        <div class="hero-stat-divider"></div>
                        <div class="hero-stat">
                            <div class="hero-stat-label">Account Status</div>
                            <div class="hero-stat-value text-green">Verified</div>
                        </div>
                    </div>
                </div>

                <!-- ===== MESSAGES ===== -->
                <% if (error != null) {%>
                <div class="msg-wrap">
                    <div class="error-message"><%= error%></div>
                </div>
                <% } %>
                <% if (success != null) {%>
                <div class="msg-wrap">
                    <div class="success-message"><%= success%></div>
                </div>
                <% }%>

                <!-- ===== EDIT FORM ===== -->
                <form action="MainController" method="POST" class="edit-form">
                    <input type="hidden" name="action" value="UpdateProfile">

                    <!-- BASIC INFO -->
                    <div class="edit-section">
                        <div class="section-title">
                            <span class="section-icon">👤</span>
                            Basic Information
                        </div>

                        <div class="form-grid">
                            <div class="form-group">
                                <label>Username</label>
                                <div class="input-wrapper">
                                    <span class="input-icon">@</span>
                                    <input type="text" name="username" value="<%= acc.getUsername()%>" required placeholder="Enter username">
                                </div>
                                <div class="helper-text">Username must be unique across the platform</div>
                            </div>

                            <div class="form-group">
                                <label>Email Address</label>
                                <div class="email-row">
                                    <div class="input-wrapper" style="flex:1">
                                        <span class="input-icon">✉</span>
                                        <input type="email" name="email"
                                               value="<%= acc.getEmail() != null ? acc.getEmail() : ""%>"
                                               <%= isEditEmail ? "" : "readonly"%>
                                               placeholder="Enter email address">
                                    </div>
                                    <% if (!isEditEmail) { %>
                                    <button type="submit" name="editEmail" value="true"
                                            formaction="editProfile.jsp" class="change-btn">✏️ Change</button>
                                    <% } %>
                                </div>
                                <% if (isEditEmail) { %>
                                <div class="helper-text">⚠️ Email must be unique. You'll need to confirm your password below.</div>
                                <% } %>
                            </div>

                            <% if (isEditEmail) { %>
                            <div class="form-group">
                                <label>Confirm Password</label>
                                <div class="input-wrapper">
                                    <span class="input-icon">🔑</span>
                                    <input type="password" name="confirmPassword" placeholder="Enter current password to confirm" required>
                                </div>
                            </div>
                            <% }%>
                        </div>
                    </div>

                    <div class="section-divider"></div>

                    <!-- CHANGE PASSWORD -->
                    <div class="edit-section">
                        <div class="section-title">
                            <span class="section-icon">🔒</span>
                            Change Password
                        </div>

                        <div class="form-grid">
                            <div class="form-group">
                                <label>Current Password</label>
                                <div class="input-wrapper">
                                    <span class="input-icon">🔓</span>
                                    <input type="password" name="oldPassword" placeholder="Enter your current password">
                                </div>
                            </div>

                            <div class="form-group">
                                <label>New Password</label>
                                <div class="input-wrapper">
                                    <span class="input-icon">🔒</span>
                                    <input type="password" name="newPassword" placeholder="Enter a strong new password">
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Confirm New Password</label>
                                <div class="input-wrapper">
                                    <span class="input-icon">✅</span>
                                    <input type="password" name="reNewPassword" placeholder="Re-enter your new password">
                                </div>
                            </div>
                        </div>

                        <div class="password-hint">
                            <span class="hint-icon">💡</span>
                            Leave password fields empty if you don't want to change your password.
                        </div>
                    </div>

                    <!-- ACTIONS -->
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            <span>💾</span> Save Changes
                        </button>
                        <button type="button" class="btn btn-secondary"
                                onclick="window.location.href = 'MainController?action=dashboard'">
                            <span>✕</span> Cancel
                        </button>
                    </div>
                </form>

            </div>
        </div>

    </body>
</html>
