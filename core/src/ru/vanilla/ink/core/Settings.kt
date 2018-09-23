package ru.vanilla.ink.core

object Settings {
    const val TITLE = "ThinkOrDie"

    var WIDTH = 1920
    var HEIGHT = 1080

    const val SCALE = 2

    var isDebug = true

    fun init(width: Int, height: Int){
        WIDTH = width
        HEIGHT = height
    }
}