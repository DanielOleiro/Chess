package test;

import block.Block;
import block.Board;
import org.junit.jupiter.api.Test;
import piece.*;
import player.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGameOver {
    private int BOARD_LENGTH=8;
    private Block[][] board= Board.initializeBoard(BOARD_LENGTH);
    Player whites=new Player(Color.WHITE);
    @Test
    public void testCheckmateForCheckmate(){
        whites.getPieces().forEach(p->{
            if(p.getType()==Type.KING) {
                p.setCurrentBlock(board[0][0]);
                board[0][0].setCurrentPiece(p);
            }
        });
        whites.cleanMaterial();
        Piece queen=new Queen(Color.BLACK);
        Piece king=new Queen(Color.BLACK);
        queen.setCurrentBlock(board[1][1]);
        board[1][1].setCurrentPiece(queen);
        king.setCurrentBlock(board[2][2]);
        board[2][2].setCurrentPiece(king);
        assertEquals(whites.isCheckmate(board), 1);
    }
    @Test
    public void testCheckmateForCheck(){
        whites.getPieces().forEach(p->{
            if(p.getType()==Type.KING) {
                p.setCurrentBlock(board[0][0]);
                board[0][0].setCurrentPiece(p);
            }
        });
        whites.cleanMaterial();
        Piece queen=new Queen(Color.BLACK);
        queen.setCurrentBlock(board[1][1]);
        board[1][1].setCurrentPiece(queen);
        assertEquals(whites.isCheckmate(board), 0);
    }
    @Test
    public void testCheckmateForNotCheck(){
        whites.getPieces().forEach(p->{
            if(p.getType()==Type.KING) {
                p.setCurrentBlock(board[0][0]);
                board[0][0].setCurrentPiece(p);
            }
        });
        whites.cleanMaterial();
        Piece queen=new Queen(Color.BLACK);
        queen.setCurrentBlock(board[1][2]);
        board[1][2].setCurrentPiece(queen);
        assertEquals(whites.isCheckmate(board), -1);
    }

    @Test
    public void testStalemate(){
        whites.getPieces().forEach(p->{
            if(p.getType()==Type.KING) {
                p.setCurrentBlock(board[0][0]);
                board[0][0].setCurrentPiece(p);
            }
        });
        whites.cleanMaterial();
        Piece queen=new Queen(Color.BLACK);
        queen.setCurrentBlock(board[1][2]);
        board[1][2].setCurrentPiece(queen);
        assertTrue(whites.isStalemate(board));
    }
    @Test
    public void testStalemateWith2Pieces(){
        whites.getPieces().forEach(p->{
            if(p.getType()==Type.KING) {
                p.setCurrentBlock(board[0][0]);
                board[0][0].setCurrentPiece(p);
            }
        });
        whites.cleanMaterial();
        Piece whitePawn=new Pawn(Color.WHITE);
        whitePawn.setCurrentBlock(board[2][2]);
        board[2][2].setCurrentPiece(whitePawn);
        Piece queen=new Queen(Color.BLACK);
        queen.setCurrentBlock(board[1][2]);
        board[1][2].setCurrentPiece(queen);
        assertTrue(whites.isStalemate(board));
    }

}
