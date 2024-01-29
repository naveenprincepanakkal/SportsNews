package com.naveenprince.domain.usecase


import com.naveenprince.common.utils.Resource
import com.naveenprince.common.utils.Utils.Companion.sortByDescendingWithDate
import com.naveenprince.data.repository.SportsNewsRepository
import com.naveenprince.domain.mapper.toSportsNews
import com.naveenprince.domain.model.SportsNews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * UseCase class to map formatted news and sort in chronological order by the most recent first.
 *
 * Created by Naveen.
 */
class SportsResultsUseCase @Inject constructor(private val sportsNewsRepository: SportsNewsRepository) {

    operator fun invoke(): Flow<Resource<List<SportsNews>>> = flow {
        sportsNewsRepository.getSportsNews().collect { it ->
            when (it) {
                is Resource.Success -> {
                    try {
                        it.data?.let { results ->
                            val newsResults = results.toSportsNews()
                            val latestNewsResult =
                                newsResults.sortByDescendingWithDate({ it.publicationDate })
                            emit(Resource.Success(data = latestNewsResult))
                        }
                    } catch (e: Exception) {
                        emit(Resource.Error(message = "Error occurred while generating news"))
                    }
                }

                else -> {
                    emit(Resource.Error(message = it.message ?: "An network error occurred."))
                }
            }
        }
    }

}