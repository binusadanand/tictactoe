package com.lastmilelink.bs.tictactoe.Utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by binusadanand on 07/06/2017.
 */

public class WinSequence {

    public class WinTriad {

        public int mFirst;
        public int mSecond;
        public int mThird;

        WinTriad(int aFirst, int aSecond, int aThird) {
            mFirst = aFirst;
            mSecond = aSecond;
            mThird = aThird;
        }
    }

    private ArrayList<WinTriad> mWinList;

    public WinSequence() {

        mWinList = new ArrayList<>();

        //Horizontal Wining sequence
        mWinList.add(new WinTriad(1,2,3));
        mWinList.add(new WinTriad(4,5,6));
        mWinList.add(new WinTriad(7,8,9));

        //Vertical winning sequence
        mWinList.add(new WinTriad(1,4,7));
        mWinList.add(new WinTriad(2,5,8));
        mWinList.add(new WinTriad(3,6,9));

        //Cross winning sequence
        mWinList.add(new WinTriad(1,5,9));
        mWinList.add(new WinTriad(3,5,7));
    }

    public WinTriad checkForWin(ArrayList<Integer> aList) {

        if ((aList != null) && (aList.size() >= 3)) {

            for (WinTriad aSeries : mWinList) {
                ArrayList<Integer> aCheckList =  new ArrayList<>();
                aCheckList.add(aSeries.mFirst);
                aCheckList.add(aSeries.mSecond);
                aCheckList.add(aSeries.mThird);
                if (aList.containsAll(aCheckList)) {
                    return aSeries;
                }
            }
        }
        return null;
    }


}
