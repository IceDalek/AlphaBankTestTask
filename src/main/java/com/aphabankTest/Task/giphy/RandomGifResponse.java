package com.aphabankTest.Task.giphy;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RandomGifResponse {
    @SerializedName("data")
    private GiphyData dataList;
    /**
     * Returns the data list.
     *
     * <p>
     * "data": [ ... ],
     *
     * @return the data list
     */
    public GiphyData getDataList() {
    return dataList;
    }

    /**
     * Sets the data list.
     *
     * @param dataList
     *            the data list
     */




}

