package inf101.sem2.terminal;

import static inf101.sem2.terminal.TerminalInput.readInt;

import java.util.Scanner;

import inf101.grid.BlobWarsLocations;
import inf101.grid.Location;
import inf101.sem2.GUI.Input;
import inf101.sem2.player.human.ConsolePlayer;

public class ConsolePlayerInput extends Input {

	/**
	 * The Scanner used to read from the terminal.
	 * We use one single static Scanner for all players
	 * rather than starting a new Scanner as all ConsolePlayers
	 * on the same computer will be typing on the same keyboard.
	 * If this game were to be played over network this might need to change.
	 */
	static Scanner sc = new Scanner(System.in);
	ConsolePlayer player;
	
	public ConsolePlayerInput() {
		super("Terminal input");
	}
	
	public void setPlayer(ConsolePlayer player) {
		this.player = player;
	}
	
	@Override
	public Integer getColumn() {
		System.out.println("Player " + player + " type [Col] to make a move.");
		return readInt(sc);
	}
	
	@Override
	public Location getLocation() {		
		return getLocation("to make a move");
	}

	public Location getLocation(String message) {		
		System.out.println("Player " + player + " type [Row] [Col] "+ message + ".");
		int row = readInt(sc);
		int col = readInt(sc);
		return new Location(row, col);
	}

	@Override 
	public BlobWarsLocations getMove() {
		System.out.println("Player " + player + " type [Row] [Col] from first location, then type [Row] [Col] to second location to make a move.");
		int fRow = readInt(sc);
		int fCol = readInt(sc);
		int sRow = readInt(sc);
		int sCol = readInt(sc);
		Location loc1 = new Location(fRow, fCol);
		Location loc2 = new Location(sRow, sCol);
		return new BlobWarsLocations(loc1, loc2);
	}
}
