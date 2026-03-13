/**
 * Form submit smoothing — team review
 * - Prevents double submit (rapid clicks) by disabling submit control once.
 * - Optional: add class .is-loading to button for CSS spinner.
 * MVC: View-only script; no business logic.
 */
(function () {
  'use strict';
  document.addEventListener('submit', function (e) {
    var form = e.target;
    if (!form || form.tagName !== 'FORM') return;
    var btn = form.querySelector('button[type="submit"]:not([disabled]), input[type="submit"]:not([disabled])');
    if (!btn) return;
    if (form.getAttribute('data-no-loading') === 'true') return;
    btn.disabled = true;
    btn.classList.add('is-loading');
    if (btn.tagName === 'BUTTON' && !btn.getAttribute('aria-busy')) {
      btn.setAttribute('aria-busy', 'true');
    }
  }, true);
})();
