package com.chess.engine.piece;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    private final int[] CANDIDATE_MOVE_COORDINATE= {8,16,7,9};

    public Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.PAWN,piecePosition, pieceAlliance);
    }
    @Override
    public Pawn movePiece(Move move) {
        return new Pawn(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection()*currentCandidateOffset);
            if(!BoardUtils.isValidCoordinate(candidateDestinationCoordinate)) continue;

            if(currentCandidateOffset==8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));
            }
            else if(currentCandidateOffset==16 && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack())||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())){
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection()*8);
                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                    !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));
                }
            }
            else if(currentCandidateOffset==7 &&
                    !(BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                    (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))
            ){
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
                        //TODO more here (pawn promotion capture)
                        legalMoves.add(new AttackMove(board,this,candidateDestinationCoordinate,pieceOnCandidate));
                    }
                }
            }
            else if(currentCandidateOffset==9 &&
                    !(BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                     (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))
            ){
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
                        //TODO more here (pawn promotion capture)
                        legalMoves.add(new AttackMove(board,this,candidateDestinationCoordinate,pieceOnCandidate));
                    }
                }
            }

        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }
}
