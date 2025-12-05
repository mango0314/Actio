<%@page import="it.actio.services.UserServiceStub.CorsoConAttivitaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import=" it.actio.services.UserServiceStub.Corso" %>

<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="it">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>Actio - Esplora</title>
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

      <a href="Index_privato" class="logo d-flex align-items-center" >
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <img src="<%= request.getContextPath() %>/img/logo_actio.png" alt="logo" >
        <!-- <h1 class="sitename">Actio</h1> -->
      </a>

      <nav id="navmenu" class="navmenu">
        <ul>
          <li><a href="Index_privato" class="active">Home</a></li>
          <li><form class="d-flex" role="search" action="Esplora" method="GET">
        <input class="form-control me-2" type="search" id = "parole_chiave" name="parole_chiave" placeholder="Scrivi qualcosa..." aria-label="Search"/>
        <button class="btn btn-outline-success" type="submit">Cerca</button>
      </form></li>
          <li><a href=Logout>Logout</a></li>
        </ul>
        <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
      </nav>

    </div>
  </header>

  <main class="main">

    <% CorsoConAttivitaDTO[] corsi_conPostiRimasti = (CorsoConAttivitaDTO[]) request.getAttribute("corsi_conPostiRimasti"); %>
    

    <!-- Portfolio Section -->
    <section id="portfolio" class="portfolio section">

      <!-- Section Title -->
      <div class="container section-title" data-aos="fade-up">
        <span class="subtitle">Esplora</span>
        <h2>Scopri i corsi disponibili</h2>
      </div><!-- End Section Title -->

      <div class="container" data-aos="fade-up" data-aos-delay="100">

        <div class="isotope-layout" data-default-filter="*" data-layout="masonry" data-sort="original-order">
          <ul class="portfolio-filters isotope-filters" data-aos="fade-up" data-aos-delay="200">
            <li data-filter="*" class="filter-active">All Work</li>
            <li data-filter=".filter-design">Digital Design</li>
            <li data-filter=".filter-development">Development</li>
            <li data-filter=".filter-strategy">Strategy</li>
            <li data-filter=".filter-consulting">Consulting</li>
          </ul><!-- End Portfolio Filters -->

          <div class="row gy-5 isotope-container" data-aos="fade-up" data-aos-delay="300">
          
          <% if (corsi_conPostiRimasti != null && corsi_conPostiRimasti.length != 0){
       		for(CorsoConAttivitaDTO c : corsi_conPostiRimasti){
        	%>

            <div class="col-lg-12 portfolio-item isotope-item filter-design">
              <article class="portfolio-card">
                <div class="row g-4">
                  <div class="col-md-6">
                    <div class="project-visual">
                      <img src="<%= request.getContextPath() %>/img/portfolio/portfolio-1.webp" alt="Enterprise Digital Platform" class="img-fluid" loading="lazy">
                      <div class="project-overlay">
                        <div class="overlay-content">
                          <a href="<%= request.getContextPath() %>/img/portfolio/portfolio-1.webp" class="view-project glightbox" aria-label="View project image">
                            <i class="bi bi-eye"></i>
                          </a>
                          <a href="DettaglioCorso?idCorso=<%= c.getId() %>" class="project-link" aria-label="View project details">
                            <i class="bi bi-arrow-up-right"></i>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="project-details">
                      <div class="project-header">
                        <span class="project-category"><%= c.getNomeAttivita() %></span>
                      </div>
                      <h3 class="project-title"><%= c.getNomeCorso() %></h3>
                      <p class="project-description"><%= c.getDescrizione() %></p>
                      <div class="project-meta">
                        <span class="client-name">Posti rimanenti: <%= c.getCapienza() %></span>
                        <div class="project-scope">
                          <span class="scope-item">UX Design</span>
                          <span class="scope-item">Development</span>
                          <span class="scope-item">Strategy</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </article>
            </div>
            
             <% 	}
        	} else {
        	%>
        	
        	<div class="col-12">
      			<div class="alert alert-info text-center">
        		Nessun corso trovato.
      		</div>
    		</div>
    		
    		<% } %>

            
          </div><!-- End Portfolio Items Container -->

        </div>

      </div>

    </section><!-- /Portfolio Section -->

    <!-- Contact Section -->
    <section id="contact" class="contact section light-background">
      <!-- Section Title -->
      <div class="container section-title" data-aos="fade-up">
        <span class="subtitle">Contatti</span>
        <h2>Compila il form</h2>
        <p>E' possibile contattarci attraverso i recapiti o il form sotto riportati. Vi risponderemo il prima possibile.</p>
      </div><!-- End Section Title -->

      <div class="container">
        <div class="row gy-4">
          <div class="col-lg-5">

            <div class="info-item">
              <div class="info-icon">
                <i class="bi bi-chat-dots"></i>
              </div>
              <div class="info-content">
                <h4>Recapiti</h4>
              </div>
            </div>

            <div class="contact-details">

              <div class="detail-item">
                <div class="detail-icon">
                  <i class="bi bi-envelope-open"></i>
                </div>
                <div class="detail-content">
                  <span class="detail-label">Email</span>
                  <span class="detail-value">andrea.marengo.2003@gmail.com</span>
                </div>
              </div>

              <div class="detail-item">
                <div class="detail-icon">
                  <i class="bi bi-telephone-outbound"></i>
                </div>
                <div class="detail-content">
                  <span class="detail-label">Telefono</span>
                  <span class="detail-value">+39 3333333</span>
                </div>
              </div>

              <div class="detail-item">
                <div class="detail-icon">
                  <i class="bi bi-geo-alt-fill"></i>
                </div>
                <div class="detail-content">
                  <span class="detail-label">Viene a trovarci</span>
                  <span class="detail-value">Via Graziella Feo di Vito<br>Reggio Calabria, RC 10022</span>
                </div>
              </div>

            </div>

          </div>

          <div class="col-lg-7">
            <div class="form-wrapper">
              <div class="form-header">
                <h3>Form</h3>
              </div>

              <form action="forms/contact.php" method="post" class="php-email-form">

                <div class="row">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label>Nome</label>
                      <input type="text" name="name" required="">
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label>Email </label>
                      <input type="email" name="email" required="">
                    </div>
                  </div>
                </div>

                <div class="form-group">
                  <label>Oggetto</label>
                  <input type="text" name="subject" required="">
                </div>

                <div class="form-group">
                  <label for="projectMessage">Messaggio</label>
                  <textarea name="message" id="projectMessage" rows="5" required=""></textarea>
                </div>

                <div class="my-3">
                  <div class="loading">Loading</div>
                  <div class="error-message"></div>
                  <div class="sent-message">Il tuo messaggio è stato inviato. Grazie!</div>
                </div>

                <button type="submit" class="submit-btn">
                  <span>Invia</span>
                  <i class="bi bi-arrow-right"></i>
                </button>

              </form>

            </div>

          </div>

        </div>
      </div>

    </section><!-- /Contact Section -->

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
  <script src="<%= request.getContextPath() %>/vendor/php-email-form/validate.js"></script>
  <script src="<%= request.getContextPath() %>/vendor/aos/aos.js"></script>
  <script src="<%= request.getContextPath() %>/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="<%= request.getContextPath() %>/vendor/purecounter/purecounter_vanilla.js"></script>
  <script src="<%= request.getContextPath() %>/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
  <script src="<%= request.getContextPath() %>/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="<%= request.getContextPath() %>/vendor/swiper/swiper-bundle.min.js"></script>

  <!-- Main JS File -->
  <script src="<%= request.getContextPath() %>/js/main.js"></script>

</body>

</html>