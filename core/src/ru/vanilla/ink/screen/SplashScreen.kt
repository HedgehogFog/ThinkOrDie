package ru.vanilla.ink.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ru.vanilla.ink.core.MainGame
import com.badlogic.gdx.graphics.Color
import ru.vanilla.ink.core.Settings
import ru.vanilla.ink.helper.input.InputHelper
import ru.vanilla.ink.model.splash.SplashLogo
import ru.vanilla.ink.assets.ContentManager
import java.util.*


class SplashScreen(val game: MainGame) : Screen {
    private var timer = 0.0f

    private var backgroundColor = Color(.0f, .0f, .0f, 1.0f)
    private val colorFade = Color(.2f, .2f, .2f, 1.0f)
    private val colorFadeOut = Color(.0f, .0f, .0f, 1.0f)
    private val colorNormalized = Color(1.0f, 1.0f, 1.0f, 1.0f)

    lateinit var sb: SpriteBatch

    private var phase: SplashScreen.Phase = Phase.INIT

    val rand = Random()

    lateinit var splashLogo: SplashLogo
    override fun hide() {
    }

    override fun show() {
        sb = game.sb
        splashLogo = SplashLogo(ContentManager.res.getTexture("badlogic")!!,
                (0f - 256f), (Settings.HEIGHT / 2).toFloat(), 256f, 256f)
        splashLogo.setSpeed(4f, 64f)

    }

    private fun update(delta: Float){
        when (phase){
            SplashScreen.Phase.INIT -> {
                timer -= Gdx.graphics.deltaTime
                if (timer < 0.0F) {

                    phase = SplashScreen.Phase.FADE_OUT
                    timer = 1.2F
                }
            }
            SplashScreen.Phase.FADE_OUT -> {
                timer -= Gdx.graphics.deltaTime
                backgroundColor.lerp(colorFade, delta * 2)

                if (timer < 0.0f) {
                    splashLogo.color = colorNormalized

                    phase = SplashScreen.Phase.BOUNCE
                    timer = 1.5f
                }
            }
            SplashScreen.Phase.BOUNCE -> {
                if (splashLogo.x < Settings.WIDTH / 2 - splashLogo.width / 2) {
                    splashLogo.update(delta)
                } else {
                    this.phase = SplashScreen.Phase.WAIT
                    this.timer = 1.5f
                }
            }

            SplashScreen.Phase.WAIT -> {
                timer -= Gdx.graphics.deltaTime
                if (timer < 0.0F) {
                    phase = SplashScreen.Phase.FADE
                }
            }
            SplashScreen.Phase.FADE -> {

                splashLogo.color.lerp(colorFadeOut, delta * 2)
                backgroundColor.lerp(colorFadeOut, delta * 2)


                if (splashLogo.color == colorFadeOut) {
                    dispose()
                    game.screen = MainMenu(game)
                }
            }
        }
    }

    override fun render(delta: Float) {

        update(delta)
        updateInput()

        sb.begin()
        sb.color = backgroundColor

        sb.draw(ContentManager.res.getTexture("bg_white"), 0f, 0f,
                Settings.WIDTH.toFloat(), Settings.HEIGHT.toFloat())
        splashLogo.render(sb)



        sb.end()
    }

    private fun updateInput() {
        InputHelper.update()
//        if (InputHelper.justClickedLeft) {
//            with(backgroundColor){
//                r = rand.nextFloat()
//                g = rand.nextFloat()
//                b = rand.nextFloat()
//                a = rand.nextFloat()
//            }
//        }

        InputHelper.updateLast()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
    }

    private enum class Phase {
        INIT,
        BOUNCE,
        FADE,
        WAIT,
        FADE_OUT
    }
}


