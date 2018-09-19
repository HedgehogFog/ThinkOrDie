package ru.vanilla.ink.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ru.vanilla.ink.core.MainGame
import com.badlogic.gdx.graphics.Color
import ru.vanilla.ink.assets.ContentManager
import com.badlogic.gdx.math.Interpolation
import ru.vanilla.ink.data.ScreenInform
import ru.vanilla.ink.helper.input.InputHelper
import ru.vanilla.ink.screen.splash.SplashLogo
import java.util.*


class SplashScreen(val game: MainGame) : Screen {
    private var timer = 0.0f

    private var color = Color(.0f, .0f, .0f, 1.0f)
    private val colorFade = Color(.2f, .2f, .2f, 1.0f)


    lateinit var sb: SpriteBatch

    private var phase: SplashScreen.Phase = Phase.INIT

    val rand = Random()

    lateinit var splashLogo: SplashLogo
    override fun hide() {
    }

    override fun show() {
        sb = game.sb
        splashLogo = SplashLogo(ContentManager.res.getTexture("badlogic")!!, 0f, (ScreenInform.HEIGHT / 2).toFloat())
    }

    fun inter(a : Color, b : Color, t : Float) : Color {
        val rr = a.r + (b.r - a.r) * t
        val gg = a.g + (b.g - a.g) * t
        val bb = a.b + (b.b - a.b) * t
        val aa = a.a + (b.a - a.a) * t
        return Color(rr, gg, bb, aa)
    }

    private fun update(delta: Float){
        when (phase){
            SplashScreen.Phase.INIT -> {
                timer -= Gdx.graphics.deltaTime
                if (timer < 0.0F) {
                    phase = SplashScreen.Phase.FADE
                    timer = 1.2F
                }
            }
            SplashScreen.Phase.BOUNCE -> {

            }
            SplashScreen.Phase.FADE -> {
                timer -= Gdx.graphics.deltaTime

                color = inter(color, colorFade, delta * 2)


//                this.sX = Interpolation.exp5Out.apply(Settings.WIDTH as Float / 2.0f + OFFSET_X, Settings.WIDTH as Float / 2.0f, this.timer / 3.0f)
//                this.sY = Interpolation.exp5Out.apply(Settings.HEIGHT as Float / 2.0f - OFFSET_Y, Settings.HEIGHT as Float / 2.0f, this.timer / 3.0f)

                if (this.timer < 0.0f) {
//                    this.phase = SplashScreen.Phase.WAIT
//                    this.timer = 1.5f
                }
            }
            SplashScreen.Phase.WAIT -> {
                timer -= Gdx.graphics.getDeltaTime();
                if (timer < 0.0F) {
                    phase = SplashScreen.Phase.FADE_OUT;
                    timer = 1.0F
                }
            }
            SplashScreen.Phase.FADE_OUT -> {

                color.a = Interpolation.fade.apply(0.0F, 1.0F, this.timer / 1.0F)
                timer -= Gdx.graphics.deltaTime;

                if (this.timer < 0.0F) {
//                    img.dispose();
//                    isDone = true;
                }
            }
        }
    }

    override fun render(delta: Float) {
        update(delta)
        updateInput()
        sb.begin()
        sb.color = color

        sb.draw(ContentManager.res.getTexture("bg_white"), 0f, 0f,
                ScreenInform.WIDTH.toFloat(), ScreenInform.HEIGHT.toFloat())
        sb.color = Color(1.0f, 1.0f, 1.0f, 1.0f)
        sb.draw(splashLogo.texture, splashLogo.x, splashLogo.y, 32f, 32f)
        if (splashLogo.x < ScreenInform.WIDTH / 2 - 16) {
            splashLogo.x++
            splashLogo.y = Math.abs(Math.cos(splashLogo.x.toDouble() / 4).toFloat()) * 32 + (ScreenInform.HEIGHT / 2 - 16)
        }
        sb.end()
    }

    private fun updateInput() {
        InputHelper.update()
        if (InputHelper.justClickedLeft) {
            with(color){
                r = rand.nextFloat()
                g = rand.nextFloat()
                b = rand.nextFloat()
                a = rand.nextFloat()
            }
        }

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


