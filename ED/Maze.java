public class Maze {
    private char[][] grid;
    private int rows;
    private int cols;

    public Maze(int rows, int cols) throws Exception {
        if (rows <= 0 || cols <= 0)
            throw new Exception("Dimensões inválidas");
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }

    public void setCell(int x, int y, char c) throws Exception {
        if (x < 0 || x >= rows || y < 0 || y >= cols)
            throw new Exception("Coordenada fora dos limites");
        grid[x][y] = c;
    }

    public char getCell(int x, int y) throws Exception {
        if (x < 0 || x >= rows || y < 0 || y >= cols)
            throw new Exception("Coordenada fora dos limites");
        return grid[x][y];
    }

    public Maze clone() {
        try {
            Maze m = new Maze(rows, cols);
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < cols; j++)
                    m.grid[i][j] = grid[i][j];
            return m;
        } catch (Exception e) {
            return null;
        }
    }
}
