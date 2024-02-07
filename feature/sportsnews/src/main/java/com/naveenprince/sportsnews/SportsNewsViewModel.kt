package com.naveenprince.sportsnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naveenprince.common.utils.Resource
import com.naveenprince.domain.usecase.SportsResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model class for sports view
 * Created by Naveen.
 */
@HiltViewModel
class SportsNewsViewModel @Inject constructor(
    private val sportsResultsUseCase: SportsResultsUseCase,
) : ViewModel() {

    private var _newsState = MutableStateFlow(SportsNewsState())
    val newsState: StateFlow<SportsNewsState> = _newsState.asStateFlow()

    private var state: SportsNewsState
        get() = _newsState.value
        set(newState) {
            _newsState.update { newState }
        }

    private var serviceJob: Job? = null

    init {
        getLatestNews()
    }

    private fun getLatestNews() {
        serviceJob?.cancel()
        serviceJob = viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            sportsResultsUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        state = state.copy(
                            sportsResults = it.data,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            sportsResults = null,
                            isLoading = false,
                            error = it.message
                        )
                    }

                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        serviceJob?.cancel()
    }
}