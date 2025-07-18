package com.aurionpro.model;

import com.aurionpro.model.enums.*;

public class Game {
	
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameStatus status;
    private Board board;

    public Game(Player p1, Player p2) {
        board = new Board();
        player1 = p1;
        player2 = p2;
        currentPlayer = p1;
        status = GameStatus.Running;
    }
    
    public GameStatus getStatus() {
        return status;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void playMove(int row, int col) {
        board.markCell(row, col, currentPlayer.getMark());
        board.display();
        if (checkWinner()) {
            status = GameStatus.WIN;
            System.out.println(currentPlayer.getName() + " wins!");
        } else if (board.isFull()) {
            status = GameStatus.DRAW;
            System.out.println("It's a draw!");
        } else {
            switchTurn();
        }
    }

    private boolean checkWinner() {
        Mark mark = currentPlayer.getMark();
        for (int i = 0; i < 3; i++)
            if (board.getCell(i, 0) == mark && board.getCell(i, 1) == mark && board.getCell(i, 2) == mark)
                return true;
       
        for (int j = 0; j < 3; j++)
            if (board.getCell(0, j) == mark && board.getCell(1, j) == mark && board.getCell(2, j) == mark)
                return true;
        
        if (board.getCell(0, 0) == mark && board.getCell(1, 1) == mark && board.getCell(2, 2) == mark)
            return true;
        
        if (board.getCell(0, 2) == mark && board.getCell(1, 1) == mark && board.getCell(2, 0) == mark)
            return true;

        return false;
    }

    private void switchTurn() {
    	if (currentPlayer == player1) {
    	    currentPlayer = player2;
    	} else {
    	    currentPlayer = player1;
    	}
    }
}

