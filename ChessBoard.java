import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ChessBoard {
    private final Map<ChessCoordinate, ChessPiece> board;
    private final Map<ChessCoordinate, ChessPieceType> specialCells;
    private ChessColor currentPlayer;
    private final TreeSet<Integer> yCoordinates;

    public Map<ChessCoordinate, ChessPiece> getBoard() {
        return new HashMap<>(board);
    }

    public ChessBoard() {
        this.board = new HashMap<>();
        this.specialCells = new HashMap<>();
        this.currentPlayer = ChessColor.WHITE;
        this.yCoordinates = new TreeSet<>();
        specialCells.put(new ChessCoordinate(0, 0), ChessPieceType.MEGAPAWN);
    }

    public boolean hasPiecesInRow(int row) {
        return yCoordinates.contains(row);
    }

    public void setStartingPlayer(ChessColor startingPlayer) {
        this.currentPlayer = startingPlayer;
    }

    public void addChessPiece(ChessPiece piece, ChessCoordinate position) {
        board.put(position, piece);
        yCoordinates.add(position.get_Y());
    }

    public ChessPiece getChessPieceAt(ChessCoordinate position) {
        return board.get(position);
    }

    public void removeChessPiece(ChessCoordinate position) {
        board.remove(position);
        yCoordinates.remove(position.get_Y());
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
}
