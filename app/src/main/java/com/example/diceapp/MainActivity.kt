package com.example.diceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceapp.ui.theme.DiceAppTheme
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceAppTheme {
                    DiceRollerApp() 
                }
            }
        }
    }


@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Preview
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result: Int by remember {mutableStateOf(1)}
    var count: Int by remember {mutableStateOf(0)}
    var total: Int by remember {mutableStateOf(0)}
    var average: Double by remember { mutableStateOf(0.0)}
    var stringValue: String by remember {mutableStateOf("")}

    val imageResource = when(result){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
            .padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Text(text = "Counter: $count", fontSize = 18.sp)
            Text(text = "Total: $total", fontSize = 18.sp)
            Text(text = "Average: $average", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(onClick = {
            count = 0
            total = 0
            average= 0.0},
            modifier = Modifier
                .width(90.dp)
                .height(40.dp)
                .alpha(0.8F)
        ) {
            Text(text = "Reset")
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringValue, fontSize = 24.sp)
        //add Dice Image
        Image(
            painter = painterResource(imageResource), contentDescription = "1"
        )
        Text(stringResource(R.string.StartText))

        //Add Space Between Button
        Spacer(modifier = Modifier.height(14.dp))

        //Add Roll Button
        Button(onClick = {
            result = (1..6).random()
            count++
            total += result

            val df = DecimalFormat("#.##")
            average = df.format((total.toDouble()/count.toDouble())).toDouble()

            stringValue = "You rolled a ${result}!" },
            modifier = Modifier
                .width(90.dp)
                .height(40.dp)
        ) {
            Text(text = stringResource(R.string.roll))
        }
    }
}

