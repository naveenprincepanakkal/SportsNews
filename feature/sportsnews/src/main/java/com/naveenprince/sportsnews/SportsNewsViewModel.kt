package com.naveenprince.sportsnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naveenprince.common.utils.Resource
import com.naveenprince.domain.usecase.SportsResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    val newsState: StateFlow<SportsNewsState> = _newsState

    private var serviceJob: Job? = null

    init {
        serviceJob?.cancel()
        serviceJob = viewModelScope.launch {
            _newsState.value = _newsState.value.copy(
                isLoading = true,
                error = null
            )
            sportsResultsUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _newsState.value = _newsState.value.copy(
                            sportsResults = it.data,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        _newsState.value = _newsState.value.copy(
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