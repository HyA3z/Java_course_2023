public class ChessCoordinate {
    private final int x;
    private final int y;

    public ChessCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int get_X() {
        return x;
    }

    public int get_Y() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChessCoordinate that = (ChessCoordinate) obj;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}