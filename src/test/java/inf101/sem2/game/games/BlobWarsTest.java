package inf101.sem2.game.games;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf101.grid.BlobWarsLocations;
import inf101.grid.Location;
import inf101.sem2.GUI.DummyGraphics;
import inf101.sem2.game.Game;
import inf101.sem2.game.GameBoard;
import inf101.sem2.game.TestGame;
import inf101.sem2.player.Player;

public class BlobWarsTest extends TestGame {

    BlobWars game;

    @BeforeEach
    protected void init() {
        super.init();
        game = new BlobWars(new DummyGraphics(), player1, player2);
        assertEquals(player1,game.getCurrentPlayer());
    }

	@Override
	public Game<?> getGame() {
		return game;
	}

    @Test
    void canConstruct() {
        assertEquals("Blob Wars", game.getName());
        BlobWars game2 = new BlobWars(game.getGraphics(), game.players());
        assertEquals("Blob Wars", game2.getName());
        boolean lik = game.getGameBoard().equals(game2.getGameBoard());
        assertTrue(lik);
    }

    @Test
    void startsWith2pieces() {
        assertEquals(player1,game.getGameBoard().get(new Location(0, 0)));
        assertEquals(player2,game.getGameBoard().get(new Location(7, 7)));
        
    }
    
    @Test 
    void validMoveTest() {
        assertFalse(game.validMove(new BlobWarsLocations(new Location(3, 5), new Location(0, 0))));
        assertTrue(game.validMove(new BlobWarsLocations(new Location(0, 0), new Location(1, 1))));
        assertTrue(game.validMove(new BlobWarsLocations(new Location(0, 0), new Location(0, 2))));
        assertFalse(game.validMove(new BlobWarsLocations(new Location(0, 0), new Location(0, 3))));
        assertFalse(game.validMove(new BlobWarsLocations(new Location(0, 0), new Location(0, -2)))); 
    }  

    @Test
    void testMakeMove() {
        BlobWarsLocations move = new BlobWarsLocations(new Location(0, 0), new Location(1, 1));
        game.makeMove(move);
        game.nextPlayer();
        assertEquals(player1, game.getGameBoard().get(move.getToLocation()));
        assertEquals(player2, game.getCurrentPlayer());


    } 

    @Test
    void testFlipPiece() {
        GameBoard Board = new GameBoard(8, 8);
        Board.set(new Location(0, 0), player1);
        Board.set(new Location(0, 2), player2);
        game = new BlobWars(Board, game.getGraphics(), player1, player2);
        game.makeMove(new BlobWarsLocations(new Location(0, 0), new Location(0, 1)));
        assertEquals(player1, game.getGameBoard().get(new Location(0, 1)));
        assertEquals(player1, game.getGameBoard().get(new Location(0, 0)));
        assertEquals(player1, game.getGameBoard().get(new Location(0, 2)));
    }

    @Test
    void testNextPlayer() {
        game.nextPlayer();
        assertEquals(player2, game.getCurrentPlayer());
        game.nextPlayer();
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    void testGetPossibleMoves() {
        GameBoard Board = new GameBoard(7, 7);
        Board.set(new Location(0, 0), player1);
        game = new BlobWars(Board, game.getGraphics(), player1, player2);
        System.out.println(game.getPossibleMoves());
        assertEquals(8, game.getPossibleMoves().size());
    } 

    @Test
	void testCopy() {
		TestGame.testCopy(game);
		assertTrue(Arrays.equals("test".getBytes(),"test".getBytes()));
	}
	

    @Test
    void testRestart() {
        game.makeMove(new BlobWarsLocations(new Location(0, 0), new Location(0,1)));
        assertEquals(player1,getPlayer(0,1));
    } 

    Player getPlayer(int row, int col) {
		return game.getGameBoard().get(new Location(0,1));
	}
    
}
