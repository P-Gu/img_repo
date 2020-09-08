package com.example.image.dao;

import com.example.image.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository("mysql")
public class ImageDataAccessService implements ImageDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ImageDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public String selectImageUrlById(int id) {
        String url = "nothing";
        try {
            final String sql = "select url from urls where id = ?";
            url = this.jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);//, String.class);//new Object[] {}, 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public String selectImageUrlByTitle(String title) {
        String url = "nothing";
        try {
            final String sql = "select url from urls where title = ?";
            url = this.jdbcTemplate.queryForObject(sql, new Object[]{title}, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public List<String> selectAllImageUrls() {
        List<String> urls = new ArrayList<>();
        try {
            final String sql = "select url from urls";
            urls = jdbcTemplate.queryForList(sql, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urls;
    }

    @Override
    public int insertImageUrl(String title, String url) {
        try {
            final String sql = "insert into urls (title, url) values (?, ?)";
            return jdbcTemplate.update(sql, new Object[]{title, url});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
