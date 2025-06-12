@file:Suppress("DEPRECATION")

package com.example.pizzaoven.di

import com.example.pizzaoven.presentation.viewmodel.PizzaViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::PizzaViewModel)
}