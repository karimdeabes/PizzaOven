package com.example.pizzaoven.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pizzaoven.R
import com.example.pizzaoven.presentation.Topping
import com.example.pizzaoven.presentation.PizzaUiState

@Composable
fun ToppingItems(
    onClick: (Topping) -> Unit,
    uiState: PizzaUiState,
    modifier: Modifier = Modifier
) {
    val toppings = listOf(
        Topping.BASEL to R.drawable.basil_3,
        Topping.ONION to R.drawable.onion_3,
        Topping.BROCCOLI to R.drawable.broccoli_3,
        Topping.MUSHROOM to R.drawable.mushroom_9,
        Topping.SAUSAGE to R.drawable.sausage_3
    )

    LazyRow(
        modifier = modifier.padding(bottom = 48.dp),
        horizontalArrangement = Arrangement.spacedBy(40.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(toppings) { (ingredient, iconRes) ->
            ToppingItem(
                isSelected = ingredient in uiState.selectedToppings,
                toppingId = iconRes,
                onClick = { onClick(ingredient) }
            )
        }
    }

}

@Composable
fun ToppingItem(
    isSelected: Boolean,
    toppingId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(75.dp)
            .background(if (isSelected) Color(0xFFDFF4E6) else Color.Transparent )
            .clickable { onClick() }
            .padding(horizontal = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(toppingId),
            contentDescription = null,
            modifier = Modifier.size(45.dp)

        )

    }
}