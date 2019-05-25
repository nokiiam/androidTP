package fr.epita.android.gameoflife

class Cell(var isAlive : Boolean) {
    public fun switch()  {
        isAlive = !isAlive
    }
}