<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard</title>
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
        <link href=".././css/admin/DashBoard.css" rel="stylesheet" type="text/css"/>
        <link href=".././css/admin/FooterPage.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../../layouts/admin/SideBar.jsp"></jsp:include>
        
        <section id="content">
            <jsp:include page="../../layouts/admin/NavBar.jsp"></jsp:include>
            
            <!-- MAIN -->
            <main class="main">
                <h1 class="title">Dashboard</h1>
                <ul class="breadcrumbs">
                    <li><a href="${pageContext.request.contextPath}/admin/dashboard">Gentle Dental Care</a></li>
                    <li class="divider">/</li>
                    <li><a href="${pageContext.request.contextPath}/admin/dashboard" class="active">Dashboard</a></li>
                </ul>
                <div class="info-data">
                    <div class="card">
                        <div class="head">
                            <div>
                                <h2>${TOTAL_VIEWER != null ? TOTAL_VIEWER : 0}</h2>
                                <p>Visitors</p>
                            </div>
                            <i class='bx bx-trending-up icon'></i>
                        </div>
                        <span class="progress" data-value="${TOTAL_VIEWER != null ? TOTAL_VIEWER : 0}%"></span>
                        <span class="label">${TOTAL_VIEWER != null ? TOTAL_VIEWER : 0}</span>
                    </div>
                    <div class="card">
                        <div class="head">
                            <div>
                                <h2>${TOTAL_BOOKING != null ? TOTAL_BOOKING : 0}</h2>
                                <p>Booking</p>
                            </div>
                            <i class='bx bx-trending-down icon down'></i>
                        </div>
                        <span class="progress" data-value="${TOTAL_BOOKING != null ? TOTAL_BOOKING : 0}%"></span>
                        <span class="label">${TOTAL_BOOKING != null ? TOTAL_BOOKING : 0}</span>
                    </div>
                    <div class="card">
                        <div class="head">
                            <div>
                                <h2>${TOTAL_FEEDBACK != null ? TOTAL_FEEDBACK : 0}</h2>
                                <p>FeedBack</p>
                            </div>
                            <i class='bx bx-trending-up icon'></i>
                        </div>
                        <span class="progress" data-value="${TOTAL_FEEDBACK != null ? TOTAL_FEEDBACK : 0}%"></span>
                        <span class="label">${TOTAL_FEEDBACK != null ? TOTAL_FEEDBACK : 0}</span>
                    </div>
                    <div class="card">
                        <div class="head">
                            <div>
                                <h2>${TOTAL_ACCOUNT != null ? TOTAL_ACCOUNT : 0}</h2>
                                <p>Customer</p>
                            </div>
                            <i class='bx bx-trending-up icon'></i>
                        </div>
                        <span class="progress" data-value="${TOTAL_ACCOUNT != null ? TOTAL_ACCOUNT : 0}%"></span>
                        <span class="label">${TOTAL_ACCOUNT != null ? TOTAL_ACCOUNT : 0}</span>
                    </div>
                </div>
                <div class="graph-chart mt-5">
                    <div class="chart-box">
                        <canvas id="ChartCustomer"></canvas>
                    </div>
                </div>
            </main>
            <!-- MAIN -->
            
            <jsp:include page="../../layouts/admin/FooterPage.jsp"></jsp:include>
        </section>
                
        <!-- Chart -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
        <!-- LINK BOOTSTRAP 5 -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
        <script src=".././js/admin/AdminRoot.js"></script>
        <script src=".././js/admin/DashBoard.js"></script>
        <script src=".././js/admin/NavBar.js"></script>
        <script src=".././js/admin/SideBar.js"></script>
        <script>
            const chartCustomer = document.getElementById("ChartCustomer").getContext("2d");
            const MONTHS = [
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
            ];

            var myChart = new Chart(chartCustomer, {
                type: "line",
                data: {
                    labels: MONTHS,
                    datasets: [
                        {
                            label: "Customers",
                            data: ${STATISTIC_BOOKING},
                            fill: false,
                            borderColor: "rgb(75, 192, 192)",
                            tension: 0.1
                        }
                    ]
                },
                options: {
                    response: true
                }
            });
        </script>
    </body>
</html>
