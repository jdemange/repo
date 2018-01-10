<%-- 
    Document   : home.jsp
    Created on : Mar 27, 2017, 2:25:51 PM
    Author     : apprentice
--%>
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
        <title>Home</title>
    </head>
    <body>
        <h1>Superhero Sightings!</h1>

        <div class="navbar">
            <ul class="nav nav-pills navbar-inverse">
                <li role="presentation" class="active">
                    <a href="${pageContext.request.contextPath}">
                        Home
                    </a>
                </li>
                <li role="presentation">
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
        <div class="container">
            <div class="col-md-8">
                <div class="col-md-1"></div>
                <div class="box col-md-6">
                    <p>
                        Welcome to your Superhero Sighting Headquarters! <br/>
                        To help us keep track of the happenings around town <br/>
                        please report any superhero news you have. Add a new <br/>
                        hero, let us know who they are affiliated with or just <br/>
                        look around.
                            
                    </p>
                </div>
            </div>
            <div class="col-md-1"></div>
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
