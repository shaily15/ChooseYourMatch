package com.shaily.chooseyourmatch.viewmodel

import androidx.lifecycle.*
import com.shaily.chooseyourmatch.models.Results
import com.shaily.chooseyourmatch.repository.MatchResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseMatchViewModel @Inject constructor(repository: MatchResultRepository) : ViewModel() {

    val matchResultsLiveData = repository.getAllMatchResults().asLiveData()
    private val matchRepository = repository

    fun saveMatch(matchResult: Results) = viewModelScope.launch {
        matchRepository.insertMatch(matchResult)
    }

    fun deleteMatch(matchResult: Results) = viewModelScope.launch {
        matchRepository.deleteMatch(matchResult)
    }
}