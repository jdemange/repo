<%-- 
    Document   : heroHome
    Created on : Mar 27, 2017, 6:26:19 PM
    Author     : apprentice
--%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet" type="text/css" />
        <title>Add Hero</title>
    </head>
    <body>
        <h1>Superhero Sightings!</h1>

        <div class="navbar">
            <ul class="nav nav-pills navbar-inverse">
                <li role="presentation" >
                    <a href="${pageContext.request.contextPath}">
                        Home
                    </a>
                </li>
                <li role="presentation" class="active">
                    <a href="${pageContext.request.contextPath}/heroController/displayHeroesPage">
                        Heroes
                    </a>
                </li>
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/sightingsController/displaySightingsPage">
                        Sightings
                    </a>
                </li>
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/organizationController/displayOrganizationsPage">
                        Organizations
                    </a>
                </li>
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/locationController/displayLocationsPage">
                        Locations
                    </a>
                </li>
            </ul>
        </div>
        <div class="">
            <div class="col-md-8">
                <div class="col-md-2">

                </div>
                <div class="box col-md-10">
                    <form class="form-horizontal" enctype="multipart/form-data" role="form" action="${pageContext.request.contextPath}/heroController/addHero"
                          method="POST" >
                        <div class="form-group">
                            <label for="add-name" class="col-md-4 control-label">Superhero Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="name" id="add-name" placeholder="name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="description" id="add-description"
                                       placeholder="description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-power" class="col-md-4 control-label">Super Power:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="superPower" id="add-power"
                                       placeholder="power"/>
                            </div>
                        </div>
                        <div class="form-group">
                    <label for="displayTitle">Photo Title:</label>
                    <input type="text" 
                           id="displayTitle" 
                           name="displayTitle"/>
                </div>
                        <div class="form-group">
                            <label for"picture">Upload Photo:</label>
                            <input type="file" id="picture" name="picture"/>
                        
                        </div>

                        <input type="submit" class="btn btn-default" value="Add Hero to Directory"/>
                    </form>

                </div>
            </div>

            <div class="col-md-4 recent_sightings">
                <h3>Latest Sightings</h3>
                <c:forEach var="currentSighting" items="${tenSightings}">
                    <div class="sightingList box">
                        <p>
                            <span>
                                * <javatime:format value="${currentSighting.dateTime}" style="MS" />
                            </span>
                            <span> -  ${currentSighting.hero.name} </span> 
                            <span> -  ${currentSighting.location.name} </span> 
                        </p>
                    </div>
                </c:forEach>
            </div>
        </div>          



        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>