package ru.vanilla.ink.core

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ru.vanilla.ink.screen.SplashScreen
import ru.vanilla.ink.assets.ContentManager


class MainGame : Game() {

    lateinit var sb: SpriteBatch



    override fun create() {
        sb = SpriteBatch()
        ContentManager.loadResouce()
        setScreen(SplashScreen(this))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {

    }

}
