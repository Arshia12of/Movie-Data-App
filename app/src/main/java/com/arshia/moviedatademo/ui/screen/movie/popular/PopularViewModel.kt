package com.arshia.moviedatademo.ui.screens.movie.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arshia.moviedatademo.data.base.BaseGenre
import com.arshia.moviedatademo.data.repository.remote.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(private val repository: DataRepository) :
    ViewModel() {
    var selectedGenre = MutableStateFlow(BaseGenre.BASE_DEFAULT_GENRE)

    @OptIn(ExperimentalCoroutinesApi::class)
    var popularMovieList = selectedGenre.flatMapLatest { genre ->
        repository.popularMoviePagingSource(genre.id)
    }.cachedIn(viewModelScope)
}