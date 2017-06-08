package com.lastmilelink.bs.tictactoe;

import com.lastmilelink.bs.tictactoe.ApiService.Api;
import com.lastmilelink.bs.tictactoe.ApiService.Provider;
import com.lastmilelink.bs.tictactoe.Model.NextMove;
import com.lastmilelink.bs.tictactoe.Utils.Position;
import com.lastmilelink.bs.tictactoe.Utils.WinSequence;
import java.util.ArrayList;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by binusadanand on 05/06/2017.
 */

public class GamePresenter {

    private static GamePresenter mGamePresenter;
    private Api mApiService;
    private final GameView mView;
    private CompositeSubscription mSubscriptions;

    private ArrayList<Integer> mAiList;
    private ArrayList<Integer> mPlayerList;
    private WinSequence mWinSeq;
    private int mRetryCount;

    private GamePresenter(GameView aView) {
        mView = aView;
        mSubscriptions = new CompositeSubscription();
        mApiService = Provider.getApiService();

        mAiList = new ArrayList<>();
        mPlayerList = new ArrayList<>();
        mWinSeq = new WinSequence();

        mRetryCount = 0;


    }

    public static GamePresenter getInstance(GameView aView) {
        if(mGamePresenter == null) {
            mGamePresenter = new GamePresenter(aView);
        }
        return mGamePresenter;
    }

    public void startNewGame() {
        mAiList.clear();
        mPlayerList.clear();
        mView.reset();
    }

    public void submitNextMove (final NextMove aMove) {

        mView.showProgress();
/*
        if (mRetryCount >= 2) {
            mRetryCount = 0;
            return;
        }*/

        if(updatePlayerMove(aMove)) {
            mView.dismissProgress();
            return;
        }

        Subscription event = mApiService.play(aMove)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NextMove>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissProgress();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(NextMove aNewMove) {
                        if (isOccupiedCell(aNewMove)) {
                            //mRetryCount ++;
                            submitNextMove(aMove);
                        } else {
                            mView.dismissProgress();
                            mView.showNextMove(aNewMove);
                            upDateAIMove(aNewMove);
                            //mRetryCount = 0;
                        }
                    }
                });

    }

    public void onStop() {
        mSubscriptions.unsubscribe();
    }


    private boolean upDateAIMove(NextMove aMove) {

        mAiList.add(Position.getIndex(aMove.location));

        WinSequence.WinTriad aWinTriad = mWinSeq.checkForWin(mAiList);
        if (aWinTriad != null) {
            mView.showWinner(aWinTriad, true);
            return true;
        } else {
            if(isAllCellOccupied()) {
                mView.showWinner(null, false);
                return true;
            }
        }

        return false;
    }

    private boolean updatePlayerMove(NextMove aMove) {

        mPlayerList.add(Position.getIndex(aMove.location));

        WinSequence.WinTriad aWinTriad = mWinSeq.checkForWin(mPlayerList);
        if (aWinTriad != null) {
            mView.showWinner(aWinTriad, false);
            return true;
        } else {
            if(isAllCellOccupied()) {
                mView.showWinner(null, false);
                return true;
            }
        }
        return false;
    }

    private boolean isOccupiedCell(NextMove aMove) {

        int aPosition = Position.getIndex(aMove.location);
        if (mAiList.contains(aPosition) || mPlayerList.contains(aPosition)) {
            return true;
        }
        return false;

    }

    private boolean isAllCellOccupied() {

        for (int i=1; i<=9; i++) {
            if (mAiList.contains(i) || mPlayerList.contains(i)) {
                continue;
            } else {
                return false;
            }
        }
      return true;
    }

}
