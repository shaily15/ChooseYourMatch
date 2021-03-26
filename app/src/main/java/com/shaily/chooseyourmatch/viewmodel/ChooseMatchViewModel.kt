package com.shaily.chooseyourmatch.viewmodel

import androidx.lifecycle.*
import com.shaily.chooseyourmatch.api.MatchResultsApi
import com.shaily.chooseyourmatch.data.MatchDetailsResponse
import com.shaily.chooseyourmatch.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ChooseMatchViewModel @Inject constructor(api: MatchResultsApi) : ViewModel() {

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
    }
}