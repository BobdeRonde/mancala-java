package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayersTest {
  @Test
  public void player1Starts() {
    Players players = new Players();
    assertEquals(1, players.getPlayerTurn());
  }
  @Test
  public void isTheGameFinished() {
    Players players = new Players();
    assertNotEquals(0, players.getPlayerTurn());
  }
  @Test
  public void doesTheTurnSwitch() {
    Bowl bowl1 = new Bowl();
    Players players = bowl1.getPlayers();
    bowl1.makeMove();
    assertEquals(2, players.getPlayerTurn());
  }
  @Test
  public void turnDoesntSwitchWhenOpponentsBowl() {
    Bowl bowl1 = new Bowl();
    Players players = bowl1.getPlayers();
    Kalaha bowl7 = bowl1.getNeighbour(7);
    bowl7.makeMove();
    assertEquals(1, players.getPlayerTurn());
  }
  @Test
  public void turnDoesntSwitchWhenBowlIsEmpty() {
    Bowl bowl1 = new Bowl();
    Players players = bowl1.getPlayers();
    Kalaha bowl7 = bowl1.getNeighbour(7);
    bowl1.makeMove();
    bowl7.makeMove();
    bowl1.makeMove();
    assertEquals(1, players.getPlayerTurn());
  }
  @Test
  public void lastStoneInOwnKalahaKeepsTurn() {
    Bowl bowl1 = new Bowl();
    Players players = bowl1.getPlayers();
    Kalaha bowl3 = bowl1.getNeighbour(2);
    bowl3.makeMove();
    assertEquals(1, players.getPlayerTurn());
  }
  @Test
  public void turnDoesntSwitchWhenKalahaDoesntMakeAMove() {
    Bowl bowl1 = new Bowl();
    Players players = bowl1.getPlayers();
    Kalaha kalaha1 = bowl1.getNeighbour(6);
    Kalaha bowl6 = bowl1.getNeighbour(5);
    Kalaha bowl7 = bowl1.getNeighbour(7);
    bowl6.makeMove();
    bowl7.makeMove();
    kalaha1.makeMove();
    assertEquals(1, players.getPlayerTurn());
  }
  @Test
  public void doesTheGameEndWhenNextPlayerHasNoMoves() {
    Bowl bowl1 = new Bowl();
    Players players = bowl1.getPlayers();
    emptyPlayer1Bowls(bowl1);
    assertEquals(0, players.getPlayerTurn());
  }
  @Test
  public void doesTheGameContinueAfterNormalMove() {
    Bowl bowl1 = new Bowl();
    Players players = bowl1.getPlayers();
    bowl1.makeMove();
    assertNotEquals(0, players.getPlayerTurn());
  }
  @Test
  public void getCorrectWinner() {
    Bowl bowl1 = new Bowl();
    Players players = bowl1.getPlayers();
    Kalaha kalaha1 = bowl1.getNeighbour(6);
    emptyPlayer1Bowls(bowl1);
    assertEquals(2, players.getWinner());
  }
  @Test
  public void getDrawCorrectlyAndEndGameInOwnKalaha() {
    Bowl bowl1 = new Bowl();
    Players players = bowl1.getPlayers();
    Kalaha kalaha1 = bowl1.getNeighbour(6);
    Kalaha bowl2 = bowl1.getNeighbour(1);
    Kalaha bowl6 = bowl1.getNeighbour(5);
    Kalaha bowl7 = bowl1.getNeighbour(7);
    Kalaha bowl8 = bowl1.getNeighbour(8);
    getCloseToDraw(bowl1);
    bowl8.makeMove();
    bowl2.makeMove();
    bowl7.makeMove();
    bowl6.makeMove();
    assertEquals(24, kalaha1.getStones());
    assertEquals(0, players.getPlayerTurn());
    assertEquals(3, players.getWinner());
  }
  @Test
  public void player1WinsWithoutMovesAndGameEnds() {
    Bowl bowl1 = new Bowl();
    Players players = bowl1.getPlayers();
    Kalaha kalaha1 = bowl1.getNeighbour(6);
    Kalaha bowl2 = bowl1.getNeighbour(1);
    Kalaha bowl5 = bowl1.getNeighbour(4);
    Kalaha bowl6 = bowl1.getNeighbour(5);
    Kalaha bowl7 = bowl1.getNeighbour(7);
    Kalaha bowl8 = bowl1.getNeighbour(8);
    Kalaha bowl9 = bowl1.getNeighbour(9);
    getCloseToDraw(bowl1);
    bowl7.makeMove();
    bowl6.makeMove();
    bowl5.makeMove();
    bowl8.makeMove();
    bowl2.makeMove();
    bowl9.makeMove();
    assertEquals(25, kalaha1.getStones());
    assertEquals(0, players.getPlayerTurn());
    assertEquals(1, players.getWinner());
  }
  @Test
  public void player1GetsNewMoveWhenPlayer2FillsSomeEmptyBowls() {
    Bowl bowl1 = new Bowl();
    Players players = bowl1.getPlayers();
    Kalaha kalaha1 = bowl1.getNeighbour(6);
    Kalaha bowl2 = bowl1.getNeighbour(1);
    Kalaha bowl5 = bowl1.getNeighbour(4);
    Kalaha bowl6 = bowl1.getNeighbour(5);
    Kalaha bowl7 = bowl1.getNeighbour(7);
    Kalaha bowl8 = bowl1.getNeighbour(8);
    Kalaha bowl12 = bowl1.getNeighbour(12);
    getCloseToDraw(bowl1);
    bowl7.makeMove();
    bowl6.makeMove();
    bowl5.makeMove();
    bowl8.makeMove();
    bowl2.makeMove();
    bowl12.makeMove();
    assertEquals(25, kalaha1.getStones());
    assertEquals(1, players.getPlayerTurn());
  }

  private void emptyPlayer1Bowls(Kalaha bowl1) {
    Kalaha bowl2 = bowl1.getNeighbour(1);
    Kalaha bowl3 = bowl1.getNeighbour(2);
    Kalaha bowl4 = bowl1.getNeighbour(3);
    Kalaha bowl5 = bowl1.getNeighbour(4);
    Kalaha bowl6 = bowl1.getNeighbour(5);
    Kalaha bowl7 = bowl1.getNeighbour(7);
    bowl1.makeMove();
    bowl7.makeMove();
    bowl2.makeMove();
    bowl3.makeMove();
    bowl7.makeMove();
    bowl4.makeMove();
    bowl7.makeMove();
    bowl5.makeMove();
    bowl7.makeMove();
    bowl6.makeMove();
    bowl7.makeMove();
  }

  private void getCloseToDraw(Kalaha bowl1) {
    Kalaha bowl2 = bowl1.getNeighbour(1);
    Kalaha bowl3 = bowl1.getNeighbour(2);
    Kalaha bowl4 = bowl1.getNeighbour(3);
    Kalaha bowl6 = bowl1.getNeighbour(5);
    Kalaha bowl7 = bowl1.getNeighbour(7);
    Kalaha bowl8 = bowl1.getNeighbour(8);
    Kalaha bowl9 = bowl1.getNeighbour(9);
    Kalaha bowl12 = bowl1.getNeighbour(12);
    bowl6.makeMove();
    bowl12.makeMove();
    bowl2.makeMove();
    bowl6.makeMove();
    bowl1.makeMove();
    bowl9.makeMove();
    bowl3.makeMove();
    bowl8.makeMove();
    bowl2.makeMove();
    bowl7.makeMove();
    bowl6.makeMove();
    bowl4.makeMove();
    bowl9.makeMove();
    bowl1.makeMove();
  }
}
