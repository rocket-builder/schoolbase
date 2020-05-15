package com.rocketbuilder.schoolbase;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Upload {

    private static String defFileDir = "/img/";
    private static String fileRootDir =  "./src/main/resources/static/img/";

    public static String avatar(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();

        File dir = new File(fileRootDir);
        File uploadedFile = new File(dir.getAbsolutePath() + File.separator +file.getOriginalFilename());
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
        stream.write(bytes);
        stream.flush();
        stream.close();

        return defFileDir+file.getOriginalFilename();
    }
}
