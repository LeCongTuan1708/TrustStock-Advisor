<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Insert New Ticker</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body class="bg-light">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-10">
                <div class="card shadow-sm border-0 rounded-3 p-2">
                    
                    <form action="MainController" method="post">
                        <input type="hidden" name="action" value="insert-ticker">
                        
                        <div class="card-header bg-white py-3 border-bottom">
                            <div class="row align-items-center">
                                <div class="col">
                                    <h5 class="mb-0 fw-bold text-dark fs-2 text-uppercase">Create New Ticker</h5>
                                </div>
                                <div class="col-auto">
                                    <button type="button" class="btn btn-outline-secondary px-4 me-2" onclick="history.back()">Cancel</button>
                                    <button type="submit" class="btn btn-dark px-4">Create</button>
                                </div>
                            </div>
                        </div>

                        <div class="card-body p-4">
                            <div class="row g-5">
                                <div class="col-md-6">
                                    <p class="fw-bold text-muted mb-4">[ Ticker Information ]</p>
                                    
                                    <div class="mb-4">
                                        <label class="form-label fw-semibold small text-uppercase text-muted">Symbol / Ticker</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control form-control-lg bg-light border-0" name="symbol" placeholder="e.g. AAPL" style="font-size: 0.9rem;">
                                            <button class="btn btn-outline-dark px-3" type="button">Verify</button>
                                        </div>
                                    </div>
                                    
                                    <div class="mb-0">
                                        <label class="form-label fw-semibold small text-uppercase text-muted d-block mb-2">Initial Status</label>
                                        <div class="d-flex gap-4">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="status" id="active" value="Active" checked>
                                                <label class="form-check-label" for="active">Active</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="status" id="inactive" value="Inactive">
                                                <label class="form-check-label" for="inactive">Inactive</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <p class="fw-bold text-muted mb-4">[ Preview Details ]</p>
                                    
                                    <div class="d-flex justify-content-between mb-3 border-bottom pb-2">
                                        <span class="text-muted small text-uppercase fw-semibold">Company Name:</span>
                                        <span class="fw-bold" id="preview-name">---</span>
                                    </div>

                                    <div class="d-flex justify-content-between mb-3 border-bottom pb-2">
                                        <span class="text-muted small text-uppercase fw-semibold">Exchange:</span>
                                        <span class="fw-bold text-primary" id="preview-exchange">---</span>
                                    </div>

                                    <div class="d-flex justify-content-between mb-3 border-bottom pb-2">
                                        <span class="text-muted small text-uppercase fw-semibold">Last Price:</span>
                                        <span class="fw-bold text-success" id="preview-price">$ 0.00</span>
                                    </div>

                                    <div class="mt-4 p-3 bg-light rounded-3 border-start border-4 border-dark">
                                        <small class="text-muted d-block mb-1 text-uppercase fw-bold" style="font-size: 0.7rem;">System Note:</small>
                                        <p class="small mb-0 text-secondary">Information will be validated against market data providers before insertion.</p>
                                    </div>
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