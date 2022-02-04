package mancala.domain;

public class Players {
  private int playerTurn = 1;
  private int winner = 0;

  Players() {

  }

  public int getPlayerTurn() {
    return playerTurn;
  }

  public int getWinner() {
    return winner;
  }

  void switchTurns() {
    this.playerTurn = (playerTurn % 2) + 1;
  }

  void endGame(int kalahaOwner, int collectedStones) {
    this.playerTurn = 0;
    if (collectedStones > 24) {
      this.winner = kalahaOwner;
    }
    else if (collectedStones < 24) {
      this.winner = (kalahaOwner % 2) + 1;
    } else {
      this.winner = 3;
    }
  }
}
