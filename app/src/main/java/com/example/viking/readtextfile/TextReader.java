package com.example.viking.readtextfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by vkwk on 10/14/16.
 * Use this class to read content from a file
 */

public class TextReader {

    static public String ReadTextForFile(File file) {
        String content = "(Null)";
        try {
            InputStream inputStream = new FileInputStream(file);
            int length = inputStream.available();
            byte[] buffer = new byte[length];
            int res = inputStream.read(buffer);
            System.out.print("reading status " + res);
            content = new String(buffer);
            inputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return content;
    }

    static public String ReadTextForURI(URI uri) {
        File file = new File(uri);
        return TextReader.ReadTextForFile(file);
    }

    static public String ReadTextForPath(String path) {
        File file = new File(path);
        return TextReader.ReadTextForFile(file);
    }

}
