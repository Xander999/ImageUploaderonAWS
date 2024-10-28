package com.xanderProjects.ImageUploder.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xanderProjects.ImageUploder.services.ImageUploader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/s3")
public class S3Controller {

    private ImageUploader uploader;

    public S3Controller(ImageUploader uploader){
        this.uploader = uploader;
    }


    //upload Image
    @PostMapping("path")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file){

        return ResponseEntity.ok(uploader.uploadImage(file));

    }

}
