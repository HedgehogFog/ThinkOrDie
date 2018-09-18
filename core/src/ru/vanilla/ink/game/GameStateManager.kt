package ru.vanilla.ink.game

import com.badlogic.gdx.utils.Disposable
import ru.vanilla.ink.core.MainGame
import ru.vanilla.ink.game.state.GameState
import ru.vanilla.ink.game.state.LoaderState
import ru.vanilla.ink.game.state.PlayState
import java.util.*

class GameStateManager(game: MainGame) : Disposable {

    var game: MainGame
        private set
    private val gameStates: Stack<GameState>


    private val isCurrentStateClear = false
//    private val isCurrentStateClear = false

    init {
        this.game = game
        this.gameStates = Stack()
    }

    fun update(dt: Float){
        gameStates.peek().update(dt)
    }

    fun resize(width: Int, height: Int){
        gameStates.peek().resize(width, height)
    }

    fun render() {
        for (gameState in gameStates) {
            gameState.render()
        }
    }

    private fun getState(state: TypeState) : GameState? {
        when (state) {
            TypeState.LOADER -> return LoaderState(this)
            TypeState.PLAY -> return PlayState(this)
//            TypeState.MAINMENU -> return MainMenuState(this)

        }
        return null
    }

    fun setState(state: TypeState){
        gameStates.forEach { it.dispose() }
        gameStates.clear()

        gameStates.push(getState(state))
    }

    fun popState() {
        val state = gameStates.pop()
        state.dispose()
    }

    override fun dispose() {
        gameStates.forEach { it.dispose() }
    }

}