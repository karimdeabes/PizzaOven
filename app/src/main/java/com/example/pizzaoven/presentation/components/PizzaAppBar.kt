package com.example.pizzaoven.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzaoven.R

@Composable
fun PizzaAppBar(modifier: Modifier = Modifier) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )
        }
        Text(
            text = "Pizza",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.weight(1f),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}