/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.workshop.holiday.sb.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.workshop.holiday.sb.exception.NoSuchHolidayException;
import com.liferay.workshop.holiday.sb.model.Holiday;
import com.liferay.workshop.holiday.sb.model.HolidayTable;
import com.liferay.workshop.holiday.sb.model.impl.HolidayImpl;
import com.liferay.workshop.holiday.sb.model.impl.HolidayModelImpl;
import com.liferay.workshop.holiday.sb.service.persistence.HolidayPersistence;
import com.liferay.workshop.holiday.sb.service.persistence.HolidayUtil;
import com.liferay.workshop.holiday.sb.service.persistence.impl.constants.HOLIDAYPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the holiday service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = HolidayPersistence.class)
public class HolidayPersistenceImpl
	extends BasePersistenceImpl<Holiday> implements HolidayPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>HolidayUtil</code> to access the holiday persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		HolidayImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the holidays where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching holidays
	 */
	@Override
	public List<Holiday> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Holiday> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<Holiday> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Holiday> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<Holiday> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Holiday> orderByComparator, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<Holiday> list = null;

		if (useFinderCache) {
			list = (List<Holiday>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Holiday holiday : list) {
					if (!uuid.equals(holiday.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_HOLIDAY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(HolidayModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<Holiday>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday
	 * @throws NoSuchHolidayException if a matching holiday could not be found
	 */
	@Override
	public Holiday findByUuid_First(
			String uuid, OrderByComparator<Holiday> orderByComparator)
		throws NoSuchHolidayException {

		Holiday holiday = fetchByUuid_First(uuid, orderByComparator);

		if (holiday != null) {
			return holiday;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchHolidayException(sb.toString());
	}

	/**
	 * Returns the first holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday, or <code>null</code> if a matching holiday could not be found
	 */
	@Override
	public Holiday fetchByUuid_First(
		String uuid, OrderByComparator<Holiday> orderByComparator) {

		List<Holiday> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday
	 * @throws NoSuchHolidayException if a matching holiday could not be found
	 */
	@Override
	public Holiday findByUuid_Last(
			String uuid, OrderByComparator<Holiday> orderByComparator)
		throws NoSuchHolidayException {

		Holiday holiday = fetchByUuid_Last(uuid, orderByComparator);

		if (holiday != null) {
			return holiday;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchHolidayException(sb.toString());
	}

	/**
	 * Returns the last holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday, or <code>null</code> if a matching holiday could not be found
	 */
	@Override
	public Holiday fetchByUuid_Last(
		String uuid, OrderByComparator<Holiday> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Holiday> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Holiday[] findByUuid_PrevAndNext(
			long holidayRequestId, String uuid,
			OrderByComparator<Holiday> orderByComparator)
		throws NoSuchHolidayException {

		uuid = Objects.toString(uuid, "");

		Holiday holiday = findByPrimaryKey(holidayRequestId);

		Session session = null;

		try {
			session = openSession();

			Holiday[] array = new HolidayImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, holiday, uuid, orderByComparator, true);

			array[1] = holiday;

			array[2] = getByUuid_PrevAndNext(
				session, holiday, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Holiday getByUuid_PrevAndNext(
		Session session, Holiday holiday, String uuid,
		OrderByComparator<Holiday> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_HOLIDAY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(HolidayModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(holiday)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Holiday> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the holidays where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Holiday holiday :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(holiday);
		}
	}

	/**
	 * Returns the number of holidays where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching holidays
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_HOLIDAY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "holiday.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(holiday.uuid IS NULL OR holiday.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByDateRequest;
	private FinderPath _finderPathWithoutPaginationFindByDateRequest;
	private FinderPath _finderPathCountByDateRequest;

	/**
	 * Returns all the holidays where dateRequest = &#63;.
	 *
	 * @param dateRequest the date request
	 * @return the matching holidays
	 */
	@Override
	public List<Holiday> findByDateRequest(String dateRequest) {
		return findByDateRequest(
			dateRequest, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Holiday> findByDateRequest(
		String dateRequest, int start, int end) {

		return findByDateRequest(dateRequest, start, end, null);
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
	@Override
	public List<Holiday> findByDateRequest(
		String dateRequest, int start, int end,
		OrderByComparator<Holiday> orderByComparator) {

		return findByDateRequest(
			dateRequest, start, end, orderByComparator, true);
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
	@Override
	public List<Holiday> findByDateRequest(
		String dateRequest, int start, int end,
		OrderByComparator<Holiday> orderByComparator, boolean useFinderCache) {

		dateRequest = Objects.toString(dateRequest, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByDateRequest;
				finderArgs = new Object[] {dateRequest};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByDateRequest;
			finderArgs = new Object[] {
				dateRequest, start, end, orderByComparator
			};
		}

		List<Holiday> list = null;

		if (useFinderCache) {
			list = (List<Holiday>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Holiday holiday : list) {
					if (!dateRequest.equals(holiday.getDateRequest())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_HOLIDAY_WHERE);

			boolean bindDateRequest = false;

			if (dateRequest.isEmpty()) {
				sb.append(_FINDER_COLUMN_DATEREQUEST_DATEREQUEST_3);
			}
			else {
				bindDateRequest = true;

				sb.append(_FINDER_COLUMN_DATEREQUEST_DATEREQUEST_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(HolidayModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDateRequest) {
					queryPos.add(dateRequest);
				}

				list = (List<Holiday>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first holiday in the ordered set where dateRequest = &#63;.
	 *
	 * @param dateRequest the date request
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday
	 * @throws NoSuchHolidayException if a matching holiday could not be found
	 */
	@Override
	public Holiday findByDateRequest_First(
			String dateRequest, OrderByComparator<Holiday> orderByComparator)
		throws NoSuchHolidayException {

		Holiday holiday = fetchByDateRequest_First(
			dateRequest, orderByComparator);

		if (holiday != null) {
			return holiday;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("dateRequest=");
		sb.append(dateRequest);

		sb.append("}");

		throw new NoSuchHolidayException(sb.toString());
	}

	/**
	 * Returns the first holiday in the ordered set where dateRequest = &#63;.
	 *
	 * @param dateRequest the date request
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday, or <code>null</code> if a matching holiday could not be found
	 */
	@Override
	public Holiday fetchByDateRequest_First(
		String dateRequest, OrderByComparator<Holiday> orderByComparator) {

		List<Holiday> list = findByDateRequest(
			dateRequest, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last holiday in the ordered set where dateRequest = &#63;.
	 *
	 * @param dateRequest the date request
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday
	 * @throws NoSuchHolidayException if a matching holiday could not be found
	 */
	@Override
	public Holiday findByDateRequest_Last(
			String dateRequest, OrderByComparator<Holiday> orderByComparator)
		throws NoSuchHolidayException {

		Holiday holiday = fetchByDateRequest_Last(
			dateRequest, orderByComparator);

		if (holiday != null) {
			return holiday;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("dateRequest=");
		sb.append(dateRequest);

		sb.append("}");

		throw new NoSuchHolidayException(sb.toString());
	}

	/**
	 * Returns the last holiday in the ordered set where dateRequest = &#63;.
	 *
	 * @param dateRequest the date request
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday, or <code>null</code> if a matching holiday could not be found
	 */
	@Override
	public Holiday fetchByDateRequest_Last(
		String dateRequest, OrderByComparator<Holiday> orderByComparator) {

		int count = countByDateRequest(dateRequest);

		if (count == 0) {
			return null;
		}

		List<Holiday> list = findByDateRequest(
			dateRequest, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Holiday[] findByDateRequest_PrevAndNext(
			long holidayRequestId, String dateRequest,
			OrderByComparator<Holiday> orderByComparator)
		throws NoSuchHolidayException {

		dateRequest = Objects.toString(dateRequest, "");

		Holiday holiday = findByPrimaryKey(holidayRequestId);

		Session session = null;

		try {
			session = openSession();

			Holiday[] array = new HolidayImpl[3];

			array[0] = getByDateRequest_PrevAndNext(
				session, holiday, dateRequest, orderByComparator, true);

			array[1] = holiday;

			array[2] = getByDateRequest_PrevAndNext(
				session, holiday, dateRequest, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Holiday getByDateRequest_PrevAndNext(
		Session session, Holiday holiday, String dateRequest,
		OrderByComparator<Holiday> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_HOLIDAY_WHERE);

		boolean bindDateRequest = false;

		if (dateRequest.isEmpty()) {
			sb.append(_FINDER_COLUMN_DATEREQUEST_DATEREQUEST_3);
		}
		else {
			bindDateRequest = true;

			sb.append(_FINDER_COLUMN_DATEREQUEST_DATEREQUEST_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(HolidayModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindDateRequest) {
			queryPos.add(dateRequest);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(holiday)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Holiday> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the holidays where dateRequest = &#63; from the database.
	 *
	 * @param dateRequest the date request
	 */
	@Override
	public void removeByDateRequest(String dateRequest) {
		for (Holiday holiday :
				findByDateRequest(
					dateRequest, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(holiday);
		}
	}

	/**
	 * Returns the number of holidays where dateRequest = &#63;.
	 *
	 * @param dateRequest the date request
	 * @return the number of matching holidays
	 */
	@Override
	public int countByDateRequest(String dateRequest) {
		dateRequest = Objects.toString(dateRequest, "");

		FinderPath finderPath = _finderPathCountByDateRequest;

		Object[] finderArgs = new Object[] {dateRequest};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_HOLIDAY_WHERE);

			boolean bindDateRequest = false;

			if (dateRequest.isEmpty()) {
				sb.append(_FINDER_COLUMN_DATEREQUEST_DATEREQUEST_3);
			}
			else {
				bindDateRequest = true;

				sb.append(_FINDER_COLUMN_DATEREQUEST_DATEREQUEST_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDateRequest) {
					queryPos.add(dateRequest);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_DATEREQUEST_DATEREQUEST_2 =
		"holiday.dateRequest = ?";

	private static final String _FINDER_COLUMN_DATEREQUEST_DATEREQUEST_3 =
		"(holiday.dateRequest IS NULL OR holiday.dateRequest = '')";

	public HolidayPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Holiday.class);

		setModelImplClass(HolidayImpl.class);
		setModelPKClass(long.class);

		setTable(HolidayTable.INSTANCE);
	}

	/**
	 * Caches the holiday in the entity cache if it is enabled.
	 *
	 * @param holiday the holiday
	 */
	@Override
	public void cacheResult(Holiday holiday) {
		entityCache.putResult(
			HolidayImpl.class, holiday.getPrimaryKey(), holiday);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the holidays in the entity cache if it is enabled.
	 *
	 * @param holidays the holidays
	 */
	@Override
	public void cacheResult(List<Holiday> holidays) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (holidays.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Holiday holiday : holidays) {
			if (entityCache.getResult(
					HolidayImpl.class, holiday.getPrimaryKey()) == null) {

				cacheResult(holiday);
			}
		}
	}

	/**
	 * Clears the cache for all holidays.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HolidayImpl.class);

		finderCache.clearCache(HolidayImpl.class);
	}

	/**
	 * Clears the cache for the holiday.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Holiday holiday) {
		entityCache.removeResult(HolidayImpl.class, holiday);
	}

	@Override
	public void clearCache(List<Holiday> holidays) {
		for (Holiday holiday : holidays) {
			entityCache.removeResult(HolidayImpl.class, holiday);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(HolidayImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(HolidayImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new holiday with the primary key. Does not add the holiday to the database.
	 *
	 * @param holidayRequestId the primary key for the new holiday
	 * @return the new holiday
	 */
	@Override
	public Holiday create(long holidayRequestId) {
		Holiday holiday = new HolidayImpl();

		holiday.setNew(true);
		holiday.setPrimaryKey(holidayRequestId);

		String uuid = PortalUUIDUtil.generate();

		holiday.setUuid(uuid);

		return holiday;
	}

	/**
	 * Removes the holiday with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param holidayRequestId the primary key of the holiday
	 * @return the holiday that was removed
	 * @throws NoSuchHolidayException if a holiday with the primary key could not be found
	 */
	@Override
	public Holiday remove(long holidayRequestId) throws NoSuchHolidayException {
		return remove((Serializable)holidayRequestId);
	}

	/**
	 * Removes the holiday with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the holiday
	 * @return the holiday that was removed
	 * @throws NoSuchHolidayException if a holiday with the primary key could not be found
	 */
	@Override
	public Holiday remove(Serializable primaryKey)
		throws NoSuchHolidayException {

		Session session = null;

		try {
			session = openSession();

			Holiday holiday = (Holiday)session.get(
				HolidayImpl.class, primaryKey);

			if (holiday == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHolidayException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(holiday);
		}
		catch (NoSuchHolidayException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Holiday removeImpl(Holiday holiday) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(holiday)) {
				holiday = (Holiday)session.get(
					HolidayImpl.class, holiday.getPrimaryKeyObj());
			}

			if (holiday != null) {
				session.delete(holiday);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (holiday != null) {
			clearCache(holiday);
		}

		return holiday;
	}

	@Override
	public Holiday updateImpl(Holiday holiday) {
		boolean isNew = holiday.isNew();

		if (!(holiday instanceof HolidayModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(holiday.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(holiday);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in holiday proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Holiday implementation " +
					holiday.getClass());
		}

		HolidayModelImpl holidayModelImpl = (HolidayModelImpl)holiday;

		if (Validator.isNull(holiday.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			holiday.setUuid(uuid);
		}

		if (isNew && (holiday.getCreateDate() == null)) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				holiday.setCreateDate(date);
			}
			else {
				holiday.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(holiday);
			}
			else {
				holiday = (Holiday)session.merge(holiday);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(HolidayImpl.class, holidayModelImpl, false, true);

		if (isNew) {
			holiday.setNew(false);
		}

		holiday.resetOriginalValues();

		return holiday;
	}

	/**
	 * Returns the holiday with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday
	 * @return the holiday
	 * @throws NoSuchHolidayException if a holiday with the primary key could not be found
	 */
	@Override
	public Holiday findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHolidayException {

		Holiday holiday = fetchByPrimaryKey(primaryKey);

		if (holiday == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHolidayException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return holiday;
	}

	/**
	 * Returns the holiday with the primary key or throws a <code>NoSuchHolidayException</code> if it could not be found.
	 *
	 * @param holidayRequestId the primary key of the holiday
	 * @return the holiday
	 * @throws NoSuchHolidayException if a holiday with the primary key could not be found
	 */
	@Override
	public Holiday findByPrimaryKey(long holidayRequestId)
		throws NoSuchHolidayException {

		return findByPrimaryKey((Serializable)holidayRequestId);
	}

	/**
	 * Returns the holiday with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param holidayRequestId the primary key of the holiday
	 * @return the holiday, or <code>null</code> if a holiday with the primary key could not be found
	 */
	@Override
	public Holiday fetchByPrimaryKey(long holidayRequestId) {
		return fetchByPrimaryKey((Serializable)holidayRequestId);
	}

	/**
	 * Returns all the holidays.
	 *
	 * @return the holidays
	 */
	@Override
	public List<Holiday> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Holiday> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Holiday> findAll(
		int start, int end, OrderByComparator<Holiday> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<Holiday> findAll(
		int start, int end, OrderByComparator<Holiday> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Holiday> list = null;

		if (useFinderCache) {
			list = (List<Holiday>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_HOLIDAY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_HOLIDAY;

				sql = sql.concat(HolidayModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Holiday>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the holidays from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Holiday holiday : findAll()) {
			remove(holiday);
		}
	}

	/**
	 * Returns the number of holidays.
	 *
	 * @return the number of holidays
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_HOLIDAY);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "holidayRequestId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_HOLIDAY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return HolidayModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the holiday persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathWithPaginationFindByDateRequest = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDateRequest",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"dateRequest"}, true);

		_finderPathWithoutPaginationFindByDateRequest = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDateRequest",
			new String[] {String.class.getName()}, new String[] {"dateRequest"},
			true);

		_finderPathCountByDateRequest = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDateRequest",
			new String[] {String.class.getName()}, new String[] {"dateRequest"},
			false);

		HolidayUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		HolidayUtil.setPersistence(null);

		entityCache.removeCache(HolidayImpl.class.getName());
	}

	@Override
	@Reference(
		target = HOLIDAYPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = HOLIDAYPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = HOLIDAYPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_HOLIDAY =
		"SELECT holiday FROM Holiday holiday";

	private static final String _SQL_SELECT_HOLIDAY_WHERE =
		"SELECT holiday FROM Holiday holiday WHERE ";

	private static final String _SQL_COUNT_HOLIDAY =
		"SELECT COUNT(holiday) FROM Holiday holiday";

	private static final String _SQL_COUNT_HOLIDAY_WHERE =
		"SELECT COUNT(holiday) FROM Holiday holiday WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "holiday.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Holiday exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Holiday exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		HolidayPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}