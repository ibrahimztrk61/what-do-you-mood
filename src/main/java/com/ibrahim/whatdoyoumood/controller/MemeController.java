package com.ibrahim.whatdoyoumood.controller;

import com.ibrahim.whatdoyoumood.model.Meme;
import com.ibrahim.whatdoyoumood.service.MemeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/meme")
public class MemeController {

    private final MemeService memeService;

    public MemeController(MemeService memeService) {
        this.memeService = memeService;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException {
        memeService.saveMeme(file, name);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<?> getImageInfoByName(@PathVariable("name") String name) {
        Meme meme = memeService.getInfoByImageByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(meme);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getImageByName(@PathVariable("name") String name) {
        byte[] image = memeService.getImage(name);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
