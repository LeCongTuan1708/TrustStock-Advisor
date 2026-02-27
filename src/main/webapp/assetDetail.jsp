<%@page import="com.investorcare.model.Asset"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Control Center</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body class="bg-light">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10">
                    <div class="card shadow-sm border-0 rounded-3 p-2">
                        <form action="MainController" method="post">
                            <input type="hidden" name="action" value="edit-asset">
                            <div class="card-header bg-white py-3 border-bottom">
                                <div class="row align-items-center">
                                    <div class="col">
                                        <h5 class="mb-0 fw-bold text-dark fs-2 text-uppercase">Asset Control Center</h5>
                                    </div>
                                    <div class="col-auto">
                                        <button type="button" class="btn btn-outline-secondary px-4 me-2" onclick="window.location.href='MainController?action=asset-search'">Cancel</button>
                                        <button type="submit" class="btn btn-dark px-4">Save Changes</button>
                                    </div>
                                </div>
                            </div>

                            <%
                                Asset old = (Asset) request.getAttribute("oldAsset");
                                if (old == null) {
                                    out.println("Asset not found!");
                                    return;
                                }
                            %>

                            <div class="card-body p-4">
                                <div class="row g-5">
                                    <div class="col-md-6">
                                        <p class="fw-bold text-muted mb-4">[ Basic Information ]</p>

                                        <div class="mb-3">
                                            <label class="form-label fw-semibold small text-uppercase text-muted">Asset ID</label>
                                            <input type="text" class="form-control form-control-lg bg-light border-0" name="assetId" value="<%= old.getAssetId()%>" readonly style="font-size: 0.9rem;">
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label fw-semibold small text-uppercase text-muted">Symbol</label>
                                            <input type="text" class="form-control form-control-lg bg-light border-0" name="symbol" value="<%= old.getSymbol()%>" readonly style="font-size: 0.9rem;">
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label fw-semibold small text-uppercase text-muted">Type</label>
                                            <select name="type" class="form-select form-select-lg bg-light border-0" style="font-size: 0.9rem;">
                                                <option value="STOCK" 
                                                        <%= "STOCK".equals(old.getType()) ? "selected" : ""%>>STOCK</option>
                                                <option value="GOLD"
                                                        <%= "GOLD".equals(old.getType()) ? "selected" : ""%>>GOLD</option>
                                                <option value="SILVER"
                                                        <%= "SILVER".equals(old.getType()) ? "selected" : ""%>>SILVER</option>
                                            </select>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label fw-semibold small text-uppercase text-muted">Exchange</label>
                                            <input type="text" class="form-control form-control-lg bg-light" name="exchange" value="<%= old.getExchange()%>" style="font-size: 0.9rem;">
                                        </div>

                                        <div class="mb-4">
                                            <label class="form-label fw-semibold small text-uppercase text-muted">Name</label>
                                            <input type="text" class="form-control form-control-lg bg-light" name="name" value="<%= old.getName()%>" style="font-size: 0.9rem;">
                                        </div>

                                        <div class="mb-4">
                                            <label class="form-label fw-semibold small text-uppercase text-muted d-block">Visible</label>
                                            <div class="d-flex gap-4">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" name="visible" value="1" id="yes" <%= old.isVisible() ? "checked" : ""%>>
                                                    <label class="form-check-label" for="yes">Yes</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" name="visible" value="0" id="no"<%= !old.isVisible() ? "checked" : ""%>>
                                                    <label class="form-check-label" for="no">No</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div>
                                            <label class="form-label fw-semibold small text-uppercase text-muted d-block">Status</label>
                                            <div class="d-flex gap-4">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" name="status" id="active" value="Active" <%= "Active".equals(old.getStatus()) ? "checked" : ""%>>
                                                    <label class="form-check-label" for="active">Active</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" name="status" value="Inactive" id="inactive" <%= !"Active".equals(old.getStatus()) ? "checked" : ""%>>
                                                    <label class="form-check-label" for="inactive">Inactive</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <p class="fw-bold text-muted mb-4">[ Data Health ]</p>

                                        <div class="d-flex justify-content-between mb-3 border-bottom pb-2">
                                            <span class="text-muted small text-uppercase fw-semibold">Total PriceBars:</span>
                                            <span class="fw-bold">1,245</span>
                                        </div>

                                        <div class="d-flex justify-content-between mb-3 border-bottom pb-2">
                                            <span class="text-muted small text-uppercase fw-semibold">Latest Price:</span>
                                            <span class="fw-bold text-primary text-decoration-underline">2026-02-24</span>
                                        </div>

                                        <div class="d-flex justify-content-between mb-3 border-bottom pb-2">
                                            <span class="text-muted small text-uppercase fw-semibold">Total NewsItems:</span>
                                            <span class="fw-bold">87</span>
                                        </div>

                                        <div class="d-flex justify-content-between mb-3 border-bottom pb-2">
                                            <span class="text-muted small text-uppercase fw-semibold">Latest News:</span>
                                            <span class="fw-bold text-primary text-decoration-underline">2026-02-23</span>
                                        </div>

                                        <div class="d-flex justify-content-between mb-3 border-bottom pb-2">
                                            <span class="text-muted small text-uppercase fw-semibold">Total Signals:</span>
                                            <span class="fw-bold">56</span>
                                        </div>

                                        <div class="d-flex justify-content-between mb-3 border-bottom pb-2">
                                            <span class="text-muted small text-uppercase fw-semibold">Latest Signal:</span>
                                            <span class="fw-bold text-primary text-decoration-underline">2026-02-24</span>
                                        </div>
                                    </div>
                                </div>

<!--                                <hr class="my-5">

                                <div class="row">
                                    <div class="col-12">
                                        <p class="fw-bold text-muted mb-3">[ System Control ]</p>
                                        <div class="d-flex gap-2">
                                            <button type="button" class="btn btn-outline-dark btn-sm px-3">Re-fetch Price Data</button>
                                            <button type="button" class="btn btn-outline-dark btn-sm px-3">Re-fetch News Data</button>
                                            <button type="button" class="btn btn-outline-danger btn-sm px-3">Recalculate Signals</button>
                                        </div>
                                    </div>
                                </div>-->
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>