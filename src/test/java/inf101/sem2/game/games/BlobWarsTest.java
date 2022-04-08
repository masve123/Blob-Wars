package inf101.sem2.game.games;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf101.grid.Location;
import inf101.sem2.GUI.DummyGraphics;
import inf101.sem2.game.Game;
import inf101.sem2.game.TestGame;

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
    void validMoveTest() {} // TODO: test for valid moves}

    @Test
    void testMakeMove() {} // TODO

    @Test
    void testNextPlayer() {
        game.nextPlayer();
        assertEquals(player2, game.getCurrentPlayer());
        game.nextPlayer();
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    void testGetPossibleMoves() {} // TODO


    @Test
    void testGetWinner() {} // TODO

    @Test
    void testGetGameBoard() {} // TODO

    @Test
    void testRestart() {} // TODO See othellotest
    
}
