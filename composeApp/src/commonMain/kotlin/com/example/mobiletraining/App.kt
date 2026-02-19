package com.example.mobiletraining

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.sp

import org.jetbrains.compose.resources.painterResource

import mobiletraining.composeapp.generated.resources.Res
import mobiletraining.composeapp.generated.resources.compose_multiplatform

@Preview
@Composable
fun App() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        DisplayImageSection()
        DisplayData()
        DisplaySpecs()
        DisplayText()
        DisplayFooter()
    }
}

@Preview
@Composable
fun DisplayImageSection() {
    // Back button, image y pill de 1/8
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        // 1. Image
        Image(
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )

        // 2. Floating Back button
        FloatingActionButton(
            onClick = {  },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(56.dp),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null
            )
        }

        // 3. Floating pill
        Text(
            text = "1 / 8",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Black.copy(alpha = 0.6f))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Preview
@Composable
fun DisplayData() {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
        ) {
        Text(
            text = "Mountain retreat",
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp),
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "Snowy Peaks, NSW, Australia",
            modifier = Modifier
                .padding( start = 8.dp, top = 4.dp),
            color = Color.Gray,
            fontSize = 12.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
            ) {
            repeat(5) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(2.dp)
                )
            }
            Text(
                text = "5.0",
                modifier = Modifier
                    .padding(8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                buildAnnotatedString {
                    val link =
                        LinkAnnotation.Url(
                            "https://www.konrad.com/careers/san-jose",
                            TextLinkStyles(SpanStyle(color = Color.Blue))
                        ) { }
                    withLink(link) { append("(23 reviews)") }
                }
            )
        }
    }
}

@Preview
@Composable
fun DisplaySpecs() {
    val list = listOf("House", "2 beds", "1 bath", "1 garage")
    Row (
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        list.forEach {
            Column (
                modifier = Modifier
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(2.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = it,
                    modifier = Modifier,
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun DisplayText() {

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(scrollState)

    ) {
        val longText = "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit, sed do eiusmod tempor incididunt" +
                " ut labore et dolore magna aliqua. Ut enim ad " +
                "minim veniam, quis nostrud exercitation ullamco" +
                " laboris nisi ut aliquip ex ea commodo consequat." +
                " Duis aute irure dolor in reprehenderit in voluptate velit" +
                " esse cillum dolore eu fugiat nulla pariatur. Excepteur sint" +
                " occaecat cupidatat non proident, sunt in culpa qui officia" +
                " deserunt mollit anim id est laborum."
        Text(
            text = longText,
            modifier = Modifier
                .padding(8.dp),
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Preview
@Composable
fun DisplayFooter() {
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier
                .padding(end = 100.dp),
            onClick = {  }
        ) {
            Text(
                text = "Book now",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(2.dp)),
                fontFamily = FontFamily.SansSerif,
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$299.00",
                modifier = Modifier
                    .padding(8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif
            )
            Text(
                text = "/ night",
                modifier = Modifier
                    .padding( start = 8.dp ),
                color = Color.Gray,
                fontSize = 8.sp,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}