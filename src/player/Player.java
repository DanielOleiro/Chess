package player;

import block.Block;
import piece.*;

import java.util.*;
import java.util.stream.Collectors;

public class Player {
    private List<Piece> pieces= new ArrayList<>();
    private King king;
    private Color color;
    public Player(Color c){
        this.color=c;
        this.king=new King(color);
        pieces.add(new Rook(color));
        pieces.add(new Knight(color));
        pieces.add(new Bishop(color));
        pieces.add(new Queen(color));
        pieces.add(king);
        pieces.add(new Bishop(color));
        pieces.add(new Knight(color));
        pieces.add(new Rook(color));
        for(int i=0; i<8; i++)
            pieces.add(new Pawn(color));
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Color getColor() { return color; }

    public boolean play(Block[][] board, Block from, Block to){
        if(from.getCurrentPiece()==null || from.getCurrentPiece().getColor()!=this.color) return false;
        if(from.getCurrentPiece()==king) return from.getCurrentPiece().makeMove(to, board);
        List<Block> attackingBlocks=new ArrayList<>();
        int attackers=king.isChecked(attackingBlocks, board, king.getCurrentBlock());
        if(attackers==0 || attackers==1 && attackingBlocks.contains(to)){
            Piece temp=from.getCurrentPiece();
            from.setCurrentPiece(null);
            int check=king.isChecked(attackingBlocks,board, king.getCurrentBlock());
            from.setCurrentPiece(temp);
            if(check==1 && !attackingBlocks.contains(to) || check>1) return false;
            if(from.getCurrentPiece().getType()==Type.PAWN &&
                    (this.color==Color.BLACK && to.getLine()==7 || this.color==Color.WHITE && to.getLine()==0) &&
                    from.getCurrentPiece().makeMove(to, board)){
                this.promotion(to);
                return true;
            }
            return from.getCurrentPiece().makeMove(to,board);
        }
        return false;
    }

    public void cleanMaterial(){
        this.pieces=this.pieces.stream().filter(p->p.getCurrentBlock()!=null).collect(Collectors.toList());
    }

    public int isCheckmate(Block[][] board){
        ArrayList<Block> attackingBlocks=new ArrayList<>();
        int nChecks=king.isChecked(attackingBlocks,board,king.getCurrentBlock());
        if(nChecks==0) return -1;
        if(king.drawPath(board).size()>0 || nChecks==1 &&
                this.pieces.stream().anyMatch(p->{
                    if(p.getType()==Type.KING) return false;
                    if(p.drawPath(board).stream().anyMatch(attackingBlocks::contains)){
                       p.getCurrentBlock().setCurrentPiece(null);
                       int nChecksWithoutP =king.isChecked(new ArrayList<Block>(), board, king.getCurrentBlock());
                       p.getCurrentBlock().setCurrentPiece(p);
                       if(nChecks==nChecksWithoutP) return true;
                    }
                    return false;
                })){
            System.out.println("Check!");
            return 0;
        }
        return 1;
    }

    public boolean isStalemate(Block[][] board){
        return this.pieces.stream().noneMatch(p->p.drawPath(board).size()>0);
    }

    private void promotion(Block pawnBlock){
        pawnBlock.getCurrentPiece().setCurrentBlock(null);
        System.out.println("Promote to which piece?[Queen/Knight/Rook/Bishop]");
        Scanner reader= new Scanner(System.in);
        String piece=reader.next();
        if(piece.compareToIgnoreCase("Q")==0 || piece.compareToIgnoreCase("Queen")==0){
            Piece queen=new Queen(this.color);
            pawnBlock.setCurrentPiece(queen);
            queen.setCurrentBlock(pawnBlock);
            pieces.add(queen);
            return;
        }
        if(piece.compareToIgnoreCase("K")==0 || piece.compareToIgnoreCase("Knight")==0){
            Piece knight=new Knight(this.color);
            pawnBlock.setCurrentPiece(knight);
            knight.setCurrentBlock(pawnBlock);
            pieces.add(knight);
            return;
        }
        if(piece.compareToIgnoreCase("R")==0 || piece.compareToIgnoreCase("Rook")==0){
            Piece rook=new Rook(this.color);
            pawnBlock.setCurrentPiece(rook);
            rook.setCurrentBlock(pawnBlock);
            pieces.add(rook);
            return;
        }
        if(piece.compareToIgnoreCase("B")==0 || piece.compareToIgnoreCase("Bishop")==0){
            Piece bishop=new Bishop(this.color);
            pawnBlock.setCurrentPiece(bishop);
            bishop.setCurrentBlock(pawnBlock);
            pieces.add(bishop);
            return;
        }
    }
}
