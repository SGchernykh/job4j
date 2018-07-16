package ru.job4j.service;

/**
 * WritePhotoToDisk.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.domain.Photo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritePhotoToDisk {

    /**
     * Write Photo To Disk.
     * @param multipartFile File.
     * @return Photo.
     */
    public Photo writePhotoToDisk(final MultipartFile multipartFile) {
        Photo photo = new Photo();
        File dir = new File("C:\\projects\\job4j\\chapter_007\\target\\image");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir.getAbsolutePath() + "/" + multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
            fos.flush();
            photo.setName(file.getName());
            photo.setImageURL(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return photo;
    }
}