package test;

import block.Block;
import block.Board;
import org.junit.jupiter.api.Test;
import piece.Color;
import piece.Rook;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestRook {
    private int BOARD_LENGTH=8;
    private Block[][] board= Board.initializeBoard(BOARD_LENGTH);
    Rook rook= new Rook(Color.WHITE);

    @Test
    public void testAllPossiblePaths(){
        board[5][5].setCurrentPiece(rook);
        rook.setCurrentBlock(board[5][5]);
        assertEquals(14, board[5][5].getCurrentPiece().drawPath(board).size());
    }
    @Test
    public void testPossibleMove(){
        board[5][5].setCurrentPiece(rook);
        rook.setCurrentBlock(board[5][5]);
        assertTrue(board[5][5].getCurrentPiece().makeMove(board[0][5],board));
        assertNull(board[5][5].getCurrentPiece());
        assertEquals(rook, board[0][5].getCurrentPiece());
    }
    @Test
    public void testImpossibleMove(){
        board[5][5].setCurrentPiece(rook);
        rook.setCurrentBlock(board[5][5]);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[0][6],board));
        assertEquals(rook, board[5][5].getCurrentPiece());
        assertNull(board[0][6].getCurrentPiece());
    }
    @Test
    public void testMoveAgainstBlockingWhite(){
        Rook white= new Rook(Color.WHITE);
        board[5][5].setCurrentPiece(rook);
        rook.setCurrentBlock(board[5][5]);
        board[5][3].setCurrentPiece(white);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[5][2],board));
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[5][3],board));
    }
    @Test
    public void testMoveAgainstBlockingBlack(){
        Rook black= new Rook(Color.BLACK);
        board[5][5].setCurrentPiece(rook);
        rook.setCurrentBlock(board[5][5]);
        board[5][3].setCurrentPiece(black);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[5][2],board));
        assertTrue(board[5][5].getCurrentPiece().makeMove(board[5][3],board));
        assertEquals(rook, board[5][3].getCurrentPiece());
        assertNull(board[5][5].getCurrentPiece());
    }
}
