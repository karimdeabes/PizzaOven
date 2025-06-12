package com.example.pizzaoven.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzaoven.presentation.viewmodel.state.Size

@Composable
fun SizeItems(
    sizeCircularBox: Dp,
    onClick: (Size) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 36.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .offset(x = sizeCircularBox)
                .size(60.dp)
                .shadow(elevation = 14.dp, shape = CircleShape)
                .clip(CircleShape)
                .background(Color.White)


        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            SizeItem(
                onClick = { onClick(Size.SMALL) },
                size = "S"
            )
            Spacer(modifier = Modifier.width(16.dp))
            SizeItem(
                onClick = { onClick(Size.MEDIUM) },
                size = "M"
            )
            Spacer(modifier = Modifier.width(16.dp))
            SizeItem(
                onClick = { onClick(Size.LARGE) },
                size = "L"
            )
        }
    }
}

@Composable
fun SizeItem(
    onClick: () -> Unit,
    size: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(52.dp)
            .clickable { onClick() }
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = size,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black

        )
    }
}