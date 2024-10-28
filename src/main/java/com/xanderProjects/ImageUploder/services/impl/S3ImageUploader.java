package com.xanderProjects.ImageUploder.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.xanderProjects.ImageUploder.exceptions.ImageUploadexception;
import com.xanderProjects.ImageUploder.services.ImageUploader;

@Service
public class S3ImageUploader implements ImageUploader {

    // repositories to inject from Database

    @Autowired
    private AmazonS3 client;

    @Value("${app.s3.bucket}")
    private String bucketName;

    @Override
    public String uploadImage(MultipartFile image) {
        // TODO Auto-generated method stub

        if(image==null){
            throw new ImageUploadexception("image is null");
        }

        String actualFileName=image.getOriginalFilename();
        String fileName = UUID.randomUUID().toString()+actualFileName.substring(actualFileName.lastIndexOf("."));
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(image.getSize());
        
        try {
            PutObjectResult putObjectResult = client.putObject(new PutObjectRequest(bucketName, fileName, image.getInputStream(), metaData));
            return fileName;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new ImageUploadexception("Error in Uploading Image "+e.getMessage());
        }
    }

    @Override
    public List<String> allFiles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'allFiles'");
    }

    @Override
    public String preSignedUrl() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'preSignedUrl'");
    } 



}
