<%@ page import="com.liferay.workshop.helloportlet.HelloPortletKeys" %>

<%@ include file="./init.jsp" %>

<portlet:actionURL var="helloPortletUrl" name="<%= HelloPortletKeys.CREATE_GREETING_ACTION %>">
</portlet:actionURL>

<aui:form action="${helloPortletUrl}" method="post" name="userNameForm">
    <aui:input name="<%= HelloPortletKeys.NAME_TEXT_INPUT %>"
               type="text"
               required="true"
               label="Enter your name"
               value="${HelloPortletKeys.NAME_TEXT_INPUT}">
    </aui:input>
    <aui:button name="submitButton" type="submit" value="helloportlet.submit" />
    <b><liferay-ui:message key="helloportlet.resultText"/> <c:out value="${HelloPortletKeys.HELLO_TEXT_RESULT}"/></b>

</aui:form>