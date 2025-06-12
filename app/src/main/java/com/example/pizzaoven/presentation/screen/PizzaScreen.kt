package com.example.pizzaoven.presentation.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pizzaoven.R
import com.example.pizzaoven.presentation.viewmodel.PizzaViewModel
import com.example.pizzaoven.presentation.components.AddToCartBox
import com.example.pizzaoven.presentation.components.ToppingItems
import com.example.pizzaoven.presentation.components.PizzaPlateWithBread
import com.example.pizzaoven.presentation.components.SizeItems
import com.example.pizzaoven.presentation.components.PizzaAppBar
import com.example.pizzaoven.presentation.viewmodel.state.Size
import org.koin.androidx.compose.koinViewModel

@Composable
fun PizzaScreen(
    modifier: Modifier = Modifier
) {
    val pizzaViewModel: PizzaViewModel = koinViewModel()
    val uiState = pizzaViewModel.uiState.collectAsStateWithLifecycle().value

    val animatedBreadRadius = animateDpAsState(
        targetValue = when (uiState.selectedSize) {
            Size.SMALL -> 240.dp
            Size.MEDIUM -> 250.dp
            Size.LARGE -> 260.dp
        },
        animationSpec = tween(200),

        )

    val animatedCircularBox = animateDpAsState(
        targetValue = when (uiState.selectedSize) {
            Size.SMALL -> -(68).dp
            Size.MEDIUM -> 0.dp
            Size.LARGE -> 68.dp
        },
        animationSpec = tween(200),
        label = "animatedCircularBox"
    )

    val animatedToppingSize = animateFloatAsState(
        targetValue = when (uiState.selectedSize) {
            Size.SMALL -> 0.5f
            Size.MEDIUM -> 0.52f
            Size.LARGE -> 0.54f
        },
        animationSpec = tween(200),

        )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        PizzaAppBar()

        PizzaPlateWithBread(
            uiState = uiState,
            breadRadius = animatedBreadRadius.value,
            toppingSize = animatedToppingSize.value,
            clearSelectedIngredients = pizzaViewModel::clearSelectedToppings
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "$17",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 30.dp),
                textAlign = TextAlign.Center
            )

            SizeItems(
                onClick = pizzaViewModel::onSizeBoxClicked,
                sizeCircularBox = animatedCircularBox.value
            )


        }
        Text(
            text = stringResource(R.string.customize_your_pizza),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black.copy(alpha = 0.3f),
            modifier = Modifier.padding(bottom = 24.dp, start = 16.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))

        ToppingItems(onClick = pizzaViewModel::onToppingBoxClicked, uiState = uiState)

        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            AddToCartBox()
        }
        Spacer(modifier = Modifier.height(16.dp))

    }

}

@Preview
@Composable
private fun PizzaScreenPreview() {
    PizzaScreen()
}