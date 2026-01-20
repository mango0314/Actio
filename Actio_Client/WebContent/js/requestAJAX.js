document.addEventListener('DOMContentLoaded', function() {
  var btns = document.querySelectorAll('button[data-action="richiesta"]');

  btns.forEach(function(btn) {
    btn.addEventListener('click', function(event) {
      event.preventDefault();

      // ✅ Disabilita subito per evitare doppi click
      if (btn.disabled) return;
      btn.disabled = true;
      
      var originalText = btn.textContent;
      btn.textContent = 'Invio in corso...';

      var idCorso = this.getAttribute('data-idcorso');
      var csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');

      if (!idCorso) {
        console.error('ID corso non trovato');
        btn.textContent = originalText;
        btn.disabled = false;
        return;
      }

      if (!csrfToken) {
        console.error('CSRF token non trovato');
        alert('Errore di sicurezza. Ricarica la pagina.');
        btn.textContent = originalText;
        btn.disabled = false;
        return;
      }

      var xmlPayload = `<request>
                          <idCorso>${idCorso}</idCorso>
                          <csrfToken>${csrfToken}</csrfToken>
                        </request>`;

      fetch('RichiediIscrizione', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/xml'
        },
        body: xmlPayload
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('Errore HTTP ' + response.status);
        }
        return response.text();
      })
      .then(data => {
        if (data.includes('<ok>true</ok>')) {
          btn.textContent = 'Richiesta in elaborazione';
          btn.classList.remove('btn-primary');
          btn.classList.add('btn-success');
          // ✅ Resta disabilitato
        } else {
          // Estrai messaggio errore se presente
          var errorMatch = data.match(/<error>(.*?)<\/error>/);
          var errorMsg = errorMatch ? errorMatch[1] : 'Errore sconosciuto';
          
          alert('Errore: ' + errorMsg);
          btn.textContent = originalText;
          btn.disabled = false;
        }
      })
      .catch(err => {
        console.error('Errore fetch:', err);
        alert('Errore di rete. Riprova.');
        btn.textContent = originalText;
        btn.disabled = false;
      });
    });
  });
});
