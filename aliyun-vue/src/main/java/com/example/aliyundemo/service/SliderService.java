package com.example.aliyundemo.service;

import com.example.aliyundemo.jpa.entity.Sliders;
import com.example.aliyundemo.jpa.repo.SlidersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SliderService {

    @Autowired
    private SlidersRepository slidersRepository;


    public List<String> getSliders(){
        List<Sliders> all = slidersRepository.findAll();
        List<String> list = new ArrayList<>();
        for (Sliders sliders : all) {
            list.add(sliders.getUrl());
        }
        return list;
    }
}
