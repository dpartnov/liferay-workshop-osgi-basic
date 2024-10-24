/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.workshop.holiday.sb.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;HOLIDAY_Holiday&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Holiday
 * @generated
 */
public class HolidayTable extends BaseTable<HolidayTable> {

	public static final HolidayTable INSTANCE = new HolidayTable();

	public final Column<HolidayTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HolidayTable, Long> holidayRequestId = createColumn(
		"holidayRequestId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<HolidayTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<HolidayTable, String> dateRequest = createColumn(
		"dateRequest", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HolidayTable, Boolean> isHoliday = createColumn(
		"isHoliday", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<HolidayTable, String> holidayName = createColumn(
		"holidayName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private HolidayTable() {
		super("HOLIDAY_Holiday", HolidayTable::new);
	}

}