package ru.vanilla.ink.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ru.vanilla.ink.assets.ContentManager
import ru.vanilla.ink.core.MainGame
import ru.vanilla.ink.core.Settings
import ru.vanilla.ink.helper.input.InputHelper
import ru.vanilla.ink.model.game.Bird

class GameScreen(val game: MainGame) : Screen {

    lateinit var sb: SpriteBatch

    private var backgroundColor = Color(.0f, .0f, .0f, 1.0f)
    private val colorNormal = Color(.2f, .2f, .2f, 1.0f)
    private val colorFade = Color(.2f, .2f, .2f, 1.0f)

    lateinit var player: Bird
    private var phase: GameScreen.Phase = GameScreen.Phase.INIT

    var isGameStart = false

    enum class Phase {
        INIT, FADE_OUT, WAIT_CLICK, GAME, FADE
    }

    override fun hide() {

    }


    override fun show() {
        sb = game.sb
        val birdWidth = 256f
        val birdHeight = 256f

        player = Bird(ContentManager.res.getTexture("badlogic")!!,
                (Settings.WIDTH / 2) - (birdWidth / 2),
                (Settings.HEIGHT / 2) - (birdHeight / 2),
                birdWidth,
                birdHeight)
    }

    override fun render(delta: Float) {
        updateInput()
        update(delta)

        sb.begin()
        sb.color = backgroundColor

        sb.draw(ContentManager.res.getTexture("bg_white"), 0f, 0f,
                Settings.WIDTH.toFloat(), Settings.HEIGHT.toFloat())
        player.render(sb)
        sb.end()
    }

    var timer = 0.0f
    private fun update(delta: Float) {
        when (phase){
            Phase.INIT -> {
                timer = 1.2f
                phase = Phase.FADE_OUT
            }
            Phase.FADE_OUT -> {
                timer -= Gdx.graphics.deltaTime
                backgroundColor.lerp(colorFade, delta * 2)

                if (timer < 0.0f) {
                    phase = GameScreen.Phase.GAME
                }
            }
            Phase.WAIT_CLICK -> {
                InputHelper.update()
                //TODO Input and start
            }
            Phase.GAME -> {
                player.update(delta)
            }
        }
    }

    private fun updateInput() {
        if (isGameStart) {
            InputHelper.update()
            player.inputUpdate()
            InputHelper.updateLast()
        }
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