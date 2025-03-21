public class GameSettings {
    private boolean gameOver = false;
    private int cellsToReveal = 195;

    public boolean isGameOver() {
        return this.gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setCellsToReveal(int cellsToReveal) {
        this.cellsToReveal = cellsToReveal;
    }

    public void decrementCellsToReveal() {
        this.cellsToReveal--;
    }

    public int getCellsToReveal() {
        return this.cellsToReveal;
    }
}
