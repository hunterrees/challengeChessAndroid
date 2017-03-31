package com.example.android.challengechess.States;

import com.example.android.challengechess.Model.GameModel;
import com.example.android.challengechess.Controllers.SquareController;

/**
 * Created by Brian on 16/12/27.
 */

public class NothingPressedState extends PressState {

    public NothingPressedState(int position, GameModel gameModel, SquareController squareController) {
        super(position, gameModel, squareController);
    }

    @Override
    public void press() {
        gameModel.getBoardModel().resetHighlightBoard();
        char pressedPieceColor = '#';
        if(gameModel.getBoardModel().getBoard()[converter[position]] < 0) {
            pressedPieceColor = 'b';
        } else if(gameModel.getBoardModel().getBoard()[converter[position]] > 0) {
            pressedPieceColor = 'w';
        }
        if(gameModel.getBoardModel().getBoard()[converter[position]] != 0 &&
            pressedPieceColor== gameModel.getTurn()) {
            gameModel.getBoardModel().getHighlightBoard()[position] = 1;
            gameModel.getBoardModel().setStateEnum(StateEnum.PIECEALREADYSELECTEDSTATE);
            gameModel.getBoardModel().setCurrentSelectedPosition(converter[position]);
            highlightPossible();
        }
    }
}
