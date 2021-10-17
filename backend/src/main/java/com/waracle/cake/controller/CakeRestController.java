package com.waracle.cake.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.waracle.cake.entity.Cake;
import com.waracle.cake.persistence.CakeRepository;
import com.waracle.cake.request.CakeRequest;

@RestController
@CrossOrigin
public class CakeRestController {

    private final CakeRepository cakeRepository;

    public CakeRestController(final CakeRepository cakeRepository){
        this.cakeRepository = cakeRepository;
    }

    @PostMapping("/cakes")
    public Cake saveCake(@RequestBody CakeRequest cakeRequest) {
        final Cake cake = new Cake();
        cake.setTitle(cakeRequest.getTitle());
        cake.setDesc(cakeRequest.getDesc());
        cake.setImage(cakeRequest.getImage());
        return cakeRepository.save(cake);
    }

    @GetMapping("/cakes")
    public List<Cake> fetchAllCakes() {
        return cakeRepository.findAll();
    }

}
