package ru.vanilla.ink.util

import ru.vanilla.ink.model.Content

object ResourceManager {

    val res = Content()

    fun loadResouce() {

        with(res){
            loadTexture("images/bunny.png", "bunny")
            loadTexture("images/crystal.png", "crystal")
            loadTexture("images/hud.png", "hud")
            loadTexture("images/bgs.png", "bgs")
            loadTexture("images/menu.png", "menu")
            loadTexture("images/spikes.png", "spikes")
            loadTexture("images/misc.png", "misc")
        }

        with(res){
            loadMusic("music/bbsong.mp3", "bbsong")

        }


        with(res){
            loadSound("sfx/changeblock.wav", "changeblock")
            loadSound("sfx/crystal.wav", "crystal")
            loadSound("sfx/hit.wav", "hit")
            loadSound("sfx/jump.wav", "jump")
            loadSound("sfx/levelselect.wav", "levelselect")
        }

    }
}