package test;

import block.Block;
import block.Board;
import org.junit.jupiter.api.Test;
import piece.Color;
import piece.Pawn;
import piece.Queen;

import static org.junit.jupiter.api.Assertions.*;

public class TestPawn {
    private int BOARD_LENGTH=8;
    private Block[][] board= Board.initializeBoard(BOARD_LENGTH);
    Pawn pawn= new Pawn(Color.WHITE);

    @Test
    public void testAllPossiblePaths(){
        board[6][2].setCurrentPiece(pawn);
        pawn.setCurrentBlock(board[6][2]);
        assertEquals(2, board[6][2].getCurrentPiece().drawPath(board).size());
    }
    @Test
    public void testPossibleMove(){
        board[6][2].setCurrentPiece(pawn);
        pawn.setCurrentBlock(board[6][2]);
        assertTrue(board[6][2].getCurrentPiece().makeMove(board[4][2],board));
        assertNull(board[6][2].getCurrentPiece());
        assertEquals(pawn, board[4][2].getCurrentPiece());
    }
    @Test
    public void testImpossibleMove(){
        board[6][2].setCurrentPiece(pawn);
        pawn.setCurrentBlock(board[6][2]);
        board[6][2].getCurrentPiece().makeMove(board[4][2],board);
        assertFalse(board[4][2].getCurrentPiece().makeMove(board[2][2],board));
        assertEquals(pawn, board[4][2].getCurrentPiece());
        assertNull(board[2][2].getCurrentPiece());
    }
    @Test
    public void testMoveAgainstBlockingWhite(){
        Pawn white= new Pawn(Color.WHITE);
        board[6][2].setCurrentPiece(pawn);
        pawn.setCurrentBlock(board[6][2]);
        board[5][2].setCurrentPiece(white);
        board[5][3].setCurrentPiece(white);
        assertFalse(board[6][2].getCurrentPiece().makeMove(board[5][2],board));
        assertFalse(board[6][2].getCurrentPiece().makeMove(board[5][3],board));
    }
    @Test
    public void testMoveAgainstBlockingBlack(){
        Pawn black= new Pawn(Color.BLACK);
        board[6][2].setCurrentPiece(pawn);
        pawn.setCurrentBlock(board[6][2]);
        board[5][2].setCurrentPiece(black);
        board[5][3].setCurrentPiece(black);
        assertFalse(board[6][2].getCurrentPiece().makeMove(board[5][2],board));
        assertTrue(board[6][2].getCurrentPiece().makeMove(board[5][3],board));
        assertEquals(pawn, board[5][3].getCurrentPiece());
        assertNull(board[6][2].getCurrentPiece());
    }
    @Test
    public void testPromotion(){
        board[3][2].setCurrentPiece(pawn);
        pawn.setCurrentBlock(board[3][2]);
        board[3][2].getCurrentPiece().makeMove(board[1][2],board);
        assertTrue(board[1][2].getCurrentPiece().makeMove(board[0][2],board));
        assertNotEquals(pawn.getType(),board[0][2].getCurrentPiece().getType());
        assertEquals(new Queen(pawn.getColor()).getType(),board[0][2].getCurrentPiece().getType());
        assertNull(board[1][2].getCurrentPiece());
    }
}
