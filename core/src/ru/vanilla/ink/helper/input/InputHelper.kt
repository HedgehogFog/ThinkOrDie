package ru.vanilla.ink.helper.input

import com.badlogic.gdx.Gdx
import ru.vanilla.ink.core.Settings


object InputHelper{

    private lateinit var processor: ScrollInputProcessor

    var mX: Int = 0
    var mY: Int = 0

    var scrolledUp = false
    var scrolledDown = false


    var isMouseDown = false
    var isMouseDown_R = false

    private var isPrevMouseDown = false
    private var isPrevMouseDown_R = false

    var justClickedLeft = false
    var justClickedRight = false

    var justReleasedClickLeft = false
    var justReleasedClickRight = false

    fun init(){
        processor = ScrollInputProcessor()
        Gdx.input.inputProcessor = processor
    }

    fun update(){
        mX = Gdx.input.x
        if (mX > Settings.WIDTH)
            mX = Settings.WIDTH
        else if (mX < 0)
            mX = 0

        mY = Settings.HEIGHT - Gdx.input.y

        if (mY > Settings.HEIGHT)
            mY = Settings.HEIGHT
        else if (mY < 0)
            mY = 0

        isMouseDown = Gdx.input.isButtonPressed(0)
        isMouseDown_R = Gdx.input.isButtonPressed(1)

        if (!isPrevMouseDown && isMouseDown) {
            justClickedLeft = true

            if (Settings.isDebug)
                println("Clicked: ($mX,$mY)")

        } else if (isPrevMouseDown && !isMouseDown) {
            justReleasedClickLeft = true
        }

        if (!isPrevMouseDown_R && isMouseDown_R) {
            justClickedRight = true
        } else if (isPrevMouseDown_R && !isMouseDown_R) {
            justReleasedClickRight = true
        }

        isPrevMouseDown_R = isMouseDown_R
        isPrevMouseDown = isMouseDown

    }

    fun updateLast() {
        justClickedLeft = false
        justClickedRight = false
        justReleasedClickLeft = false
        scrolledUp = false
        scrolledDown = false
    }
}