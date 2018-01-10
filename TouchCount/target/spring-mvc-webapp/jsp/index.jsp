<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                /*background-image: url("Untitled.png");*/
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

            .card {
                background-color: #F7F7F7;
            }
            .color-custom {
                /*background-color: #EFEFF0;*/
                background-color: #FCFCFC;
                color: #101b41;
                font-size: 150%;
                font-weight: bold;

            }
            .choice-row {
                padding-bottom: 20px;
                padding-top: 10px;
            }
            .color-custom-accord{
                /*background-color: #F7F7F7;*/
                background-color: #FCFCFC;
            }
            .color-custom-accord-expand{
                /*background-color: #FCFCFC;*/
                background-color: #F7F7F7;
            }
        </style>



    </head>

    <body>
        <!-- Views -->
        <div class="views">
            <!-- Your main view, should have "view-main" class -->
            <div class="view view-main">
                <!-- Pages container, because we use fixed navbar and toolbar, it has additional appropriate classes-->
                <div class="pages navbar-fixed toolbar-fixed">
                    <!-- Page, "data-page" contains page name -->
                    <div data-page="index" class="page theme-indigo">

                        <!-- Top Navbar. In Material theme it should be inside of the page-->
                        <div class="navbar">
                            <div class="navbar-inner">
                                <div class=""><span>TOUCH<font size="6">C</font></span>OUNT
                                    <span id="audit-name"></span>
                                    <input type="hidden" id="audit-id" />
                                </div>
                            </div>
                            <input id="USER" value="1" type="hidden" />
                            <p><span id="current-user"></span></p>

                        </div>


                        <!-- Toolbar. In Material theme it should be inside of the page-->
                        <div class="toolbar">
                            <div class="toolbar-inner">
                                <!-- Toolbar links -->
                                <a href="javascript:displayNewOrExistingAudit()" class="link">Home</a>
                                <a href="javascript:displayAuditLines()" class="link">Audit Details</a>
                            </div>
                        </div>

                        <!-- Scrollable page content -->
                        <br/>
                        <div class="page-content backgroundimg">




                            <!--BEGIN CARD CONTENT-->

                            <div class="card container room-details">
                                <div class="card-content">
                                    <div class="row">
                                        <div class="col-100 tablet-33 card-content-inner">
                                            <label for="floor-number" class="color-gray ">Floor # </label>

                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[1]">
                                                        <span class="glyphicon glyphicon-minus"></span>
                                                    </button>
                                                </span>
                                                <input type="text" name="quant[1]" id="floor-number" class="form-control input-number" value="1">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[1]">
                                                        <span class="glyphicon glyphicon-plus"></span>
                                                    </button>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="col-100 tablet-33 card-content-inner">
                                            <label for="map-number" class="color-gray">Map # </label>
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[2]">
                                                        <span class="glyphicon glyphicon-minus"></span>
                                                    </button>
                                                </span>
                                                <input type="text" name="quant[2]" id="map-number" class="form-control input-number" value="1">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[2]">
                                                        <span class="glyphicon glyphicon-plus"></span>
                                                    </button>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="col-100 tablet-33 card-content-inner">
                                            <label for="room-code" class="color-gray">Room Code </label>
                                            <input type="text" name="room-code" id="room-code" class="form-control input-text" value="OO">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-75 tablet-75">
                                            <div class="list-block inset">
                                                <ul>
                                                    <li>
                                                        <div class="item-content">
                                                            <div class="item-inner">
                                                                <div class="item-title floating-label" align="center">Room Name </div>
                                                                <div class="item-input bg-white border-gray">
                                                                    <input type="text" name="room-description" id="room-description">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <br/>
                                </div>
                            </div>

                            <!--END CARD CONTENT-->





                            <!--<hr/>-->
                            <!--MAIN AREA OF TEXT ITEMS-->
                            <div class="container ">
                                <div class="choices ">
                                    <!--==================================-->




                                    <!--==================================-->
                                </div>
                            </div>


                            <!--CURRENT CODE ITEMS-->
                            <div class="card container current-code-items">
                                <div class="card-content">
                                    <div class="row">
                                        <div class="col-100 tablet-33 card-content-inner">
                                            <div class="list-block inset">
                                                <div class="item-content">
                                                    <div class="item-inner">
                                                        <div class="item-title floating-label" align="center">Base Code </div>
                                                        <div class="item-input bg-white border-gray">
                                                            <input type="text" name="base-code" id="base-code">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-100 tablet-33 card-content-inner">
                                            <div class="list-block inset">
                                                <div class="item-content">
                                                    <div class="item-inner">
                                                        <div class="item-title floating-label" align="center">Ext Code </div>
                                                        <div class="item-input bg-white border-gray">
                                                            <input type="text" name="extended-code" id="extended-code">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-100 tablet-33 card-content-inner">
                                            <div class="list-block inset">
                                                <div class="item-content">
                                                    <div class="item-inner">
                                                        <div class="item-title floating-label" align="center">Comments </div>
                                                        <div class="item-input bg-white border-gray">
                                                            <input type="text" name="comments" id="comments">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--END CURRENT CODE ITEMS-->
                            <div id="finalEntryItems">

                            </div>





                        </div>
                        <!--<div data-page="toolbar-bottom " class="page ">-->

<!--                        <div class="toolbar toolbar-bottom ">
                            <div class="toolbar-inner ">
                                <a href="auditController/getExcelFile/97">
                                    <i class="icon material-icons ">email</i>
                                </a> 
                                <a href="javascript:loadInitialFixtureOptions()" class="link ">BACK</a>
                            </div>

                        </div>-->
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
    <script src="${pageContext.request.contextPath}/js/PlusMinus.js"></script>
    <script src="${pageContext.request.contextPath}/js/home.js"></script>


</body>
</html>

