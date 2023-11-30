# Chess Game

## Overview

This Java program implements a console-based two-player chess game, complete with basic chess rules, piece movements, and check situations.

## Features

- **Chess Board Representation**: The chess board is modeled using a 2D grid, with pieces placed on specific coordinates.

- **Chess Pieces**: Supports standard chess pieces, including Pawn, Rook, Knight, Bishop, Queen, and King. Additionally, a MegaPawn piece is introduced.

- **Piece Movement Validation**: The `ChessMoveHandler` class validates move legality based on the type of chess piece and the current board state.

- **Turn Management**: The game keeps track of the current player's turn and switches players after each valid move.

- **Check Detection**: The `ChessMoveHandler` class identifies whether a move puts the opponent's king in check.

- **Special Cells**: Special cells on the board, represented by the `specialCells` map in `ChessBoard`, can affect the game. In the example, a MegaPawn is introduced when a Pawn reaches a specific position.

## How to Play

1. **Initializing the Board**: The `ChessGame` class initializes the chess board and places pieces on it using the `addPieces` method. The initial board state is printed.

2. **Making Moves**: The program demonstrates a series of moves using the `ChessMoveHandler.makeMove` method. Moves are specified by providing the starting and ending coordinates in the `moves` array.

3. **Check Detection**: After each move, the program checks if the move puts the opponent's king in check using the `ChessMoveHandler.isCheck` method.

4. **Special Cells**: If a Pawn reaches a specific position in the `specialCells` map, a MegaPawn piece is introduced.

5. **Updated Board**: The final updated chess board is printed to the console.

## Running the Program

Compile and run the `ChessGame` class to execute the chess game. Adjust the `moves` array in the `main` method to customize the sequence of moves.

```bash
javac ChessGame.java
java ChessGame
