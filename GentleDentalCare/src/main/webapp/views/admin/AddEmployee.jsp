<%@page import="com.khl.gentledentalcare.models.EmployeeError"%>
<%@page import="com.khl.gentledentalcare.models.AccountError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    AccountError accountError = (AccountError) request.getAttribute("ACCOUNT_ERROR");
    EmployeeError employeeError = (EmployeeError) request.getAttribute("EMPLOYEE_ERROR");
    
    if(accountError == null) {
        accountError = new AccountError();
    }
    
    if (employeeError == null) {
        employeeError = new EmployeeError();
    }
    
    String imageDentist = (String) request.getAttribute("EMPLOYEE_IMAGE");
    String concatStringImage = "";
    if (imageDentist != null) {
        concatStringImage = "data:image/png;base64," + imageDentist;
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
        <link href=".././css/admin/AddEmployee.css" rel="stylesheet" type="text/css"/>
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
                    <li><a href="${pageContext.request.contextPath}/admin/add-employee" class="active">${BUTTON_ACTION}</a></li>
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
                                        <input type="text" class="full-name" name="fullName" value="${FULL_NAME != null ? FULL_NAME : ""}" placeholder="Full Name" oninput="CheckFullName()" onblur="CheckFullName()" />
                                    </div>
                                    <div class="text-danger full-name-error">
                                        <p><%=accountError.getFullNameError()%></p>
                                    </div>
                                    <div class="input_field checkbox_option">
                                        <input type="checkbox" id="cb1" class="" name="gender" value="M">
                                        <label for="cb1">Male</label>
                                        <input type="checkbox" id="cb2" class="" name="gender" value="F">
                                        <label for="cb2">Female</label>
                                    </div>
                                    <div class="text-danger gender-error">
                                        <p><%=accountError.getGenderError()%></p>
                                    </div>
                                    <div class="input_field">
                                        <span>
                                            <i class="fa-solid fa-at"></i>
                                        </span>
                                        <input type="text" class="email" name="email" value="${EMAIL != null ? EMAIL : ""}" placeholder="Email" oninput="CheckEmail()" onblur="CheckEmail()" />
                                    </div>
                                    <div class="text-danger email-error">
                                        <p><%=accountError.getEmailError()%></p>
                                    </div>
                                    <div class="input_field">
                                        <span>
                                            <i class="fa-solid fa-calendar"></i>
                                        </span>
                                        <input type="date" class="date-of-birth" name="dateOfBirth" value="${DATE_OF_BIRTH != null ? DATE_OF_BIRTH : ""}" placeholder="Date Of Birth" oninput="CheckDateOfBirth()" onblur="CheckDateOfBirth()" />
                                    </div>
                                    <div class="text-danger date-of-birth-error">
                                        <p><%=accountError.getDateOfBirthError()%></p>
                                    </div>
                                    <div class="input_field">
                                        <span>
                                            <i class="fa-solid fa-mobile"></i>
                                        </span>
                                        <input type="number" class="phone-number" name="phoneNumber" value="${PHONE_NUMBER != null ? PHONE_NUMBER : ""}" placeholder="Phone Number" oninput="CheckPhoneNumber()" onblur="CheckPhoneNumber()" />
                                    </div>
                                    <div class="text-danger phone-number-error">
                                        <p><%=accountError.getPhoneNumberError()%></p>
                                    </div>
                                    <div class="input_field">
                                        <div class="image-input">
                                            <input type="file" accept="image/*" id="imageInput">
                                            <img src="<%=concatStringImage%>" class="image-preview image-edit">
                                            <input type="hidden" class="image-hidden" name="${DENTIST_IMAGE != null ? "employeeImage" : ""}" value="<%=concatStringImage%>">
                                            <label for="imageInput" class="image-button add-button">
                                                <i class="fa-solid fa-image"></i>Avatar Picture
                                            </label>
                                            <span class="change-image">Change Picture</span>
                                        </div>
                                    </div>
                                    <div class="text-danger image-error">
                                        <p><%=accountError.getImageAvatarError()%></p>
                                    </div>
                                    <div class="input_field">
                                        <span>
                                            <i class="fa-solid fa-location-dot"></i>
                                        </span>
                                        <input type="text" class="address" name="address" value="${ADDRESS != null ? ADDRESS : ""}" placeholder="Address" oninput="CheckAddress()" onblur="CheckAddress()" />
                                    </div>
                                    <div class="text-danger address-error">
                                        <p><%=accountError.getAddressError()%></p>
                                    </div>
                                    <div class="input_field">
                                        <span>
                                            <i class="fa-solid fa-money-bill-wave"></i>
                                        </span>
                                        <input type="number" class="salary" name="salary" value="${SALARY != null ? SALARY : ""}" placeholder="Salary" oninput="CheckSalary()" onblur="CheckSalary()" />
                                    </div>
                                    <div class="text-danger salary-error">
                                        <p><%=employeeError.getSalaryError()%></p>
                                    </div>
                                    <div class="input_field">
                                        <span>
                                            <i class="fa-solid fa-id-card"></i>
                                        </span>
                                        <input type="number" class="insurance" name="insurance" value="${INSURANCE != null ? INSURANCE : ""}" placeholder="Insurance" oninput="CheckInsurance()" onblur="CheckInsurance()" />
                                    </div>
                                    <div class="text-danger insurance-error">
                                        <p><%=employeeError.getInsuranceError()%></p>
                                    </div>
                                    <input class="button add-service" type="submit" value="${BUTTON_ACTION}" />
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
        <script src=".././js/admin/AddEmployee.js"></script>
        <script>
            activeSidebarLink();
        </script>
    </body>
</html>
