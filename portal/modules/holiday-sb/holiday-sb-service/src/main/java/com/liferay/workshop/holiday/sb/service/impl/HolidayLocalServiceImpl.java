/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.workshop.holiday.sb.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.workshop.holiday.sb.model.Holiday;
import com.liferay.workshop.holiday.sb.service.base.HolidayLocalServiceBaseImpl;

import com.liferay.workshop.holiday.sb.service.persistence.HolidayPersistence;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.time.Instant;
import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.liferay.workshop.holiday.sb.model.Holiday",
	service = AopService.class
)
public class HolidayLocalServiceImpl extends HolidayLocalServiceBaseImpl {

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
			_userLocalService;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
			_counterLocalService;

	@Reference
	protected HolidayPersistence _holidayPersistence;

	public Holiday addHolidayRequest(long userId, String holidayName, String dateRequest,
									 ServiceContext serviceContext) throws PortalException {

		long groupId = serviceContext.getScopeGroupId();

		User user = _userLocalService.getUserById(userId);

		long entryId = _counterLocalService.increment();

		Holiday entry = _holidayPersistence.create(entryId);

		entry.setUuid(serviceContext.getUuid());
		entry.setGroupId(groupId);
		entry.setCreateDate(serviceContext.getCreateDate(Date.from(Instant.now())));
		entry.setExpandoBridgeAttributes(serviceContext);

		entry.setHolidayName(holidayName);
		entry.setDateRequest(dateRequest);

		_holidayPersistence.update(entry);

		return entry;
	}
}