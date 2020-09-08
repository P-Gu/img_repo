package com.example.image.api;

import com.example.image.service.*;
import com.example.image.dao.*;
import com.example.image.model.*;

import java.util.List;
import java.util.ArrayList;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/findImage")
public class ImageController {

	private final ImageService imageService;

	@Autowired
	public ImageController(ImageService imageService) {
		this.imageService = imageService;
	}

	@GetMapping("/one")
	public String findAnImage(Model model){//@RequestParam("name") String name, Model model)  {
		List<String> urls = new ArrayList<>();
		urls.add(imageService.selectImageUrlByTitle("vofan1"));
		model.addAttribute("urls", urls);

		return "index";

	}

	@PostMapping("/add")
	public String addImage(@RequestParam("title") String title, @RequestParam("file") MultipartFile file, Model model){
		
		
		model.addAttribute("urls", imageService.selectAllImageUrls());

		String added = imageService.addImageToS3(file, title) ? "Added!" : "Failed";

		model.addAttribute("addResponse", added);
		
		return "index";

	}

	@GetMapping(value={"/add", ""})
	public String findAllImages(Model model){
		
		model.addAttribute("urls", imageService.selectAllImageUrls());

		return "index";
	}
}
