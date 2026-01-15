


        function aggiungiRiga() {
            const tbody = document.getElementById('orariBody');
            const tr = document.createElement('tr');
            
            // HTML della nuova riga
            tr.innerHTML = `
                <td>
                    <select name="giorno[]" class="form-select" required>
                        <option value="" selected disabled>Scegli...</option>
                        <option value="1">Lunedì</option>
                        <option value="2">Martedì</option>
                        <option value="3">Mercoledì</option>
                        <option value="4">Giovedì</option>
                        <option value="5">Venerdì</option>
                        <option value="6">Sabato</option>
                        <option value="7">Domenica</option>
                    </select>
                </td>
                <td>
                    <input type="time" name="oraInizio[]" class="form-control" required>
                </td>
                <td>
                    <input type="time" name="oraFine[]" class="form-control" required>
                </td>
                <td>
                    <button type="button" class="btn btn-outline-danger btn-sm" onclick="rimuoviRiga(this)">
                        X
                    </button>
                </td>
            `;
            tbody.appendChild(tr);
        }

        function rimuoviRiga(btn) {
            btn.closest('tr').remove();
        }
