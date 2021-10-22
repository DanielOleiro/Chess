package test;

import block.Block;
import block.Board;
import org.junit.jupiter.api.Test;
import piece.Color;
import piece.King;
import piece.Queen;

import static org.junit.jupiter.api.Assertions.*;

public class TestKing {
    private int BOARD_LENGTH=8;
    private Block[][] board= Board.initializeBoard(BOARD_LENGTH);
    King king= new King(Color.WHITE);

    @Test
    public void testAllPossiblePaths(){
        board[5][5].setCurrentPiece(king);
        king.setCurrentBlock(board[5][5]);
        assertEquals(8, board[5][5].getCurrentPiece().drawPath(board).size());
    }
    @Test
    public void testPossibleMove(){
        board[5][5].setCurrentPiece(king);
        king.setCurrentBlock(board[5][5]);
        assertTrue(board[5][5].getCurrentPiece().makeMove(board[4][5],board));
        assertNull(board[5][5].getCurrentPiece());
        assertEquals(king, board[4][5].getCurrentPiece());
    }
    @Test
    public void testImpossibleMove(){
        board[5][5].setCurrentPiece(king);
        king.setCurrentBlock(board[5][5]);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[3][5],board));
        assertEquals(king, board[5][5].getCurrentPiece());
        assertNull(board[3][5].getCurrentPiece());
    }
    @Test
    public void testMoveAgainstBlockingWhite(){
        King white= new King(Color.WHITE);
        board[5][5].setCurrentPiece(king);
        king.setCurrentBlock(board[5][5]);
        board[4][4].setCurrentPiece(white);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[4][4],board));
    }
    @Test
    public void testMoveAgainstBlockingBlack(){
        King black= new King(Color.BLACK);
        board[5][5].setCurrentPiece(king);
        king.setCurrentBlock(board[5][5]);
        board[4][4].setCurrentPiece(black);
        assertTrue(board[5][5].getCurrentPiece().makeMove(board[4][4],board));
        assertEquals(king, board[4][4].getCurrentPiece());
        assertNull(board[5][5].getCurrentPiece());
    }
    @Test
    public void testMoveAgainstAttackingBlack(){
        Queen black= new Queen(Color.BLACK);
        board[5][5].setCurrentPiece(king);
        king.setCurrentBlock(board[5][5]);
        board[3][3].setCurrentPiece(black);
        assertFalse(board[5][5].getCurrentPiece().makeMove(board[4][4],board));
        assertEquals(king, board[5][5].getCurrentPiece());
        assertNull(board[4][4].getCurrentPiece());
    }
}
