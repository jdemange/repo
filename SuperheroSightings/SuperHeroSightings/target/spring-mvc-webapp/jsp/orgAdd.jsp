<%-- 
    Document   : orgAdd
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
        <title>Add/Edit Location</title>
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
                <li role="presentation" class="active">
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
                    <h3>${organization.name}</h3>
                    <p>${organization.description}</p>
                    <p>${organization.street}</p>
                    <p>${organization.city}, ${organization.state} ${organization.zip}</p>
                    <p>${organization.president}</p>
                    <p>${organization.phone}</p>
                    <c:if test ="${organization.id > 0}">
                        <p>
                        <h4>Members</h4>
                        <c:forEach var="currentHero" items="${organization.heroes}">
                            <a href="${pageContext.request.contextPath}/heroController/displayHeroDetails?heroId=${currentHero.id}">
                                ${currentHero.name} <br>
                            </a>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="box col-md-10">
                    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/organizationController/${destination}"
                          method="POST">
                        <div class="form-group">
                            <label for="add-orgName" class="col-md-4 control-label">Organization Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="orgName" id="add-orgName" placeholder="name" value="${organization.name}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-orgDescription" class="col-md-4 control-label" value="${organization.description}">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="orgDescription" value = "${organization.description}" id="add-orgDescription"
                                       placeholder="description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-orgStreet" class="col-md-4 control-label">Street:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="orgStreet" id="add-orgStreet"
                                       placeholder="street" value="${organization.street}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-orgCity" class="col-md-4 control-label">City:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="orgCity" id="add-orgCity"
                                       placeholder="city" value="${organization.city}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-orgState" class="col-md-4 control-label">State:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="orgState" id="add-orgState"
                                       placeholder="state" value="${organization.state}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-orgZip" class="col-md-4 control-label">Zip Code:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="orgZip" id="add-orgZip"
                                       placeholder="zip code" value="${organization.zip}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-president" class="col-md-4 control-label">President:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="president" id="add-president"
                                       placeholder="president" value="${organization.president}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-phone" class="col-md-4 control-label">Phone Number:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="phone" id="add-phone"
                                       placeholder="phone number" value="${organization.phone}"/>
                            </div>
                        </div>
                        <input type="hidden" name="id" value="${organization.id}"/>                         
                        <div class="form-group">
                            <label for="add-members" class="col-md-4 control-label">Members:</label>
                            <div class="col-md-8">
                                <select name="members" multiple>
                                    <c:forEach var="currentHero" items="${selectedHeroes}">
                                    <option value="${currentHero.id}" selected>${currentHero.name}</option>
                                    </c:forEach>
                                    <c:forEach var="currentHero" items="${heroes}">
                                    <option value="${currentHero.id}">${currentHero.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
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