package com.hgicreate.rno.xdr.backend.service.util;

import java.io.File;
import java.io.FilenameFilter;

public class FileSelectFilter implements FilenameFilter {
    private String name;
    private String extension;

    public FileSelectFilter(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public boolean accept(File directory, String filename) {
        boolean fileMatch = true;
        if (name != null) {
            fileMatch = filename.startsWith(name);
            if (extension != null && fileMatch) {
                fileMatch = filename.endsWith('.' + extension);
            }
        }
        return fileMatch;
    }
}
