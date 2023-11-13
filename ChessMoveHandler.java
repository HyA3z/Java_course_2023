import java.util.Map;

public class ChessMoveHandler {
    public static boolean isValidMove(ChessPieceType type, ChessCoordinate from, ChessCoordinate to, ChessBoard board) {
        int deltaX = Math.abs(to.get_X() - from.get_X());
        int deltaY = Math.abs(to.get_Y() - from.get_Y());

        return switch (type) {
            case PAWN -> isValidPawnMove(from, to, board);
            case ROOK -> isValidRookMove(from, to, board);
            case KNIGHT -> (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
            case BISHOP -> isValidBishopMove(from, to, board);
            case QUEEN -> isValidQueenMove(from, to, board);
            case KING, MEGAPAWN -> deltaX <= 1 && deltaY <= 1;
            default -> false;
        };
    }

    public static void makeMove(ChessCoordinate from, ChessCoordinate to, ChessBoard board) {
        try {
            ChessPiece piece = board.getChessPieceAt(from);

            if (piece != null && isValidMove(piece.getType(), from, to, board) && board.getCurrentPlayer() == piece.getColor()) {
                ChessPiece destinationPiece = board.getChessPieceAt(to);

                if (destinationPiece != null) {
                    System.out.println("Captured: " + destinationPiece.getType() + " at " + to);
                }

                board.addChessPiece(piece, to);
                board.removeChessPiece(from);

                System.out.println("Current Player: " + board.getCurrentPlayer());
                System.out.println("Move successful: " + piece.getType() + " from " + from + " to " + to);

                board.switchPlayer();
            } else {
                handleInvalidMoveError(piece, board);
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleInvalidMoveError(ChessPiece piece, ChessBoard board) {
        if (piece == null) {
            System.out.println("Error: No piece at the specified 'from' position.");
        } else if (board.getCurrentPlayer() != piece.getColor()) {
            System.out.println("Error: It's currently " + board.getCurrentPlayer() + "'s turn to move.");
        } else {
            System.out.println("Invalid move");
        }
    }

    private static boolean isValidPawnMove(ChessCoordinate from, ChessCoordinate to, ChessBoard board) {
        ChessPiece piece = board.getChessPieceAt(from);
        if (piece == null) {
            throw new NullPointerException("No piece at the specified 'from' position.");
        }

        int deltaX = to.get_X() - from.get_X();
        int deltaY = to.get_Y() - from.get_Y();

        if (piece.getColor() == ChessColor.WHITE) {
            if (deltaY == 0 && deltaX == 1) {
                return board.getChessPieceAt(to) == null;
            } else if (deltaY == 1 && Math.abs(deltaX) == 1) {
                ChessPiece destinationPiece = board.getChessPieceAt(to);
                return destinationPiece != null && destinationPiece.getColor() == ChessColor.BLACK;
            }
        } else {
            if (deltaY == 0 && deltaX == -1) {
                return board.getChessPieceAt(to) == null;
            } else if (deltaY == -1 && Math.abs(deltaX) == 1) {
                ChessPiece destinationPiece = board.getChessPieceAt(to);
                return destinationPiece != null && destinationPiece.getColor() == ChessColor.WHITE;
            }
        }

        throw new IllegalArgumentException("Invalid pawn move from " + from + " to " + to);
    }

    private static boolean isValidRookMove(ChessCoordinate from, ChessCoordinate to, ChessBoard board) {
        int deltaX = to.get_X() - from.get_X();
        int deltaY = to.get_Y() - from.get_Y();

        if (deltaX == 0 && deltaY != 0) {
            return isPathClearVertical(from, to, board);
        } else if (deltaY == 0 && deltaX != 0) {
            return isPathClearHorizontal(from, to, board);
        }

        throw new IllegalArgumentException("Invalid rook move from " + from + " to " + to);
    }

    private static boolean isValidBishopMove(ChessCoordinate from, ChessCoordinate to, ChessBoard board) {
        int deltaX = to.get_X() - from.get_X();
        int deltaY = to.get_Y() - from.get_Y();

        if (Math.abs(deltaX) == Math.abs(deltaY)) {
            return isPathClearDiagonal(from, to, board);
        }

        throw new IllegalArgumentException("Invalid bishop move from " + from + " to " + to);
    }

    private static boolean isValidQueenMove(ChessCoordinate from, ChessCoordinate to, ChessBoard board) {
        int deltaX = to.get_X() - from.get_X();
        int deltaY = to.get_Y() - from.get_Y();

        if (deltaX == 0 && deltaY != 0) {
            return isPathClearVertical(from, to, board);
        } else if (deltaY == 0 && deltaX != 0) {
            return isPathClearHorizontal(from, to, board);
        } else if (Math.abs(deltaX) == Math.abs(deltaY)) {
            return isPathClearDiagonal(from, to, board);
        }

        throw new IllegalArgumentException("Invalid queen move from " + from + " to " + to);
    }

    private static boolean isPathClearHorizontal(ChessCoordinate from, ChessCoordinate to, ChessBoard board) {
        int directionX = Integer.compare(to.get_X(), from.get_X());

        for (int i = from.get_X() + directionX; i != to.get_X(); i += directionX) {
            ChessCoordinate currentPos = new ChessCoordinate(i, from.get_Y());
            if (board.getChessPieceAt(currentPos) != null) {
                throw new IllegalArgumentException("Path is not clear for rook move from " + from + " to " + to);
            }
        }

        return true;
    }

    private static boolean isPathClearVertical(ChessCoordinate from, ChessCoordinate to, ChessBoard board) {
        int directionY = Integer.compare(to.get_Y(), from.get_Y());

        for (int i = from.get_Y() + directionY; i != to.get_Y(); i += directionY) {
            ChessCoordinate currentPos = new ChessCoordinate(from.get_X(), i);
            if (board.getChessPieceAt(currentPos) != null) {
                throw new IllegalArgumentException("Path is not clear for rook move from " + from + " to " + to);
            }
        }

        return true;
    }

    private static boolean isPathClearDiagonal(ChessCoordinate from, ChessCoordinate to, ChessBoard board) {
        int directionX = Integer.compare(to.get_X(), from.get_X());
        int directionY = Integer.compare(to.get_Y(), from.get_Y());

        int currentX = from.get_X() + directionX;
        int currentY = from.get_Y() + directionY;

        while (currentX != to.get_X() || currentY != to.get_Y()) {
            ChessCoordinate currentPos = new ChessCoordinate(currentX, currentY);
            if (board.getChessPieceAt(currentPos) != null) {
                throw new IllegalArgumentException("Path is not clear for bishop or queen move from " + from + " to " + to);
            }
            currentX += directionX;
            currentY += directionY;
        }

        return true;
    }
    public static void isCheck(ChessBoard board, ChessColor color) {
        ChessCoordinate opponentKing = findKingPosition(board, color);
        color = (color == ChessColor.WHITE) ? ChessColor.BLACK : ChessColor.WHITE;

        for (Map.Entry<ChessCoordinate, ChessPiece> entry : board.getBoard().entrySet()) {
            ChessCoordinate from = entry.getKey();
            ChessPiece piece = entry.getValue();

            try {
                if (piece.getColor() == color && isValidMove(piece.getType(), from, opponentKing, board)) {
                    System.out.println("Check: " + piece.getType() + " at " + from + " threatens the opponent's king at " + opponentKing);
                }
            } catch (Exception ignored) {
            }
        }
    }


    private static ChessCoordinate findKingPosition(ChessBoard board, ChessColor color) {

        for (Map.Entry<ChessCoordinate, ChessPiece> entry : board.getBoard().entrySet()) {
            ChessPiece piece = entry.getValue();
            if (piece.getType() == ChessPieceType.KING && piece.getColor() == color) {
                return entry.getKey();
            }
        }

        throw new IllegalStateException("Cannot find opponent's king on the board for color " + color);
    }

}
