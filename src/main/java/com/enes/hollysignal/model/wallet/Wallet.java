package com.enes.hollysignal.model.wallet;


import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Accessors(chain = true)
@Document(collection = "Predict")
@TypeAlias("Predict")
public class Wallet {

}
