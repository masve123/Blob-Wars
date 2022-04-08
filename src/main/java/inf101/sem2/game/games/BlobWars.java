package inf101.sem2.game.games;

import java.util.ArrayList;
import java.util.List;

import inf101.grid.BlobWarsLocations;
import inf101.grid.GridDirection;
import inf101.grid.Location;
import inf101.sem2.GUI.Graphics;
import inf101.sem2.game.Game;
import inf101.sem2.game.GameBoard;
import inf101.sem2.player.Player;

public class BlobWars extends Game<BlobWarsLocations> {

    /**
     * The constructor of the TerminalMenu class in the terminal-package.
     * 
     * @param graphics
     * @param p1
     * @param p2
     */
    public BlobWars(Graphics Graphics, Player player1, Player player2) {
        super(new GameBoard(8, 8), Graphics);
        addPlayer(player1);
        addPlayer(player2);
        initializeBoard();
    }

    /**
     * The constructor of the MainMenu class in the GUI-package.
     * 
     * @param graphics
     * @param players
     */
    public BlobWars(Graphics graphics, Iterable<Player> players) {
        super(new GameBoard(8, 8), graphics, players);
        initializeBoard();
    }

    /**
     * Has to be implemented because we need pieces on the board
     * to start the game according to the rules in Blob Wars.
     */
    private void initializeBoard() {
        board.set(new Location(0, 0), getCurrentPlayer());
        players.nextPlayer();
        board.set(new Location(board.numRows() - 1, board.numColumns() - 1), getCurrentPlayer());
        players.nextPlayer();

    }

    @Override
    public Game<BlobWarsLocations> copy() {
        Game<BlobWarsLocations> newGame = new BlobWars(this.graphics, players);
        copyTo(newGame);
        return newGame;
    }

    @Override
    public void makeMove(BlobWarsLocations move) {
        if (!validMove(move))
            throw new IllegalArgumentException("Invalid move");
        // something

    }
    @Override
    public boolean validMove(BlobWarsLocations move) {
        if (!board.canPlace(move.getToLocation()))
            return false;
        if (move.getFromLocation() != getCurrentPlayer()) {
            return false;
        }
        return true;
    }

    @Override
	public boolean isWinner(Player player) {
		if (!gameOver())
			return false;
		int count = getGameBoard().countPieces(player);
		for (Player p : players) {
			if (p.equals(player))
				continue;
			if (board.countPieces(p) >= count)
				return false;
		}
		return true;
	}
    

    @Override
    public List<BlobWarsLocations> getPossibleMoves() {
        ArrayList<Location> fromLoc = new ArrayList<>();
        ArrayList<BlobWarsLocations> possiblemoves = new ArrayList<>();

        for (Location from : board.locations()) {
            if (board.get(from) != null) {
                fromLoc.add(from);

            }

        }
        for (Location from : fromLoc) {
            for (GridDirection direction : GridDirection.EIGHT_DIRECTIONS) {
                Location neighbour = from.getNeighbor(direction);
                if (board.canPlace(neighbour)) {
                    possiblemoves.add(new BlobWarsLocations(from, neighbour));

                    for (GridDirection nextdirection : GridDirection.EIGHT_DIRECTIONS) {
                        Location nextNeighbour = from.getNeighbor(nextdirection);
                        BlobWarsLocations nextLoc = new BlobWarsLocations(neighbour, nextNeighbour);
                        if (board.canPlace(nextNeighbour) && !possiblemoves.contains(nextLoc)) {
                            possiblemoves.add(nextLoc);
                        }
                    }

                }
            }

        }
        return possiblemoves;
    }

    @Override
    public String getName() {
        return "Blob Wars";
    }

    @Override
    public boolean gameOver() {
        if (getPossibleMoves().isEmpty()) {
            return true;
        }
        if ((getGameBoard().countPieces(getCurrentPlayer()) < 1))  {
            return true;
        }
        return false;
    }

    /**
     * The method that is called when the game is over.
     * This method restarts the game with the pieces
     * in place.
     */
    @Override
    public void restart() {
        super.restart();
        initializeBoard();
    }

    // things to do:
    // the flipping of pieces -> look at getFlipped() in othello.
    // Complete the makeMove() method.
    // Do the score() method. ??? similar to othello ???
    // add lines to Input class (two basically)
}
