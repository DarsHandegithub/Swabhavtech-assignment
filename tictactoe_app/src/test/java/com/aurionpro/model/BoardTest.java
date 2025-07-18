package com.aurionpro.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.aurionpro.model.enums.*;

public class BoardTest {

	private Board board;

	@BeforeEach
	public void init() {
		board = new Board();
	}

	@Test
	public void CreatedCellShouldBeEmpty() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				assertEquals(Mark.EMPTY, board.getCell(i, j));
	}

	@Test
	public void ShouldBeAbleToMarkCell() {
		board.markCell(1, 2, Mark.X);
		assertEquals(Mark.X, board.getCell(1, 2));

		board.markCell(2, 1, Mark.O);
		assertEquals(Mark.O, board.getCell(2, 1));
	}

	@Test
    void CannotMarkAlreadyMarkedCell() {
        board.markCell(0, 0, Mark.X);
        assertThrows(IllegalArgumentException.class, () -> board.markCell(0, 0, Mark.O));
    }
}