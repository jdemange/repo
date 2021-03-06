<%-- 
    Document   : orgHome
    Created on : Mar 27, 2017, 6:26:19 PM
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
        <title>Organization Home Page</title>
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


                        <table id="heroTable" class="table table-hover">
                            <c:forEach var="currentOrg" items="${organizations}">
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/organizationController/displayOrganizationDetails?orgId=${currentOrg.id}">
                                            ${currentOrg.name}
                                        </a>
                                    </td>
                                    <td>
                                        ${currentOrg.description}
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/organizationController/displayEditOrganization?orgId=${currentOrg.id}">
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/organizationController/deleteOrganization?orgId=${currentOrg.id}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <a href="${pageContext.request.contextPath}/organizationController/displayAddOrganization">
                            <button class="btn btn-block">Add Organization</button>
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