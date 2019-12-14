package com.enes.hollysignal.service;

import com.enes.hollysignal.repository.PredictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredictService {

    @Autowired
    private PredictRepository predictRepository;

}
