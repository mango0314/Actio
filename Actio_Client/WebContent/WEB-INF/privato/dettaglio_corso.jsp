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
  <title>Actio - Area riservata</title>
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
          <li><a href="#corsi">Corsi</a></li>
          <li><a href="Esplora">Esplora</a></li>
          <li><a href="#contact">Contatti</a></li>
          <li><a href=Login>Accedi</a></li>
        </ul>
        <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
      </nav>

    </div>
  </header>

  <main class="main">

    <% CorsoConAttivitaDTO corso_conAttivita_e_PostiRimasti = (CorsoConAttivitaDTO) request.getAttribute("corso_conAttivita_e_PostiRimasti"); %>
    
    <section id="hero" class="hero section light-background">

      <div class="container" data-aos="fade-up" data-aos-delay="100">

        <div class="row align-items-center">
          <div class="col-lg-6">
            <div class="hero-content">
              <h1 data-aos="fade-up" data-aos-delay="200"><%= corso_conAttivita_e_PostiRimasti.getNomeCorso() %></h1>
              <p data-aos="fade-up" data-aos-delay="300"><%= corso_conAttivita_e_PostiRimasti.getDescrizione() %></p>
              <div class="hero-cta" data-aos="fade-up" data-aos-delay="400">
                <a href=#orari class="btn-primary">Orari <i class="bi bi-arrow-down"></i></a>
                <a href="#https://www.youtube.com/watch?v=Y7f98aduVJ8" class="btn-secondary glightbox">
                  <i class="bi bi-play-circle"></i>
                  Watch Demo
                </a>
              </div>
              <div class="hero-stats" data-aos="fade-up" data-aos-delay="500">
                <div class="stat-item">
                  <div class="stat-number"><%= corso_conAttivita_e_PostiRimasti.getNomeAttivita() %></div>
                  <div class="stat-label">Attività</div>
                </div>
                <div class="stat-item">
                  <div class="stat-number"><%= corso_conAttivita_e_PostiRimasti.getPostiRimasti() %></div>
                  <div class="stat-label">Posti rimasti</div>
                </div>
                
              </div>
            </div>
          </div>

          <div class="col-lg-6">
            <div class="hero-image" data-aos="fade-left" data-aos-delay="300">
              <img src="img/img_fitness.png" alt="img pilates" class="img-fluid">
              </div>
          </div>
        </div>

      </div>

    </section><!-- /Hero Section -->
    
       <section id="orari" class="about section">

      <!-- Section Title -->
      <div class="container section-title" data-aos="fade-up">
        <span class="subtitle">Orari</span>
        <h2> Tabella orari </h2>
        <p>
			La nostra missione è semplificare la vita di chi organizza corsi e di chi li frequenta, 
	creando un ecosistema unico dove palestre, scuole sportive, istruttori e appassionati si incontrano.</p>
      </div><!-- End Section Title -->

      <div class="container" data-aos="fade-up" data-aos-delay="100">
    <div class="row justify-content-center">
        <div class="col-lg-10" data-aos="fade-right" data-aos-delay="200">

            <div class="content d-flex justify-content-center">
                <table class="table table-lg">
                    <thead>
                        <tr>
                            <th scope="col">Giorno</th>
                            <th scope="col">Ora inizio</th>
                            <th scope="col">Ora fine</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">Lunedì</th>
                            <td>19:00</td>
                            <td>20:30</td>
                        </tr>
                        <tr>
                            <th scope="row">Meercoledì</th>
                            <td>17:00</td>
                            <td>18:30</td>
                        </tr>
                        <tr>
                            <th scope="row">Venerdì</th>
                            <td>18:00</td>
                            <td>10:30</td>
                        </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>

          

    </section><!-- /About Section -->
   

    <!-- Portfolio Section -->
    <section id="portfolio" class="portfolio section">

      <!-- Section Title -->
      <div class="container section-title" data-aos="fade-up">
        <span class="subtitle">Portfolio</span>
        <h2>Check Our Portfolio</h2>
        <p>Necessitatibus eius consequatur ex aliquid fuga eum quidem sint consectetur velit. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium totam rem aperiam</p>
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

            <div class="col-lg-12 portfolio-item isotope-item filter-design">
              <article class="portfolio-card">
                <div class="row g-4">
                  <div class="col-md-6">
                    <div class="project-visual">
                      <img src="img/portfolio/portfolio-1.webp" alt="Enterprise Digital Platform" class="img-fluid" loading="lazy">
                      <div class="project-overlay">
                        <div class="overlay-content">
                          <a href="img/portfolio/portfolio-1.webp" class="view-project glightbox" aria-label="View project image">
                            <i class="bi bi-eye"></i>
                          </a>
                          <a href="#" class="project-link" aria-label="View project details">
                            <i class="bi bi-arrow-up-right"></i>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="project-details">
                      <div class="project-header">
                        <span class="project-category">Digital Design</span>
                        <time class="project-year">2024</time>
                      </div>
                      <h3 class="project-title">Enterprise Digital Platform</h3>
                      <p class="project-description">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium totam rem aperiam.</p>
                      <div class="project-meta">
                        <span class="client-name">Fortune 500 Company</span>
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

            <div class="col-lg-12 portfolio-item isotope-item filter-development">
              <article class="portfolio-card">
                <div class="row g-4">
                  <div class="col-md-6 order-md-2">
                    <div class="project-visual">
                      <img src="img/portfolio/portfolio-3.webp" alt="SaaS Product Suite" class="img-fluid" loading="lazy">
                      <div class="project-overlay">
                        <div class="overlay-content">
                          <a href="img/portfolio/portfolio-3.webp" class="view-project glightbox" aria-label="View project image">
                            <i class="bi bi-eye"></i>
                          </a>
                          <a href="#" class="project-link" aria-label="View project details">
                            <i class="bi bi-arrow-up-right"></i>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6 order-md-1">
                    <div class="project-details">
                      <div class="project-header">
                        <span class="project-category">Development</span>
                        <time class="project-year">2024</time>
                      </div>
                      <h3 class="project-title">SaaS Product Suite</h3>
                      <p class="project-description">At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti.</p>
                      <div class="project-meta">
                        <span class="client-name">Tech Startup</span>
                        <div class="project-scope">
                          <span class="scope-item">Full Stack</span>
                          <span class="scope-item">Cloud Architecture</span>
                          <span class="scope-item">DevOps</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </article>
            </div>

            <div class="col-lg-12 portfolio-item isotope-item filter-strategy">
              <article class="portfolio-card">
                <div class="row g-4">
                  <div class="col-md-6">
                    <div class="project-visual">
                      <img src="img/portfolio/portfolio-5.webp" alt="Brand Transformation" class="img-fluid" loading="lazy">
                      <div class="project-overlay">
                        <div class="overlay-content">
                          <a href="img/portfolio/portfolio-5.webp" class="view-project glightbox" aria-label="View project image">
                            <i class="bi bi-eye"></i>
                          </a>
                          <a href="#" class="project-link" aria-label="View project details">
                            <i class="bi bi-arrow-up-right"></i>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="project-details">
                      <div class="project-header">
                        <span class="project-category">Strategy</span>
                        <time class="project-year">2023</time>
                      </div>
                      <h3 class="project-title">Brand Transformation</h3>
                      <p class="project-description">Excepteur sint occaecat cupidatat non proident sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                      <div class="project-meta">
                        <span class="client-name">Global Corporation</span>
                        <div class="project-scope">
                          <span class="scope-item">Brand Strategy</span>
                          <span class="scope-item">Visual Identity</span>
                          <span class="scope-item">Guidelines</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </article>
            </div>

            <div class="col-lg-12 portfolio-item isotope-item filter-consulting">
              <article class="portfolio-card">
                <div class="row g-4">
                  <div class="col-md-6 order-md-2">
                    <div class="project-visual">
                      <img src="img/portfolio/portfolio-6.webp" alt="Digital Transformation" class="img-fluid" loading="lazy">
                      <div class="project-overlay">
                        <div class="overlay-content">
                          <a href="img/portfolio/portfolio-6.webp" class="view-project glightbox" aria-label="View project image">
                            <i class="bi bi-eye"></i>
                          </a>
                          <a href="#" class="project-link" aria-label="View project details">
                            <i class="bi bi-arrow-up-right"></i>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6 order-md-1">
                    <div class="project-details">
                      <div class="project-header">
                        <span class="project-category">Consulting</span>
                        <time class="project-year">2024</time>
                      </div>
                      <h3 class="project-title">Digital Transformation</h3>
                      <p class="project-description">Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                      <div class="project-meta">
                        <span class="client-name">Healthcare Provider</span>
                        <div class="project-scope">
                          <span class="scope-item">Process Optimization</span>
                          <span class="scope-item">Technology Audit</span>
                          <span class="scope-item">Implementation</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </article>
            </div>

            <div class="col-lg-12 portfolio-item isotope-item filter-design">
              <article class="portfolio-card">
                <div class="row g-4">
                  <div class="col-md-6">
                    <div class="project-visual">
                      <img src="img/portfolio/portfolio-2.webp" alt="E-commerce Experience" class="img-fluid" loading="lazy">
                      <div class="project-overlay">
                        <div class="overlay-content">
                          <a href="img/portfolio/portfolio-2.webp" class="view-project glightbox" aria-label="View project image">
                            <i class="bi bi-eye"></i>
                          </a>
                          <a href="#" class="project-link" aria-label="View project details">
                            <i class="bi bi-arrow-up-right"></i>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="project-details">
                      <div class="project-header">
                        <span class="project-category">Digital Design</span>
                        <time class="project-year">2024</time>
                      </div>
                      <h3 class="project-title">E-commerce Experience</h3>
                      <p class="project-description">Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur excepteur sint.</p>
                      <div class="project-meta">
                        <span class="client-name">Retail Brand</span>
                        <div class="project-scope">
                          <span class="scope-item">User Experience</span>
                          <span class="scope-item">Interface Design</span>
                          <span class="scope-item">Conversion Optimization</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </article>
            </div>

            <div class="col-lg-12 portfolio-item isotope-item filter-development">
              <article class="portfolio-card">
                <div class="row g-4">
                  <div class="col-md-6 order-md-2">
                    <div class="project-visual">
                      <img src="img/portfolio/portfolio-4.webp" alt="Mobile Application" class="img-fluid" loading="lazy">
                      <div class="project-overlay">
                        <div class="overlay-content">
                          <a href="img/portfolio/portfolio-4.webp" class="view-project glightbox" aria-label="View project image">
                            <i class="bi bi-eye"></i>
                          </a>
                          <a href="#" class="project-link" aria-label="View project details">
                            <i class="bi bi-arrow-up-right"></i>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6 order-md-1">
                    <div class="project-details">
                      <div class="project-header">
                        <span class="project-category">Development</span>
                        <time class="project-year">2023</time>
                      </div>
                      <h3 class="project-title">Mobile Application</h3>
                      <p class="project-description">Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat duis aute.</p>
                      <div class="project-meta">
                        <span class="client-name">Financial Services</span>
                        <div class="project-scope">
                          <span class="scope-item">iOS Development</span>
                          <span class="scope-item">Android Development</span>
                          <span class="scope-item">API Integration</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </article>
            </div>

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

</body>

</html>