package com.mm.noughtyfoxtechtask.presentation.detailsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mm.noughtyfoxtechtask.data.repository.ApiRepository
import com.mm.noughtyfoxtechtask.data.repository.utils.NetworkResult
import com.mm.noughtyfoxtechtask.domain.di.AppDispatchers
import com.mm.noughtyfoxtechtask.domain.di.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsScreenState())
    val uiState = _uiState.asStateFlow()

    val errorChannel: Channel<String> = Channel()

    fun getArticle(articleUUID: String) {
        viewModelScope.launch(ioDispatcher) {
            _uiState.update { state ->
                state.copy(
                    isLoading = true
                )
            }
            when (val result = apiRepository.getArticle(articleUUID)) {
                is NetworkResult.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false
                        )
                    }
                    errorChannel.send(result.message)
                }

                is NetworkResult.Exception -> {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false
                        )
                    }
                    result.e.message?.let { errorChannel.send(it) }
                }

                is NetworkResult.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            newsArticle = result.data,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}