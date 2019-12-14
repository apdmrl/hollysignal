package com.enes.hollysignal.repository;


import com.enes.hollysignal.model.prediction.Predict;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PredictRepository extends MongoRepository<Predict, String> {
}
