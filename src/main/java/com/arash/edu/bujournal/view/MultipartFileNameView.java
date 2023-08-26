package com.arash.edu.bujournal.view;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

public class MultipartFileNameView implements MultipartFile {

    private final String name;

    public MultipartFileNameView(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return name;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return 1;
    }

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }

    @Override
    public void transferTo(File dest) {

    }
}
