package com.mm.newsreader.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mm.newsreader.data.repository.ApiRepository
import com.mm.newsreader.data.repository.utils.Languages
import com.mm.newsreader.data.repository.utils.NetworkResult
import com.mm.newsreader.domain.di.AppDispatchers
import com.mm.newsreader.domain.di.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()

    val errorChannel: Channel<String> = Channel()

    init {
        getNewsArticles()
    }

    fun getNewsArticles() {
        viewModelScope.launch(ioDispatcher) {
            _uiState.update { state ->
                state.copy(
                    isLoading = true
                )
            }
            when (val result = apiRepository.getAllNews(Languages.EN)) {
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
                            newsArticles = result.data.data,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}