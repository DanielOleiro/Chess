package piece;

import block.Block;

import java.util.List;

public interface Move {
    boolean makeMove(Block to, Block[][] board);
    List<Block> drawPath(Block[][] board);
}
