package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.piece.Piece;

import java.util.Collection;
import java.util.List;

public class WhitePlayer extends Player {
    public WhitePlayer(Board board,
                       Collection<Move> whiteStandardLegalMoves,
                       Collection<Move> blackStandardLegalMoves) {
        super(board,whiteStandardLegalMoves,blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

}
