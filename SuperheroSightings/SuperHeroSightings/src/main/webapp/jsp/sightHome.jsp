<%-- 
    Document   : sightHome
    Created on : Mar 28, 2017, 5:35:51 PM
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
        <title>Sightings Home Page</title>
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
                <li role="presentation" >
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
                    <c:if test = "${id > 0}">
                        <c:if test ="${ind != 1}">
                        <h3><javatime:format value="${date}" style="MS" /></h3>
                        </c:if>

                        
                        <h3>${heroName}</h3>
                        <c:forEach var="currentSighting" items="${sightingsDate}">
                            <a href="${pageContext.request.contextPath}/heroController/displayHeroDetails?heroId=${currentSighting.hero.id}">
                                ${currentSighting.hero.name}
                            </a>
                            <span> - </span>
                            <a href="${pageContext.request.contextPath}/locationController/displayLocationDetails?locId=${currentSighting.location.id}">
                                ${currentSighting.location.name}
                            </a>
                        </c:forEach>
                    </c:if>

                </div>
                <div class="box col-md-10">


                    <table id="heroTable" class="table table-hover">
                        <c:forEach var="currentSighting" items="${sightings}">
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/sightingsController/displaySightingsForDate?sightId=${currentSighting.id}">
                                        <javatime:format value="${currentSighting.dateTime}" style="MS" />
                                    </a>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/heroController/displayHeroDetails?heroId=${currentSighting.hero.id}">
                                        ${currentSighting.hero.name}
                                    </a>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/locationController/displayLocationDetails?locId=${currentSighting.location.id}">
                                        ${currentSighting.location.name}
                                    </a>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/sightingsController/displayEditSighting?sightId=${currentSighting.id}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/sightingsController/deleteSighting?sightId=${currentSighting.id}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a href="${pageContext.request.contextPath}/sightingsController/displayAddSighting">
                        <button class="btn btn-block">Add Sighting</button>
                    </a>
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
