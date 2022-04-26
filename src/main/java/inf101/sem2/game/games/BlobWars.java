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
     * This constructor is used for testing purposes.
     * 
     * @param board
     * @param graphics
     * @param player1
     * @param player2
     */
    public BlobWars(GameBoard board, Graphics graphics, Player player1, Player player2) {
        super(board, graphics);
        addPlayer(player1);
        addPlayer(player2);

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
        Location to = move.getToLocation();
        Location from = move.getFromLocation();
        if (!getPossibleLocations(from).contains(to)) {
            throw new IllegalArgumentException("Illegal move");
        }

        if (board.getNeighbourHood(from).contains(to)) {
            board.set(to, getCurrentPlayer());
            displayBoard();

        } else {
            board.movePiece(from, to);
            displayBoard();
        }
        swapPieces(to);
        displayBoard();
    }

    public List<Location> getPossibleLocations(Location loc) {
        return board.getNeighbourHood(loc, 2);
    }

    /**
     * This method is used to swap the pieces of the players.
     * <p>
     * It is similar, but not equal to the getFlipped() method
     * in the Othello class.
     * 
     * @param loc
     */

    private void swapPieces(Location loc) {
        for (Location neighbour : board.getNeighbourHood(loc)) {
            Player neighbourPiece = board.get(neighbour);
            if (neighbourPiece == null)
                continue;
            if (!neighbourPiece.equals(getCurrentPlayer())) {
                board.swap(neighbour, getCurrentPlayer());
            }
        }
    }

    @Override
    public boolean validMove(BlobWarsLocations move) {
        if (Math.max(Math.abs(move.getFromLocation().row - move.getToLocation().row),
                Math.abs(move.getFromLocation().col - move.getToLocation().col)) > 2) {
            return false;
        }
        if (!board.isOnBoard(move.getToLocation()))
            return false;
        if (!board.canPlace(move.getToLocation()))
            return false;
        if (board.get(move.getFromLocation()) != getCurrentPlayer()) {
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
            if (board.get(from) == getCurrentPlayer()) {
                fromLoc.add(from);

            }

        }
        for (Location from : fromLoc) {
            for (GridDirection direction : GridDirection.EIGHT_DIRECTIONS) {
                Location neighbour = from.getNeighbor(direction);
                BlobWarsLocations oneJump = new BlobWarsLocations(from, neighbour);
                if (board.canPlace(neighbour)) {
                    if (!possiblemoves.contains(oneJump)) {
                        possiblemoves.add(oneJump);
                    }
                }

                for (GridDirection nextdirection : GridDirection.EIGHT_DIRECTIONS) {
                    Location nextNeighbour = neighbour.getNeighbor(nextdirection);
                    BlobWarsLocations nextLoc = new BlobWarsLocations(from, nextNeighbour);
                    if (nextNeighbour.equals(from)) {
                        continue;
                    }
                    if (board.canPlace(nextNeighbour) && !possiblemoves.contains(nextLoc)) {
                        possiblemoves.add(nextLoc);
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
        if ((getGameBoard().countPieces(getCurrentPlayer()) < 1)) {
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

    @Override
    public int score(Player player) {
        int otherPiecesSum = 0;
        for (Player p : players) {
            if (player.equals(p))
                continue;
            otherPiecesSum += board.countPieces(p);
        }
        int nPlayerPieces = board.countPieces(player);
        return nPlayerPieces - otherPiecesSum;
    }

}
