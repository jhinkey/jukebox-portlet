/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.liferay.jukebox.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.expando.service.persistence.ExpandoValuePersistence;
import com.liferay.portlet.messageboards.service.persistence.MBMessagePersistence;
import com.liferay.portlet.social.service.persistence.SocialActivityPersistence;

import org.liferay.jukebox.model.Artist;
import org.liferay.jukebox.service.ArtistLocalService;
import org.liferay.jukebox.service.persistence.AlbumPersistence;
import org.liferay.jukebox.service.persistence.ArtistPersistence;
import org.liferay.jukebox.service.persistence.SongPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the artist local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.liferay.jukebox.service.impl.ArtistLocalServiceImpl}.
 * </p>
 *
 * @author Julio Camarero
 * @see org.liferay.jukebox.service.impl.ArtistLocalServiceImpl
 * @see org.liferay.jukebox.service.ArtistLocalServiceUtil
 * @generated
 */
public abstract class ArtistLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements ArtistLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link org.liferay.jukebox.service.ArtistLocalServiceUtil} to access the artist local service.
	 */

	/**
	 * Adds the artist to the database. Also notifies the appropriate model listeners.
	 *
	 * @param artist the artist
	 * @return the artist that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Artist addArtist(Artist artist) throws SystemException {
		artist.setNew(true);

		return artistPersistence.update(artist);
	}

	/**
	 * Creates a new artist with the primary key. Does not add the artist to the database.
	 *
	 * @param artistId the primary key for the new artist
	 * @return the new artist
	 */
	@Override
	public Artist createArtist(long artistId) {
		return artistPersistence.create(artistId);
	}

	/**
	 * Deletes the artist with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param artistId the primary key of the artist
	 * @return the artist that was removed
	 * @throws PortalException if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Artist deleteArtist(long artistId)
		throws PortalException, SystemException {
		return artistPersistence.remove(artistId);
	}

	/**
	 * Deletes the artist from the database. Also notifies the appropriate model listeners.
	 *
	 * @param artist the artist
	 * @return the artist that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Artist deleteArtist(Artist artist) throws SystemException {
		return artistPersistence.remove(artist);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Artist.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return artistPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return artistPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return artistPersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return artistPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return artistPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Artist fetchArtist(long artistId) throws SystemException {
		return artistPersistence.fetchByPrimaryKey(artistId);
	}

	/**
	 * Returns the artist with the matching UUID and company.
	 *
	 * @param uuid the artist's UUID
	 * @param  companyId the primary key of the company
	 * @return the matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchArtistByUuidAndCompanyId(String uuid, long companyId)
		throws SystemException {
		return artistPersistence.fetchByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the artist matching the UUID and group.
	 *
	 * @param uuid the artist's UUID
	 * @param groupId the primary key of the group
	 * @return the matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchArtistByUuidAndGroupId(String uuid, long groupId)
		throws SystemException {
		return artistPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the artist with the primary key.
	 *
	 * @param artistId the primary key of the artist
	 * @return the artist
	 * @throws PortalException if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist getArtist(long artistId)
		throws PortalException, SystemException {
		return artistPersistence.findByPrimaryKey(artistId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return artistPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the artist with the matching UUID and company.
	 *
	 * @param uuid the artist's UUID
	 * @param  companyId the primary key of the company
	 * @return the matching artist
	 * @throws PortalException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist getArtistByUuidAndCompanyId(String uuid, long companyId)
		throws PortalException, SystemException {
		return artistPersistence.findByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the artist matching the UUID and group.
	 *
	 * @param uuid the artist's UUID
	 * @param groupId the primary key of the group
	 * @return the matching artist
	 * @throws PortalException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist getArtistByUuidAndGroupId(String uuid, long groupId)
		throws PortalException, SystemException {
		return artistPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the artists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @return the range of artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> getArtists(int start, int end)
		throws SystemException {
		return artistPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of artists.
	 *
	 * @return the number of artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getArtistsCount() throws SystemException {
		return artistPersistence.countAll();
	}

	/**
	 * Updates the artist in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param artist the artist
	 * @return the artist that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Artist updateArtist(Artist artist) throws SystemException {
		return artistPersistence.update(artist);
	}

	/**
	 * Returns the album local service.
	 *
	 * @return the album local service
	 */
	public org.liferay.jukebox.service.AlbumLocalService getAlbumLocalService() {
		return albumLocalService;
	}

	/**
	 * Sets the album local service.
	 *
	 * @param albumLocalService the album local service
	 */
	public void setAlbumLocalService(
		org.liferay.jukebox.service.AlbumLocalService albumLocalService) {
		this.albumLocalService = albumLocalService;
	}

	/**
	 * Returns the album remote service.
	 *
	 * @return the album remote service
	 */
	public org.liferay.jukebox.service.AlbumService getAlbumService() {
		return albumService;
	}

	/**
	 * Sets the album remote service.
	 *
	 * @param albumService the album remote service
	 */
	public void setAlbumService(
		org.liferay.jukebox.service.AlbumService albumService) {
		this.albumService = albumService;
	}

	/**
	 * Returns the album persistence.
	 *
	 * @return the album persistence
	 */
	public AlbumPersistence getAlbumPersistence() {
		return albumPersistence;
	}

	/**
	 * Sets the album persistence.
	 *
	 * @param albumPersistence the album persistence
	 */
	public void setAlbumPersistence(AlbumPersistence albumPersistence) {
		this.albumPersistence = albumPersistence;
	}

	/**
	 * Returns the artist local service.
	 *
	 * @return the artist local service
	 */
	public org.liferay.jukebox.service.ArtistLocalService getArtistLocalService() {
		return artistLocalService;
	}

	/**
	 * Sets the artist local service.
	 *
	 * @param artistLocalService the artist local service
	 */
	public void setArtistLocalService(
		org.liferay.jukebox.service.ArtistLocalService artistLocalService) {
		this.artistLocalService = artistLocalService;
	}

	/**
	 * Returns the artist remote service.
	 *
	 * @return the artist remote service
	 */
	public org.liferay.jukebox.service.ArtistService getArtistService() {
		return artistService;
	}

	/**
	 * Sets the artist remote service.
	 *
	 * @param artistService the artist remote service
	 */
	public void setArtistService(
		org.liferay.jukebox.service.ArtistService artistService) {
		this.artistService = artistService;
	}

	/**
	 * Returns the artist persistence.
	 *
	 * @return the artist persistence
	 */
	public ArtistPersistence getArtistPersistence() {
		return artistPersistence;
	}

	/**
	 * Sets the artist persistence.
	 *
	 * @param artistPersistence the artist persistence
	 */
	public void setArtistPersistence(ArtistPersistence artistPersistence) {
		this.artistPersistence = artistPersistence;
	}

	/**
	 * Returns the song local service.
	 *
	 * @return the song local service
	 */
	public org.liferay.jukebox.service.SongLocalService getSongLocalService() {
		return songLocalService;
	}

	/**
	 * Sets the song local service.
	 *
	 * @param songLocalService the song local service
	 */
	public void setSongLocalService(
		org.liferay.jukebox.service.SongLocalService songLocalService) {
		this.songLocalService = songLocalService;
	}

	/**
	 * Returns the song remote service.
	 *
	 * @return the song remote service
	 */
	public org.liferay.jukebox.service.SongService getSongService() {
		return songService;
	}

	/**
	 * Sets the song remote service.
	 *
	 * @param songService the song remote service
	 */
	public void setSongService(
		org.liferay.jukebox.service.SongService songService) {
		this.songService = songService;
	}

	/**
	 * Returns the song persistence.
	 *
	 * @return the song persistence
	 */
	public SongPersistence getSongPersistence() {
		return songPersistence;
	}

	/**
	 * Sets the song persistence.
	 *
	 * @param songPersistence the song persistence
	 */
	public void setSongPersistence(SongPersistence songPersistence) {
		this.songPersistence = songPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the company local service.
	 *
	 * @return the company local service
	 */
	public com.liferay.portal.service.CompanyLocalService getCompanyLocalService() {
		return companyLocalService;
	}

	/**
	 * Sets the company local service.
	 *
	 * @param companyLocalService the company local service
	 */
	public void setCompanyLocalService(
		com.liferay.portal.service.CompanyLocalService companyLocalService) {
		this.companyLocalService = companyLocalService;
	}

	/**
	 * Returns the company remote service.
	 *
	 * @return the company remote service
	 */
	public com.liferay.portal.service.CompanyService getCompanyService() {
		return companyService;
	}

	/**
	 * Sets the company remote service.
	 *
	 * @param companyService the company remote service
	 */
	public void setCompanyService(
		com.liferay.portal.service.CompanyService companyService) {
		this.companyService = companyService;
	}

	/**
	 * Returns the company persistence.
	 *
	 * @return the company persistence
	 */
	public CompanyPersistence getCompanyPersistence() {
		return companyPersistence;
	}

	/**
	 * Sets the company persistence.
	 *
	 * @param companyPersistence the company persistence
	 */
	public void setCompanyPersistence(CompanyPersistence companyPersistence) {
		this.companyPersistence = companyPersistence;
	}

	/**
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.service.GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.service.GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group remote service.
	 *
	 * @return the group remote service
	 */
	public com.liferay.portal.service.GroupService getGroupService() {
		return groupService;
	}

	/**
	 * Sets the group remote service.
	 *
	 * @param groupService the group remote service
	 */
	public void setGroupService(
		com.liferay.portal.service.GroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the asset entry local service.
	 *
	 * @return the asset entry local service
	 */
	public com.liferay.portlet.asset.service.AssetEntryLocalService getAssetEntryLocalService() {
		return assetEntryLocalService;
	}

	/**
	 * Sets the asset entry local service.
	 *
	 * @param assetEntryLocalService the asset entry local service
	 */
	public void setAssetEntryLocalService(
		com.liferay.portlet.asset.service.AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}

	/**
	 * Returns the asset entry remote service.
	 *
	 * @return the asset entry remote service
	 */
	public com.liferay.portlet.asset.service.AssetEntryService getAssetEntryService() {
		return assetEntryService;
	}

	/**
	 * Sets the asset entry remote service.
	 *
	 * @param assetEntryService the asset entry remote service
	 */
	public void setAssetEntryService(
		com.liferay.portlet.asset.service.AssetEntryService assetEntryService) {
		this.assetEntryService = assetEntryService;
	}

	/**
	 * Returns the asset entry persistence.
	 *
	 * @return the asset entry persistence
	 */
	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	/**
	 * Sets the asset entry persistence.
	 *
	 * @param assetEntryPersistence the asset entry persistence
	 */
	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {
		this.assetEntryPersistence = assetEntryPersistence;
	}

	/**
	 * Returns the expando value local service.
	 *
	 * @return the expando value local service
	 */
	public com.liferay.portlet.expando.service.ExpandoValueLocalService getExpandoValueLocalService() {
		return expandoValueLocalService;
	}

	/**
	 * Sets the expando value local service.
	 *
	 * @param expandoValueLocalService the expando value local service
	 */
	public void setExpandoValueLocalService(
		com.liferay.portlet.expando.service.ExpandoValueLocalService expandoValueLocalService) {
		this.expandoValueLocalService = expandoValueLocalService;
	}

	/**
	 * Returns the expando value remote service.
	 *
	 * @return the expando value remote service
	 */
	public com.liferay.portlet.expando.service.ExpandoValueService getExpandoValueService() {
		return expandoValueService;
	}

	/**
	 * Sets the expando value remote service.
	 *
	 * @param expandoValueService the expando value remote service
	 */
	public void setExpandoValueService(
		com.liferay.portlet.expando.service.ExpandoValueService expandoValueService) {
		this.expandoValueService = expandoValueService;
	}

	/**
	 * Returns the expando value persistence.
	 *
	 * @return the expando value persistence
	 */
	public ExpandoValuePersistence getExpandoValuePersistence() {
		return expandoValuePersistence;
	}

	/**
	 * Sets the expando value persistence.
	 *
	 * @param expandoValuePersistence the expando value persistence
	 */
	public void setExpandoValuePersistence(
		ExpandoValuePersistence expandoValuePersistence) {
		this.expandoValuePersistence = expandoValuePersistence;
	}

	/**
	 * Returns the message-boards message local service.
	 *
	 * @return the message-boards message local service
	 */
	public com.liferay.portlet.messageboards.service.MBMessageLocalService getMBMessageLocalService() {
		return mbMessageLocalService;
	}

	/**
	 * Sets the message-boards message local service.
	 *
	 * @param mbMessageLocalService the message-boards message local service
	 */
	public void setMBMessageLocalService(
		com.liferay.portlet.messageboards.service.MBMessageLocalService mbMessageLocalService) {
		this.mbMessageLocalService = mbMessageLocalService;
	}

	/**
	 * Returns the message-boards message remote service.
	 *
	 * @return the message-boards message remote service
	 */
	public com.liferay.portlet.messageboards.service.MBMessageService getMBMessageService() {
		return mbMessageService;
	}

	/**
	 * Sets the message-boards message remote service.
	 *
	 * @param mbMessageService the message-boards message remote service
	 */
	public void setMBMessageService(
		com.liferay.portlet.messageboards.service.MBMessageService mbMessageService) {
		this.mbMessageService = mbMessageService;
	}

	/**
	 * Returns the message-boards message persistence.
	 *
	 * @return the message-boards message persistence
	 */
	public MBMessagePersistence getMBMessagePersistence() {
		return mbMessagePersistence;
	}

	/**
	 * Sets the message-boards message persistence.
	 *
	 * @param mbMessagePersistence the message-boards message persistence
	 */
	public void setMBMessagePersistence(
		MBMessagePersistence mbMessagePersistence) {
		this.mbMessagePersistence = mbMessagePersistence;
	}

	/**
	 * Returns the social activity local service.
	 *
	 * @return the social activity local service
	 */
	public com.liferay.portlet.social.service.SocialActivityLocalService getSocialActivityLocalService() {
		return socialActivityLocalService;
	}

	/**
	 * Sets the social activity local service.
	 *
	 * @param socialActivityLocalService the social activity local service
	 */
	public void setSocialActivityLocalService(
		com.liferay.portlet.social.service.SocialActivityLocalService socialActivityLocalService) {
		this.socialActivityLocalService = socialActivityLocalService;
	}

	/**
	 * Returns the social activity remote service.
	 *
	 * @return the social activity remote service
	 */
	public com.liferay.portlet.social.service.SocialActivityService getSocialActivityService() {
		return socialActivityService;
	}

	/**
	 * Sets the social activity remote service.
	 *
	 * @param socialActivityService the social activity remote service
	 */
	public void setSocialActivityService(
		com.liferay.portlet.social.service.SocialActivityService socialActivityService) {
		this.socialActivityService = socialActivityService;
	}

	/**
	 * Returns the social activity persistence.
	 *
	 * @return the social activity persistence
	 */
	public SocialActivityPersistence getSocialActivityPersistence() {
		return socialActivityPersistence;
	}

	/**
	 * Sets the social activity persistence.
	 *
	 * @param socialActivityPersistence the social activity persistence
	 */
	public void setSocialActivityPersistence(
		SocialActivityPersistence socialActivityPersistence) {
		this.socialActivityPersistence = socialActivityPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("org.liferay.jukebox.model.Artist",
			artistLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"org.liferay.jukebox.model.Artist");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return Artist.class;
	}

	protected String getModelClassName() {
		return Artist.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = artistPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = org.liferay.jukebox.service.AlbumLocalService.class)
	protected org.liferay.jukebox.service.AlbumLocalService albumLocalService;
	@BeanReference(type = org.liferay.jukebox.service.AlbumService.class)
	protected org.liferay.jukebox.service.AlbumService albumService;
	@BeanReference(type = AlbumPersistence.class)
	protected AlbumPersistence albumPersistence;
	@BeanReference(type = org.liferay.jukebox.service.ArtistLocalService.class)
	protected org.liferay.jukebox.service.ArtistLocalService artistLocalService;
	@BeanReference(type = org.liferay.jukebox.service.ArtistService.class)
	protected org.liferay.jukebox.service.ArtistService artistService;
	@BeanReference(type = ArtistPersistence.class)
	protected ArtistPersistence artistPersistence;
	@BeanReference(type = org.liferay.jukebox.service.SongLocalService.class)
	protected org.liferay.jukebox.service.SongLocalService songLocalService;
	@BeanReference(type = org.liferay.jukebox.service.SongService.class)
	protected org.liferay.jukebox.service.SongService songService;
	@BeanReference(type = SongPersistence.class)
	protected SongPersistence songPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.CompanyLocalService.class)
	protected com.liferay.portal.service.CompanyLocalService companyLocalService;
	@BeanReference(type = com.liferay.portal.service.CompanyService.class)
	protected com.liferay.portal.service.CompanyService companyService;
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = com.liferay.portal.service.GroupLocalService.class)
	protected com.liferay.portal.service.GroupLocalService groupLocalService;
	@BeanReference(type = com.liferay.portal.service.GroupService.class)
	protected com.liferay.portal.service.GroupService groupService;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = com.liferay.portlet.asset.service.AssetEntryLocalService.class)
	protected com.liferay.portlet.asset.service.AssetEntryLocalService assetEntryLocalService;
	@BeanReference(type = com.liferay.portlet.asset.service.AssetEntryService.class)
	protected com.liferay.portlet.asset.service.AssetEntryService assetEntryService;
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	@BeanReference(type = com.liferay.portlet.expando.service.ExpandoValueLocalService.class)
	protected com.liferay.portlet.expando.service.ExpandoValueLocalService expandoValueLocalService;
	@BeanReference(type = com.liferay.portlet.expando.service.ExpandoValueService.class)
	protected com.liferay.portlet.expando.service.ExpandoValueService expandoValueService;
	@BeanReference(type = ExpandoValuePersistence.class)
	protected ExpandoValuePersistence expandoValuePersistence;
	@BeanReference(type = com.liferay.portlet.messageboards.service.MBMessageLocalService.class)
	protected com.liferay.portlet.messageboards.service.MBMessageLocalService mbMessageLocalService;
	@BeanReference(type = com.liferay.portlet.messageboards.service.MBMessageService.class)
	protected com.liferay.portlet.messageboards.service.MBMessageService mbMessageService;
	@BeanReference(type = MBMessagePersistence.class)
	protected MBMessagePersistence mbMessagePersistence;
	@BeanReference(type = com.liferay.portlet.social.service.SocialActivityLocalService.class)
	protected com.liferay.portlet.social.service.SocialActivityLocalService socialActivityLocalService;
	@BeanReference(type = com.liferay.portlet.social.service.SocialActivityService.class)
	protected com.liferay.portlet.social.service.SocialActivityService socialActivityService;
	@BeanReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private ArtistLocalServiceClpInvoker _clpInvoker = new ArtistLocalServiceClpInvoker();
}