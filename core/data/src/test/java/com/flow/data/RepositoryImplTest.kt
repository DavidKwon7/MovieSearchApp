package com.flow.data

import app.cash.turbine.test
import com.flow.data.mapper.SearchDataDomainMapper
import com.flow.data.repository.RepositoryImpl
import com.flow.data.source.LocalDataSource
import com.flow.data.source.Remote
import com.flow.domain.repository.Repository
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class RepositoryImplTest {

    @MockK
    private lateinit var localDataSource: LocalDataSource

    @MockK
    private lateinit var remote: Remote

    private val searchDataDomainMapper = SearchDataDomainMapper()

    private lateinit var repository: Repository

    @Before
    fun set_up() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = RepositoryImpl(
            localDataSource = localDataSource,
            remote = remote,
            searchDataDomainMapper = searchDataDomainMapper
        )
    }

    @Test
    fun test_get_all_search_success() = runTest {
        val search = TestDataGenerator.searchModel()

        coEvery { localDataSource.getAllSearch() } returns search

        val flow = repository.getAllSearch()
        flow.test {
            val expected = expectMostRecentItem()
            Truth.assertThat(expected).containsExactlyElementsIn(
                searchDataDomainMapper.fromList(search)
            )
            coVerify { localDataSource.getAllSearch() }
        }
    }

}