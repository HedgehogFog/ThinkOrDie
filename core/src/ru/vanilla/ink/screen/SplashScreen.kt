package ru.vanilla.ink.screen

import android.R.attr.*
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ru.vanilla.ink.core.MainGame
import javax.microedition.khronos.opengles.GL10
import com.badlogic.gdx.graphics.Color
import ru.vanilla.ink.util.ResourceManager
import com.badlogic.gdx.math.Interpolation
import android.R.color








class SplashScreen(val game: MainGame) : Screen {
    private var timer = 0.0f

    private val color = Color(0.0f, 0.0f, 0.0f, 0.0f)


    lateinit var sb: SpriteBatch

    private var phase: SplashScreen.Phase = Phase.INIT

    override fun hide() {
    }

    override fun show() {
        sb = game.sb
    }

    fun update(delta: Float){
        when (phase){
            SplashScreen.Phase.INIT -> {
                timer -= Gdx.graphics.deltaTime
                color.r = 1.0f
                if (timer < 0.0F) {
                    phase = SplashScreen.Phase.FADE
                    timer = 1.2F
                }
            }
            SplashScreen.Phase.BOUNCE -> {

            }
            SplashScreen.Phase.FADE -> {
                timer -= Gdx.graphics.deltaTime

                with(color){
//                    r = Interpolation.fade.apply(cream.r, 1.0f, timer / 3.0f)
//                    g = Interpolation.fade.apply(cream.g, 1.0f, timer / 3.0f)
//                    b = Interpolation.fade.apply(cream.b, 1.0f, timer / 3.0f)
                }



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

        sb.begin()
        sb.color = this.color
        sb.draw(ResourceManager.res.getTexture("bg_white"), )

        sb.end()
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


