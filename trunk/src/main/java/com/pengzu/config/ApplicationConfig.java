package com.pengzu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {

    @Value("${file.root}")
    private String fileRoot;

    public String getFileRoot() {
        return fileRoot;
    }

    public void setFileRoot(String fileRoot) {
        this.fileRoot = fileRoot;
    }

    @Override
    public String toString() {
        return "ApplicationConfig{" +
                "fileRoot='" + fileRoot + '\'' +
                '}';
    }
}
