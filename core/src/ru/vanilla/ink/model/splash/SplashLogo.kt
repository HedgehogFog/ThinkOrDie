package ru.vanilla.ink.model.splash

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ru.vanilla.ink.core.Settings
import ru.vanilla.ink.model.GamePrefab

class SplashLogo(val textureName: String, val texture: Texture, var x: Float, var y: Float, var width: Float, var height: Float) : GamePrefab(){

    var speedX = 1.0f
    var speedY = 1.0f

    override fun update(delta: Float) {
        x += speedX
        y = Math.abs(Math.cos(x.toDouble() / speedY).toFloat()) * height + (Settings.HEIGHT / 2 - height)
    }

    override fun render(sb: SpriteBatch) {
        super.render(sb)
    }

    fun setSpeed(speedX: Float, speedY: Float){
        this.speedX = speedX
        this.speedY = speedY
    }
}