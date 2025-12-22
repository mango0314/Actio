<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>Actio - Home</title>
  <meta name="description" content="">
  <meta name="keywords" content="">

  <!-- Favicons -->
  
  <link rel="apple-touch-icon" sizes="57x57" href="favicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="favicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="favicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="favicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="favicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="favicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="favicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="favicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="favicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="favicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="favicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="favicon/favicon-16x16.png">
<link rel="manifest" href="favicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

  <!-- Fonts -->
  <link href="https://fonts.googleapis.com" rel="preconnect">
  <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="vendor/aos/aos.css" rel="stylesheet">
  <link href="vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Main CSS File -->
  <link href="css/main.css" rel="stylesheet">

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
        <img src="img/logo_actio.png" alt="logo" >
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
 

    <!-- Contact Section -->
    <section id="contact" class="contact section light-background">
      <!-- Section Title -->
      <div class="container section-title" data-aos="fade-up">
        <span class="subtitle">Contatti</span>
        <h2>Compila il form</h2>
        <p>E' possibile contattarci attraverso i recapiti o il form sotto riportati. Vi risponderemo il prima possibile.</p>
      </div><!-- End Section Title -->

      <div class="container">
		  <div class="row gy-4 justify-content-center">
		    <div class="col-12 col-md-10 col-lg-7">

            <div class="form-wrapper">
              <div class="form-header">
                <h3>Form</h3>
              </div>

              <form action="Registrazione" method="post" class="php-email-form" enctype="multipart/form-data">

                <div class="row mb-4">
				  <div class="col-12 d-flex justify-content-center gap-5">
				    <div class="form-check">
				      <input class="form-check-input" type="radio" name="tipoAccount" id="radioUtente" value="1" required>
				      <label class="form-check-label" for="radioUtente">Utente</label>
				    </div>
				
				    <div class="form-check">
				      <input class="form-check-input" type="radio" name="tipoAccount" id="radioAttivita" value="0">
				      <label class="form-check-label" for="radioAttivita">Attività</label>
				    </div>
				  </div>
				</div>
				
				<div class="col-md-6">
                    <div class="form-group">
                      <label>Email </label>
                      <input type="email" name="email" required="">
                    </div>
                  </div>
                  
                  <div class="col-md-6">
                    <div class="form-group">
                      <label>Password </label>
                      <input type="password" name="password" required="">
                    </div>
                  </div>
				
				<div id="campiUtente" class="mt-4 d-none">
					  <div class="row">
					    <div class="col-md-6">
					      <div class="form-group">
					        <label>Nome</label>
					        <input type="text" name="nomeUtente" class="form-control">
					      </div>
					    </div>
					
					    <div class="col-md-6">
					      <div class="form-group">
					        <label>Cognome</label>
					        <input type="text" name="cognomeUtente" class="form-control">
					      </div>
					    </div>
					    
					    <div class="col-md-6">
					      <div class="form-group">
					        <label>Data di nascita</label>
					        <input type="date" name="data_di_nascita_Utente" class="form-control">
					      </div>
					    </div>
					    
					    <div class="col-md-6">
						  <input type="number" class="form-control" name="altezzaUtente"
						         min="100" max="250" step="1" inputmode="numeric"
						         aria-label="Altezza in centimetri">
						  <span class="input-group-text">cm</span>
						</div>
						
						<div class="col-md-6">
						  <input type="number" class="form-control" name="pesoUtente"
						         min="40" max="300" step="0.1" inputmode="decimal"
						         aria-label="Peso in chilogrammi">
						  <span class="input-group-text">kg</span>
						</div>

						<div class="mb-3">
							  <label for="fotoProfilo" class="form-label">Carica la tua foto profilo</label>
							  <input class="form-control" type="file" id="fotoProfilo" name="fotoProfilo" accept="image/png, image/jpeg">
							</div>
					  </div>
					</div>
					
					<div id="campiAttivita" class="mt-4 d-none">
					  <div class="col-md-6">
					      <div class="form-group">
					        <label>Nome Attività</label>
					        <input type="text" name="nomeAttivita" class="form-control">
					      </div>
					    </div>
					    
					    <div class="col-md-6">
					      <div class="form-group">
					        <label>Città</label>
					        <input type="text" name="cittaAttivita" class="form-control">
					      </div>
					    </div>
					    
					    <select class="form-select" name="tipoAttivita" aria-label="Tipo di attività sportiva">
						  
						  <option value="0">Palestra</option>
						  <option value="1">Scuola di ballo</option>
						  <option value="2">Associazione sportiva</option>
						</select>
					  
					  
					
					  <div class="mb-3">
							  <label for="logoAttivita" class="form-label">Carica il logo dell'azienda</label>
							  <input class="form-control" type="file" id="logoAttivita" name="logoAttivita" accept="image/png, image/jpeg">
							</div>
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
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="vendor/php-email-form/validate.js"></script>
  <script src="vendor/aos/aos.js"></script>
  <script src="vendor/glightbox/js/glightbox.min.js"></script>
  <script src="vendor/purecounter/purecounter_vanilla.js"></script>
  <script src="vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
  <script src="vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="vendor/swiper/swiper-bundle.min.js"></script>

  <!-- Main JS File -->
  <script src="js/main.js"></script>
  <script src="js/form_registrazione.js"></script>

</body>

</html>