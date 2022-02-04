package mancala.domain;

public class Kalaha {
  int amountOfStones = 0;
  int owner;
  Kalaha neighbour;
  Players players;

  Kalaha() {

  }

  Kalaha(int kalahaNumber, Kalaha firstBowl, Players playing) {
    this.players = playing;
    int kalahaPlayer1 = 6;
    if (kalahaNumber == kalahaPlayer1) {
      this.owner = 1;
      this.neighbour = new Bowl(kalahaNumber + 1, firstBowl, playing);
    }
    else {
      this.owner = 2;
      this.neighbour = firstBowl;
    }
  }

  int getStones() {
    return this.amountOfStones;
  }

  int getOwner() {
    return this.owner;
  }

  Kalaha getNeighbour(int neighbourNumber) {
    Kalaha neighbouringKalaha;
    if (neighbourNumber == 0) {
      neighbouringKalaha = this;
    } else {
      neighbouringKalaha = neighbour.getNeighbour(neighbourNumber - 1);
    }
    return neighbouringKalaha;
  }

  public Players getPlayers() {
    return this.players;
  }

  public void makeMove() {

  }

  void takeStonePassOnOthers(int stonesPassedOn) {
    if (players.getPlayerTurn() == this.owner) {
      this.amountOfStones++;
      if (stonesPassedOn > 1) {
        neighbour.takeStonePassOnOthers(stonesPassedOn - 1);
      }
      else {
        neighbour.checkEndOfGame();
      }
    }
    else {
      neighbour.takeStonePassOnOthers(stonesPassedOn);
    }
  }

  void startStealingStones(int counter) {
    int stolenStones = neighbour.stealStones(counter);
    this.amountOfStones += stolenStones + 1;
  }

  int stealStones(int counter) {
    int stealStones = 0;
    return stealStones;
  }

  void checkEndOfGame() {
    if (this.owner == players.getPlayerTurn()) {
      neighbour.checkEndOfGame();
    }
    else {
      neighbour.checkEmptyBowls();
    }
  }

  void checkEmptyBowls() {
    players.endGame(this.owner, this.amountOfStones);
  }
}
