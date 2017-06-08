package com.lastmilelink.bs.tictactoe;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.lastmilelink.bs.tictactoe.Model.NextMove;
import com.lastmilelink.bs.tictactoe.Utils.Position;
import com.lastmilelink.bs.tictactoe.Utils.ShowMessage;
import com.lastmilelink.bs.tictactoe.Utils.WinSequence;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity implements GameView, View.OnClickListener{

    @BindView(R.id.tile1)
    ImageView mTile1;

    @BindView(R.id.tile2)
    ImageView mTile2;

    @BindView(R.id.tile3)
    ImageView mTile3;

    @BindView(R.id.tile4)
    ImageView mTile4;

    @BindView(R.id.tile5)
    ImageView mTile5;

    @BindView(R.id.tile6)
    ImageView mTile6;

    @BindView(R.id.tile7)
    ImageView mTile7;

    @BindView(R.id.tile8)
    ImageView mTile8;

    @BindView(R.id.tile9)
    ImageView mTile9;

    @OnClick(R.id.btnPlayAgain) void newGame() {
        GamePresenter
                .getInstance(this)
                .startNewGame();
    }

    @BindView(R.id.backend_thinking_view)
    View mWaitStateView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_board_layout);
        ButterKnife.bind(this);

        GamePresenter
                .getInstance(this)
                .startNewGame();

    }

    @Override
    public void reset() {
        clearAllTiles();
    }

    @Override
    public void showProgress() {
        mWaitStateView.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        mWaitStateView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNextMove(NextMove aMove) {
        int aPosition = Position.getIndex(aMove.location);
        setPlayerResponse(aPosition, false);
    }

    @Override
    public void showWinner(WinSequence.WinTriad aWinTriad, boolean isAI) {

        if (aWinTriad != null) {
            setWinningTiles(aWinTriad.mFirst, isAI);
            setWinningTiles(aWinTriad.mSecond, isAI);
            setWinningTiles(aWinTriad.mThird, isAI);

            if (isAI) {
                ShowMessage.now(this,
                        mTile1,
                        R.string.ai_wins_msg,
                        R.color.topaz);
            } else {
                ShowMessage.now(this,
                        mTile1,
                        R.string.player_wins_msg,
                        R.color.topaz);
            }
        } else {
            ShowMessage.now(this,
                    mTile1,
                    R.string.draw_wins_msg,
                    R.color.topaz);
        }
    }

    @Override
    public void showError(String aReason) {
        ShowMessage.now(this, mTile1,  aReason);
    }

    @Override
    public void onClick(View v) {
        int aPosition = (int)v.getTag();
        if (aPosition > 0 && aPosition < 10) {
            if (setPlayerResponse(aPosition, true)) {
                GamePresenter
                        .getInstance(this)
                        .submitNextMove(new NextMove(Position.getIndex(aPosition)));
            } else {
                ShowMessage.now(this,
                        mTile1,
                        R.string.tile_already_played_msg,
                        R.color.rhodonite);
            }
        }
    }



    private boolean  setPlayerResponse(final int aPosition, final boolean isCross) {
        switch(aPosition) {
            case 1: return setTile(mTile1, isCross);
            case 2: return setTile(mTile2, isCross);
            case 3: return setTile(mTile3, isCross);

            case 4: return setTile(mTile4, isCross);
            case 5: return setTile(mTile5, isCross);
            case 6: return setTile(mTile6, isCross);

            case 7: return setTile(mTile7, isCross);
            case 8: return setTile(mTile8, isCross);
            case 9: return setTile(mTile9, isCross);

            default: return false;
        }

    }


    private boolean setTile(final ImageView aIv, final boolean isCross) {
        if (aIv.isEnabled()) {
            if (isCross) {
                Drawable aDrawable = ContextCompat.getDrawable(this, R.drawable.blue_multiply);
                aIv.setImageDrawable(aDrawable);
            } else {
                Drawable aDrawable = ContextCompat.getDrawable(this, R.drawable.yellow_zero);
                aIv.setImageDrawable(aDrawable);
            }
            aIv.setEnabled(false);
            return true;
        }
        return false;
    }


    private void resetTile(final ImageView aIv, final int aPosition){
        Drawable aDrawable = ContextCompat.getDrawable(this, R.drawable.play);
        aIv.setImageDrawable(aDrawable);
        aIv.setEnabled(true);
        aIv.setTag(aPosition);
        aIv.setOnClickListener(this);
    }

    private void setWinningTiles(final int aPosition, boolean isAI) {

        Drawable aDrawable;
        if (isAI) {
            aDrawable = ContextCompat.getDrawable(this, R.drawable.ironman);
        } else {
            aDrawable = ContextCompat.getDrawable(this, R.drawable.win);
        }

        switch(aPosition) {
            case 1: mTile1.setImageDrawable(aDrawable);
                break;
            case 2: mTile2.setImageDrawable(aDrawable);
                break;
            case 3: mTile3.setImageDrawable(aDrawable);
                break;

            case 4: mTile4.setImageDrawable(aDrawable);
                break;
            case 5: mTile5.setImageDrawable(aDrawable);
                break;
            case 6: mTile6.setImageDrawable(aDrawable);
                break;

            case 7: mTile7.setImageDrawable(aDrawable);
                break;
            case 8: mTile8.setImageDrawable(aDrawable);
                break;
            case 9: mTile9.setImageDrawable(aDrawable);
                break;
        }

        mTile1.setEnabled(false);
        mTile2.setEnabled(false);
        mTile3.setEnabled(false);

        mTile4.setEnabled(false);
        mTile5.setEnabled(false);
        mTile6.setEnabled(false);

        mTile7.setEnabled(false);
        mTile8.setEnabled(false);
        mTile9.setEnabled(false);


    }

    private void clearAllTiles() {

        resetTile(mTile1, 1);
        resetTile(mTile2, 2);
        resetTile(mTile3, 3);

        resetTile(mTile4, 4);
        resetTile(mTile5, 5);
        resetTile(mTile6, 6);

        resetTile(mTile7, 7);
        resetTile(mTile8, 8);
        resetTile(mTile9, 9);
        ShowMessage.now(this,
                mTile1,
                R.string.starting_new_game_msg,
                R.color.topaz);
    }
}
