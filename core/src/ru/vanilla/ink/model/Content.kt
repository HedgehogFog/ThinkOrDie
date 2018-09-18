package ru.vanilla.ink.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Disposable

class Content : Disposable {

    private var textures: HashMap<String, Texture> = HashMap()
    private var sounds: HashMap<String, Sound> = HashMap()
    private var musics: HashMap<String, Music> = HashMap()

    // ** Textures **

    fun loadTexture(path: String, key: String) {
        val tex = Texture(Gdx.files.internal(path))
        textures.put(key, tex)
    }

    fun getTexture(key: String) : Texture? {
        return textures[key]
    }

    fun removeTexture(key: String) {
        val tex = textures[key]
        if (tex != null) {
            textures.remove(key)
            tex.dispose()
        }
    }

    // ** Sounds ** //
    fun loadSound(path: String, key: String) {
        val s = Gdx.audio.newSound(Gdx.files.internal(path))
        sounds.put(key, s)
    }

    fun getSound(key: String): Sound? {
        return sounds[key]
    }

    fun removeSound(key: String) {
        val s = sounds[key]
        if (s != null) {
            sounds.remove(key)
            s.dispose()
        }
    }

    // ** Musics ** //
    fun loadMusic(path: String, key: String) {
        val m = Gdx.audio.newMusic(Gdx.files.internal(path))
        musics.put(key, m)
    }

    fun getMusic(key: String): Music? {
        return musics[key]
    }

    fun removeMusic(key: String) {
        val m = musics[key]
        if (m != null) {
            musics.remove(key)
            m.dispose()
        }
    }

    override fun dispose() {
        textures.keys.toSet().asIterable().forEach { removeTexture(it) }
    }

}