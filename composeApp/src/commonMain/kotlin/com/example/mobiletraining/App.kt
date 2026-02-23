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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.mobiletraining.ui.Display
import com.example.mobiletraining.ui.DisplayBooking
import com.example.mobiletraining.ui.DisplayData
import com.example.mobiletraining.ui.DisplayFooter
import com.example.mobiletraining.ui.DisplayImageSection
import com.example.mobiletraining.ui.DisplaySpecs
import com.example.mobiletraining.ui.DisplayText
import com.example.mobiletraining.ui.OpenPopup
import com.example.mobiletraining.viewmodels.DashboardViewModel

import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
sealed class Screen : NavKey {
    @Serializable
    data object Dashboard : Screen()
    @Serializable
    data object Booking : Screen()
}

@Composable
fun App(

) {

    val config = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(Screen.Dashboard::class, Screen.Dashboard.serializer())
                subclass(Screen.Booking::class, Screen.Booking.serializer())
            }
        }
    }

    val backStack = rememberNavBackStack(config, Screen.Dashboard)

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry <Screen.Dashboard> {
                Display(navBackStack = backStack)
            }
            entry <Screen.Booking> {
                DisplayBooking(navBackStack = backStack)
            }
        }
    )
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AppPreview() {
    App()
}
