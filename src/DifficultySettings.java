public class DifficultySettings {
    private String difficulty = "Medium";
    private int gridRowSize = 15;
    private int gridColumnSize = 15;
    private int totalMines = 30;

    // Getter for difficulty
    public String getDifficulty() {
        return this.difficulty;
    }

    // Setter for difficulty with logic to adjust settings
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        switch (difficulty) {
            case "Easy":
                this.gridRowSize = 10;
                this.gridColumnSize = 10;
                this.totalMines = 20;
                break;
            case "Medium":
                this.gridRowSize = 15;
                this.gridColumnSize = 15;
                this.totalMines = 30;
                break;
            case "Hard":
                this.gridRowSize = 20;
                this.gridColumnSize = 20;
                this.totalMines = 100;
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level: " + difficulty);
        }
    }

    // Getters for grid size and total mines
    public int getGridRowSize() {
        return this.gridRowSize;
    }

    public int getGridColumnSize() {
        return this.gridColumnSize;
    }

    public int getTotalMines() {
        return this.totalMines;
    }
}
