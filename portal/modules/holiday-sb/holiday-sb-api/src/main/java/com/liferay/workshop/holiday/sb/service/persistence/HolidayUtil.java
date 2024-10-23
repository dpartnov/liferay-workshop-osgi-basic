/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.workshop.holiday.sb.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.workshop.holiday.sb.model.Holiday;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the holiday service. This utility wraps <code>com.liferay.workshop.holiday.sb.service.persistence.impl.HolidayPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayPersistence
 * @generated
 */
public class HolidayUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Holiday holiday) {
		getPersistence().clearCache(holiday);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Holiday> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Holiday> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Holiday> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Holiday> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Holiday> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Holiday update(Holiday holiday) {
		return getPersistence().update(holiday);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Holiday update(
		Holiday holiday, ServiceContext serviceContext) {

		return getPersistence().update(holiday, serviceContext);
	}

	/**
	 * Returns all the holidays where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching holidays
	 */
	public static List<Holiday> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the holidays where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HolidayModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of holidays
	 * @param end the upper bound of the range of holidays (not inclusive)
	 * @return the range of matching holidays
	 */
	public static List<Holiday> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the holidays where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HolidayModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of holidays
	 * @param end the upper bound of the range of holidays (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching holidays
	 */
	public static List<Holiday> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Holiday> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the holidays where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HolidayModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of holidays
	 * @param end the upper bound of the range of holidays (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching holidays
	 */
	public static List<Holiday> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Holiday> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday
	 * @throws NoSuchHolidayException if a matching holiday could not be found
	 */
	public static Holiday findByUuid_First(
			String uuid, OrderByComparator<Holiday> orderByComparator)
		throws com.liferay.workshop.holiday.sb.exception.
			NoSuchHolidayException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday, or <code>null</code> if a matching holiday could not be found
	 */
	public static Holiday fetchByUuid_First(
		String uuid, OrderByComparator<Holiday> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday
	 * @throws NoSuchHolidayException if a matching holiday could not be found
	 */
	public static Holiday findByUuid_Last(
			String uuid, OrderByComparator<Holiday> orderByComparator)
		throws com.liferay.workshop.holiday.sb.exception.
			NoSuchHolidayException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday, or <code>null</code> if a matching holiday could not be found
	 */
	public static Holiday fetchByUuid_Last(
		String uuid, OrderByComparator<Holiday> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the holidays before and after the current holiday in the ordered set where uuid = &#63;.
	 *
	 * @param holidayRequestId the primary key of the current holiday
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next holiday
	 * @throws NoSuchHolidayException if a holiday with the primary key could not be found
	 */
	public static Holiday[] findByUuid_PrevAndNext(
			long holidayRequestId, String uuid,
			OrderByComparator<Holiday> orderByComparator)
		throws com.liferay.workshop.holiday.sb.exception.
			NoSuchHolidayException {

		return getPersistence().findByUuid_PrevAndNext(
			holidayRequestId, uuid, orderByComparator);
	}

	/**
	 * Removes all the holidays where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of holidays where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching holidays
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the holiday where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchHolidayException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching holiday
	 * @throws NoSuchHolidayException if a matching holiday could not be found
	 */
	public static Holiday findByUUID_G(String uuid, long groupId)
		throws com.liferay.workshop.holiday.sb.exception.
			NoSuchHolidayException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the holiday where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching holiday, or <code>null</code> if a matching holiday could not be found
	 */
	public static Holiday fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the holiday where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching holiday, or <code>null</code> if a matching holiday could not be found
	 */
	public static Holiday fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the holiday where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the holiday that was removed
	 */
	public static Holiday removeByUUID_G(String uuid, long groupId)
		throws com.liferay.workshop.holiday.sb.exception.
			NoSuchHolidayException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of holidays where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching holidays
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the holidays where dateRequest = &#63;.
	 *
	 * @param dateRequest the date request
	 * @return the matching holidays
	 */
	public static List<Holiday> findByDateRequest(String dateRequest) {
		return getPersistence().findByDateRequest(dateRequest);
	}

	/**
	 * Returns a range of all the holidays where dateRequest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HolidayModelImpl</code>.
	 * </p>
	 *
	 * @param dateRequest the date request
	 * @param start the lower bound of the range of holidays
	 * @param end the upper bound of the range of holidays (not inclusive)
	 * @return the range of matching holidays
	 */
	public static List<Holiday> findByDateRequest(
		String dateRequest, int start, int end) {

		return getPersistence().findByDateRequest(dateRequest, start, end);
	}

	/**
	 * Returns an ordered range of all the holidays where dateRequest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HolidayModelImpl</code>.
	 * </p>
	 *
	 * @param dateRequest the date request
	 * @param start the lower bound of the range of holidays
	 * @param end the upper bound of the range of holidays (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching holidays
	 */
	public static List<Holiday> findByDateRequest(
		String dateRequest, int start, int end,
		OrderByComparator<Holiday> orderByComparator) {

		return getPersistence().findByDateRequest(
			dateRequest, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the holidays where dateRequest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HolidayModelImpl</code>.
	 * </p>
	 *
	 * @param dateRequest the date request
	 * @param start the lower bound of the range of holidays
	 * @param end the upper bound of the range of holidays (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching holidays
	 */
	public static List<Holiday> findByDateRequest(
		String dateRequest, int start, int end,
		OrderByComparator<Holiday> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByDateRequest(
			dateRequest, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first holiday in the ordered set where dateRequest = &#63;.
	 *
	 * @param dateRequest the date request
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday
	 * @throws NoSuchHolidayException if a matching holiday could not be found
	 */
	public static Holiday findByDateRequest_First(
			String dateRequest, OrderByComparator<Holiday> orderByComparator)
		throws com.liferay.workshop.holiday.sb.exception.
			NoSuchHolidayException {

		return getPersistence().findByDateRequest_First(
			dateRequest, orderByComparator);
	}

	/**
	 * Returns the first holiday in the ordered set where dateRequest = &#63;.
	 *
	 * @param dateRequest the date request
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday, or <code>null</code> if a matching holiday could not be found
	 */
	public static Holiday fetchByDateRequest_First(
		String dateRequest, OrderByComparator<Holiday> orderByComparator) {

		return getPersistence().fetchByDateRequest_First(
			dateRequest, orderByComparator);
	}

	/**
	 * Returns the last holiday in the ordered set where dateRequest = &#63;.
	 *
	 * @param dateRequest the date request
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday
	 * @throws NoSuchHolidayException if a matching holiday could not be found
	 */
	public static Holiday findByDateRequest_Last(
			String dateRequest, OrderByComparator<Holiday> orderByComparator)
		throws com.liferay.workshop.holiday.sb.exception.
			NoSuchHolidayException {

		return getPersistence().findByDateRequest_Last(
			dateRequest, orderByComparator);
	}

	/**
	 * Returns the last holiday in the ordered set where dateRequest = &#63;.
	 *
	 * @param dateRequest the date request
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday, or <code>null</code> if a matching holiday could not be found
	 */
	public static Holiday fetchByDateRequest_Last(
		String dateRequest, OrderByComparator<Holiday> orderByComparator) {

		return getPersistence().fetchByDateRequest_Last(
			dateRequest, orderByComparator);
	}

	/**
	 * Returns the holidays before and after the current holiday in the ordered set where dateRequest = &#63;.
	 *
	 * @param holidayRequestId the primary key of the current holiday
	 * @param dateRequest the date request
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next holiday
	 * @throws NoSuchHolidayException if a holiday with the primary key could not be found
	 */
	public static Holiday[] findByDateRequest_PrevAndNext(
			long holidayRequestId, String dateRequest,
			OrderByComparator<Holiday> orderByComparator)
		throws com.liferay.workshop.holiday.sb.exception.
			NoSuchHolidayException {

		return getPersistence().findByDateRequest_PrevAndNext(
			holidayRequestId, dateRequest, orderByComparator);
	}

	/**
	 * Removes all the holidays where dateRequest = &#63; from the database.
	 *
	 * @param dateRequest the date request
	 */
	public static void removeByDateRequest(String dateRequest) {
		getPersistence().removeByDateRequest(dateRequest);
	}

	/**
	 * Returns the number of holidays where dateRequest = &#63;.
	 *
	 * @param dateRequest the date request
	 * @return the number of matching holidays
	 */
	public static int countByDateRequest(String dateRequest) {
		return getPersistence().countByDateRequest(dateRequest);
	}

	/**
	 * Caches the holiday in the entity cache if it is enabled.
	 *
	 * @param holiday the holiday
	 */
	public static void cacheResult(Holiday holiday) {
		getPersistence().cacheResult(holiday);
	}

	/**
	 * Caches the holidays in the entity cache if it is enabled.
	 *
	 * @param holidays the holidays
	 */
	public static void cacheResult(List<Holiday> holidays) {
		getPersistence().cacheResult(holidays);
	}

	/**
	 * Creates a new holiday with the primary key. Does not add the holiday to the database.
	 *
	 * @param holidayRequestId the primary key for the new holiday
	 * @return the new holiday
	 */
	public static Holiday create(long holidayRequestId) {
		return getPersistence().create(holidayRequestId);
	}

	/**
	 * Removes the holiday with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param holidayRequestId the primary key of the holiday
	 * @return the holiday that was removed
	 * @throws NoSuchHolidayException if a holiday with the primary key could not be found
	 */
	public static Holiday remove(long holidayRequestId)
		throws com.liferay.workshop.holiday.sb.exception.
			NoSuchHolidayException {

		return getPersistence().remove(holidayRequestId);
	}

	public static Holiday updateImpl(Holiday holiday) {
		return getPersistence().updateImpl(holiday);
	}

	/**
	 * Returns the holiday with the primary key or throws a <code>NoSuchHolidayException</code> if it could not be found.
	 *
	 * @param holidayRequestId the primary key of the holiday
	 * @return the holiday
	 * @throws NoSuchHolidayException if a holiday with the primary key could not be found
	 */
	public static Holiday findByPrimaryKey(long holidayRequestId)
		throws com.liferay.workshop.holiday.sb.exception.
			NoSuchHolidayException {

		return getPersistence().findByPrimaryKey(holidayRequestId);
	}

	/**
	 * Returns the holiday with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param holidayRequestId the primary key of the holiday
	 * @return the holiday, or <code>null</code> if a holiday with the primary key could not be found
	 */
	public static Holiday fetchByPrimaryKey(long holidayRequestId) {
		return getPersistence().fetchByPrimaryKey(holidayRequestId);
	}

	/**
	 * Returns all the holidays.
	 *
	 * @return the holidays
	 */
	public static List<Holiday> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the holidays.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HolidayModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of holidays
	 * @param end the upper bound of the range of holidays (not inclusive)
	 * @return the range of holidays
	 */
	public static List<Holiday> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the holidays.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HolidayModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of holidays
	 * @param end the upper bound of the range of holidays (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of holidays
	 */
	public static List<Holiday> findAll(
		int start, int end, OrderByComparator<Holiday> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the holidays.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HolidayModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of holidays
	 * @param end the upper bound of the range of holidays (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of holidays
	 */
	public static List<Holiday> findAll(
		int start, int end, OrderByComparator<Holiday> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the holidays from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of holidays.
	 *
	 * @return the number of holidays
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HolidayPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(HolidayPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile HolidayPersistence _persistence;

}