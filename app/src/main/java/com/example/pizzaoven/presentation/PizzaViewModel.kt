package com.example.pizzaoven.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PizzaViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(PizzaUiState())
    val uiState = _uiState.asStateFlow()


    fun onSizeBoxClicked(size: Size) {
        _uiState.update {
            it.copy(
                selectedSize = size
            )
        }
    }

    fun onToppingBoxClicked(topping: Topping) {
        _uiState.update {
            it.copy(
                selectedToppings = if (topping in it.selectedToppings) {
                    it.selectedToppings - topping
                } else {
                    it.selectedToppings + topping
                }
            )
        }
    }
    fun clearSelectedToppings() {
        _uiState.update { it.copy(selectedToppings = emptyList()) }
    }
}