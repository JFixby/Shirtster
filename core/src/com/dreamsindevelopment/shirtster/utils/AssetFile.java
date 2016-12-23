package com.dreamsindevelopment.shirtster.utils;

public class AssetFile {

    public String path;
    public Class<?> type;

    public AssetFile(String path, Class<?> type) {
        this.path = path;
        this.type = type;
    }
}
