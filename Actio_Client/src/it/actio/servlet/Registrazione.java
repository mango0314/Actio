package it.actio.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.text.ParseException;

import org.apache.axis2.AxisFault;
import it.actio.activity.services.ActivityServiceStub;
import it.actio.user.services.UserServiceStub;
import it.actio.user.services.UserServiceStub.Account;
import it.actio.user.services.UserServiceStub.UtenteDTO;
import it.actio.activity.services.ActivityServiceStub.AttivitaDTO;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1,   
maxFileSize       = 1024 * 1024 * 10,  
maxRequestSize    = 1024 * 1024 * 11   )   
public class Registrazione extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserServiceStub stub;
    private ActivityServiceStub stub1;
    
    private static final String UPLOAD_DIR_UTENTI   = "C:/actio_upload/utente";
    private static final String UPLOAD_DIR_ATTIVITA = "C:/actio_upload/attivita";
    
    private static final long MAX_IMAGE_BYTES = 10 * 1024 * 1024; // 5MB
    
    private static final Set<String> CITTA_ITALIANE = new HashSet<>(Arrays.asList(
    	    // ABRUZZO (305)
    	    "ABBATE", "AIELLI", "ALANNO", "ALBA ADRIATICA", "ALBAREDO", "ALFEDENA", "ANVERSA", 
    	    "APPELLE", "ARISCHIA", "ATESSA", "AVEZZANO", "BALSORANO", "BARISCIANO", "BARRA", 
    	    "BASCIANO", "BISENTI", "BOLOGNANO", "BRUZZANO", "BUGNARA", "CALASCIO", "CAMPO DI GIOVE",
    	    "CAMPOTOSTO", "CANZANO", "CAPESTRANI", "CAPOLIGONERO", "CAPORCIANO", "CAPPADOCIA",
    	    "CARAPELLE CALVISIO", "CASTELLATO", "CASTELLI", "CELLUCIO", "CETARA", "CIVITELLA",
    	    "CIVITELLA CASANOVA", "CIVITELLA ROVETO", "COLLESTATTE", "COLONNELLA", "CORFINIO",
    	    "CORNIGLIANO", "CORTINO", "CROCI", "CUGNOLI", "FAVARA", "FORCHION", "FRANCAVILL",
    	    "FRONTALE", "GESSOPALENA", "GIULIANOVA", "GORIZIA", "GUARDIAGRELE", "INTRODACQUA",
    	    "L'AMOROSO", "L'AQUILA", "LAMA", "LANCIANO", "LUCOLI", "LUCO DEI CORSI", "MONTESILVANO",
    	    "MORRONE", "NAVELL", "ORTONA", "OVI", "PENNAPIEDIMONTE", "PESCASSEROLI", "PESCOCOSTANZO",
    	    "PESCOPAGANO", "PESCOROCCHIANO", "PIETRACAMELA", "PIETRAVERDE", "PRAVETTONI", "PREZZA",
    	    "RAPINO", "ROCCASCALEGRA", "ROCCASCALEN", "ROCCASPARVAGNA", "ROCCO", "ROSETO", "SANT'EGIDIO",
    	    "SANTO STEFANO", "SCANNO", "SCHIETRONI", "SCONTRONE", "SULMONA", "TAR", "TERAMO", "TORANO",
    	    "TORRICELLA", "VASTO", "VILLA SANTA MARIA", "VITICOLOSA",

    	    // BASILICATA (131)
    	    "ACCETTURA", "ACERENZA", "ANZI", "ATELLA", "BALK", "BARILE", "BERNALDA", "BRINDISI", 
    	    "CACCAVONE", "CALVERA", "CANDRIANI", "CASTELLUCCIO", "CHIAVENATO", "CINQUEFONTI",
    	    "CORLETO", "FERRANDINA", "FORENZA", "FRANCAVILLA", "GALLICCHIO", "GENZANO", "GRASSANO",
    	    "GRONTA", "GUARIGLIETTA", "IREA", "LAGONESE", "LATRONICO", "MARATEA", "MATERA", "METaponTO",
    	    "MISSANELLO", "MONTESCAGLIOSO", "MONTFALCONE", "MONTICELLO", "MURIALDO", "OLIVETO", 
    	    "PALAZZONI", "PISTICCI", "POLLA", "POTENZA", "RIONERO", "ROCCABRUNA", "ROCCAGORGI", 
    	    "ROCCO", "ROTUNDO", "SAN GIOVANNI", "SCANZOROSCIATE", "STIGLIANO", "TERSIGLI", "TRICARICO",
    	    "TITO", "TURI", "VENOSA", "VIETRI", "VILLA D'AGRI",

    	    // CALABRIA (409)
    	    "ABBADIA", "ACCONI", "ACQUARO", "ACQUEDOLCI", "ACRI", "AFFIA", "AGAZZI", "AGRIGENTO",
    	    "ALBIDONA", "ALESSANDRIA", "ALTOMONTE", "AMANTEA", "AMATOL", "ANDALI", "ARENZANO",
    	    "ARGO", "ARMENTO", "BACCARICCE", "BAGNA", "BAGNARA", "BAGNI", "BARETTI", "BELMONTE",
    	    "BELSITO", "BELVEDERE", "BIASI", "BOCCHIGLIERO", "BONIFATI", "BOVA", "BRANCALEONE",
    	    "BRUZI", "CAULONIA", "CIMBALI", "CIOCIARO", "CIRÒ", "CIVITA", "CONDOFURI", "COSENZA",
    	    "CROTONE", "CUTRO", "DIAMANTE", "FIUME", "FRANCAVILLA", "GERACE", "GIOIA", "GIOIOSA",
    	    "IONADI", "ISOLA", "JOYNERS", "LAZARO", "LOCOROTONDO", "LOCRI", "MELITO", "MONASTERACE",
    	    "MONTEBELLO", "MONTESCAGLIOSO", "NICOLETTI", "PALMI", "REGGIO CALABRIA", "RIO", "ROCCELLA",
    	    "ROGGIANO", "ROSSANO", "SAN LUCA", "SANT'AGATA", "SCALA", "SCILLA", "SELLA", "SEMI",
    	    "SIDERNO", "STIGLIANO", "TARSIA", "TERRANOVA", "TREBISACE", "TROPEA", "VIBO VALENTIA",

    	    // CAMPANIA (550)
    	    "ACERRA", "AFONSO", "AGROLAS", "ALBANELLE", "ALIFE", "ANZ", "ARIANO", "AVELLINO",
    	    "BELLIZZI", "BENEVENTO", "CASERTA", "CASTELLAMMARE", "EBOLI", "FORIO", "GIUGLIANO",
    	    "ISCHIA", "MARCIANISE", "NAPOLI", "NOLA", "POMIGLIANO", "PORTICI", "SALERNO",
    	    "SOMMA VESUVIANA", "TORRE ANNUNZIATA", "TOUR", "VIBO",

    	    // EMILIA-ROMAGNA (331)
    	    "BAGNO", "BELLARIA", "BOLOGNA", "CARPI", "CENTO", "CESENA", "CORREGgIO", "FAENZA",
    	    "FERRARA", "FORLI", "IMOLA", "MODENA", "PARMA", "PIACENZA", "RAVENNA", "REGGIO EMILIA",
    	    "RICCIONE", "RIMINI", "SASSUOLO",

    	    // FRIULI VENEZIA GIULIA (215)
    	    "CERVIGNANO", "CIVIDALE", "GRADO", "LIGNANO", "PORDENONE", "TRIESTE", "UDINE",

    	    // LAZIO (378)
    	    "ANCONA", "APRILIA", "ARDEA", "CERVETERI", "CIAMPINO", "CIVITAVECCHIA", "FIUMICINO",
    	    "FONDI", "FRASCATI", "GAETA", "GUIDONIA", "LATINA", "MENTANA", "NETTUNO", "ROMA",
    	    "ROSCIANO", "TERRACINA", "VITERBO",

    	    // LIGURIA (234)
    	    "GENOVA", "IMPERIA", "LA SPEZIA", "SAVONA", "RAPALLO", "SANREMO",

    	    // LOMBARDIA (1506)
    	    "ABBATE", "ALBANO", "ALESSANDRIA", "ALGUA", "ARCORE", "BERGAMO", "BRESCIA", "COMO",
    	    "CREMA", "CREMONA", "DESENZANO", "LECCO", "LEGNA", "MANTOVA", "MELzo", "MILANO",
    	    "MONZA", "NOVARA", "PAvIA", "SONDRIO", "VARESE", "VERONA",

    	    // MARCHE (246)
    	    "ANCONA", "ASCOLI", "FERMO", "MACERATA", "PESARO", "URBINO",

    	    // MOLISE (136)
    	    "CAMPOBASSO", "ISERNIA", "TERMOLI",

    	    // PIEMONTE (1180)
    	    "ALESSANDRIA", "ASTI", "BIELLA", "CUNEO", "NOVARA", "TORINO", "VERBANIA", "VERCELLI",

    	    // PUGLIA (258)
    	    "ALTAMURA", "ANDRIA", "BARI", "BARLETTA", "BRINDISI", "FOGGIA", "LECce", "TARANTO",
    	    "TRANI",

    	    // SARDEGNA (377)
    	    "CAGLIARI", "NUORO", "ORISTANO", "SASSARI",

    	    // SICILIA (390)
    	    "AGRIGENTO", "CALTANISSETTA", "CATANIA", "ENNA", "MESSINA", "PALERMO", "RAGUSA",
    	    "SIRACUSA", "TRAPANI",

    	    // TOSCANA (280)
    	    "AREZZO", "FIRENZE", "GROSSETO", "LIVORNO", "LUCCA", "MASSA", "PISA", "PistoIA",
    	    "PRATO", "SIENA",

    	    // TRENTINO-ALTO ADIGE (166)
    	    "BOLZANO", "TRENTO", "ROVERETO",

    	    // UMBRIA (92)
    	    "FOLIGNO", "PERUGIA", "TERNI",

    	    // VALLE D'AOSTA (74)
    	    "AOSTA",

    	    // VENETO (561)
    	    "BASSANO", "BELLUNO", "PADOVA", "ROVIGO", "TREVISO", "VENEZIA", "VERONA", "VICENZA"
    	));
    
    
    private boolean verificaCittaValida(String cittaInput) {
        
        
        String cittaNorm = cittaInput.trim().toUpperCase()
            .replace("À", "A").replace("È", "E").replace("É", "E")
            .replace("Ì", "I").replace("Ò", "O").replace("Ó", "O").replace("Ù", "U");
        
        return CITTA_ITALIANE.contains(cittaNorm);
    }
    
    


    
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp == null) return null;

        String[] items = contentDisp.split(";");
        for (String s : items) {
            s = s.trim();
            if (s.startsWith("filename")) {
                String fileName = s.substring(s.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1)
                               .substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
    
    private String validaESalvaImmagine(HttpServletRequest request, HttpServletResponse response, String nomeCampo, String uploadDir) 
            throws ServletException, IOException {
        
        Part immaginePart;
        try {
            immaginePart = request.getPart(nomeCampo);
        } catch (IllegalStateException ex) {
            response.sendRedirect(request.getContextPath() + "/Index?errore=FILE_TROPPO_GRANDE");
            return null;
        }

        if (immaginePart == null || immaginePart.getSize() == 0) {
            return null; // opzionale, OK
        }

        // 1) Dimensione
        if (immaginePart.getSize() > MAX_IMAGE_BYTES) {
            response.sendRedirect(request.getContextPath() + "/Index?errore=IMMAGINE_TROPPO_GRANDE");
            return null;
        }

        // 2) MIME type
        String contentType = immaginePart.getContentType();
        boolean okMime = "image/jpeg".equalsIgnoreCase(contentType) || "image/png".equalsIgnoreCase(contentType);
        if (!okMime) {
            response.sendRedirect(request.getContextPath() + "/Index?errore=FORMATO_FILE_NON_CONSENTITO");
            return null;
        }

        // 3) Estensione
        String fileName = extractFileName(immaginePart);
        if (fileName == null || fileName.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/Index?errore=NOME_FILE_NON_VALIDO");
            return null;
        }

        String lower = fileName.toLowerCase();
        boolean okExt = lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".png");
        if (!okExt) {
            response.sendRedirect(request.getContextPath() + "/Index?errore=ESTENSIONE_NON_CONSENTITA");
            return null;
        }

        // 4) Verifica immagine reale
        try (java.io.InputStream in = immaginePart.getInputStream()) {
            java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(in);
            if (img == null) {
                response.sendRedirect(request.getContextPath() + "/Index?errore=FILE_NON_E_IMMAGINE");
                return null;
            }
        }

        // 5) Salva su disco
        String estensione = lower.substring(lower.lastIndexOf('.'));
        String nomeUnico = java.util.UUID.randomUUID().toString() + estensione;

        java.io.File uploadDirFile = new java.io.File(uploadDir);
        if (!uploadDirFile.exists()) uploadDirFile.mkdirs();

        java.io.File fileDaSalvare = new java.io.File(uploadDirFile, nomeUnico);
        immaginePart.write(fileDaSalvare.getAbsolutePath());

        return fileDaSalvare.getAbsolutePath();
    }



    @Override
    public void init() throws ServletException {
        super.init();
     
        try {
            stub = new UserServiceStub();
            stub1 = new ActivityServiceStub();
        } catch (AxisFault e) {
            throw new ServletException("Errore inizializzazione stub", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("===== DEBUG PARAMETRI =====");
        
        // Debug tutti i parametri
        java.util.Enumeration<String> paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            System.out.println("Parametro: " + name + " = " + request.getParameter(name));
        }
        
        // Debug tutte le parti multipart
        try {
            for(Part part : request.getParts()) {
                System.out.println("Part: " + part.getName() + " | Size: " + part.getSize() + " | ContentType: " + part.getContentType());
            }
        } catch(Exception e) {
            System.out.println("Errore lettura parts: " + e.getMessage());
        }
        
        System.out.println("===== FINE DEBUG =====");
        
        String tipoAccount = request.getParameter("tipoAccount");
        System.out.println("[DEBUG] tipoAccount ricevuto: " + tipoAccount);
        
        int tipo;
        if (tipoAccount == null) {
            System.out.println("[ERROR] tipoAccount è null");
            response.sendRedirect("404.html");
            return;
        }
        
        boolean registrato = false;
        
        try {
            tipo = Integer.parseInt(tipoAccount);
            System.out.println("[DEBUG] tipo parsato: " + tipo);
        } catch (Exception e) {
            System.out.println("[ERROR] Parsing tipoAccount fallito: " + e.getMessage());
            response.sendRedirect("404.html");
            return;
        }
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("[DEBUG] Email: " + email);
        System.out.println("[DEBUG] Password length: " + (password != null ? password.length() : "null"));
        
        UserServiceStub.ValidaCredenziali req = new UserServiceStub.ValidaCredenziali();
        req.setEmail(email);
        req.setPassword(password);
        
        System.out.println("[DEBUG] Chiamata validaCredenziali...");
        UserServiceStub.ValidaCredenzialiResponse resp = stub.validaCredenziali(req);
        boolean valide = resp.get_return();
        System.out.println("[DEBUG] Credenziali valide: " + valide);
        
        if(!valide){
            System.out.println("[ERROR] Credenziali non valide");
            response.sendRedirect(request.getContextPath() + "/RichiediRegistrazione?FORMATO+CREDENZIALI+ERRATO");
            return;
        }
       
        if (tipo == 1) {
            System.out.println("[DEBUG] === REGISTRAZIONE UTENTE ===");
            
            String nome = request.getParameter("nomeUtente");
            String cognome = request.getParameter("cognomeUtente");
            String dataNascita = request.getParameter("data_di_nascita_Utente");
            String altezzaStr = request.getParameter("altezzaUtente");
            String pesoStr = request.getParameter("pesoUtente");
            
            System.out.println("[DEBUG] Nome: " + nome);
            System.out.println("[DEBUG] Cognome: " + cognome);
            System.out.println("[DEBUG] Data nascita: " + dataNascita);
            System.out.println("[DEBUG] Altezza: " + altezzaStr);
            System.out.println("[DEBUG] Peso: " + pesoStr);
            
            boolean datiMancanti = nome == null || nome.trim().isEmpty() ||
                    cognome == null || cognome.trim().isEmpty() ||
                    dataNascita == null || dataNascita.trim().isEmpty() ||
                    altezzaStr == null || altezzaStr.trim().isEmpty() ||
                    pesoStr == null || pesoStr.trim().isEmpty();

            if (datiMancanti) {
                System.out.println("[ERROR] Dati mancanti");
                response.sendRedirect(request.getContextPath() + "/Index?errore=DATI+MANCANTI");
                return;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dataNascitaDate;
            try {
                dataNascitaDate = sdf.parse(dataNascita.trim());
                System.out.println("[DEBUG] Data parsata: " + dataNascitaDate);
            } catch (ParseException e) {
                System.out.println("[ERROR] Parse data fallito: " + e.getMessage());
                response.sendRedirect(request.getContextPath() + "/Index?errore=DATA_NASCITA_NON_VALIDA");
                return;
            }
            
            int altezza;
            double peso;
            
            try {
                altezza = Integer.valueOf(altezzaStr.trim());
                System.out.println("[DEBUG] Altezza parsata: " + altezza);
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Parse altezza fallito: " + e.getMessage());
                response.sendRedirect(request.getContextPath() + "/Index?errore=ALTEZZA_NON_VALIDA");
                return;
            }
            
            try {
                pesoStr = pesoStr.trim().replace(',', '.');
                peso = Double.valueOf(pesoStr);
                System.out.println("[DEBUG] Peso parsato: " + peso);
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Parse peso fallito: " + e.getMessage());
                response.sendRedirect(request.getContextPath() + "/Index?errore=PESO_NON_VALIDO");
                return;
            }
            
            if (altezza < 100 || altezza > 250 || peso < 40.0 || peso > 300.0) {
                System.out.println("[ERROR] Altezza o peso fuori range");
                response.sendRedirect(request.getContextPath() + "/Index?errore=ALTEZZA_O_PESO_FUORI_RANGE");
                return;
            }
            
            System.out.println("[DEBUG] Validazione immagine utente...");
            String pathFoto = validaESalvaImmagine(request, response, "fotoProfilo", UPLOAD_DIR_UTENTI);
            System.out.println("[DEBUG] Path foto: " + pathFoto);
            if (pathFoto == null) {
                System.out.println("[ERROR] Foto non valida o ritorno null");
                return;
            }
            
            System.out.println("[DEBUG] Creazione UtenteDTO...");
            UtenteDTO utenteDTO = new UtenteDTO();
            utenteDTO.setEmail(email.trim());
            utenteDTO.setPassword(password);
            utenteDTO.setRuolo(1);
            utenteDTO.setNome(nome.trim());
            utenteDTO.setCognome(cognome.trim());
            utenteDTO.setData_di_nascita(dataNascitaDate);
            utenteDTO.setAltezza(altezza);
            utenteDTO.setPeso(peso);
            utenteDTO.setFotoPath(pathFoto);
            System.out.println("[DEBUG] DTO creato con successo");

            UserServiceStub.RegistrazioneUtente req1 = new UserServiceStub.RegistrazioneUtente();
            req1.setUtente(utenteDTO);

            System.out.println("[DEBUG] Invio richiesta registrazione utente al WS...");
            UserServiceStub.RegistrazioneUtenteResponse resp1 = stub.registrazioneUtente(req1);
            boolean rispostaUserService = resp1.get_return();
            System.out.println("[DEBUG] Risposta WS: " + rispostaUserService);
            
            if(rispostaUserService){
                registrato = true;
                System.out.println("[SUCCESS] Utente registrato");
            } else {
                System.out.println("[ERROR] Registrazione utente fallita");
            }

        } else if (tipo == 0) {
            System.out.println("[DEBUG] === REGISTRAZIONE ATTIVITÀ ===");
            
            String nomeAttivita = request.getParameter("nomeAttivita");
            String citta = request.getParameter("cittaAttivita");
            String tipoAttivitaStr = request.getParameter("tipoAttivita");
            
            System.out.println("[DEBUG] Nome attività: " + nomeAttivita);
            System.out.println("[DEBUG] Città: " + citta);
            System.out.println("[DEBUG] Tipo attività: " + tipoAttivitaStr);
            
            boolean datiMancanti = nomeAttivita == null || nomeAttivita.trim().isEmpty() ||
                    citta == null || citta.trim().isEmpty() ||
                    tipoAttivitaStr == null || tipoAttivitaStr.trim().isEmpty();

            if (datiMancanti) {
                System.out.println("[ERROR] Dati mancanti");
                response.sendRedirect(request.getContextPath() + "/Index?errore=DATI+MANCANTI");
                return;
            }
            
            int tipoAttivita;
            try {
                tipoAttivita = Integer.valueOf(tipoAttivitaStr.trim());
                System.out.println("[DEBUG] Tipo attività parsato: " + tipoAttivita);
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Parse tipo attività fallito: " + e.getMessage());
                response.sendRedirect(request.getContextPath() + "/Index?errore=TIPO_ATTIVITA_NON_VALIDO");
                return;
            }
            
            if (tipoAttivita < 0 || tipoAttivita > 2){
                System.out.println("[ERROR] Tipo attività fuori range: " + tipoAttivita);
                response.sendRedirect(request.getContextPath() + "/Index?errore=TIPOLOGIA_ATTIVITA_NON_VALIDA");
                return;
            }
            
            System.out.println("[DEBUG] Verifica città valida: " + citta);
            if(!verificaCittaValida(citta)){
                System.out.println("[ERROR] Città non valida: " + citta);
                response.sendRedirect(request.getContextPath() + "/Index?errore=CITTA_NON_VALIDA");
                return;
            }
            System.out.println("[DEBUG] Città validata");
            
            System.out.println("[DEBUG] Validazione logo attività...");
            String pathLogo = validaESalvaImmagine(request, response, "logoAttivita", UPLOAD_DIR_ATTIVITA);
            System.out.println("[DEBUG] Path logo: " + pathLogo);
            if (pathLogo == null) {
                System.out.println("[ERROR] Logo non valido o ritorno null");
                return;
            }

            System.out.println("[DEBUG] Creazione AttivitaDTO...");
            AttivitaDTO attivitaDTO = new AttivitaDTO();
            attivitaDTO.setEmail(email.trim());
            attivitaDTO.setPassword(password);
            attivitaDTO.setNomeAttivita(nomeAttivita.trim());
            attivitaDTO.setFotoPath(pathLogo);
            attivitaDTO.setTipo(tipoAttivita);
            attivitaDTO.setCitta(citta.trim());
            System.out.println("[DEBUG] DTO attività creato");
            
            ActivityServiceStub.RegistrazioneAttivita req2 = new ActivityServiceStub.RegistrazioneAttivita();
            req2.setAttivita(attivitaDTO);
            
            System.out.println("[DEBUG] Invio richiesta registrazione attività al WS...");
            ActivityServiceStub.RegistrazioneAttivitaResponse resp2 = stub1.registrazioneAttivita(req2);
            boolean rispostaActivityService = resp2.get_return();
            System.out.println("[DEBUG] Risposta WS: " + rispostaActivityService);
            
            if(rispostaActivityService){
                registrato = true;
                System.out.println("[SUCCESS] Attività registrata");
            } else {
                System.out.println("[ERROR] Registrazione attività fallita");
            }
            
        } else {
            System.out.println("[ERROR] Tipo account non riconosciuto: " + tipo);
            response.sendRedirect("404.html");
            return;
        }
        
        System.out.println("[DEBUG] Stato finale registrato: " + registrato);
        if (registrato) {
            System.out.println("[SUCCESS] Redirect a successo");
            response.sendRedirect(request.getContextPath() + "/Index?success=REGISTRATO");
        } else {
            System.out.println("[ERROR] Redirect a errore");
            response.sendRedirect(request.getContextPath() + "/Index?errore=REGISTRAZIONE_FALLITA");
        }
        
        System.out.println("===== FINE REGISTRAZIONE =====");
        return;
    }
}