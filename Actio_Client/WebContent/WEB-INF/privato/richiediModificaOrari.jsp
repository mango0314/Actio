<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import=" it.actio.activity.services.ActivityServiceStub.OrarioCorsoDTO" %>

<!DOCTYPE html>
<html lang="it">

<head>

<% OrarioCorsoDTO[] orari = (OrarioCorsoDTO[]) request.getAttribute("orari"); %>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  
  <title>Actio - Modifica Orari</title>

  <meta name="description" content="">
  <meta name="keywords" content="">

  <!-- Favicons -->
  
  <link rel="apple-touch-icon" sizes="57x57" href="<%= request.getContextPath() %>/favicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="<%= request.getContextPath() %>/favicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%= request.getContextPath() %>/favicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="<%= request.getContextPath() %>/favicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%= request.getContextPath() %>/favicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="<%= request.getContextPath() %>/favicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%= request.getContextPath() %>/favicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="<%= request.getContextPath() %>/favicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="<%= request.getContextPath() %>/favicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="<%= request.getContextPath() %>/favicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="<%= request.getContextPath() %>/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="<%= request.getContextPath() %>/favicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="<%= request.getContextPath() %>/favicon/favicon-16x16.png">
<link rel="manifest" href="<%= request.getContextPath() %>/favicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="<%= request.getContextPath() %>/favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

  <!-- Fonts -->
  <link href="https://fonts.googleapis.com" rel="preconnect">
  <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="<%= request.getContextPath() %>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/vendor/aos/aos.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Main CSS File -->
  <link href="<%= request.getContextPath() %>/css/main.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: Axis
  * Template URL: https://bootstrapmade.com/axis-bootstrap-corporate-template/
  * Updated: Sep 13 2025 with Bootstrap v5.3.8
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body class="index-page">

  <header id="header" class="header d-flex align-items-center fixed-top">
    <div class="container-fluid container-xl position-relative d-flex align-items-center justify-content-between">

      <a href="index.jsp" class="logo d-flex align-items-center" >
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <img src="<%= request.getContextPath() %>/img/logo_actio.png" alt="logo" >
        <!-- <h1 class="sitename">Actio</h1> -->
      </a>

      <nav id="navmenu" class="navmenu">
        <ul>
          <li><a href="#hero" class="active">Home</a></li>
          <li><a href="#services">Servizi</a></li>
          <li><a href="#about">Chi siamo</a></li>
          <li><a href="#portfolio">Portfolio</a></li>
          <li class="dropdown"><a href="#"><span>Dropdown</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a>
            <ul>
              <li><a href="#">Dropdown 1</a></li>
              <li class="dropdown"><a href="#"><span>Deep Dropdown</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a>
                <ul>
                  <li><a href="#">Deep Dropdown 1</a></li>
                  <li><a href="#">Deep Dropdown 2</a></li>
                  <li><a href="#">Deep Dropdown 3</a></li>
                  <li><a href="#">Deep Dropdown 4</a></li>
                  <li><a href="#">Deep Dropdown 5</a></li>
                </ul>
              </li>
              <li><a href="#">Dropdown 2</a></li>
              <li><a href="#">Dropdown 3</a></li>
              <li><a href="#">Dropdown 4</a></li>
            </ul>
          </li>
          <li><a href="#contact">Contatti</a></li>
          <li><a href=RichiediLogin>Accedi</a></li>
        </ul>
        <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
      </nav>

    </div>
  </header>
  

  
  

  <main class="main">

    <!-- Hero Section -->
 <% int idCorso = (int) request.getAttribute("idCorso"); %>

    <!-- Contact Section -->
    <section id="contact" class="contact section light-background">
      <!-- Section Title -->
      <div class="container section-title" data-aos="fade-up">
        <span class="subtitle">Contatti</span>
        <h2>Compila il form</h2>
        <p>E' possibile contattarci attraverso i recapiti o il form sotto riportati. Vi risponderemo il prima possibile.</p>
      </div><!-- End Section Title -->
     <div class="container mt-5 mb-5">
        <div class="card shadow">
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0">Gestione Orari Corso</h4>
            </div>
            <div class="card-body">
                
                <form action="ModificaOrari" method="POST">
                    <input type="hidden" name="corso_id" value="<%= idCorso %>">

                    <div class="table-responsive">
                        <table class="table table-bordered align-middle">
                            <thead class="table-light">
                                <tr>
                                    <th style="width: 30%">Giorno</th>
                                    <th>Inizio</th>
                                    <th>Fine</th>
                                    <th style="width: 50px"></th>
                                </tr>
                            </thead>
                            <tbody id="orariBody">
                                <% 
                                if (orari != null) {
                                    for (OrarioCorsoDTO o : orari) { 
                                %>
                                    <!-- RIGA ESISTENTE -->
                                    <tr>
                                        <td>
                                            <select name="giorno[]" class="form-select" required>
                                                <option value="0" <%= o.getGiornoSettimana() == 0 ? "selected" : "" %>>Lunedì</option>
                                                <option value="1" <%= o.getGiornoSettimana() == 1 ? "selected" : "" %>>Martedì</option>
                                                <option value="2" <%= o.getGiornoSettimana() == 2 ? "selected" : "" %>>Mercoledì</option>
                                                <option value="3" <%= o.getGiornoSettimana() == 3 ? "selected" : "" %>>Giovedì</option>
                                                <option value="4" <%= o.getGiornoSettimana() == 4 ? "selected" : "" %>>Venerdì</option>
                                                <option value="5" <%= o.getGiornoSettimana() == 5 ? "selected" : "" %>>Sabato</option>
                                                <option value="6" <%= o.getGiornoSettimana() == 6 ? "selected" : "" %>>Domenica</option>
                                            </select>
                                        </td>
                                        <td>
                                            <!-- Validazione sicura fatta lato server, qui stampiamo diretto -->
                                            <input type="time" name="oraInizio[]" class="form-control" value="<%= o.getOrarioInizio() %>" required>
                                        </td>
                                        <td>
                                            <input type="time" name="oraFine[]" class="form-control" value="<%= o.getOrarioFine() %>" required>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-outline-danger btn-sm" onclick="rimuoviRiga(this)">
                                                <i class="bi bi-trash"></i> X
                                            </button>
                                        </td>
                                    </tr>
                                <% 
                                    } 
                                } 
                                %>
                            </tbody>
                        </table>
                    </div>

                    <div class="d-flex justify-content-between">
                        <button type="button" class="btn btn-success" onclick="aggiungiRiga()">
                            + Aggiungi Riga
                        </button>
                        <button type="submit" class="btn btn-primary px-4">
                            Salva Modifiche
                        </button>
                    </div>
                </form>

            </div>
        </div>
    </div>
    </section>

  </main>

  <footer id="footer" class="footer dark-background">

    <div class="container footer-top">
      <div class="row gy-4">
        <div class="col-lg-5 col-md-12 footer-about">
          <a href="index.html" class="logo d-flex align-items-center">
            <span class="sitename">Actio</span>
          </a>
          <p>Cras fermentum odio eu feugiat lide par naso tierra. Justo eget nada terra videa magna derita valies darta donna mare fermentum iaculis eu non diam phasellus.</p>
          <div class="social-links d-flex mt-4">
            <a href=""><i class="bi bi-twitter-x"></i></a>
            <a href=""><i class="bi bi-facebook"></i></a>
            <a href=""><i class="bi bi-instagram"></i></a>
            <a href=""><i class="bi bi-linkedin"></i></a>
          </div>
        </div>

        <div class="col-lg-2 col-6 footer-links">
          <h4>Useful Links</h4>
          <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">About us</a></li>
            <li><a href="#">Services</a></li>
            <li><a href="#">Terms of service</a></li>
            <li><a href="#">Privacy policy</a></li>
          </ul>
        </div>

        <div class="col-lg-2 col-6 footer-links">
          <h4>Our Services</h4>
          <ul>
            <li><a href="#">Web Design</a></li>
            <li><a href="#">Web Development</a></li>
            <li><a href="#">Product Management</a></li>
            <li><a href="#">Marketing</a></li>
            <li><a href="#">Graphic Design</a></li>
          </ul>
        </div>

        <div class="col-lg-3 col-md-12 footer-contact text-center text-md-start">
          <h4>Contattaci</h4>
          <p>Via Graziella Feo di vito</p>
          <p>Reggio Calabria, RC 84508</p>
          <p>Italia</p>
          <p class="mt-4"><strong>Phone:</strong> <span>+1 5589 55488 55</span></p>
          <p><strong>Email:</strong> <span>andrea.marengo.2003@gmail.com</span></p>
        </div>

      </div>
    </div>

    <div class="container copyright text-center mt-4">
      <p>@2025 <span>Copyright</span> <strong class="px-1 sitename">Actio</strong> <span>Tutti i diritti riservati</span></p>
      <div class="credits">
        <!-- All the links in the footer should remain intact. -->
        <!-- You can delete the links only if you've purchased the pro version. -->
        <!-- Licensing information: https://bootstrapmade.com/license/ -->
        <!-- Purchase the pro version with working PHP/AJAX contact form: [buy-url] -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>

  </footer>

  <!-- Scroll Top -->
  <a href="#" id="scroll-top" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Preloader -->
  <div id="preloader"></div>

  <!-- Vendor JS Files -->
  <script src="<%= request.getContextPath() %>/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
 <!--   <script src="vendor/php-email-form/validate.js"></script> -->
  <script src="<%= request.getContextPath() %>/vendor/aos/aos.js"></script>
  <script src="<%= request.getContextPath() %>/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="<%= request.getContextPath() %>/vendor/purecounter/purecounter_vanilla.js"></script>
  <script src="<%= request.getContextPath() %>/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
  <script src="<%= request.getContextPath() %>/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="<%= request.getContextPath() %>/vendor/swiper/swiper-bundle.min.js"></script>
  <script src='<%= request.getContextPath() %>/js/aggiungiRiga.js'></script>

  <!-- Main JS File -->
  <script src="<%= request.getContextPath() %>/js/main.js"></script>

</body>

</html>