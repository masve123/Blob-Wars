package inf101.sem2.terminal;

import java.util.ArrayList;
import java.util.Scanner;

import inf101.sem2.game.Game;
import inf101.sem2.game.games.ConnectFour;
import inf101.sem2.game.games.Othello;
import inf101.sem2.game.games.TicTacToe;
import inf101.sem2.player.Player;
import inf101.sem2.player.ai.DumbPlayer;
import inf101.sem2.player.human.ConsolePlayer;

public class TerminalMenu {

	static Scanner sc = new Scanner(System.in);
	
	public static Game<?> selectGame(ArrayList<Player> players) {
		System.out.println("Which game do you wish to play?");
		System.out.println("Press 1 for TicTacToe, 2 for Connect 4 and 3 for Othello");
		int choice = TerminalInput.readInt(sc);
		Game<?> game;
		switch (choice) {
			case 1:
				game = new TicTacToe(new TerminalGraphics(), players.get(0), players.get(1));
				break;
			case 2:
				game = new ConnectFour(new TerminalGraphics(), players.get(0), players.get(1));
				break;
			case 3:
				game = new Othello(new TerminalGraphics(), players.get(0), players.get(1));
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
		}
		return game;
	}

	public static ArrayList<Player> getPlayers() {
		System.out.println("Player 1, what is your name?");
		ArrayList<Player> players = new ArrayList<>();
		players.add(new ConsolePlayer('X'));
		System.out.println("(1) Two players or \n(2) play against computer?");
		int multiplayerChoice = TerminalInput.readInt(new Scanner(System.in));
		switch (multiplayerChoice) {
			case 1:
				players.add(new ConsolePlayer('O'));
				break;
			case 2:
				// players.add(new MiniMaxPlayer('O', 5));
				players.add(new DumbPlayer('O'));
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + multiplayerChoice);
		}
		return players;
	}

	public static boolean isDone() {
		System.out.println("Play again? y/n");
		String choice = TerminalInput.readString(sc);
		return !choice.startsWith("y");
	}
}