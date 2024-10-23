create table HOLIDAY_Holiday (
	uuid_ VARCHAR(75) null,
	holidayRequestId LONG not null primary key,
	groupId LONG,
	createDate DATE null,
	dateRequest VARCHAR(75) null,
	isHoliday BOOLEAN,
	holidayName VARCHAR(75) null
);