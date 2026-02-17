package com.example.mobiletraining

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform