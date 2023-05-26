package com.ibrahim.whatdoyoumood.service;

import com.ibrahim.whatdoyoumood.ImageUtil;
import com.ibrahim.whatdoyoumood.model.Meme;
import com.ibrahim.whatdoyoumood.repository.MemeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class MemeService {

    private final MemeRepository memeRepository;

    public MemeService(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    public void saveMeme(MultipartFile file, String name) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a valid file");
        }

        Meme meme = Meme.builder().name(file.getOriginalFilename())
                .image(ImageUtil.compressImage(file.getBytes()))
                .build();

        memeRepository.save(meme);
        System.out.println("meme saved");
    }

    @Transactional
    public Meme getInfoByImageByName(String name) {
        Optional<Meme> dbImage = memeRepository.findByName(name);

        return Meme.builder()
                .name(dbImage.get().getName())
                .image(ImageUtil.decompressImage(dbImage.get().getImage())).build();
    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<Meme> dbImage = memeRepository.findByName(name);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImage());
        return image;
    }
}
