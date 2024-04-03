package com.example.second.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.second.R


@Composable
fun CookieClicker(
){
    var cookies by remember { mutableIntStateOf(0) }
    Column(modifier = Modifier.padding(10.dp)){
        Cookie(
            onClick = {
                cookies++
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        CookieCount(
            cookies = cookies,
        )
    }
}

@Composable
fun CookieCount(
    cookies: Int,
){
    Row(){
        Text(text = stringResource(id = R.string.cookies), fontSize = 50.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = cookies.toString(), fontWeight = FontWeight.Bold, fontSize = 50.sp)
    }
}

@Composable
fun Cookie(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(onClick = onClick, modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.cookie),
            contentDescription = "cookie",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(200.dp)
        )
    }
}