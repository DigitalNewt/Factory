<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <style>
        .table-container {
            width: 1100px;
            height: 200px;
            overflow-y: scroll;
        }
    </style>
</head>
<body>

<table align="center" width="50%">
    <tr>
        <td>
            <table width="100%">
                <tr>
                    <td align="left" valign="top" nowrap>${totalPortals} Portals</td>
                    <td align="left" valign="top" nowrap>${totalUsers} Users</td>
                    <td align="left" >${avgUsersPerPortal} Avg. Users Per Portal</td>
                    <td align="right" width="20%">
                        <c:url value="/admin/reload" var="reloadUrl"/>
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <a href="javascript:resetForm()"> reload</a>
                        </c:if>
                    </td>
                    <td align="right" width="20%">
                        <c:url value="/logout" var="logoutUrl"/>
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <a href="javascript:logoutForm()"> logout</a>
                        </c:if>
                    </td>
                </tr>
            </table>
            <hr>
            <table width="100%">
                <tr>
                    <td colspan="2" align="Center" nowrap><b>Features</b></td>
                </tr>
                <tr>
                    <td width="10%" nowrap>Projects:</td>
                    <td align="left">${totalProjects} projects created</td>
                </tr>
                <tr>
                    <td></td>
                    <td align="left">${totalWorkItems} work items created</td>
                </tr>
                <tr>
                    <td colspan="2"><hr></td>
                </tr>
                <tr>
                    <td width="10%" nowrap>CRM:</td>
                    <td align="left">${totalAccounts} accounts created</td>
                </tr>
                <tr>
                    <td></td>
                    <td align="left">${totalContacts} contacts created</td>
                </tr>
                <tr>
                    <td colspan="2"><hr></td>
                </tr>
                <tr>
                    <td width="10%" nowrap>Calendar:</td>
                    <td align="left">${totalEvents} events created</td>
                </tr>
                <tr>
                    <td colspan="2"><hr></td>
                </tr>
                <tr>
                    <td width="10%" nowrap>Feeds:</td>
                    <td align="left">${totalPosts} feeds created</td>
                </tr>
            </table>
            <hr>
            <table width="900px">
                <tr>
                    <td width="250px"><b>Portal</b></td>
                    <td width="155px"><b>Domain Name</b></td>
                    <td nowrap><b># of Users</b></td>
                    <td width="190px" align="left"><b>Last Used</b></td>
                    <td width="100px" align="left"><b>Creator</b></td>
                    <td width="100px" align="left"><b>Email</b></td>
                </tr>
            </table>
            <div class="table-container">
                <table>
                    <c:forEach var="portal" items="${portals}">
                        <tr>
                            <td width="250px" valign="top">${portal.name}</td>
                            <td width="150px" valign="top">${portal.domainName}</td>
                            <td width="60px" align="left" valign="top">${portal.users.size()}</td>
                            <td width="190px" align="left" valign="top" nowrap> ${portal.displayDate}</td>
                            <td width="100px" align="left" valign="top">${portal.creator.name}</td>
                            <td width="100px" align="left" valign="top" nowrap>${portal.creator.email}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <br/>
            <c:choose>
                <c:when test="${empty duplicatePortals}">
                    <p>No Duplicate Portals</p>
                </c:when>
                <c:otherwise>
                    <table width="100%">
                        <c:forEach var="portal" items="${duplicatePortals}">
                            <tr>
                                <td><c:out value="${portal}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>

<!-- csrt support -->
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>


<form method="GET" action="${reloadUrl}" id="reloadForm">
</form>

<script>
    function logoutForm() {
        document.getElementById("logoutForm").submit();
    }

    function resetForm() {
        document.getElementById("reloadForm").submit();
    }
</script>


</body>
</html>