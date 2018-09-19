package ru.vanilla.ink.core

object Settings {
    const val TITLE = "ThinkOrDie"

    var WIDTH = 320
    var HEIGHT = 240

    const val SCALE = 2

    var isDebug = true

    fun init(width: Int, height: Int){
        WIDTH = width
        HEIGHT = height
    }
}