package com.example.second

import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.second.data.Datasource
import com.example.second.model.CardData
import com.example.second.ui.theme.SecondTheme
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecondTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    Column(){
        CookieClicker()
        CardList(
            cardList = Datasource().loadCards()
        )
    }
}

@Composable
fun CardList(cardList: List<CardData>, modifier:Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(cardList){
            card -> MyCard(cardData = card, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun MyCard(cardData: CardData, modifier: Modifier = Modifier){
    var expanded by remember {mutableStateOf(false)}
    val color by animateColorAsState(
        targetValue = if(expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )
    Card(modifier = modifier){
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)
        ){
            Image(
                painter = painterResource(id = cardData.imageResourceId),
                contentDescription = stringResource(id = cardData.stringResourceId),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            {
                Spacer(modifier = Modifier.weight(1f))
                MyItemButton(
                    expanded = expanded,
                    onClick = {
                        expanded = !expanded
                    })
            }
            if (expanded){
                MyText(str = LocalContext.current.getString(cardData.stringResourceId))
            }
        }
    }
}

@Composable
fun CookieClicker(
){
    var cookies by remember {mutableIntStateOf(0)}
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

@Composable
fun MyText(
    str: String,
    modifier: Modifier = Modifier
){
    Text(
        text = str,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun MyItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){

    IconButton(
        onClick = onClick,
        modifier = modifier
    ){
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = stringResource(id = R.string.expand),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MyApp()
}