package com.example.second

import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
    CardList(
        cardList = Datasource().loadCards()
    )
}

@Composable
fun CardList(cardList: List<CardData>, modifier:Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(cardList){
            card -> MyCard(cardData = card, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun MyCard(cardData: CardData, modifier: Modifier = Modifier){
    Card(modifier = modifier){
        Column{
            Image(
                painter = painterResource(id = cardData.imageResourceId),
                contentDescription = stringResource(id = cardData.stringResourceId),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
            )
            Text(
                text = LocalContext.current.getString(cardData.stringResourceId),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MyApp()
}