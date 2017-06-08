package com.lastmilelink.bs.tictactoe.ApiService;

import com.lastmilelink.bs.tictactoe.Model.NextMove;

import retrofit2.http.Body;
import retrofit2.http.PUT;
import rx.Observable;

/**
 * Created by binusadanand on 05/06/2017.
 */

public interface Api {
    @PUT(EndpointConstants.NEXT_TURN_API)
    Observable<NextMove> play(@Body NextMove aMove);
}
