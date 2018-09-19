package ru.vanilla.ink.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ru.vanilla.ink.core.MainGame
import com.badlogic.gdx.graphics.Color
import ru.vanilla.ink.util.ResourceManager
import com.badlogic.gdx.math.Interpolation
import ru.vanilla.ink.core.Settings
import ru.vanilla.ink.helper.input.InputHelper
import java.util.*


class SplashScreen(val game: MainGame) : Screen {
    private var timer = 0.0f

    private val color = Color(1.0f, 1.0f, 0.0f, 0.0f)
    private val colorFade = Color(1.0f, 1.0f, 1.0f, 0.0f)


    lateinit var sb: SpriteBatch

    private var phase: SplashScreen.Phase = Phase.INIT

    val rand = Random()
    override fun hide() {
    }

    override fun show() {
        sb = game.sb
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

                print("Pre $color;")
                with(color){
                    r = Interpolation.fade.apply(colorFade.r, 1.0f, timer / 3)
                    g = Interpolation.fade.apply(colorFade.g, 1.0f, timer / 3)
                    b = Interpolation.fade.apply(colorFade.b, 1.0f, timer / 3)
                }
                println("Post $color")



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

        sb.draw(ResourceManager.res.getTexture("bg_white"), 0f, 0f,
                Settings.WIDTH.toFloat(), Settings.HEIGHT.toFloat())

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


