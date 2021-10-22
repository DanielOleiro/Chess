package piece;

import block.Block;

public abstract class Piece implements Move{
    protected Color color;
    protected Type type;
    protected Block currentBlock;
    public Piece(Color c){
        this.color=c;
    }

    public Color getColor() {
        return color;
    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(Block currentBlock) {
        this.currentBlock = currentBlock;
    }

    public Type getType(){return type;}

    @Override
    public boolean makeMove(Block to, Block[][] board) {
        if(drawPath(board).contains(to)){
            this.getCurrentBlock().setCurrentPiece(null);
            this.setCurrentBlock(to);
            if(to.getCurrentPiece()!=null)
                to.getCurrentPiece().setCurrentBlock(null);
            this.getCurrentBlock().setCurrentPiece(this);
            return true;
        }
        return false;
    }
}
