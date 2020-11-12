package com.muqeem.assignment.base.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.muqeem.assignment.base.constants.AppConstants
import com.muqeem.assignment.base.network.retrofit.RestClient
import com.muqeem.assignment.home.models.NewsModel
import java.io.IOException
import java.io.InvalidObjectException

@ExperimentalPagingApi
class NewsMediator {  //: RemoteMediator<Int, NewsModel>() {
    val DEFAULT_PAGE_INDEX = 1
    val DEFAULT_PAGE_SIZE = 20



//    override suspend fun load(
//            loadType: LoadType, state: PagingState<Int, NewsModel>
//    ): MediatorResult {
//
//        val pageKeyData = getKeyPageData(loadType, state)
//        val page = when (pageKeyData) {
//            is MediatorResult.Success -> {
//                return pageKeyData
//            }
//            else -> {
//                pageKeyData as Int
//            }
//        }
//        try {
//            val response = RestClient.retrofitService.getNews2(AppConstants.query, AppConstants.API_KEY, page, state.config.pageSize)
//                        val isEndOfList:Boolean = response.getNewsList()!!.isEmpty()
////                            appDatabase.withTransaction {
////                                // clear all tables in the database
////                                if (loadType == LoadType.REFRESH) {
////                                    appDatabase.getRepoDao().clearRemoteKeys()
////                                    appDatabase.getDoggoImageModelDao().clearAllDoggos()
////                                }
////                                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
////                                val nextKey = if (isEndOfList) null else page + 1
////
//////                                appDatabase.getRepoDao().insertAll(keys)
//////                                appDatabase.getDoggoImageModelDao().insertAll(response)
////                            }
//
//            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
//
//        } catch (exception: IOException) {
//            return MediatorResult.Error(exception)
//        } catch (exception: HttpException) {
//            return MediatorResult.Error(exception)
//        }
//    }
//
//    /**
//     * this returns the page key or the final end of list success result
//     */
////    suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, NewsModel>): Any? {
////        return when (loadType) {
////            LoadType.REFRESH -> {
////                val remoteKeys = getClosestRemoteKey(state)
////                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
////            }
//////            LoadType.APPEND -> {
//////                val remoteKeys = getLastRemoteKey(state)
//////                        ?: throw InvalidObjectException("Remote key should not be null for $loadType")
//////                remoteKeys.nextKey
//////            }
//////            LoadType.PREPEND -> {
//////                val remoteKeys = getFirstRemoteKey(state)
//////                        ?: throw InvalidObjectException("Invalid state, key should not be null")
//////                //end of list condition reached
//////                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
//////                remoteKeys.prevKey
//////            }
////        }
////    }
//
//    /**
//     * get the last remote key inserted which had the data
//     */
//    private suspend fun getLastRemoteKey(state: PagingState<Int, DoggoImageModel>): RemoteKeys? {
//        return state.pages
//                .lastOrNull { it.data.isNotEmpty() }
//                ?.data?.lastOrNull()
//                ?.let { doggo -> appDatabase.getRepoDao().remoteKeysDoggoId(doggo.id) }
//    }
//
//    /**
//     * get the first remote key inserted which had the data
//     */
//    private suspend fun getFirstRemoteKey(state: PagingState<Int, DoggoImageModel>): RemoteKeys? {
//        return state.pages
//                .firstOrNull() { it.data.isNotEmpty() }
//                ?.data?.firstOrNull()
//                ?.let { doggo -> appDatabase.getRepoDao().remoteKeysDoggoId(doggo.id) }
//    }
//
//    /**
//     * get the closest remote key inserted which had the data
//     */
//    private suspend fun getClosestRemoteKey(state: PagingState<Int, DoggoImageModel>): RemoteKeys? {
//        return state.anchorPosition?.let { position ->
//            state.closestItemToPosition(position)?.id?.let { repoId ->
//                appDatabase.getRepoDao().remoteKeysDoggoId(repoId)
//            }
//        }
//    }

}