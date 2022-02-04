package mancala.domain;

public class MancalaImpl implements Mancala {
    private Bowl bowl1;
    public MancalaImpl() {
        bowl1 = new Bowl();
    }

    @Override
    public boolean isPlayersTurn(int player) {
        Players players = bowl1.getPlayers();
        return player == players.getPlayerTurn();
    }

    @Override
	public void playPit(int index) {
        // Implement playing a pit.
        bowl1.getNeighbour(index).makeMove();
    }
	
	@Override
	public int getStonesForPit(int index) {
        // Make a sane implementation.
        return bowl1.getNeighbour(index).getStones();
    }

	@Override
	public boolean isEndOfGame() {
        Players players = bowl1.getPlayers();
        return players.getPlayerTurn() == 0;
    }

	@Override
	public int getWinner() {
        Players players = bowl1.getPlayers();
        return players.getWinner();
    }
}