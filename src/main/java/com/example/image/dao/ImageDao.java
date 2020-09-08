package com.example.image.dao;

import com.example.image.model.Image;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageDao {

    /*int insertPerson(UUID id, Person person) throws SQLException;

    default int insertPerson(Person person) throws SQLException {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }*/
    String selectImageUrlByTitle(String title);

    String selectImageUrlById(int id);

    List<String> selectAllImageUrls();
    
}
