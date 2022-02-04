package mancala.domain;

public class Bowl extends Kalaha {
  public Bowl() {
    this.amountOfStones = 4;
    this.players = new Players();
    this.owner = 1;
    this.neighbour = new Bowl(1, this, this.players);
  }

  Bowl(int bowlNumber, Kalaha firstBowl, Players playing) {
    this.amountOfStones = 4;
    this.players = playing;
    int lastBowlplayer1 = 5;
    int lastBowlplayer2 = 12;
    if (bowlNumber < lastBowlplayer1) {
      this.owner = 1;
      this.neighbour = new Bowl(bowlNumber + 1, firstBowl, playing);
    }
    else if (bowlNumber == lastBowlplayer1) {
      this.owner = 1;
      this.neighbour = new Kalaha(bowlNumber + 1, firstBowl, playing);
    }
    else if (bowlNumber < lastBowlplayer2) {
      this.owner = 2;
      this.neighbour = new Bowl(bowlNumber + 1, firstBowl, playing);
    }
    else {
      this.owner = 2;
      this.neighbour = new Kalaha(bowlNumber + 1, firstBowl, playing);
    }
  }

/*  public int getStones() {
    return this.amountOfStones;
  }*/

  public void makeMove() {
    if (this.owner == players.getPlayerTurn() && this.amountOfStones > 0) {
      int stonesToPassOn = this.amountOfStones;
      this.amountOfStones = 0;
      neighbour.takeStonePassOnOthers(stonesToPassOn);
    }
  }

  void takeStonePassOnOthers(int stonesPassedOn) {
    this.amountOfStones++;
    if (stonesPassedOn == 1) {
      if (this.amountOfStones == 1 && owner == players.getPlayerTurn()) {
        this.amountOfStones--;
        neighbour.startStealingStones(0);
      }
      players.switchTurns();
      neighbour.checkEndOfGame();
    }
    else {
      neighbour.takeStonePassOnOthers(stonesPassedOn - 1);
    }
  }

  void startStealingStones(int counter) {
    neighbour.startStealingStones(counter + 1);
  }

  int stealStones(int counter) {
    int stonesToSteal;
    if (counter == 0) {
      stonesToSteal = this.amountOfStones;
      this.amountOfStones = 0;
    }
    else {
      stonesToSteal = neighbour.stealStones(counter - 1);
    }
    return stonesToSteal;
  }

  void checkEndOfGame() {
    neighbour.checkEndOfGame();
  }

  void checkEmptyBowls() {
    if (this.amountOfStones == 0) {
      neighbour.checkEmptyBowls();
    }
  }
}
