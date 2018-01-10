<%-- 
    Document   : customization
    Created on : May 5, 2017, 8:37:53 PM
    Author     : jocel
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
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
                                <font size="6"><b>MADE JUST FOR YOU</b></font>
                            </div>
                        </div>
                        <br/>
                        <div class="container">
                            <div class="list-block">
                                <ul>
                                    <li class="accordion-item">
                                        <a href="#" class="item-content item-link">
                                            <div class="item-inner">
                                                <div class="item-title">INFORMATION</div>
                                            </div>
                                        </a>
                                        <div class="accordion-item-content">
                                            <div class="content-block">
                                                <p>We work with you to determine what information relevant to YOU. A comprehensive lighting audit means different things to different organizations and we build <b>touchCount</b> menus accordingly.</p>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="accordion-item">
                                        <a href="#" class="item-content item-link">
                                            <div class="item-inner">
                                                <div class="item-title">NO MORE DROP DOWNS</div>
                                            </div>
                                        </a>
                                        <div class="accordion-item-content">
                                            <div class="content-block">
                                                <p>
                                                    The problem with all of the audit applications are those endless drop down menus. Together we determine custom fixture flows that allow for a smoother auditing process. Our custom fixture flows make it possible for even the most novice of employees to
                                                    complete energy audits.
                                                    <br/> <span class="color-red">*The irony of this drop down (technically an accordion) is not lost on us*</span>
                                                </p>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="accordion-item">
                                        <a href="#" class="item-content item-link">
                                            <div class="item-inner">
                                                <div class="item-title">AUDIT CODES</div>
                                            </div>
                                        </a>
                                        <div class="accordion-item-content">
                                            <div class="content-block">
                                                <p>We know that your lighting organization has fixture codes that work with existing energy software and that's not a problem! We are able to tailor our <b>touchCount</b> software to your fixture codes.
                                                </p>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="accordion-item">
                                        <a href="#" class="item-content item-link">
                                            <div class="item-inner">
                                                <div class="item-title">OUTPUT</div>
                                            </div>
                                        </a>
                                        <div class="accordion-item-content">
                                            <div class="content-block">
                                                <p>Along with custom codes we know that your energy proposal software likes information formatted in a particular manner. <b>touchCount</b> works with you to customize how the audit information is downloaded
                                                    from our online library.
                                                </p>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <!--</div>-->










                    </div>
                </div>
            </div>
        </div>
    </div>


    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <!-- Path to Framework7 Library JS-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/framework7.min.js"></script>
    <!-- Path to your app js-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/my-app.js"></script>

    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!--<script src="${pageContext.request.contextPath}/js/PlusMinus.js"></script>-->
    <!--<script src="${pageContext.request.contextPath}/js/home.js"></script>-->


</body>
</html>
