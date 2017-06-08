package com.lastmilelink.bs.tictactoe.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by binusadanand on 05/06/2017.
 */

public class Position {

    private static Map<String, Integer> mMap;

    static {
        HashMap<String, Integer> aMap = new HashMap<>();
        aMap.put("TOP:LEFT", 1);
        aMap.put("TOP:MID", 2);
        aMap.put("TOP:RIGHT", 3);
        aMap.put("MID:LEFT", 4);
        aMap.put("MID:MID", 5);
        aMap.put("MID:RIGHT", 6);
        aMap.put("BOTTOM:LEFT", 7);
        aMap.put("BOTTOM:MID", 8);
        aMap.put("BOTTOM:RIGHT", 9);

        mMap = Collections.unmodifiableMap(aMap);
    }

    public static int getIndex(String aPosStr) {
        return mMap.get(aPosStr);

    }

    public static String getIndex(int aPosition) {
        switch(aPosition) {
            case 1: return "TOP:LEFT";
            case 2: return "TOP:MID";
            case 3: return "TOP:RIGHT";
            case 4: return "MID:LEFT";
            case 5: return "MID:MID";
            case 6: return "MID:RIGHT";
            case 7: return "BOTTOM:LEFT";
            case 8: return "BOTTOM:MID";
            case 9: return "BOTTOM:RIGHT";

            default:
                return null;

        }

    }




}