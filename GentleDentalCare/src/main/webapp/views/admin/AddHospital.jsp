<%@page import="com.khl.gentledentalcare.models.HospitalError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HospitalError dentistError = (HospitalError) request.getAttribute("DENTIST_ERROR");
    if(dentistError == null) {
        dentistError = new HospitalError();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${BUTTON_ACTION}</title>
        <!-- Favicon icon -->
        <link rel="icon" href=".././images/favicon-100x100.png" type="image/png" sizes="200x138" />
        <!-- Iconscout CSS -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <!-- LINK BOOTSTRAP 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <!-- LINK FONTAWESOME -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.1.1/css/all.css">
        <!-- Box Icons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- LINK STYLE -->
        <link href=".././css/admin/AdminRoot.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/NavBar.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/SideBar.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/AddHospital.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/FooterPage.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../../layouts/admin/SideBar.jsp"></jsp:include>
        
        <section id="content">
            <jsp:include page="../../layouts/admin/NavBar.jsp"></jsp:include>
            
            <!-- MAIN -->
            <main class="main">
                <h1 class="title">${BUTTON_ACTION}</h1>
                <ul class="breadcrumbs">
                    <li><a href="${pageContext.request.contextPath}/admin/dashboard">Gentle Dental Care</a></li>
                    <li class="divider">/</li>
                    <li><a href="${pageContext.request.contextPath}/admin/add-hospital" class="active">${BUTTON_ACTION}</a></li>
                </ul>

                <div class="form_wrapper">
                    <div class="form_container">
                        <div class="title_container">
                            <h2>${BUTTON_ACTION}</h2>
                        </div>
                        <div class="row clearfix">
                            <div>
                                <form action="${ACTION_URL}" method="post">
                                    <div class="input_field">
                                        <span>
                                            <i class="fa-solid fa-font"></i>
                                        </span>
                                        <input type="text" class="hospital-name" name="hospitalName" placeholder="Hospital Name"
                                            oninput="CheckHospitalName()" onblur="CheckHospitalName()" />
                                    </div>
                                    <div class="text-danger hospital-name-error">
                                        <p></p>
                                    </div>
                                    <div class="input_field">
                                        <span>
                                            <i class="fa-solid fa-mobile"></i>
                                        </span>
                                        <input type="number" class="hospital-phone" name="hospitalPhone"
                                            placeholder="Hospital Phone" oninput="CheckHospitalPhone()"
                                            onblur="CheckHospitalPhone()" />
                                    </div>
                                    <div class="text-danger hospital-phone-error">
                                        <p></p>
                                    </div>
                                    <div class="input_field">
                                        <span>
                                            <i class="fa-solid fa-location-dot"></i>
                                        </span>
                                        <input type="text" class="hospital-address" name="hospitalAddress"
                                            placeholder="Hospital Address" oninput="CheckHospitalAddress()"
                                            onblur="CheckHospitalAddress()" />
                                    </div>
                                    <div class="text-danger hospital-address-error">
                                        <p></p>
                                    </div>
                                    <input class="button add-hospital" type="submit" value="${BUTTON_ACTION}" />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <!-- MAIN -->
            
            <jsp:include page="../../layouts/admin/FooterPage.jsp"></jsp:include>
        </section>
        
        <!-- LINK BOOTSTRAP 5 -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
        <!-- JQUERY -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- LINK SCRIPT -->
        <script src=".././js/admin/AdminRoot.js"></script>
        <script src=".././js/admin/NavBar.js"></script>
        <script src=".././js/admin/SideBar.js"></script>
        <script src=".././js/admin/AddHospital.js"></script>
        <script>
            activeSidebarLink();
        </script>
    </body>
</html>
