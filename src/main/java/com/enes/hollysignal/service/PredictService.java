package com.enes.hollysignal.service;

import com.enes.hollysignal.dto.request.PredictDto;
import com.enes.hollysignal.dto.response.GenericResponse;
import com.enes.hollysignal.model.auth.Account;
import com.enes.hollysignal.model.auth.Role;
import com.enes.hollysignal.model.prediction.Predict;
import com.enes.hollysignal.model.prediction.PredictType;
import com.enes.hollysignal.repository.PredictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PredictService {

    private Logger logger = LoggerFactory.getLogger(PredictService.class);

    private Role[] allowedRoles = new Role[]{Role.admin()};

    @Autowired
    private PredictRepository predictRepository;

    @Autowired
    private AccountService accountService;


    public GenericResponse createPredict(Account account, PredictDto predictDto) {
        if(predictDto.getId() == null){
            Predict predict = new Predict()
                    .setCurrencyType(predictDto.getCurrencyType())
                    .setPredictPrice(predictDto.getPredictPrice())
                    .setPredictType(predictDto.getPredictType())
                    .setAccountId(account.getId())
                    .setApproved(false)
                    .setDoubleChecked(predictDto.isDoubleChecked())
                    .setLongTerm(predictDto.isLongTerm())
                    .setPair(predictDto.getPair())
                    .setNote(predictDto.getNote())
                    .setPriceDown(predictDto.isPriceDown())
                    .setPurchased(0)
                    .setTitle(predictDto.getTitle());

            predict.setId(UUID.randomUUID().toString());
            predictRepository.save(predict);
            account.getPredictList().add(predict);
            accountService.save(account);

            return new GenericResponse()
                    .setCode(0)
                    .setData(predict);
        } else {
            return new GenericResponse()
                    .setCode(1);
        }

    }


    public List<Predict> getAllPredictsWithMask(){
        List<Predict> predictList = predictRepository.findAllByApproved(true);

        for(Predict predict: predictList){
            switch (predict.getPredictType()){
                case PAIR:
                    predict.setPair(null);
                    break;
                case PRICE:
                    predict.setTargetPrice(0);
                    break;

            }
        }
        return predictList;
    }

    public Predict getPredict(String id){
        return predictRepository.findById(id).orElse(null);
    }

    public GenericResponse updatePredict(Account account, PredictDto predictDto) {
        if(Arrays.asList(allowedRoles).contains(account.getRole())) {
                Predict predict = predictRepository.findById(predictDto.getId()).orElse(null);
                if (predict != null) {
                    predict
                            .setCurrencyType(predictDto.getCurrencyType())
                            .setPredictPrice(predictDto.getPredictPrice())
                            .setPredictType(predictDto.getPredictType())
                            .setDoubleChecked(predictDto.isDoubleChecked())
                            .setLongTerm(predictDto.isLongTerm())
                            .setPair(predictDto.getPair())
                            .setNote(predictDto.getNote())
                            .setPriceDown(predictDto.isPriceDown())
                            .setTitle(predictDto.getTitle());

                    predict.setUpdated(new Date());
                    predictRepository.save(predict);
                    return new GenericResponse()
                            .setCode(0)
                            .setData(predict);
                } else {
                    logger.error("predict not found");
                    return new GenericResponse()
                            .setCode(2)
                            .setMessage("Predict Not Found");
                }

        } else {
            logger.error("Update can only be done by admin role");
            return new GenericResponse()
                    .setCode(1);
        }
    }



}
