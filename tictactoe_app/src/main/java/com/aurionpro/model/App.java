package com.aurionpro.model;

import java.util.Scanner;
import com.aurionpro.model.enums.*;
import com.aurionpro.model.enums.GameStatus;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter Player 1 name: ");
		String p1Name = sc.nextLine();
		Player p1 = new Player(p1Name, Mark.X);

		System.out.print("Enter Player 2 name: ");
		String p2Name = sc.nextLine();
		Player p2 = new Player(p2Name, Mark.O);

		Game game = new Game(p1, p2);
		Board board = new Board();
		board.display();
		while (game.getStatus() == GameStatus.Running) {
			System.out.println(game.getCurrentPlayer().getName() + "'s turn. Enter row and column (0-2): ");
			int row = sc.nextInt();
			int col = sc.nextInt();

			try {
				game.playMove(row, col);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid move: " + e.getMessage());
			}
		}

		sc.close();
	}
}
