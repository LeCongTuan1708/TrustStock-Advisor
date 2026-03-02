<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Ticker</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-10">
                
                <div class="card shadow-sm border-0 rounded-3 p-2">
                    <form action="MainController" method="post">
                        <input type="hidden" name="action" value="add-asset">
                        
                        <div class="card-header bg-white py-3 border-bottom">
                            <div class="row align-items-center">
                                <div class="col">
                                    <h5 class="mb-0 fw-bold text-dark fs-2 text-uppercase">Add New Ticker</h5>
                                </div>
                                <div class="col-auto">
                                    <button type="button" class="btn btn-outline-secondary px-4 me-2" onclick="history.back()">Cancel</button>
                                    <button type="submit" class="btn btn-dark px-4">Create Ticker</button>
                                </div>
                            </div>
                        </div>

                        <div class="card-body p-4">
                            <p class="fw-bold text-muted mb-4">[ Ticker Configuration ]</p>
                            
                            <div class="row g-4">
                                <div class="col-md-6">
                                    <label class="form-label fw-semibold small text-uppercase text-muted">Symbol</label>
                                    <input type="text" name="symbol" class="form-control form-control-lg bg-light border-0" 
                                           placeholder="e.g. AAPL, BTC" style="font-size: 0.9rem;" required>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label fw-semibold small text-uppercase text-muted">Name (Asset/Company)</label>
                                    <input type="text" name="name" class="form-control form-control-lg bg-light border-0" 
                                           placeholder="e.g. Apple Inc." style="font-size: 0.9rem;" required>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label fw-semibold small text-uppercase text-muted">Type</label>
                                    <select name="type" class="form-select form-select-lg bg-light border-0" style="font-size: 0.9rem;">
                                        <option value="STOCK">STOCK</option>
                                        <option value="GOLD">GOLD</option>
                                        <option value="SILVER">SILVER</option>
                                    </select>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label fw-semibold small text-uppercase text-muted">Exchange</label>
                                    <input type="text" name="exchange" class="form-control form-control-lg bg-light border-0" 
                                           placeholder="e.g. NASDAQ, HOSE" style="font-size: 0.9rem;">
                                </div>
                            </div>

                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</body>
</html>