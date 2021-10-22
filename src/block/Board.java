package block;

public class Board {
    public static Block[][] initializeBoard(int length){
        Block[][] board=new Block[length][length];
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                board[i][j]=new Block(i,j,null);
            }
        }
        return board;
    }
}
