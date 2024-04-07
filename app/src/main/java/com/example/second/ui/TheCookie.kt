package com.example.second.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.second.R


@Composable
fun CookieClicker(
){
    var cookies by remember { mutableIntStateOf(0) }
    var myRandom by remember { mutableIntStateOf(0)}
    var specialNums = arrayOf<Int>(1)
    Column(modifier = Modifier.padding(10.dp)){
        Cookie(
            onClick = {
                cookies++
                if (!specialNums.contains(myRandom)){
                    myRandom = (0..10).random()
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        CookieCount(
            cookies = cookies,
        )
    }
    if(myRandom == 1){
        MathMinigame(
            onComplete = {
                cookies+=10
                myRandom = (0..10).random()
            },
            modifier = Modifier
                .padding(5.dp)
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

@Composable
fun MathMinigame(
    onComplete: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.math),
                contentDescription = "math",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(100.dp)
            )
            Text(stringResource(id = R.string.mathrulez))
            Text(stringResource(id = R.string.whatis))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview(){
    CookieClicker()
}