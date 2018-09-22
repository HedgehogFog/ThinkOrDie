package ru.vanilla.ink.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ru.vanilla.ink.core.MainGame

class GameScreen(val game: MainGame) : Screen {

    lateinit var sb: SpriteBatch

    override fun hide() {

    }


    override fun show() {
        sb = game.sb
    }

    override fun render(delta: Float) {
        println("GAME")
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
    }

}