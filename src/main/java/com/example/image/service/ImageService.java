package com.example.image.service;

import com.example.image.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.io.File;
import java.io.InputStream;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;


@Service
public class ImageService {

    private final ImageDao imageDao;

    @Autowired
    public ImageService(@Qualifier("mysql") ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public String selectImageUrlById(int id){
        return imageDao.selectImageUrlById(id);
    }

    public String selectImageUrlByTitle(String title){
        return imageDao.selectImageUrlByTitle(title);
    }

    public List<String> selectAllImageUrls(){
        return imageDao.selectAllImageUrls();
    }

    public boolean addImageToS3(MultipartFile file, String title) {
        Regions clientRegion = Regions.US_EAST_2;
        String bucketName = "pguimages";

        try {
            BasicAWSCredentials creds = new BasicAWSCredentials("AKIAJSQPCHPAG65BASBA",
             "Mzrz3ZZPE1pD1wxHPfdUrI2C2WkDYHuA7qipogtT");
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .withCredentials(new AWSStaticCredentialsProvider(creds))
                    .build();

            InputStream inputStream = file.getInputStream();
            ObjectMetadata data = new ObjectMetadata();
            data.setContentType(file.getContentType());
            data.setContentLength(file.getSize());

            PutObjectResult objectResult = s3Client.putObject(bucketName, title+".jpg", inputStream, data);

            String url = "https://pguimages.s3.us-east-2.amazonaws.com/" + title + ".jpg";
            if (imageDao.insertImageUrl(title, url)>=1) return true;
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return false;
    }

}
