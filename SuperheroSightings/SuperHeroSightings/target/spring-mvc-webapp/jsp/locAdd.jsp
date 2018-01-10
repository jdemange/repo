<%-- 
    Document   : locAdd
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
        <title>Add Location</title>
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
                <li role="presentation" class="active">
                    <a href="${pageContext.request.contextPath}/locationController/displayLocationsPage">
                        Locations
                    </a>
                </li>
            </ul>
        </div>
        <div class="">
            <div class="col-md-8">
                <div class="col-md-2">
                    <c:if test = "${location.id > 0}">
                    <h3>${location.name}</h3>
                    <p>${location.description}</p>
                    <p>${location.street}</p>
                    <p>${location.city}, ${location.state} ${location.zip}</p>
                    <p>${location.latitude} by ${location.longitude}</p>
               </c:if>
                </div>
                <div class="box col-md-10">
                    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/locationController/${destination}"
                          method="POST">
                        <div class="form-group">
                            <label for="add-locName" class="col-md-4 control-label">Location Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="locName" id="add-locName" placeholder="name" value="${location.name}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-locDescription" class="col-md-4 control-label" value="${location.description}">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="locDescription" value = "${location.description}" id="add-locDescription"
                                       placeholder="description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-locStreet" class="col-md-4 control-label">Street:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="locStreet" id="add-locStreet"
                                       placeholder="street" value="${location.street}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-locCity" class="col-md-4 control-label">City:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="locCity" id="add-locCity"
                                       placeholder="city" value="${location.city}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-locState" class="col-md-4 control-label">State:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="locState" id="add-locState"
                                       placeholder="state" value="${location.state}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-locZip" class="col-md-4 control-label">Zip Code:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="locZip" id="add-locZip"
                                       placeholder="zip code" value="${location.zip}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="latitude" id="add-latitude"
                                       placeholder="latitude" value="${location.latitude}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="longitude" id="add-longitude"
                                       placeholder="longitude" value="${location.longitude}"/>
                            </div>
                        </div>
                            <input type="hidden" name="id" value="${location.id}"/>
                        <input type="submit" class="btn btn-default" value="${buttonText}"/>
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