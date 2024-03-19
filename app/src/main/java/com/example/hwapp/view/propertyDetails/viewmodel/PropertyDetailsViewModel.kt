package com.example.hwapp.view.propertyDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hwapp.data.database.controller.PropertyController
import com.example.hwapp.view.propertyDetails.effect.PropertyDetailsEffect
import com.example.hwapp.view.propertyDetails.event.PropertyDetailsEvent
import com.example.hwapp.view.propertyDetails.state.PropertyDetailsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailsViewModel @Inject constructor(
    private val propertyController: PropertyController
) : ViewModel() {

    private val _effect = Channel<PropertyDetailsEffect>(Channel.UNLIMITED)
    val effect: Flow<PropertyDetailsEffect> = _effect.receiveAsFlow()

    private val _uiState = MutableStateFlow(PropertyDetailsUIState())
    val uiState: StateFlow<PropertyDetailsUIState> = this._uiState.asStateFlow()

    fun onEvent(event: PropertyDetailsEvent) {
        when (event) {
            is PropertyDetailsEvent.OnBack -> navigateBack()
            is PropertyDetailsEvent.OnClickCurrency -> fetchExchangeRate(event.currencyCode)
        }
    }

    private fun fetchExchangeRate(currencyCode: String) {
        /*
        * This API requires credit card details therefore I'll not be using it,
        * If I was to do it, when the user selects a currency, then I would hit the API
        * and fetch the appropriate exchange rate and apply it to the total
        * */
    }

    private fun navigateBack() = viewModelScope.launch {
        _effect.send(PropertyDetailsEffect.Navigation.Back)
    }

    fun fetchProperty(propertyId: Long?) = viewModelScope.launch {
        if (propertyId == null) navigateBack()

        _uiState.update { it.copy(isLoading = true) }
        val property = propertyController.fetchPropertyBy(propertyId!!)
        _uiState.update { it.copy(property = property, isLoading = false) }
    }

}