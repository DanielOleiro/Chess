package block;

import piece.Piece;

public class Block {
    private int line;
    private int col;
    private Piece currentPiece;

    public Block(int l, int c, Piece p){
        this.line=l;
        this.col=c;
        this.currentPiece=p;
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }
}
