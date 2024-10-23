/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.workshop.holiday.sb.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Holiday service. Represents a row in the &quot;HOLIDAY_Holiday&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see HolidayModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.workshop.holiday.sb.model.impl.HolidayImpl"
)
@ProviderType
public interface Holiday extends HolidayModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.workshop.holiday.sb.model.impl.HolidayImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Holiday, Long> HOLIDAY_REQUEST_ID_ACCESSOR =
		new Accessor<Holiday, Long>() {

			@Override
			public Long get(Holiday holiday) {
				return holiday.getHolidayRequestId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Holiday> getTypeClass() {
				return Holiday.class;
			}

		};

}