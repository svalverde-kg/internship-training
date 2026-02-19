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
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import org.jetbrains.compose.resources.painterResource

import mobiletraining.composeapp.generated.resources.Res
import mobiletraining.composeapp.generated.resources.compose_multiplatform
import mobiletraining.composeapp.generated.resources.loading_icon
import mobiletraining.composeapp.generated.resources.rating1
import mobiletraining.composeapp.generated.resources.rating2

@Composable
fun App(
    viewModel: DashboardViewModel = DashboardViewModel
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        OpenPopup(viewState.booked ) {
            viewModel.bookDestination(false)
        }
        DisplayImageSection(viewState.isImageLoading)
        DisplayData(viewState.isDataLoading)
        DisplaySpecs(viewState.isSpecsLoading)
        DisplayText(viewState.isTextLoading)
        DisplayFooter(viewState.booked, viewState.isFooterLoading) {
            viewModel.bookDestination()
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AppPreview() {
    App()
}
@Composable
fun DisplayImageSection(
    isImageLoading: Boolean
) {
    val imagePainter = if (isImageLoading) {
        painterResource(Res.drawable.loading_icon) // Replace with your filled drawable
    } else {
        painterResource(Res.drawable.compose_multiplatform) // Replace with your border drawable
    }
    // Back button, image y pill de 1/8
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        // 1. Image
        Image(
            painter = imagePainter,
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

@Composable
fun DisplayData(
    isDataLoading: Boolean
) {
    var titleText: String
    var addressText: String
    var ratingPainter: Painter

    if(isDataLoading){
        titleText = "Loading.."
        addressText = "Fetching address.."
        ratingPainter = painterResource(Res.drawable.rating1)
    } else{
        titleText = "Mountain retreatfjadsfksakldfaslkfjksladjflksajdfklasjdfklasjdfklasdjfkladsjfjksadbjkbfaskjfbsankdbfbbabfbasdkjfbsakdfbasmnfbdn,asmnbdfn,masnbdfn,masnbdfn,masndbf,masdnbf,madsbnf,masnbf"
        addressText = "Snowy Peaks, NSW, Australia"
        ratingPainter = painterResource(Res.drawable.rating2)
    }

    Column(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        ) {
        Text(
            text = titleText,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp),
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = addressText,
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
                    painter = ratingPainter,
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

@Composable
fun DisplaySpecs(
    isSpecsLoading: Boolean
) {
    val imagePainter = if (isSpecsLoading) {
        painterResource(Res.drawable.loading_icon)
    } else {
        painterResource(Res.drawable.compose_multiplatform)
    }
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
                    painter = imagePainter,
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

@Composable
fun DisplayText(
    isTextLoading: Boolean
) {
    val scrollState = rememberScrollState()

    val longText = if(isTextLoading) {
        "Loading description..."
    } else {
        "Lorem ipsum dolor sit amet, consectetur " +
        "adipiscing elit, sed do eiusmod tempor incididunt" +
        " ut labore et dolore magna aliqua. Ut enim ad " +
        "minim veniam, quis nostrud exercitation ullamco" +
        " laboris nisi ut aliquip ex ea commodo consequat." +
        " Duis aute irure dolor in reprehenderit in voluptate velit" +
        " esse cillum dolore eu fugiat nulla pariatur. Excepteur sint" +
        " occaecat cupidatat non proident, sunt in culpa qui officia" +
        " deserunt mollit anim id est laborum.\n" +
        "Lorem ipsum dolor sit amet, consectetur" +
        " adipiscing elit, sed do eiusmod tempor incididunt" +
        " ut labore et dolore magna aliqua. Ut enim ad " +
        "minim veniam, quis nostrud exercitation ullamco" +
        " laboris nisi ut aliquip ex ea commodo consequat." +
        " Duis aute irure dolor in reprehenderit in voluptate velit" +
        " esse cillum dolore eu fugiat nulla pariatur. Excepteur sint" +
        " occaecat cupidatat non proident, sunt in culpa qui officia" +
        " deserunt mollit anim id est laborum."
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(16.dp)

    ) {
        Text(
            text = longText,
            modifier = Modifier
                .padding(8.dp),
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
fun DisplayFooter(
    booked: Boolean, isFooterLoading: Boolean, onBookedClick: () -> Unit
) {
    val buttonColor = if(isFooterLoading){
        Color.LightGray
    } else {
        Color.Magenta
    }
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor, // Use the conditional color
                contentColor = Color.White // Text/icon color
            ),
            modifier = Modifier
                .padding(end = 80.dp),
            onClick = onBookedClick
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
                    .padding(start = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif
            )
            Text(
                text = "/ night",
                modifier = Modifier
                    .padding( start = 8.dp ),
                color = Color.Gray,
                fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}

@Composable
fun OpenPopup(showPopup: Boolean, onDismiss: () -> Unit ) {
    if(showPopup) {
        Popup(
            onDismissRequest = onDismiss,
            properties = PopupProperties(focusable = true)
        ) {
            Box(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                Text(
                    text = "Booking completed successfully!",
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
    }
}