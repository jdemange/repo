<%-- 
    Document   : theApp
    Created on : May 8, 2017, 2:09:02 PM
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
                                    <font size="6"><b>GIVE IT A TRY</b></font>
                                    <p><i>Just input your promo code
                                            <br/>to get started!</i></p>
                                </div>
                            </div>
                            <br/>
                            <div class="row col-100 tablet-45">
                                <div class="list-block inset">

                                    <div class="item-content">

                                        <div class="item-inner">

                                            <div class="item-input">
                                                <input type="text" placeholder="PROMO CODE" name="promo-code" id="promo-code">
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="row col-100 tablet-45">
                                <button class="button button-large button-raised button-fill" onclick="javascript:verify()">ENTER</button>
                            </div>
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
