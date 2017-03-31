package com.example.android.challengechess.Model;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.example.android.challengechess.R;
import com.example.android.challengechess.SquareAdapter;

/**
 * Created by Brian on 16/12/27.
 */

public class GameModel {

    private BoardModel boardModel;
    private Context context;
    private char turn = 'w';
    private boolean gameover = false;

    protected SquareAdapter squareAdapter;


    public GameModel(final Context context, boolean whiteComputer, boolean blackComputer) {
        boardModel = new BoardModel(context);
        ((Activity)(context)).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MediaPlayer mp = MediaPlayer.create(context, R.raw.newgame);
                mp.start();
            }
        });
        this.context = context;
    }

    public void play() {
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public void setBoardModel(BoardModel boardModel) {
        this.boardModel = boardModel;
    }

    public synchronized void switchTurn() {
        if(gameover) {
            return;
        }


        if(turn == 'w') {
            turn = 'b';
            if(boardModel.getAllPossibleBlackMoves().size() == 0) {
                gameover = true;
                ((Activity)(context)).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        squareAdapter.notifyDataSetChanged();
                        CharSequence text = "White wins!";
                        if(!boardModel.isBlackKingInCheck()) {
                            text = "Stalemate!";
                        }
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });

                return;
            }
        } else {
            turn = 'w';
            if(boardModel.getAllPossibleWhiteMoves().size() == 0) {
                gameover = true;


                ((Activity)(context)).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CharSequence text = "Black wins!";
                        if(!boardModel.isWhiteKingInCheck()) {
                            text = "Stalemate!";
                        }
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });

                return;
            }
        }
    }


    // Getters and Setters
    public char getTurn() {
        return turn;
    }

    public void setTurn(char turn) {
        this.turn = turn;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public SquareAdapter getSquareAdapter() {
        return squareAdapter;
    }

    public void setSquareAdapter(SquareAdapter squareAdapter) {
        this.squareAdapter = squareAdapter;
    }

}
