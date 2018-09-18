package ru.vanilla.ink.util

object SystemState {

    private fun getAllocatedMemory(): Long {
        return Runtime.getRuntime().totalMemory()
    }

    private fun getFreeMemory(): Long {
        return Runtime.getRuntime().freeMemory()
    }

    fun getUsedMemory() : Long {
        return getAllocatedMemory() - getFreeMemory()
    }
}