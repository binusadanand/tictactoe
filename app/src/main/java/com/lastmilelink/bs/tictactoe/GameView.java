package com.lastmilelink.bs.tictactoe;

import com.lastmilelink.bs.tictactoe.Model.NextMove;
import com.lastmilelink.bs.tictactoe.Utils.WinSequence;

/**
 * Created by binusadanand on 05/06/2017.
 */

public interface GameView {
    void reset();
    void showProgress();
    void dismissProgress();
    void showNextMove(NextMove aMove);
    void showWinner(WinSequence.WinTriad aWinTriad, boolean isAI);
    void showError(String aReason);
}
