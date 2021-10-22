package piece;

import block.Block;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color c) {
        super(c);
        this.type=Type.BISHOP;
    }

    @Override
    public List<Block> drawPath(Block[][] board) {
        List<Block> paths= new ArrayList<>();
        Block currBlock=this.getCurrentBlock();
        drawDiagonal(currBlock, paths, 1,1, board);
        drawDiagonal(currBlock, paths, -1,1, board);
        drawDiagonal(currBlock, paths, 1,-1, board);
        drawDiagonal(currBlock, paths, -1,-1, board);
        return paths;
    }

    private void drawDiagonal(Block curr, List<Block> paths, int nextLine, int nextCol, Block[][] board){
        int plusLine=nextLine;
        int plusCol=nextCol;
        while(board[0].length>curr.getCol()+nextCol && 0<=curr.getCol()+nextCol &&
                board.length>curr.getLine()+nextLine && 0<=curr.getLine()+nextLine){
            if(board[curr.getLine()+nextLine][curr.getCol()+nextCol].getCurrentPiece()!=null){
                if(board[curr.getLine()+nextLine][curr.getCol()+nextCol].getCurrentPiece().color!=this.color){
                    paths.add(board[curr.getLine()+nextLine][curr.getCol()+nextCol]);
                }
                break;
            }
            paths.add(board[curr.getLine()+nextLine][curr.getCol()+nextCol]);
            nextLine+=plusLine;
            nextCol+=plusCol;
        }
    }

}
