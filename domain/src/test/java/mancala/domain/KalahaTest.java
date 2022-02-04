package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KalahaTest {
  @Test
  public void aKalahaStartsWith0Stones() {
    Bowl bowl1 = new Bowl();
    Kalaha kalaha1 = bowl1.getNeighbour(6);
    assertEquals(0, kalaha1.getStones());
  }
  @Test
  public void aKalahaBelongsToAUser() {
    Bowl bowl1 = new Bowl();
    Kalaha kalaha1 = bowl1.getNeighbour(6);
    Kalaha kalaha2 = bowl1.getNeighbour(13);
    assertEquals(1, kalaha1.getOwner());
    assertEquals(2, kalaha2.getOwner());
  }
  @Test
  public void aKalahaHasANeighbour() {
    Bowl bowl1 = new Bowl();
    Kalaha kalaha1 = bowl1.getNeighbour(6);
    Kalaha bowl7 = kalaha1.getNeighbour(1);
    assertNotNull(bowl7);
  }
  @Test
  public void aKalahaTakesStonesFromOwner() {
    Bowl bowl1 = new Bowl();
    Kalaha kalaha1 = bowl1.getNeighbour(6);
    Kalaha bowl6 = bowl1.getNeighbour(5);
    bowl6.makeMove();
    assertEquals(1, kalaha1.getStones());
  }
  @Test
  public void aKalahaTakesNoStonesFromOpponent() {
    Bowl bowl1 = new Bowl();
    Kalaha kalaha2 = bowl1.getNeighbour(13);
    Kalaha bowl2 = bowl1.getNeighbour(1);
    Kalaha bowl3 = bowl1.getNeighbour(2);
    Kalaha bowl4 = bowl1.getNeighbour(3);
    Kalaha bowl6 = bowl1.getNeighbour(5);
    Kalaha bowl11 = bowl1.getNeighbour(11);
    Kalaha bowl12 = bowl1.getNeighbour(12);
    bowl3.makeMove();
    bowl4.makeMove();
    bowl11.makeMove();
    bowl2.makeMove();
    bowl1.makeMove();
    bowl12.makeMove();
    bowl6.makeMove();
    assertEquals(2, kalaha2.getStones());
    assertEquals(2, bowl1.getStones());
  }
  @Test
  public void aKalahaDoesntMakeAMove() {
    Bowl bowl1 = new Bowl();
    Kalaha kalaha1 = bowl1.getNeighbour(6);
    Kalaha bowl6 = bowl1.getNeighbour(5);
    Kalaha bowl7 = bowl1.getNeighbour(7);
    bowl6.makeMove();
    bowl7.makeMove();
    kalaha1.makeMove();
    assertEquals(1, kalaha1.getStones());
  }
}
