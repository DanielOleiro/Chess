package piece;

import block.Block;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Color c) {
        super(c);
        this.type=Type.KING;
    }

    @Override
    public List<Block> drawPath(Block[][] board) {
        List<Block> paths= new ArrayList<>();
        Block currBlock=this.getCurrentBlock();
        drawStep(currBlock, paths, 1, 1, board);
        drawStep(currBlock, paths, 0, 1, board);
        drawStep(currBlock, paths, -1, 1, board);
        drawStep(currBlock, paths, 1, 0, board);
        drawStep(currBlock, paths, -1, 0, board);
        drawStep(currBlock, paths, 0, -1, board);
        drawStep(currBlock, paths, -1, -1, board);
        drawStep(currBlock, paths, 1, -1, board);
        return paths;
    }

    private void drawStep(Block curr, List<Block> paths, int nextLine, int nextCol, Block[][] board){
        if(curr.getLine()+nextLine<board.length && curr.getCol()+nextCol<board.length &&
                curr.getLine()+nextLine>=0 && curr.getCol()+nextCol>=0){
            if(board[curr.getLine()+nextLine][curr.getCol()+nextCol].getCurrentPiece()==null ||
                    board[curr.getLine()+nextLine][curr.getCol()+nextCol].getCurrentPiece().color!=this.color){
                if(isChecked(new ArrayList<Block>(), board, board[curr.getLine()+nextLine][curr.getCol()+nextCol])==0)
                    paths.add(board[curr.getLine()+nextLine][curr.getCol()+nextCol]);
            }
        }
    }

    public  int isChecked(List<Block>attackingBlocks, Block[][] board, Block currBlock){
        int attackers=0;
        attackers+=checkVertical(currBlock, attackingBlocks, 1, board);
        attackers+=checkVertical(currBlock, attackingBlocks, -1, board);
        attackers+=checkHorizontal(currBlock, attackingBlocks, 1, board);
        attackers+=checkHorizontal(currBlock, attackingBlocks, -1, board);
        attackers+=checkDiagonal(currBlock, attackingBlocks, 1,1, board);
        attackers+=checkDiagonal(currBlock, attackingBlocks, -1,1, board);
        attackers+=checkDiagonal(currBlock, attackingBlocks, 1,-1, board);
        attackers+=checkDiagonal(currBlock, attackingBlocks, -1,-1, board);
        attackers+=checkJump(currBlock,attackingBlocks,1,2,board);
        attackers+=checkJump(currBlock,attackingBlocks,-1,2,board);
        attackers+=checkJump(currBlock,attackingBlocks,1,-2,board);
        attackers+=checkJump(currBlock,attackingBlocks,-1,-2,board);
        attackers+=checkJump(currBlock,attackingBlocks,2,1,board);
        attackers+=checkJump(currBlock,attackingBlocks,2,-1,board);
        attackers+=checkJump(currBlock,attackingBlocks,-2,1,board);
        attackers+=checkJump(currBlock,attackingBlocks,-2,-1,board);
        return attackers;
    }

    private int checkVertical(Block curr, List<Block> attackingBlocks, int next, Block[][] board) {
        List<Block> temp=new ArrayList<>();
        int plus=next;
        while(board.length>curr.getLine()+next && 0<=curr.getLine()+next){
            if(board[curr.getLine()+next][curr.getCol()].getCurrentPiece()!=null){
                if(board[curr.getLine()+next][curr.getCol()].getCurrentPiece().color!=this.color){
                    Type type=board[curr.getLine()+next][curr.getCol()].getCurrentPiece().type;
                    if(type==Type.QUEEN || type==Type.ROOK) {
                        temp.add(board[curr.getLine() + next][curr.getCol()]);
                        attackingBlocks.addAll(temp);
                        return 1;
                    }
                }
                return 0;
            }
            temp.add(board[curr.getLine()+next][curr.getCol()]);
            next+=plus;
        }
        return 0;
    }

    private int checkHorizontal(Block curr, List<Block> attackingBlocks, int next, Block[][] board) {
        List<Block> temp=new ArrayList<>();
        int plus=next;
        while(board[0].length>curr.getCol()+next && 0<=curr.getCol()+next){
            if(board[curr.getLine()][curr.getCol()+next].getCurrentPiece()!=null){
                if(board[curr.getLine()][curr.getCol()+next].getCurrentPiece().color!=this.color){
                    Type type=board[curr.getLine()][curr.getCol()+next].getCurrentPiece().type;
                    if(type==Type.QUEEN || type==Type.ROOK) {
                        temp.add(board[curr.getLine()][curr.getCol()+ next]);
                        attackingBlocks.addAll(temp);
                        return 1;
                    }
                }
                return 0;
            }
            temp.add(board[curr.getLine()][curr.getCol()+next]);
            next+=plus;
        }
        return 0;
    }

    private int checkDiagonal(Block curr, List<Block> attackingBlocks, int nextLine, int nextCol, Block[][] board){
        List<Block> temp=new ArrayList<>();
        int plusLine=nextLine;
        int plusCol=nextCol;
        while(board[0].length>curr.getCol()+nextCol && 0<=curr.getCol()+nextCol &&
                board.length>curr.getLine()+nextLine && 0<=curr.getLine()+nextLine){
            if(board[curr.getLine()+nextLine][curr.getCol()+nextCol].getCurrentPiece()!=null){
                if(board[curr.getLine()+nextLine][curr.getCol()+nextCol].getCurrentPiece().color!=this.color){
                    Type type=board[curr.getLine()+nextLine][curr.getCol()+nextCol].getCurrentPiece().type;
                    if(type==Type.QUEEN || type==Type.BISHOP || type==Type.PAWN) {
                        temp.add(board[curr.getLine()+nextLine][curr.getCol()+nextCol]);
                        attackingBlocks.addAll(temp);
                        return 1;
                    }
                }
                return 0;
            }
            temp.add(board[curr.getLine()+nextLine][curr.getCol()+nextCol]);
            nextLine+=plusLine;
            nextCol+=plusCol;
        }
        return 0;
    }

    private int checkJump(Block curr, List<Block> attackingBlocks, int nextLine, int nextCol, Block[][] board){
        if(curr.getLine()+nextLine<board.length && curr.getCol()+nextCol<board.length &&
                curr.getLine()+nextLine>=0 && curr.getCol()+nextCol>=0){
            if(board[curr.getLine()+nextLine][curr.getCol()+nextCol].getCurrentPiece()!=null &&
                    board[curr.getLine()+nextLine][curr.getCol()+nextCol].getCurrentPiece().color!=this.color){
                if(board[curr.getLine()+nextLine][curr.getCol()+nextCol].getCurrentPiece().type==Type.KNIGHT) {
                    attackingBlocks.add(board[curr.getLine() + nextLine][curr.getCol() + nextCol]);
                    return 1;
                }
            }
        }
        return 0;
    }
}
