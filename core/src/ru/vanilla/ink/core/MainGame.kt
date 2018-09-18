package ru.vanilla.ink.core

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.Viewport
import ru.vanilla.ink.game.GameStateManager
import ru.vanilla.ink.util.SystemState
import ru.vanilla.ink.core.GameMode
import ru.vanilla.ink.screen.SplashScreen
import ru.vanilla.ink.util.ResourceManager


class MainGame : Game() {

    lateinit var sb: SpriteBatch



    override fun create() {
        sb = SpriteBatch()
        ResourceManager.loadResouce()
        setScreen(SplashScreen(this))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {

    }

}
