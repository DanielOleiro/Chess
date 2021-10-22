package test;

import block.Block;
import block.Board;
import org.junit.jupiter.api.Test;
import piece.Knight;
import piece.Color;

import static org.junit.jupiter.api.Assertions.*;

public class TestKnight {

    private int BOARD_LENGTH=8;
    private Block[][] board= Board.initializeBoard(BOARD_LENGTH);
    Knight knight= new Knight(Color.WHITE);

    @Test
    public void testAllPossiblePaths(){
        board[5][5].setCurrentPiece(knight);
        knight.setCurrentBlock(board[5][5]);
        assertEquals(8, board[5][5].getCurrentPiece().drawPath(board).size());
    }
    @Test
    public void testPossibleMove(){
        board[5][5].setCurrentPiece(knight);
        knight.setCurrentBlock(board[5][5]);
        assertTrue(board[5][5].getCurrentPiece().makeMove(board[7][6],board));
        assertNull(board[5][5].getCurrentPiece());
        assertEquals(knight, board[7][6].getCurrentPiece());
    }
    @Test
    public void testImpossibleMove(){
        board[5][5].setCurrentPiece(knight);
        knight.setCurrentBlock(board[5][5]);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[7][7],board));
        assertEquals(knight, board[5][5].getCurrentPiece());
        assertNull(board[7][7].getCurrentPiece());
    }
    @Test
    public void testMoveAgainstBlockingWhite(){
        Knight white= new Knight(Color.WHITE);
        board[5][5].setCurrentPiece(knight);
        knight.setCurrentBlock(board[5][5]);
        board[3][4].setCurrentPiece(white);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[3][4],board));
    }
    @Test
    public void testMoveAgainstBlockingBlack(){
        Knight black= new Knight(Color.BLACK);
        board[5][5].setCurrentPiece(knight);
        knight.setCurrentBlock(board[5][5]);
        board[3][4].setCurrentPiece(black);
        assertTrue(board[5][5].getCurrentPiece().makeMove(board[3][4],board));
        assertEquals(knight, board[3][4].getCurrentPiece());
        assertNull(board[5][5].getCurrentPiece());
    }

}
