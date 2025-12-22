
  const radioUtente = document.getElementById("radioUtente");
  const radioAttivita = document.getElementById("radioAttivita");

  const campiUtente = document.getElementById("campiUtente");
  const campiAttivita = document.getElementById("campiAttivita");

  function aggiornaCampi() {
    if (radioUtente.checked) {
      campiUtente.classList.remove("d-none");
      campiAttivita.classList.add("d-none");
      
      campiUtente.querySelectorAll("input").forEach(i => i.required = true);
      campiAttivita.querySelectorAll("input").forEach(i => i.required = false);
    } else if (radioAttivita.checked) {
      campiAttivita.classList.remove("d-none");
      campiUtente.classList.add("d-none");
      
      campiAttivita.querySelectorAll("input").forEach(i => i.required = true);
      campiUtente.querySelectorAll("input").forEach(i => i.required = false);
    }
  }

  radioUtente.addEventListener("change", aggiornaCampi);
  radioAttivita.addEventListener("change", aggiornaCampi);

/**
 * 
 */