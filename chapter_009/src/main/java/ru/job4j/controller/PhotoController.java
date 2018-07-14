package ru.job4j.controller;

/**
 * PhotoController.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.service.PhotoService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class PhotoController {
    private PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @ResponseBody
    @GetMapping(value = "/image/{photoID}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable final int photoID) throws IOException {
        return IOUtils.toByteArray(new FileInputStream(new File(this.photoService.getById(photoID).getImageURL())));
    }
}