package com.example.android.challengechess.Model;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.example.android.challengechess.R;
import com.example.android.challengechess.SquareAdapter;
import com.example.android.challengechess.computerplayers.AlphaBetaComputerPlayer;
import com.example.android.challengechess.computerplayers.ComputerPlayer;
import com.example.android.challengechess.computerplayers.Move;

import java.util.ArrayList;

/**
 * Created by Brian on 16/12/27.
 */

public class GameModel {

    private BoardModel boardModel;
    private Context context;
    private char turn = 'w';
    private boolean gameover = false;
    private ArrayList<Move> gameHistory = new ArrayList<>();
    private boolean isInComputerMode = false;
    private ComputerPlayer whiteComputerPlayer = null;
    private ComputerPlayer blackComputerPlayer = null;
    private boolean computerThinking = false;

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
        isInComputerMode = UserDataHolder.getInstance().isPlayingAgainstAI;
        if(whiteComputer) {
            whiteComputerPlayer = new AlphaBetaComputerPlayer(boardModel, 'w');
        }
        if(blackComputer) {
            blackComputerPlayer = new AlphaBetaComputerPlayer(boardModel, 'b');
        }
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

            if (isInComputerMode)
            {
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
                if(blackComputerPlayer != null) {
                    //CharSequence text = "Computer is thinking...";
                    //int duration = Toast.LENGTH_SHORT;

                    //Toast toast = Toast.makeText(context, text, duration);
                    // toast.show();
                    new Thread(new BlackAITurn()).start();

                }
            }
            else
            {
                //do server stuff
            }

        } else {
            turn = 'w';
            if (isInComputerMode)
            {
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
                if(whiteComputerPlayer != null) {


                    ((Activity)(context)).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                      /*  CharSequence text = "Computer is thinking...";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();*/
                            new Thread(new WhiteAITurn()).start();
                        }
                    });

                }
            }

        }
    }

    class BlackAITurn implements Runnable{

        @Override
        public void run() {
            computerThinking = true;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                Move move = blackComputerPlayer.getMove();
                if(move == null) {
                    gameover = true;
                    return;
                }
                gameHistory.add(move);
                if(!(boardModel.getBoard()[move.getBefore()] == 1 ||
                        boardModel.getBoard()[move.getBefore()] == -1)) {
                    boardModel.movePiece(move.getBefore(),move.getAfter());
                } else {
                    boardModel.pawnMove(move.getBefore(),move.getAfter());
                }
                boardModel.getHighlightBoard()[boardModel.unconvert(move.getBefore())] = 2;
                boardModel.getHighlightBoard()[boardModel.unconvert(move.getAfter())] = 2;
                ((Activity)(context)).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        squareAdapter.notifyDataSetChanged();
                    }
                });
                computerThinking = false;
                switchTurn();
            }

        }
    }
    class WhiteAITurn implements Runnable{

        @Override
        public void run() {
            computerThinking = true;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                Move move = whiteComputerPlayer.getMove();
                if(move == null) {
                    gameover = true;
                    return;
                }
                gameHistory.add(move);
                if(!(boardModel.getBoard()[move.getBefore()] == 1 ||
                        boardModel.getBoard()[move.getBefore()] == -1)) {
                    boardModel.movePiece(move.getBefore(),move.getAfter());
                } else {
                    boardModel.pawnMove(move.getBefore(),move.getAfter());
                }
                boardModel.getHighlightBoard()[boardModel.unconvert(move.getBefore())] = 2;
                boardModel.getHighlightBoard()[boardModel.unconvert(move.getAfter())] = 2;
                ((Activity)(context)).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        squareAdapter.notifyDataSetChanged();
                    }
                });
                computerThinking = false;
                switchTurn();
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
