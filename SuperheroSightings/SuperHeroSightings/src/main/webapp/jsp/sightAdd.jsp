<%-- 
    Document   : sightAdd
    Created on : Mar 28, 2017, 9:24:13 PM
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
        <title>Add Sighting</title>
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
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/heroController/displayHeroesPage">
                        Heroes
                    </a>
                </li>
                <li role="presentation"  class="active">
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
                    <form class="form-horizontal" role="form" id="sightingForm" action="${pageContext.request.contextPath}/sightingsController/${destination}"
                          method="POST">
                        <div class="form-group">
                            <label for="add-hero" class="col-md-4 control-label">Superhero:</label>
                            <div class="col-md-8">
                                <div>
                                    <select name="heroId" form="sightingForm">
                                        <c:if test="${editSighting.id > 0}">
                                            <option  value="${editSighting.hero.id}" selected="selected">${editSighting.hero.name}</option>
                                        </c:if>
                                        <c:forEach var="currentHero" items="${heroes}">
                                            <option   value="${currentHero.id}">${currentHero.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location" class="col-md-4 control-label">Location:</label>
                            <div class="col-md-8">
                                <div>
                                    <select name="locId" form="sightingForm"  >
                                        <c:if test="${editSighting.id > 0}">
                                            <option  value="${editSighting.location.id}" selected>${editSighting.location.name}</option>
                                        </c:if>
                                        <c:forEach var="currentLocation" items="${locations}">
                                            <option  value="${currentLocation.id}">${currentLocation.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-date" class="col-md-4 control-label">Sighting Date and Time:</label>
                            <div class="col-md-8">
                                <input value="${editSighting.dateTime}" style="MS"  id="add-date" type="datetime-local" name="daytime" class="form-control">
                            </div>
                        </div>
                        <input type="hidden" value="${editSighting.id}" name="sightId"/>
                        <input type="submit" class="btn btn-default" value="${message}"/>
                    </form>

                </div>
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
