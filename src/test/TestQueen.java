package test;

import block.Block;
import block.Board;
import org.junit.jupiter.api.Test;
import piece.Color;
import piece.Queen;

import static org.junit.jupiter.api.Assertions.*;

public class TestQueen {
    private int BOARD_LENGTH=8;
    private Block[][] board= Board.initializeBoard(BOARD_LENGTH);
    Queen queen= new Queen(Color.WHITE);

    @Test
    public void testAllPossiblePaths(){
        board[5][5].setCurrentPiece(queen);
        queen.setCurrentBlock(board[5][5]);
        assertEquals(14+11, board[5][5].getCurrentPiece().drawPath(board).size());
    }
    @Test
    public void testPossibleMove(){
        board[5][5].setCurrentPiece(queen);
        queen.setCurrentBlock(board[5][5]);
        assertTrue(board[5][5].getCurrentPiece().makeMove(board[0][5],board));
        assertNull(board[5][5].getCurrentPiece());
        assertEquals(queen, board[0][5].getCurrentPiece());
    }
    @Test
    public void testImpossibleMove(){
        board[5][5].setCurrentPiece(queen);
        queen.setCurrentBlock(board[5][5]);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[0][6],board));
        assertEquals(queen, board[5][5].getCurrentPiece());
        assertNull(board[0][6].getCurrentPiece());
    }
    @Test
    public void testMoveAgainstBlockingWhite(){
        Queen white= new Queen(Color.WHITE);
        board[5][5].setCurrentPiece(queen);
        queen.setCurrentBlock(board[5][5]);
        board[5][3].setCurrentPiece(white);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[5][2],board));
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[5][3],board));
    }
    @Test
    public void testMoveAgainstBlockingBlack(){
        Queen black= new Queen(Color.BLACK);
        board[5][5].setCurrentPiece(queen);
        queen.setCurrentBlock(board[5][5]);
        board[5][3].setCurrentPiece(black);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[5][2],board));
        assertTrue(board[5][5].getCurrentPiece().makeMove(board[5][3],board));
        assertEquals(queen, board[5][3].getCurrentPiece());
        assertNull(board[5][5].getCurrentPiece());
    }
}
