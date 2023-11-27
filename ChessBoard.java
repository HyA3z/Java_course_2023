import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class ChessBoard {
    private final Map<ChessCoordinate, ChessPiece> board;
    private final Map<ChessCoordinate, ChessPieceType> specialCells;
    private ChessColor currentPlayer;
    private final TreeMap<Integer, Boolean> yCoordinates;
    private final Map<ChessColor, Map<ChessPieceType, Set<ChessCoordinate>>> pieceMap;
    private final Set<Integer> occupiedRows;
    private final Set<Integer> occupiedCols;
    private final Set<Integer> occupiedDiagonals;

    public Map<ChessCoordinate, ChessPiece> getBoard() {
        return new HashMap<>(board);
    }

    public ChessBoard() {
        this.board = new HashMap<>();
        this.specialCells = new HashMap<>();
        this.currentPlayer = ChessColor.WHITE;
        this.yCoordinates = new TreeMap<>();
        this.pieceMap = new HashMap<>();
        this.occupiedRows = new HashSet<>();
        this.occupiedCols = new HashSet<>();
        this.occupiedDiagonals = new HashSet<>();

        specialCells.put(new ChessCoordinate(0, 0), ChessPieceType.MEGAPAWN);

        for (ChessColor color : ChessColor.values()) {
            pieceMap.put(color, new HashMap<>());
            for (ChessPieceType type : ChessPieceType.values()) {
                pieceMap.get(color).put(type, new HashSet<>());
            }
        }
    }

    public boolean hasPiecesInRow(int row) {
        return yCoordinates.containsKey(row);
    }

    public void setStartingPlayer(ChessColor startingPlayer) {
        this.currentPlayer = startingPlayer;
    }

    public void addChessPiece(ChessPiece piece, ChessCoordinate position) {
        board.put(position, piece);
        yCoordinates.put(position.get_Y(), true);
        pieceMap.get(piece.getColor()).get(piece.getType()).add(position);

        int x = position.get_X();
        int y = position.get_Y();

        occupiedRows.add(x);
        occupiedCols.add(y);
        occupiedDiagonals.add(x - y);
        occupiedDiagonals.add(x + y);
    }

    public ChessPiece getChessPieceAt(ChessCoordinate position) {
        return board.get(position);
    }

    public void removeChessPiece(ChessCoordinate position) {
        ChessPiece removedPiece = board.remove(position);
        yCoordinates.remove(position.get_Y());
        if (removedPiece != null) {
            pieceMap.get(removedPiece.getColor()).get(removedPiece.getType()).remove(position);
        }
        int x = position.get_X();
        int y = position.get_Y();
        occupiedRows.remove(x);
        occupiedCols.remove(y);
        occupiedDiagonals.remove(x - y);
        occupiedDiagonals.remove(x + y);
    }

    public ChessColor getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == ChessColor.WHITE) ? ChessColor.BLACK : ChessColor.WHITE;
    }

    public void printBoard(int size) {
        System.out.print("    ");
        for (int i = -size; i < size; i++) {
            System.out.printf("%-5d", i);
        }
        System.out.println();

        for (int i = -size; i < size; i++) {
            System.out.printf("%-4d", i);

            for (int j = -size; j < size; j++) {
                ChessCoordinate currentPos = new ChessCoordinate(i, j);
                ChessPiece piece = board.get(currentPos);

                if (piece != null) {
                    if (piece.getType() == ChessPieceType.KNIGHT) {
                        String colorCode = (piece.getColor() == ChessColor.WHITE) ? "\u001B[34m" : "\u001B[31m";
                        System.out.printf("%sN    \u001B[0m", colorCode);
                    } else {
                        printColoredPiece(piece);
                    }
                } else if (specialCells.containsKey(currentPos)) {
                    System.out.printf("\u001B[32m%-5s\u001B[0m", specialCells.get(currentPos).toString().charAt(0));
                } else {
                    System.out.print("-    ");
                }
            }
            System.out.println();
        }
    }

    private void printColoredPiece(ChessPiece piece) {
        String colorCode = (piece.getColor() == ChessColor.WHITE) ? "\u001B[34m" : "\u001B[31m";
        System.out.printf("%s%-5s\u001B[0m", colorCode, piece.getType().toString().charAt(0));
    }

    public Map<ChessCoordinate, ChessPieceType> getSpecialCells() {
        return specialCells;
    }

    public Map<ChessColor, Map<ChessPieceType, Set<ChessCoordinate>>> getPieceMap() {
        return pieceMap;
    }

    boolean isPathClearHorizontal(ChessCoordinate from, ChessCoordinate to) {
        int y = from.get_Y();

        if (occupiedRows.contains(from.get_X()) || occupiedRows.contains(to.get_X())) {
            return false; // If either the starting or ending row is occupied, the path is not clear.
        }

        return !occupiedCols.contains(y); // Check if the entire column is clear.
    }

    boolean isPathClearVertical(ChessCoordinate from, ChessCoordinate to) {
        int x = from.get_X();

        if (occupiedCols.contains(from.get_Y()) || occupiedCols.contains(to.get_Y())) {
            return false; // If either the starting or ending column is occupied, the path is not clear.
        }

        return !occupiedRows.contains(x); // Check if the entire row is clear.
    }

    boolean isPathClearDiagonal(ChessCoordinate from, ChessCoordinate to) {
        int diagonalIndexFrom = from.get_X() - from.get_Y();
        int diagonalIndexTo = to.get_X() - to.get_Y();

        return !occupiedDiagonals.contains(diagonalIndexFrom) && !occupiedDiagonals.contains(diagonalIndexTo);
    }

}
