document.addEventListener('DOMContentLoaded', function() {
  // seleziona tutti i bottoni di richiesta (adatta selettore se serve)
  var btns = document.querySelectorAll('button[data-action="richiesta"]');

  btns.forEach(function(btn) {
    btn.addEventListener('click', function(event) {
      // se vuoi evitare submit default (se bottone è inside form)
      event.preventDefault();

      var idCorso = this.getAttribute('data-idcorso'); // devi aggiungere questo attributo nel bottone nel JSP
      var csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');

      if (!idCorso) {
        console.error('ID corso non trovato sul bottone');
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
      .then(response => response.text())
      .then(data => {
        if (data.includes('<ok>true</ok>')) {
          btn.textContent = 'Richiesta in elaborazione';
          btn.disabled = true;
        } else {
          alert('Errore nella richiesta, riprova.');
        }
      })
      .catch(err => {
        console.error('Errore fetch:', err);
      });
    });
  });
});
