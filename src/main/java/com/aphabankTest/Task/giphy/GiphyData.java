package com.aphabankTest.Task.giphy;
import com.google.gson.annotations.SerializedName;
public class GiphyData {

    @SerializedName("id")
    private String id;

    @SerializedName("url")
    private String url;

    @SerializedName("embed_url")
    private String embedUrl;

    public String getEmbedUrl() {
        return embedUrl;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}