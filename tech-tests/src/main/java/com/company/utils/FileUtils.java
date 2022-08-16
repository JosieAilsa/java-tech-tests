package com.company.utils;

import java.io.File;

public abstract class FileUtils {

    public static String getRelativeFilePath() {
        return new File ("").getAbsolutePath();
    }
}
