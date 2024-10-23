<%@ page import="com.liferay.workshop.holiday.constants.HolidayPortletKeys" %>

<%@ include file="./init.jsp" %>

<portlet:actionURL var="holidayUrl" name="<%= HolidayPortletKeys.HOLIDAY_ACTION %>">
</portlet:actionURL>

<h1>Holidays check</h1>

<aui:form action="${holidayUrl}" method="post" name="dateForm">
    <aui:input name="<%= HolidayPortletKeys.ATTR_DATE %>"
               type="text"
               required="true"
               label="Enter date in format YYYY-MM-DD:"
               value="${date}">
    </aui:input>

    <liferay-ui:error key="error" message="Invalid date format!" />

    <aui:button name="submitButton" type="submit" value="Send" />

    <p>
    	<b><liferay-ui:message key="Is holiday:"/> <c:out value="${resultIsHoliday}" /></b><br>
    	<b><liferay-ui:message key="Holiday name:"/> <c:out value="${resultHolidayName}" /></b><br>
    </p>

</aui:form>