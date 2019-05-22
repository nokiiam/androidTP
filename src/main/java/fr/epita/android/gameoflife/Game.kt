package fr.epita.android.gameoflife

class Game (val height : Int, val width : Int){
    var grid : MutableList<MutableList<Cell>> = MutableList(height, { MutableList(width, { Cell(false) })})

    fun switch(posy : Int, posx : Int) {
        grid[posy][posx].switch()
    }

    fun countNeighor(posy: Int, posx: Int): Int {
        var count = 0;
        for (i in -1..1) {
            for (j in -1..1) {
                if (posy + i < 0 || posy + i >= height || posx + i < 0 || posx + i >= width)
                    continue
                if (grid[posy][posx].isAlive) count++
            }
        }
        return count
    }

    fun nextTurn() {
        var tochange : MutableList<Boolean> = MutableList(height * width, { false })
        for (i in 0..height) {
            for (j in 0..width) {
                val count = countNeighor(i, j);
                val isAlive = grid[i][j].isAlive;
                tochange[i * height + j] = (isAlive && (count < 2 || count > 3)) || (!isAlive && count == 3)
            }
        }

        for (i in 0..height) {
            for (j in 0..width) {
                if (tochange[i * height + j]) grid[i][j].switch()
            }
        }
    }

    fun reset() {
        for (i in 0..height) {
            for (j in 0..width) {
               if (grid[i][j].isAlive)
                   grid[i][j].switch()
            }
        }
    }
}