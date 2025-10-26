document.addEventListener('DOMContentLoaded', function () {
  // Enable bootstrap tooltips where used
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
  tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl);
  });

  // Attach delete confirmation modal flow for elements with .js-delete
  document.querySelectorAll('.js-delete').forEach(function (el) {
    el.addEventListener('click', function (ev) {
      ev.preventDefault();
      var href = el.getAttribute('data-href') || el.getAttribute('href');
      var id = el.getAttribute('data-id');
      var modal = document.getElementById('globalConfirmModal');
      if (!modal) {
        // fallback to browser confirm
        if (confirm('Are you sure you want to delete this item?')) {
          if (href && href !== '#') window.location.href = href;
        }
        return;
      }
      var bsModal = new bootstrap.Modal(modal);
      var okBtn = modal.querySelector('#globalConfirmOk');
      okBtn.setAttribute('data-delete-href', href);
      bsModal.show();
    });
  });

  // When confirm ok clicked, navigate to the stored href
  var globalOk = document.querySelector('#globalConfirmOk');
  if (globalOk) {
    globalOk.addEventListener('click', function () {
      var href = this.getAttribute('data-delete-href');
      var modalEl = document.getElementById('globalConfirmModal');
      var modal = bootstrap.Modal.getInstance(modalEl);
      if (modal) modal.hide();
      if (href) window.location.href = href;
    });
  }

  // Live search for receipts table by title/category/amount
  var searchInput = document.getElementById('searchReceipt');
  if (searchInput) {
    searchInput.addEventListener('input', function () {
      var q = this.value.trim().toLowerCase();
      var rows = document.querySelectorAll('#receiptsTable tr');
      rows.forEach(function (r) {
        var text = r.innerText.toLowerCase();
        r.style.display = text.indexOf(q) !== -1 ? '' : 'none';
      });
    });
  }

  // small page entrance animation helper
  document.body.classList.add('app-page-enter');
});

// small toast helper
function appToast(message, type) {
  type = type || 'info';
  var el = document.createElement('div');
  el.className = 'app-toast bg-' + (type === 'success' ? 'success' : (type === 'error' ? 'danger' : 'dark'));
  el.innerText = message;
  document.body.appendChild(el);
  setTimeout(function () { el.classList.add('visible'); }, 50);
  setTimeout(function () { el.classList.remove('visible'); setTimeout(function () { el.remove(); }, 350); }, 3500);
}

// expose for debugging
window.appToast = appToast;
// Minimal interactivity for App landing page
document.addEventListener('DOMContentLoaded', function(){
  // graceful fallback if image missing
  const img = document.querySelector('.device-mockup img');
  if(img){
    img.onerror = () => { img.style.display = 'none'; };
  }

  // initialize tooltips globally
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
  tooltipTriggerList.forEach(function (el) { new bootstrap.Tooltip(el); });

  // intercept links with class js-delete to show global confirm modal
  const deleteLinks = document.querySelectorAll('.js-delete');
  const confirmModalEl = document.getElementById('globalConfirmModal');
  let confirmModal = confirmModalEl ? new bootstrap.Modal(confirmModalEl) : null;
  const confirmMessage = document.getElementById('globalConfirmMessage');
  const confirmOk = document.getElementById('globalConfirmOk');

  deleteLinks.forEach(link => {
    link.addEventListener('click', function(evt){
      evt.preventDefault();
      const id = this.getAttribute('data-id');
      const href = this.getAttribute('href');
      if(!confirmModal) { window.location.href = href; return; }
      confirmMessage.textContent = 'Are you sure you want to delete this item?';
      confirmOk.href = href;
      confirmModal.show();
    });
  });

  // live search for receipts table (if present)
  const searchReceipt = document.getElementById('searchReceipt');
  if(searchReceipt){
    searchReceipt.addEventListener('input', function(){
      const q = this.value.toLowerCase();
      document.querySelectorAll('table tbody tr').forEach(row => {
        const txt = row.innerText.toLowerCase();
        row.style.display = txt.includes(q) ? '' : 'none';
      });
    });
  }
  
  // page entrance micro animation
  document.querySelectorAll('.page-fade').forEach(el => el.classList.add('visible'));

  // helper: show toast
  window.appToast = function(message, timeout=2400){
    const t = document.createElement('div');
    t.className = 'app-toast';
    t.textContent = message;
    document.body.appendChild(t);
    setTimeout(()=>{ t.remove(); }, timeout);
  }
});
