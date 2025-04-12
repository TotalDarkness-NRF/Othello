# Othello

This is a simple implementation of the Othello (also known as Reversi) game in Java. 
Othello is a strategy board game for two players, 
where the goal is to have the majority of discs in your color on the board at the end of the game.

<table align="center">
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/9ca116b2-5c63-4643-8875-d573cb2cb74c" height="200" width="200"><br>
      <sub>Main menu</sub>
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/4918954a-e4da-4ef0-82a6-c154891e2543" height="200" width="200"><br>
      <sub>Player selecter</sub>
    </td>
    </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/96b03075-96ce-4648-88d8-766bbd642233" height="200" width="200"><br>
      <sub>Othello gameplay</sub>
    </td>
  </tr>
</table>

## Features
- Two-player gameplay (Player vs. Player).
- Human vs Human (Human vs. Human).
- Human vs Computer (Player vs. Random or Greedy).
- Computer vs Computer (Either combination of Random and Greedy).
- Board display with javafx.
- Game ending condition (when no valid moves are left for either player).
- Saving Othello games to files.
- Loading Othello games from files using default file manager.
- Undo and redo of moves.
- Restarting games.

## Requirements
- Java 17 or higher.

## How to Run

1. **Clone the repository**:
    ```bash
    git clone https://github.com/TotalDarkness-NRF/Othello.git
    cd Othello
    ```
    
2. **Run the Othello Application**:
    ```bash
    ./gradlew run
    ```
    
3. **Optional play on terminal**:
   Run any of the OthelloController main functions.<br>
   Example: OthelloControllerHumanVSHuman plays to human players on the terminal.

## Gameplay Instructions
- Player 1 uses the `X` or 'Black' pieces and Player 2 uses the `O` or 'White' pieces.
- Players take turns placing their pieces on the board. The piece is placed on an empty space where it surrounds one or more of the opponent's pieces between the newly placed piece and another of the player's own pieces.
- Once all valid moves are exhausted, the player with the most pieces of their color wins.
- **Board Size**: The Othello board is an 8x8 grid.
- **Valid Moves**: A move is valid if it captures at least one of the opponent's pieces by flipping them to your own color.
- **Flipping**: A piece is flipped if it's surrounded by the players pieces on one or more lines (horizontally, vertically, or diagonally).

