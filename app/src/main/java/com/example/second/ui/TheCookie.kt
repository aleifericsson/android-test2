package com.example.second.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.second.R
import androidx.compose.runtime.collectAsState


@Composable
fun CookieClicker(
    gameViewModel: GameViewModel = viewModel()
){

    val gameUIState by gameViewModel.uiState.collectAsState()
    var cookies by remember { mutableIntStateOf(0) }
    var myRandom by remember { mutableIntStateOf(0)}
    var specialNums = arrayOf<Int>(1)
    Box(){
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
                cookies = gameUIState.cookies,
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
    var userInput by remember { mutableStateOf( "0")}
    val gradientColors = listOf(Color.hsl(246F, .36F, .53F), Color.hsl(274F, 0.61F, 0.38F), Color.hsl(293F, 0.45F, 0.60F) /*...*/)
    val brush = remember {
        Brush.linearGradient(
            colors = gradientColors
        )
    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.padding(60.dp))
    {
        Column(
            modifier = modifier
                .background(color = Color.Gray)
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.math),
                contentDescription = "math",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(100.dp)
            )
            Text(stringResource(R.string.math_rulez))
            Text(stringResource(R.string.math_rulez2))
            Text(stringResource(R.string.what_is))
            TextField(
                value = userInput,
                onValueChange = { userInput = it},
                modifier = modifier,
                singleLine = true,
                label = {Text(stringResource(id = R.string.enter_answer))},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(fontSize = 20.sp, brush=brush)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview(){
    CookieClicker()
}