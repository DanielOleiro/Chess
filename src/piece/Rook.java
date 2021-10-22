package piece;

import block.Block;

import java.util.ArrayList;
import java.util.List;


public class Rook extends Piece{

    public Rook(Color c) {
        super(c);
        this.type=Type.ROOK;
    }

    @Override
    public List<Block> drawPath(Block[][] board) {
        List<Block> paths= new ArrayList<>();
        Block currBlock=this.getCurrentBlock();
        drawHorizontal(currBlock, paths, 1, board);
        drawHorizontal(currBlock, paths, -1, board);
        drawVertical(currBlock, paths, 1, board);
        drawVertical(currBlock, paths, -1, board);
        return paths;
    }

    private void drawHorizontal(Block curr, List<Block> paths, int next, Block[][] board) {
        int plus=next;
        while(board[0].length>curr.getCol()+next && 0<=curr.getCol()+next){
            if(board[curr.getLine()][curr.getCol()+next].getCurrentPiece()!=null){
                if(board[curr.getLine()][curr.getCol()+next].getCurrentPiece().color!=this.color){
                    paths.add(board[curr.getLine()][curr.getCol()+next]);
                }
                break;
            }
            paths.add(board[curr.getLine()][curr.getCol()+next]);
            next+=plus;
        }
    }

    private void drawVertical(Block curr, List<Block> paths, int next, Block[][] board) {
        int plus=next;
        while(board.length>curr.getLine()+next && 0<=curr.getLine()+next){
            if(board[curr.getLine()+next][curr.getCol()].getCurrentPiece()!=null){
                if(board[curr.getLine()+next][curr.getCol()].getCurrentPiece().color!=this.color){
                    paths.add(board[curr.getLine()+next][curr.getCol()]);
                }
                break;
            }
            paths.add(board[curr.getLine()+next][curr.getCol()]);
            next+=plus;
        }
    }


}
