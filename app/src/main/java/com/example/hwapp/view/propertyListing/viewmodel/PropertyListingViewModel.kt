package com.example.hwapp.view.propertyListing.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hwapp.common.Resource
import com.example.hwapp.data.database.controller.PropertyController
import com.example.hwapp.data.database.entity.ImageEntity
import com.example.hwapp.data.database.entity.PropertyEntity
import com.example.hwapp.domain.api.repository.PropertyApiRepo
import com.example.hwapp.domain.model.Property
import com.example.hwapp.view.propertyListing.effect.PropertyListingEffect
import com.example.hwapp.view.propertyListing.event.PropertyListingEvent
import com.example.hwapp.view.propertyListing.state.PropertyListingUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PropertyListingViewModel @Inject constructor(
    private val propertyApiRepository: PropertyApiRepo,
    private val propertyController: PropertyController
): ViewModel() {

    private val _effect = Channel<PropertyListingEffect>(Channel.UNLIMITED)
    val effect: Flow<PropertyListingEffect> = _effect.receiveAsFlow()

    private val _uiState = MutableStateFlow(PropertyListingUIState())
    val uiState: StateFlow<PropertyListingUIState> = this._uiState.asStateFlow()

    fun onEvent(event: PropertyListingEvent) {
        when (event) {
            is PropertyListingEvent.OnRefresh -> refreshProperties()
            is PropertyListingEvent.OnClickProperty ->
                navigateTo(PropertyListingEffect.Navigation.PropertyDetailsScreen(event.propertyId))
        }
    }

    private fun navigateTo(destination: PropertyListingEffect.Navigation) = viewModelScope.launch {
        _effect.send(destination)
    }

    private fun refreshProperties() {
        _uiState.update { it.copy(isRefreshing = true) }
        fetchProperties()
    }

    fun initialiseProperties() = viewModelScope.launch {
        Log.d(TAG, "Initialise Properties")
        val properties = fetchPropertiesFromDB()
        if (properties.isEmpty()) {
            fetchProperties()
            return@launch
        }
        _uiState.update {
            it.copy(
                properties = properties,
                loading = false
            )
        }
    }

    private fun fetchProperties() = viewModelScope.launch {
        runBlocking {
            Log.d(TAG, "Calling PropertyAPI")

            propertyApiRepository.getProperties(1, 20)
                .observeOn(Schedulers.io())
                .subscribe({ result ->
                    when (result) {
                        is Resource.Success -> {
                            Log.d(TAG, "Success")
                            val properties = result.data?.first
                            val images = result.data?.second
                            saveProperties(properties)
                            savePropertyImages(images)
                            updateUI()
                        }
                        is Resource.Error -> {
                            Log.d(TAG, "Error")
                            _uiState.update { it.copy(
                                error = result.message ?: "An unexpected error occurred",
                                loading = false
                            ) }
                        }
                        is Resource.Loading -> {
                            _uiState.update { it.copy(loading = true) }
                        }
                    }
                }, { error ->
                    Log.e(TAG, "Error: ${error.message}")
                })
        }
    }

    private fun updateUI() = viewModelScope.launch {
        Log.d(TAG, "Updating UI")
        Log.d(TAG, "Fetching Properties")
        val properties = fetchPropertiesFromDB()
        Log.d(TAG, "Properties = $properties")
        delay(1500)
        _uiState.update {
            it.copy(
                properties = properties,
                loading = false,
                isRefreshing = false
            )
        }
    }

    private suspend fun fetchPropertiesFromDB(): List<Property> =
        propertyController.fetchAllProperties()


    private fun savePropertyImages(images: List<ImageEntity>?) = viewModelScope.launch {
        runBlocking {
            images?.run { propertyController.upsertImages(this) }
        }
    }

    private fun saveProperties(properties: List<PropertyEntity>?) = viewModelScope.launch {
        runBlocking {
            properties?.run { propertyController.upsertProperties(this) }
        }
    }
}