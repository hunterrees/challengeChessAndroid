package com.example.android.challengechess.States;


import com.example.android.challengechess.Model.GameModel;
import com.example.android.challengechess.Controllers.SquareController;

/**
 * Created by Brian on 16/12/27.
 */

public class PieceAlreadySelectedState extends PressState {

    public PieceAlreadySelectedState(int position, GameModel gameModel, SquareController squareController) {
        super(position, gameModel, squareController);
    }

    @Override
    public void press() {
        // Setup
        char pressedPieceColor = '#';
        if(gameModel.getBoardModel().getBoard()[converter[position]] < 0) {
            pressedPieceColor = 'b';
        } else if(gameModel.getBoardModel().getBoard()[converter[position]] > 0) {
            pressedPieceColor = 'w';
        }

        char selectedPieceColor = '-';
        if(gameModel.getBoardModel().getBoard()[gameModel.getBoardModel().getCurrentSelectedPosition()] < 0) {
            selectedPieceColor = 'b';
        } else if(gameModel.getBoardModel().getBoard()[gameModel.getBoardModel().getCurrentSelectedPosition()] > 0) {
            selectedPieceColor = 'w';
        }

        // Change board
        if(pressedPossibleMove() && selectedPieceColor == gameModel.getTurn()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gameModel.getBoardModel().resetHighlightBoard();
            gameModel.getBoardModel().setStateEnum(StateEnum.NOTHINGPRESSEDSTATE);
            if(!(gameModel.getBoardModel().getBoard()[gameModel.getBoardModel().getCurrentSelectedPosition()] == 1 ||
                    gameModel.getBoardModel().getBoard()[gameModel.getBoardModel().getCurrentSelectedPosition()] == -1)) {
                gameModel.getBoardModel().movePiece(gameModel.getBoardModel().getCurrentSelectedPosition(), converter[position]);
            } else {
                gameModel.getBoardModel().pawnMove(gameModel.getBoardModel().getCurrentSelectedPosition(), converter[position]);
            }
            gameModel.switchTurn();
            gameModel.getBoardModel().getCurrentPossibleMoves().clear();
        } else if(pressedPieceColor == '#') {
            gameModel.getBoardModel().resetHighlightBoard();
            gameModel.getBoardModel().setStateEnum(StateEnum.NOTHINGPRESSEDSTATE);

        } else if(selectedPieceColor == pressedPieceColor) {
            gameModel.getBoardModel().resetHighlightBoard();
            gameModel.getBoardModel().getHighlightBoard()[position] = 1;
            gameModel.getBoardModel().setCurrentSelectedPosition(converter[position]);
            highlightPossible();

        } else {
            gameModel.getBoardModel().resetHighlightBoard();
            gameModel.getBoardModel().setCurrentSelectedPosition(converter[position]);
            gameModel.getBoardModel().setStateEnum(StateEnum.NOTHINGPRESSEDSTATE);

        }
    }

    private boolean pressedPossibleMove() {
        for(int i = 0; i < gameModel.getBoardModel().getCurrentPossibleMoves().size(); i++) {
            if(gameModel.getBoardModel().getCurrentPossibleMoves().get(i) == converter[position]) {
                return true;
            }
        }
        return false;
    }



}
