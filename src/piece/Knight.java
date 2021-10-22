package piece;

import block.Block;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Color c) {
        super(c);
        this.type=Type.KNIGHT;
    }

    @Override
    public List<Block> drawPath(Block[][] board) {
        List<Block> paths= new ArrayList<>();
        Block currBlock=this.getCurrentBlock();
        drawJump(currBlock,paths,1,2,board);
        drawJump(currBlock,paths,-1,2,board);
        drawJump(currBlock,paths,1,-2,board);
        drawJump(currBlock,paths,-1,-2,board);
        drawJump(currBlock,paths,2,1,board);
        drawJump(currBlock,paths,2,-1,board);
        drawJump(currBlock,paths,-2,1,board);
        drawJump(currBlock,paths,-2,-1,board);
        return paths;
    }

    private void drawJump(Block curr, List<Block> paths, int nextLine, int nextCol, Block[][] board){
        if(curr.getLine()+nextLine<board.length && curr.getCol()+nextCol<board.length &&
            curr.getLine()+nextLine>=0 && curr.getCol()+nextCol>=0){
            if(board[curr.getLine()+nextLine][curr.getCol()+nextCol].getCurrentPiece()==null ||
                    board[curr.getLine()+nextLine][curr.getCol()+nextCol].getCurrentPiece().color!=this.color){
                paths.add(board[curr.getLine()+nextLine][curr.getCol()+nextCol]);
            }
        }

    }
}
