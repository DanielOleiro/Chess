package game;

import block.Block;
import block.Board;
import piece.Color;
import piece.Piece;
import piece.Rook;
import player.Player;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {
    private static final int BOARD_SIZE=8;
    public static void main(String[] args) {
        Player whites= new Player(Color.WHITE);
        Player blacks= new Player(Color.BLACK);
        Block[][] board= Board.initializeBoard(BOARD_SIZE);
        for(int i=0; i<8; i++){
            whites.getPieces().get(i).setCurrentBlock(board[7][i]);
            board[7][i].setCurrentPiece(whites.getPieces().get(i));
            blacks.getPieces().get(i).setCurrentBlock(board[0][i]);
            board[0][i].setCurrentPiece(blacks.getPieces().get(i));
        }
        for(int i=0; i<8; i++){
            whites.getPieces().get(i+8).setCurrentBlock(board[6][i]);
            board[6][i].setCurrentPiece(whites.getPieces().get(i+8));
            blacks.getPieces().get(i+8).setCurrentBlock(board[1][i]);
            board[1][i].setCurrentPiece(blacks.getPieces().get(i+8));
        }
        while(true){
            if(!play(board, whites, blacks)) return;
            blacks.cleanMaterial();
            if(isGameOver(board,blacks,whites)) return;
            if(!play(board, blacks, whites)) return;
            whites.cleanMaterial();
            if(isGameOver(board,whites,blacks)) return;
        }

    }
    private static boolean play(Block[][] board, Player player, Player other){
        for(int i=0; i<BOARD_SIZE; i++) {
            System.out.print(8-i+" ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j].getCurrentPiece()!=null) {
                    Piece piece = board[i][j].getCurrentPiece();
                    System.out.print(piece.getType().toString().charAt(0) + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H");
        while(true) {
            System.out.println(player.getColor().toString()+" make a move:");
            Scanner reader= new Scanner(System.in);
            if(!reader.hasNext(Pattern.compile("[A-H][1-8]->[A-H][1-8]", Pattern.CASE_INSENSITIVE))){
                String request=reader.next();
                if(request.compareToIgnoreCase("resign")==0 || request.compareToIgnoreCase("forfeit")==0
                        || request.compareToIgnoreCase("surrender")==0){
                    System.out.println(player.getColor().toString()+" has resigned!");
                    System.out.println("Congratulations "+other.getColor().toString()+"!!!");
                    return false;
                }
                if(request.compareToIgnoreCase("draw?")==0 || request.compareToIgnoreCase("tie?")==0){
                    System.out.println("Does "+other.getColor().toString()+" want to accept draw?[Yes/No]");
                    Scanner ansReader=new Scanner(System.in);
                    String ans=ansReader.next();
                    if(ans.compareToIgnoreCase("y")==0 || ans.compareToIgnoreCase("yes")==0){
                        System.out.println("Draw by Mutual Agreement!");
                        return false;
                    }
                    continue;
                }
                System.out.println("Wrong input try something like this 'A1->A2'");
                continue;
            }
            String[] move=reader.next().split("->");
            if(player.play(board, board['8'-move[0].charAt(1)][move[0].toUpperCase().charAt(0)-'A'], board['8'-move[1].charAt(1)][move[1].toUpperCase().charAt(0)-'A']))
                break;
            System.out.println("Incorrect move.");
        }
        return true;
    }

    private static boolean isGameOver(Block[][] board, Player player, Player other){
        int checkmate=player.isCheckmate(board);
        if(checkmate==1){
            System.out.println("The "+player.getColor().toString()+" has been Checkmated!");
            System.out.println("Congratulations "+other.getColor().toString()+"!!!");
            return true;
        }
        if(checkmate==0) return false;
        if(player.isStalemate(board)){
            System.out.println("Draw by Stalemate!");
            return true;
        }
        return false;
    }
}
