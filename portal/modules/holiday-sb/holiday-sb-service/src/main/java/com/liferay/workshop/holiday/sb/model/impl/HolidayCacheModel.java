/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.workshop.holiday.sb.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.workshop.holiday.sb.model.Holiday;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Holiday in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HolidayCacheModel implements CacheModel<Holiday>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HolidayCacheModel)) {
			return false;
		}

		HolidayCacheModel holidayCacheModel = (HolidayCacheModel)object;

		if (holidayRequestId == holidayCacheModel.holidayRequestId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, holidayRequestId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", holidayRequestId=");
		sb.append(holidayRequestId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", dateRequest=");
		sb.append(dateRequest);
		sb.append(", isHoliday=");
		sb.append(isHoliday);
		sb.append(", holidayName=");
		sb.append(holidayName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Holiday toEntityModel() {
		HolidayImpl holidayImpl = new HolidayImpl();

		if (uuid == null) {
			holidayImpl.setUuid("");
		}
		else {
			holidayImpl.setUuid(uuid);
		}

		holidayImpl.setHolidayRequestId(holidayRequestId);

		if (createDate == Long.MIN_VALUE) {
			holidayImpl.setCreateDate(null);
		}
		else {
			holidayImpl.setCreateDate(new Date(createDate));
		}

		if (dateRequest == null) {
			holidayImpl.setDateRequest("");
		}
		else {
			holidayImpl.setDateRequest(dateRequest);
		}

		holidayImpl.setIsHoliday(isHoliday);

		if (holidayName == null) {
			holidayImpl.setHolidayName("");
		}
		else {
			holidayImpl.setHolidayName(holidayName);
		}

		holidayImpl.resetOriginalValues();

		return holidayImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		holidayRequestId = objectInput.readLong();
		createDate = objectInput.readLong();
		dateRequest = objectInput.readUTF();

		isHoliday = objectInput.readBoolean();
		holidayName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(holidayRequestId);
		objectOutput.writeLong(createDate);

		if (dateRequest == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dateRequest);
		}

		objectOutput.writeBoolean(isHoliday);

		if (holidayName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(holidayName);
		}
	}

	public String uuid;
	public long holidayRequestId;
	public long createDate;
	public String dateRequest;
	public boolean isHoliday;
	public String holidayName;

}