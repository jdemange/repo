<%-- 
    Document   : about
    Created on : May 5, 2017, 7:24:22 PM
    Author     : jocel
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags-->
<meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <!-- Color theme for statusbar -->
        <meta name="theme-color" content="#4666db">
        <!-- Your app title -->
        <title>TOUCHCOUNT</title>
        <!-- Path to Framework7 Library CSS, Material Theme -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/framework7.material.min.css">
        <!-- Path to Framework7 color related styles, Material Theme -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/framework7.material.colors.min.css">
        <!-- Path to your custom app styles-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/my-app.css">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/MainIndex.css" rel="stylesheet">
        <style>
                     body {
                background-color: #E3F09B;
            }

            .backgroundimg {
                /*height: 100%;*/
                /* To fill everything */
                /*width: 100%;*/
                /* To fill everything */
                /*background: url(Untitled.png);*/
                /* Your Image */
                /*background-size: cover;*/
                /* To cover everything */
                /*position: absolute; */
                /* this is most important! */
                background-color: #F7F4EA
            }
        </style>



    </head>

    <body>
        <!-- Views -->

        <div class="views">
            <!-- Your main view, should have "view-main" class -->
            <div class="view view-main">

                <!-- Pages container, because we use fixed navbar and toolbar, it has additional appropriate classes-->
                <div class="pages navbar-fixed toolbar-fixed theme-indigo">

                    <!-- Page, "data-page" contains page name -->
                    <div data-page="index" class="page toolbar-fixed navbar-fixed">

                        <!-- Top Navbar. In Material theme it should be inside of the page-->
                        <div class="navbar">
                            <div class="navbar-inner">

                                <div class="left">TOUCH<span><font size="6">C</font></span>OUNT
                                </div>
                            </div>
                        </div>

                        <!-- Toolbar. In Material theme it should be inside of the page-->
                        <div class="toolbar tabbar tabbar-scrollable">
                            <div class="toolbar-inner">
                                <!-- Toolbar links -->
                                <a href="${pageContext.request.contextPath}/home" class="tab-link">Home</a>
                                <a href="${pageContext.request.contextPath}/about" class="tab-link">About</a>
                                <a href="${pageContext.request.contextPath}/customization" class="tab-link">Customization</a>
                                <a href="${pageContext.request.contextPath}/process" class="tab-link">The Process</a>
                                <a href="${pageContext.request.contextPath}/theApp" class="tab-link">The App</a>
                            </div>
                        </div>
                        <!-- Scrollable page content -->
                        <div class="page-content backgroundimg">
                            <!--<div class="container">-->
                            <br/>
                            <br/>
                            <br/>
                            <div class="row">
                                <div class="container" style="text-align: center">
                                <font size="6"><b>DeMange Enterprises, LLC</b></font>
                            </div>
                            </div>
                            <br/>
                            <div class="container">
                                <p style="text-align: justify">DeMange Enterprises, LLC was founded in 2017 by Jocelyn DeMange. Jocelyn is a graduate of the University of Cincinnati with a degree in Civil Engineering with a concentration in Sustainable Engineering. After graduation she
                                    entered the energy savings industry via a national turnkey lighting solutions company. While there she was able gain knowledge about the lighting retrofit industry. As a project manager she was able to become familiar with
                                    different lighting solutions and eventually transitioned into a role in engineering where she completed lighting audits and proposed energy saving solutions for national corporations.
                                    <br/> <br/>After six and a half years in the lighting industry, Jocelyn decided to attend The Software Guild, a computer programming bootcamp, to learn about application development. While attending the programming she
                                    began thinking about a need in the lighting industry. <b>A lighting audit application that didn't require a lot of extra on site time.</b> That's when she started the design plans for <b>touchCount</b>, an audit program
                                    that differentiates itself from all the others on the market. Jocelyn welcomes you to explore the site and see how <b>touchCount</b> can change the way you audit.
                                </p>

                            </div>

                            <!--</div>-->










                        </div>
                    </div>
                </div>
            </div>
        </div>


    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <!-- Path to Framework7 Library JS-->
    <!--<script type="text/javascript" src="js/framework7.min.js"></script>-->
    <!-- Path to your app js-->
    <!--<script type="text/javascript" src="js/my-app.js"></script>-->

    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/PlusMinus.js"></script>
    <script src="${pageContext.request.contextPath}/js/home.js"></script>



    </body>
</html>
