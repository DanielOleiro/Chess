package piece;

import block.Block;
import player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pawn extends Piece {
    private boolean firstMove;
    public Pawn(Color c) {
        super(c);
        this.type=Type.PAWN;
        firstMove=true;
    }

    @Override
    public boolean makeMove(Block to, Block[][] board) {
        if(drawPath(board).contains(to)){
            firstMove=false;
            this.getCurrentBlock().setCurrentPiece(null);
            this.setCurrentBlock(to);
            if(to.getCurrentPiece()!=null)
                to.getCurrentPiece().setCurrentBlock(null);
            this.getCurrentBlock().setCurrentPiece(this);
            return true;
        }
        return false;
    }

    @Override
    public List<Block> drawPath(Block[][] board) {
        List<Block> paths= new ArrayList<>();
        Block currBlock=this.getCurrentBlock();
        if(this.color==Color.WHITE) {
            if (board[currBlock.getLine() - 1][currBlock.getCol()].getCurrentPiece() == null)
                paths.add(board[currBlock.getLine() - 1][currBlock.getCol()]);
            if (firstMove && board[currBlock.getLine() - 2][currBlock.getCol()].getCurrentPiece() == null) {
                paths.add(board[currBlock.getLine() - 2][currBlock.getCol()]);
            }
            if (currBlock.getCol() + 1 < 8 && board[currBlock.getLine() - 1][currBlock.getCol() + 1].getCurrentPiece() != null
                    && board[currBlock.getLine() - 1][currBlock.getCol() + 1].getCurrentPiece().color != this.color)
                paths.add(board[currBlock.getLine() - 1][currBlock.getCol() + 1]);
            if (currBlock.getCol() - 1 >= 0 && board[currBlock.getLine() - 1][currBlock.getCol() - 1].getCurrentPiece() != null
                    && board[currBlock.getLine() - 1][currBlock.getCol() - 1].getCurrentPiece().color != this.color)
                paths.add(board[currBlock.getLine() - 1][currBlock.getCol() - 1]);
        }else{
            if (board[currBlock.getLine() + 1][currBlock.getCol()].getCurrentPiece() == null)
                paths.add(board[currBlock.getLine() + 1][currBlock.getCol()]);
            if (firstMove && board[currBlock.getLine() + 2][currBlock.getCol()].getCurrentPiece() == null) {
                paths.add(board[currBlock.getLine() + 2][currBlock.getCol()]);
            }
            if (currBlock.getCol() + 1 < 8 && board[currBlock.getLine() + 1][currBlock.getCol() + 1].getCurrentPiece() != null
                    && board[currBlock.getLine() + 1][currBlock.getCol() + 1].getCurrentPiece().color != this.color)
                paths.add(board[currBlock.getLine() + 1][currBlock.getCol() + 1]);
            if (currBlock.getCol() - 1 >= 0 && board[currBlock.getLine() + 1][currBlock.getCol() - 1].getCurrentPiece() != null
                    && board[currBlock.getLine() + 1][currBlock.getCol() - 1].getCurrentPiece().color != this.color)
                paths.add(board[currBlock.getLine() + 1][currBlock.getCol() - 1]);
        }
        System.out.println(paths.size());
        return paths;
    }
}
