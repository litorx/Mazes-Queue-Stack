public class Coordinate implements Cloneable {
    private int x;
    private int y;

    /**
     * Construtor para coordenadas
     * @param x a posição X
     * @param y a posição Y
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinate that = (Coordinate) obj;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public Object clone() {
        try {
            return super.clone(); // Clonando diretamente a coordenada
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
