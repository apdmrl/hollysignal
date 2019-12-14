package com.enes.hollysignal.repository;


import com.enes.hollysignal.model.prediction.Predict;
import com.enes.hollysignal.model.prediction.PredictType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PredictRepository extends MongoRepository<Predict, String> {
    List<Predict> findAllByAccountId(String id);

    List<Predict> findAllByApproved(boolean isApproved);
}
