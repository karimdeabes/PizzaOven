package com.example.pizzaoven.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pizzaoven.R
import com.example.pizzaoven.presentation.PizzaUiState
import com.example.pizzaoven.presentation.Topping
import kotlin.random.Random

@Composable
fun PizzaPlateWithBread(
    breadRadius: Dp,
    toppingSize: Float,
    uiState: PizzaUiState,
    clearSelectedIngredients: () -> Unit,
    modifier: Modifier = Modifier
) {
    val breadList = listOf(
        R.drawable.bread_1,
        R.drawable.bread_2,
        R.drawable.bread_3,
        R.drawable.bread_4,
        R.drawable.bread_5
    )

    val pagerState = rememberPagerState(pageCount = { breadList.size })

    LaunchedEffect(pagerState.currentPage) {
        clearSelectedIngredients()
    }
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.plate),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 60.dp)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = breadList[page]),
                    contentDescription = "Image $page",
                    modifier = Modifier
                        .size(breadRadius)
                        .clip(CircleShape)
                )


                Topping.entries.forEach { topping ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        ToppingAnimation(
                            uiState = uiState,
                            topping = topping,
                            toppingSize = toppingSize
                        )

                    }
                }
            }
        }

    }


}

@Composable
fun ToppingAnimation(
    uiState: PizzaUiState,
    topping: Topping,
    toppingSize: Float,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        uiState.selectedToppings.contains(topping),
        enter = scaleIn(initialScale = 80f, animationSpec = tween(500)),
        exit = fadeOut(tween(100)),
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            repeat(3) {
                topping.toppingImages.forEach { image ->
                    val randomX =
                        remember { (Random.nextFloat() - 0.5f) * 300 }
                    val randomY =
                        remember { (Random.nextFloat() - 0.5f) * 300 }
                    val randomAnimatedX =
                        animateFloatAsState(
                            randomX * toppingSize * 0.9f,
                            tween(100)
                        )
                    val randomAnimatedY =
                        animateFloatAsState(
                            randomY * toppingSize * 0.9f,
                            tween(100)
                        )
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = Modifier
                            .size((44 * toppingSize).dp)
                            .offset(
                                x = randomAnimatedX.value.dp,
                                y = randomAnimatedY.value.dp,
                            ),
                        contentScale = ContentScale.Fit,
                    )

                }

            }
        }

    }
}
