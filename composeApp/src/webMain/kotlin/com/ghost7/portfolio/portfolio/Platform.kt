package com.ghost7.portfolio.portfolio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform