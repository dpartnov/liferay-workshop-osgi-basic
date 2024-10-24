/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.workshop.holiday.sb.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Holiday}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Holiday
 * @generated
 */
public class HolidayWrapper
	extends BaseModelWrapper<Holiday>
	implements Holiday, ModelWrapper<Holiday> {

	public HolidayWrapper(Holiday holiday) {
		super(holiday);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("holidayRequestId", getHolidayRequestId());
		attributes.put("createDate", getCreateDate());
		attributes.put("dateRequest", getDateRequest());
		attributes.put("isHoliday", isIsHoliday());
		attributes.put("holidayName", getHolidayName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long holidayRequestId = (Long)attributes.get("holidayRequestId");

		if (holidayRequestId != null) {
			setHolidayRequestId(holidayRequestId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String dateRequest = (String)attributes.get("dateRequest");

		if (dateRequest != null) {
			setDateRequest(dateRequest);
		}

		Boolean isHoliday = (Boolean)attributes.get("isHoliday");

		if (isHoliday != null) {
			setIsHoliday(isHoliday);
		}

		String holidayName = (String)attributes.get("holidayName");

		if (holidayName != null) {
			setHolidayName(holidayName);
		}
	}

	@Override
	public Holiday cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the create date of this holiday.
	 *
	 * @return the create date of this holiday
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the date request of this holiday.
	 *
	 * @return the date request of this holiday
	 */
	@Override
	public String getDateRequest() {
		return model.getDateRequest();
	}

	/**
	 * Returns the holiday name of this holiday.
	 *
	 * @return the holiday name of this holiday
	 */
	@Override
	public String getHolidayName() {
		return model.getHolidayName();
	}

	/**
	 * Returns the holiday request ID of this holiday.
	 *
	 * @return the holiday request ID of this holiday
	 */
	@Override
	public long getHolidayRequestId() {
		return model.getHolidayRequestId();
	}

	/**
	 * Returns the is holiday of this holiday.
	 *
	 * @return the is holiday of this holiday
	 */
	@Override
	public boolean getIsHoliday() {
		return model.getIsHoliday();
	}

	/**
	 * Returns the primary key of this holiday.
	 *
	 * @return the primary key of this holiday
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the uuid of this holiday.
	 *
	 * @return the uuid of this holiday
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this holiday is is holiday.
	 *
	 * @return <code>true</code> if this holiday is is holiday; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsHoliday() {
		return model.isIsHoliday();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the create date of this holiday.
	 *
	 * @param createDate the create date of this holiday
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the date request of this holiday.
	 *
	 * @param dateRequest the date request of this holiday
	 */
	@Override
	public void setDateRequest(String dateRequest) {
		model.setDateRequest(dateRequest);
	}

	/**
	 * Sets the holiday name of this holiday.
	 *
	 * @param holidayName the holiday name of this holiday
	 */
	@Override
	public void setHolidayName(String holidayName) {
		model.setHolidayName(holidayName);
	}

	/**
	 * Sets the holiday request ID of this holiday.
	 *
	 * @param holidayRequestId the holiday request ID of this holiday
	 */
	@Override
	public void setHolidayRequestId(long holidayRequestId) {
		model.setHolidayRequestId(holidayRequestId);
	}

	/**
	 * Sets whether this holiday is is holiday.
	 *
	 * @param isHoliday the is holiday of this holiday
	 */
	@Override
	public void setIsHoliday(boolean isHoliday) {
		model.setIsHoliday(isHoliday);
	}

	/**
	 * Sets the primary key of this holiday.
	 *
	 * @param primaryKey the primary key of this holiday
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the uuid of this holiday.
	 *
	 * @param uuid the uuid of this holiday
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected HolidayWrapper wrap(Holiday holiday) {
		return new HolidayWrapper(holiday);
	}

}