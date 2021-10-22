package test;

import block.Block;
import block.Board;
import org.junit.jupiter.api.Test;
import piece.Bishop;
import piece.Color;

import static org.junit.jupiter.api.Assertions.*;

public class TestBishop {
    private int BOARD_LENGTH=8;
    private Block[][] board= Board.initializeBoard(BOARD_LENGTH);
    Bishop bishop= new Bishop(Color.WHITE);

    @Test
    public void testAllPossiblePaths(){
        board[5][5].setCurrentPiece(bishop);
        bishop.setCurrentBlock(board[5][5]);
        assertEquals(11, board[5][5].getCurrentPiece().drawPath(board).size());
    }
    @Test
    public void testPossibleMove(){
        board[5][5].setCurrentPiece(bishop);
        bishop.setCurrentBlock(board[5][5]);
        assertTrue(board[5][5].getCurrentPiece().makeMove(board[2][2],board));
        assertNull(board[5][5].getCurrentPiece());
        assertEquals(bishop, board[2][2].getCurrentPiece());
    }
    @Test
    public void testImpossibleMove(){
        board[5][5].setCurrentPiece(bishop);
        bishop.setCurrentBlock(board[5][5]);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[0][5],board));
        assertEquals(bishop, board[5][5].getCurrentPiece());
        assertNull(board[0][5].getCurrentPiece());
    }
    @Test
    public void testMoveAgainstBlockingWhite(){
        Bishop white= new Bishop(Color.WHITE);
        board[5][5].setCurrentPiece(bishop);
        bishop.setCurrentBlock(board[5][5]);
        board[2][2].setCurrentPiece(white);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[1][1],board));
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[2][2],board));
    }
    @Test
    public void testMoveAgainstBlockingBlack(){
        Bishop black= new Bishop(Color.BLACK);
        board[5][5].setCurrentPiece(bishop);
        bishop.setCurrentBlock(board[5][5]);
        board[2][2].setCurrentPiece(black);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[1][1],board));
        assertTrue(board[5][5].getCurrentPiece().makeMove(board[2][2],board));
        assertEquals(bishop, board[2][2].getCurrentPiece());
        assertNull(board[5][5].getCurrentPiece());
    }
}
