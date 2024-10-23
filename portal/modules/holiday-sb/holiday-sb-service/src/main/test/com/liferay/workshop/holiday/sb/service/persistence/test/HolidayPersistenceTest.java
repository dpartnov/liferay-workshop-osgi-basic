/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.workshop.holiday.sb.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.workshop.holiday.sb.exception.NoSuchHolidayException;
import com.liferay.workshop.holiday.sb.model.Holiday;
import com.liferay.workshop.holiday.sb.service.HolidayLocalServiceUtil;
import com.liferay.workshop.holiday.sb.service.persistence.HolidayPersistence;
import com.liferay.workshop.holiday.sb.service.persistence.HolidayUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class HolidayPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.workshop.holiday.sb.service"));

	@Before
	public void setUp() {
		_persistence = HolidayUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Holiday> iterator = _holidays.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Holiday holiday = _persistence.create(pk);

		Assert.assertNotNull(holiday);

		Assert.assertEquals(holiday.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Holiday newHoliday = addHoliday();

		_persistence.remove(newHoliday);

		Holiday existingHoliday = _persistence.fetchByPrimaryKey(
			newHoliday.getPrimaryKey());

		Assert.assertNull(existingHoliday);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHoliday();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Holiday newHoliday = _persistence.create(pk);

		newHoliday.setUuid(RandomTestUtil.randomString());

		newHoliday.setGroupId(RandomTestUtil.nextLong());

		newHoliday.setCreateDate(RandomTestUtil.nextDate());

		newHoliday.setDateRequest(RandomTestUtil.randomString());

		newHoliday.setIsHoliday(RandomTestUtil.randomBoolean());

		newHoliday.setHolidayName(RandomTestUtil.randomString());

		_holidays.add(_persistence.update(newHoliday));

		Holiday existingHoliday = _persistence.findByPrimaryKey(
			newHoliday.getPrimaryKey());

		Assert.assertEquals(existingHoliday.getUuid(), newHoliday.getUuid());
		Assert.assertEquals(
			existingHoliday.getHolidayRequestId(),
			newHoliday.getHolidayRequestId());
		Assert.assertEquals(
			existingHoliday.getGroupId(), newHoliday.getGroupId());
		Assert.assertEquals(
			Time.getShortTimestamp(existingHoliday.getCreateDate()),
			Time.getShortTimestamp(newHoliday.getCreateDate()));
		Assert.assertEquals(
			existingHoliday.getDateRequest(), newHoliday.getDateRequest());
		Assert.assertEquals(
			existingHoliday.isIsHoliday(), newHoliday.isIsHoliday());
		Assert.assertEquals(
			existingHoliday.getHolidayName(), newHoliday.getHolidayName());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByDateRequest() throws Exception {
		_persistence.countByDateRequest("");

		_persistence.countByDateRequest("null");

		_persistence.countByDateRequest((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Holiday newHoliday = addHoliday();

		Holiday existingHoliday = _persistence.findByPrimaryKey(
			newHoliday.getPrimaryKey());

		Assert.assertEquals(existingHoliday, newHoliday);
	}

	@Test(expected = NoSuchHolidayException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Holiday> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"HOLIDAY_Holiday", "uuid", true, "holidayRequestId", true,
			"groupId", true, "createDate", true, "dateRequest", true,
			"isHoliday", true, "holidayName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Holiday newHoliday = addHoliday();

		Holiday existingHoliday = _persistence.fetchByPrimaryKey(
			newHoliday.getPrimaryKey());

		Assert.assertEquals(existingHoliday, newHoliday);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Holiday missingHoliday = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHoliday);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Holiday newHoliday1 = addHoliday();
		Holiday newHoliday2 = addHoliday();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHoliday1.getPrimaryKey());
		primaryKeys.add(newHoliday2.getPrimaryKey());

		Map<Serializable, Holiday> holidays = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, holidays.size());
		Assert.assertEquals(
			newHoliday1, holidays.get(newHoliday1.getPrimaryKey()));
		Assert.assertEquals(
			newHoliday2, holidays.get(newHoliday2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Holiday> holidays = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(holidays.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Holiday newHoliday = addHoliday();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHoliday.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Holiday> holidays = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, holidays.size());
		Assert.assertEquals(
			newHoliday, holidays.get(newHoliday.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Holiday> holidays = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(holidays.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Holiday newHoliday = addHoliday();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHoliday.getPrimaryKey());

		Map<Serializable, Holiday> holidays = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, holidays.size());
		Assert.assertEquals(
			newHoliday, holidays.get(newHoliday.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			HolidayLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Holiday>() {

				@Override
				public void performAction(Holiday holiday) {
					Assert.assertNotNull(holiday);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Holiday newHoliday = addHoliday();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Holiday.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"holidayRequestId", newHoliday.getHolidayRequestId()));

		List<Holiday> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Holiday existingHoliday = result.get(0);

		Assert.assertEquals(existingHoliday, newHoliday);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Holiday.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"holidayRequestId", RandomTestUtil.nextLong()));

		List<Holiday> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Holiday newHoliday = addHoliday();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Holiday.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("holidayRequestId"));

		Object newHolidayRequestId = newHoliday.getHolidayRequestId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"holidayRequestId", new Object[] {newHolidayRequestId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingHolidayRequestId = result.get(0);

		Assert.assertEquals(existingHolidayRequestId, newHolidayRequestId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Holiday.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("holidayRequestId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"holidayRequestId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Holiday newHoliday = addHoliday();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newHoliday.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		Holiday newHoliday = addHoliday();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Holiday.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"holidayRequestId", newHoliday.getHolidayRequestId()));

		List<Holiday> result = _persistence.findWithDynamicQuery(dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(Holiday holiday) {
		Assert.assertEquals(
			holiday.getUuid(),
			ReflectionTestUtil.invoke(
				holiday, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "uuid_"));
		Assert.assertEquals(
			Long.valueOf(holiday.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				holiday, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));
	}

	protected Holiday addHoliday() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Holiday holiday = _persistence.create(pk);

		holiday.setUuid(RandomTestUtil.randomString());

		holiday.setGroupId(RandomTestUtil.nextLong());

		holiday.setCreateDate(RandomTestUtil.nextDate());

		holiday.setDateRequest(RandomTestUtil.randomString());

		holiday.setIsHoliday(RandomTestUtil.randomBoolean());

		holiday.setHolidayName(RandomTestUtil.randomString());

		_holidays.add(_persistence.update(holiday));

		return holiday;
	}

	private List<Holiday> _holidays = new ArrayList<Holiday>();
	private HolidayPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}