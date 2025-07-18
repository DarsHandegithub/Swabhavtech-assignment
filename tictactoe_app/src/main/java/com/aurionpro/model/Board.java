package com.aurionpro.model;

import com.aurionpro.model.enums.*;

public class Board {

	private Mark[][] cells = new Mark[3][3];

	public Board() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				cells[i][j] = Mark.EMPTY;
	}

	public Mark getCell(int row, int col) {
		return cells[row][col];
	}

	public void markCell(int row, int col, Mark mark) {
		if (cells[row][col] != Mark.EMPTY)
			throw new IllegalArgumentException("Cell is already marked!");
		cells[row][col] = mark;
	}

	public boolean isFull() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (cells[i][j] == Mark.EMPTY)
					return false;
		return true;
	}

	public void display() {
		System.out.println("Current Board:");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(cells[i][j] + " ");
			System.out.println();
		}
	}
}
