package com.enes.hollysignal.model.prediction;

import com.enes.hollysignal.model.Base;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Accessors(chain = true)
@Document(collection = "Pair")
@TypeAlias("Pair")
public class Pair extends Base {

}
