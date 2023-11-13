public class ChessGame {
    public static void main(String[] args) {
        // Initialize the chess
        ChessBoard chessBoard = new ChessBoard();
        addPieces(chessBoard, -4, -3, ChessColor.WHITE);
        addPieces(chessBoard, 2, 3, ChessColor.BLACK);

        //Set the first player
        chessBoard.setStartingPlayer(ChessColor.WHITE);

        // Check if line 2 has chess
        int targetRow = 2;
        boolean hasPiecesInRow = chessBoard.hasPiecesInRow(targetRow);

        if (hasPiecesInRow) {
            System.out.println("There are pieces in row " + targetRow);
        } else {
            System.out.println("There are no pieces in row " + targetRow);
        }

        System.out.println("Initial Chess Board:");
        chessBoard.printBoard(5);

        // Set some moves to test check
        ChessCoordinate[] moves = {
                new ChessCoordinate(-3, 0), new ChessCoordinate(-2, 0),
                new ChessCoordinate(2, 0), new ChessCoordinate(1, 0),
                new ChessCoordinate(-2, 0), new ChessCoordinate(-1, 0),
                new ChessCoordinate(1, 0), new ChessCoordinate(0, 0),
                new ChessCoordinate(-3, 1), new ChessCoordinate(-2, 1),
                new ChessCoordinate(0, 0), new ChessCoordinate(-1, 0),
                new ChessCoordinate(-2, 1), new ChessCoordinate(-1, 1),
                new ChessCoordinate(-1, 0), new ChessCoordinate(-2, 0),
                new ChessCoordinate(-1, 1), new ChessCoordinate(0, 1),
                new ChessCoordinate(-2, 0), new ChessCoordinate(-3, 0),
//                new ChessCoordinate(0, 1), new ChessCoordinate(1, 1),
        };

        for (int i = 0; i < moves.length - 1; i += 2) {
            ChessCoordinate from = moves[i];
            ChessCoordinate to = moves[i + 1];
            ChessMoveHandler.makeMove(from, to, chessBoard);

            // Check for check
            ChessMoveHandler.isCheck(chessBoard, chessBoard.getCurrentPlayer());

            if (chessBoard.getSpecialCells().containsKey(to) && chessBoard.getChessPieceAt(to).getType() == ChessPieceType.PAWN) {
                ChessPieceType megaPawnType = chessBoard.getSpecialCells().get(to);
                ChessColor pawnColor = chessBoard.getChessPieceAt(to).getColor();
                chessBoard.addChessPiece(new ChessPiece(megaPawnType, pawnColor), to);
            }
        }
        // Print final chess
        System.out.println("\nUpdated Chess Board:");
        chessBoard.printBoard(5);
    }

    // Add chess
    public static void addPieces(ChessBoard board, int whiteRow, int blackRow, ChessColor color) {
        ChessPieceType[] pieceOrder = {
                ChessPieceType.ROOK, ChessPieceType.KNIGHT, ChessPieceType.BISHOP, ChessPieceType.QUEEN,
                ChessPieceType.KING, ChessPieceType.BISHOP, ChessPieceType.KNIGHT, ChessPieceType.ROOK
        };

        int startingRow = (color == ChessColor.WHITE) ? whiteRow : blackRow;

        for (int i = 0; i < pieceOrder.length; i++) {
            board.addChessPiece(new ChessPiece(pieceOrder[i], color), new ChessCoordinate(startingRow, i - 4));
            board.addChessPiece(new ChessPiece(ChessPieceType.PAWN, color), new ChessCoordinate(startingRow + (color == ChessColor.WHITE ? 1 : -1), i - 4));
        }
    }
}
