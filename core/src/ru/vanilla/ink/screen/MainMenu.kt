package ru.vanilla.ink.screen

import com.badlogic.gdx.Screen
import ru.vanilla.ink.core.MainGame

class MainMenu(val game: MainGame) : Screen {
    override fun hide() {

    }

    override fun show() {
        game.screen = GameScreen(game)
    }

    override fun render(delta: Float) {
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
