<%@ page import="com.liferay.workshop.greeting.constants.GreetingPortletKeys" %>

<%@ include file="./init.jsp" %>

<portlet:actionURL var="greetingUrl" name="<%= GreetingPortletKeys.GREETING_ACTION %>">
</portlet:actionURL>

<aui:form action="${greetingUrl}" method="post" name="greetingForm">
    <aui:input name="<%= GreetingPortletKeys.ATTR_USER_NAME %>"
               type="text"
               required="true"
               label="Enter your name:"
               value="${userName}">
    </aui:input>

    <c:if test="${showButton}">
        <aui:button name="submitButton" type="submit" value="Send" />
    </c:if>

    <b><liferay-ui:message key=""/> <c:out value="${result}"/></b>

</aui:form>