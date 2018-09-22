package ru.vanilla.ink.assets

object ContentManager {


    val res = Content()

    fun loadResouce() {

        with(res){
            loadTexture("bg_white.png", "bg_white")
            loadTexture("badlogic.jpg", "badlogic")
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