package ru.vanilla.ink.model.game

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import ru.vanilla.ink.helper.input.InputHelper
import ru.vanilla.ink.model.GamePrefab
import com.badlogic.gdx.math.Vector2
import ru.vanilla.ink.core.Settings


class Bird(val texture: Texture, var x: Float, var y: Float, var width: Float, var height: Float) : GamePrefab() {

    //val position = Vector2(x, y)
    //val velocity = Vector2()
    //val acceleration = Vector2()

    private val MAX_JUMP_SPEED = 9.81f * 100;
    //private var GRAVITY = -20f
    //private val DAMP = 0.90f
    private val colorNormalized = Color(1.0f, 1.0f, 1.0f, 1.0f)

    //val step = 4f

    private val GRAVITY = Vector2(0f, -9.81f * 100);

    val position = Vector2(x, y)
    private val velocity = Vector2(9.81f * 100, 0f)

    fun moveTowards(vecFrom: Float, vecTo: Float, maxDelta: Float): Float {
        val distance = Math.abs(vecTo - vecFrom)
        val ddd = Math.min(distance, maxDelta)
        val sn = if (vecTo >= vecFrom) 1 else -1

        return vecFrom + ddd * sn

    }

    override fun update(delta: Float) {
        velocity.add(GRAVITY.x * delta, GRAVITY.y * delta)
        position.add(velocity.x * delta, velocity.y * delta)

        if (position.y <= 0 || position.y + height >= Settings.HEIGHT) {
            if (velocity.y < 0)
                velocity.x = moveTowards(velocity.x, 0f, 4f * 100 * delta)

            velocity.y = -velocity.y * 0.8f
        } else {
            velocity.x = moveTowards(velocity.x, 9.81f * 100 * Math.signum(velocity.x) + 0.1f, 4f * 100 * delta)
        }

        if (position.x <= 0 || position.x + width >= Settings.WIDTH) {
            val error = if (position.x < Settings.WIDTH / 2) position.x else (position.x + width - Settings.WIDTH)
            position.x -= error

            velocity.x = -velocity.x * 0.8f
        }

    }

    override fun inputUpdate() {
        if (InputHelper.justClickedLeft) {
            velocity.y = MAX_JUMP_SPEED
        }
    }

    override fun render(sb: SpriteBatch) {
        sb.color = colorNormalized
        sb.draw(texture, position.x, position.y, width, height)
    }
}