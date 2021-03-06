package com.bilingoal.covirus.network;

import com.bilingoal.covirus.dto.Day;
import com.bilingoal.covirus.dto.Summary;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface NetworkInterface {
    @GET("summary")
    Observable<Summary> getSummary();

    @GET("total/dayone/country/{slug}/status/confirmed")
    Observable<List<Day>> getCasesSinceDayOne(@Path("slug") String slug);
}
