package ru.vanilla.ink.game.state

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.Viewport
import ru.vanilla.ink.core.MainGame
import ru.vanilla.ink.data.ScreenInform
import ru.vanilla.ink.game.GameStateManager
import ru.vanilla.ink.util.SystemState

abstract class GameState(gsm: GameStateManager){

    protected var gsm: GameStateManager = gsm


    protected lateinit var camViewport: Viewport
    protected lateinit var hudViewport: Viewport

    protected val game: ApplicationListener = gsm.game
    protected val sb: SpriteBatch = gsm.game.sb

    protected lateinit var cam: OrthographicCamera
    protected lateinit var hudCam: OrthographicCamera

    init {
        setupCamera(ScreenInform.WIDTH, ScreenInform.HEIGHT)
        setupViewport(cam, hudCam, ScreenInform.WIDTH, ScreenInform.HEIGHT)
        // always update viewport
        camViewport.update(Gdx.app.graphics.width, Gdx.app.graphics.height)
        hudViewport.update(Gdx.app.graphics.width, Gdx.app.graphics.height, true)
    }

    abstract fun handleInput()
    abstract fun update(dt: Float)
    abstract fun render()
    abstract fun dispose()
    abstract fun resizeUser(width: Int, height: Int)


    fun resize(width: Int, height: Int) {
        camViewport.update(width, height)
        hudViewport.update(width, height, true)

        resizeUser(width, height)
    }

    protected open fun setupCamera(viewportWidth: Float, viewportHeight: Float){
        cam = OrthographicCamera()
        cam.setToOrtho(false, viewportWidth, viewportHeight)

        hudCam = OrthographicCamera()
        hudCam.setToOrtho(false, viewportWidth, viewportHeight)
    }

    protected open fun setupViewport(cam: OrthographicCamera, hudCam: OrthographicCamera, viewportWidth: Float, viewportHeight: Float){
        camViewport = FillViewport(viewportWidth, viewportHeight, cam)
        hudViewport = FillViewport(viewportWidth, viewportHeight, hudCam)
    }

}