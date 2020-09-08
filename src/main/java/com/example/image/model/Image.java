package com.example.image.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Image {

	@NotBlank
	private int id;

	private String title;
	private String artist;
    @NotBlank
    private String url;

    public Image(@NotBlank int id, String title, String artist, @NotBlank String url) {
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.url = url;
    }
}