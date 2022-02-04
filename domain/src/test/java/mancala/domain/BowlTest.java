package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BowlTest {
  @Test
  public void aBowlStartsWith4Stones() {
    Bowl bowl1 = new Bowl();
    assertEquals(4, bowl1.getStones());
  }
  @Test
  public void aBowlBelongsToAUser() {
    Bowl bowl1 = new Bowl();
    Kalaha bowl7 = bowl1.getNeighbour(7);
    assertEquals(1, bowl1.getOwner());
    assertEquals(2, bowl7.getOwner());
  }
  @Test
  public void aBowlHasANeighbour() {
    Bowl bowl1 = new Bowl();
    Kalaha bowl2 = bowl1.getNeighbour(1);
    assertNotNull(bowl2);
  }
  @Test
  public void neighbourZeroReturnsItself() {
    Bowl bowl1 = new Bowl();
    Kalaha bowl2 = bowl1.getNeighbour(0);
    assertEquals(bowl1, bowl2);
  }
  @Test
  public void allBowlsFormALoop() {
    Bowl bowl1 = new Bowl();
    Kalaha bowl2 = bowl1.getNeighbour(14);
    assertEquals(bowl1, bowl2);
  }
  @Test
  public void stonesGetDistributedCorrectlyByMakeMove() {
    Bowl bowl1 = new Bowl();
    Kalaha bowl2 = bowl1.getNeighbour(1);
    Kalaha bowl3 = bowl1.getNeighbour(2);
    Kalaha bowl4 = bowl1.getNeighbour(3);
    Kalaha bowl5 = bowl1.getNeighbour(4);
    Kalaha bowl6 = bowl1.getNeighbour(5);
    bowl1.makeMove();
    assertEquals(0, bowl1.getStones());
    assertEquals(5, bowl2.getStones());
    assertEquals(5, bowl3.getStones());
    assertEquals(5, bowl4.getStones());
    assertEquals(5, bowl5.getStones());
    assertEquals(4, bowl6.getStones());
  }
  @Test
  public void dontMakeMoveWhenOpponentsBowl() {
    Bowl bowl1 = new Bowl();
    Kalaha bowl7 = bowl1.getNeighbour(7);
    bowl7.makeMove();
    assertEquals(4, bowl7.getStones());
  }
  @Test
  public void dontMakeMoveWhenBowlIsEmpty() {
    Bowl bowl1 = new Bowl();
    Kalaha bowl2 = bowl1.getNeighbour(1);
    Kalaha bowl7 = bowl1.getNeighbour(7);
    bowl1.makeMove();
    bowl7.makeMove();
    bowl1.makeMove();
    assertEquals(0, bowl1.getStones());
    assertEquals(5, bowl2.getStones());
  }
  @Test
  public void stonesGetStolenCorrectly() {
    Bowl bowl1 = new Bowl();
    Kalaha kalaha1 = bowl1.getNeighbour(6);
    Kalaha bowl6 = bowl1.getNeighbour(5);
    Kalaha bowl7 = bowl1.getNeighbour(7);
    Kalaha bowl12 = bowl1.getNeighbour(12);
    bowl6.makeMove();
    bowl12.makeMove();
    bowl1.makeMove();
    assertEquals(7, kalaha1.getStones());
    assertEquals(0, bowl6.getStones());
    assertEquals(0, bowl7.getStones());
  }
  @Test
  public void stonesDontGetStolenAtEmptyOpponentsBowl() {
    Bowl bowl1 = new Bowl();
    Kalaha kalaha1 = bowl1.getNeighbour(6);
    Kalaha bowl3 = bowl1.getNeighbour(2);
    Kalaha bowl7 = bowl1.getNeighbour(7);
    Kalaha bowl8 = bowl1.getNeighbour(8);
    bowl1.makeMove();
    bowl7.makeMove();
    bowl3.makeMove();
    assertEquals(1, kalaha1.getStones());
    assertEquals(1, bowl7.getStones());
    assertEquals(5, bowl8.getStones());
  }
  @Test
  public void makeMoveDoesNotWorkAfterGameEnds() {
    Bowl bowl1 = new Bowl();
    Players players = bowl1.getPlayers();
    Kalaha bowl11 = bowl1.getNeighbour(11);
    emptyPlayer1Bowls(bowl1);
    bowl11.makeMove();
    assertEquals(7, bowl11.getStones());
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
}
