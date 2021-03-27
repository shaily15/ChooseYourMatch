package com.shaily.chooseyourmatch.viewmodel

import androidx.lifecycle.*
import com.shaily.chooseyourmatch.repository.MatchResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChooseMatchViewModel @Inject constructor(repository: MatchResultRepository) : ViewModel() {

    val matchResultsLiveData = repository.getAllMatchResults().asLiveData()

    /* Network Call before Room
    val matchResultsLiveData: MutableLiveData<Resource<MatchDetailsResponse>> = MutableLiveData()

    init {
        viewModelScope.launch {
            matchResultsLiveData.postValue(Resource.Loading())
            val matchResultsResponse = api.getMatchDetails()
            delay(2000)
            matchResultsLiveData.postValue(handleNetworkResponse(matchResultsResponse))
        }
    }

    private fun handleNetworkResponse(response: Response<MatchDetailsResponse>): Resource<MatchDetailsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    } */
}