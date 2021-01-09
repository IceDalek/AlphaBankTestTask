package com.aphabankTest.Task.giphy;

import feign.Param;
import feign.RequestLine;

public interface Giphy {
    @RequestLine("GET /v1/gifs/random?api_key=QPayNCt8xoMomlYfVdySXOEGxAxXfiV2&tag={tag}")
    RandomGifResponse getGif(@Param("tag") String tag);
}