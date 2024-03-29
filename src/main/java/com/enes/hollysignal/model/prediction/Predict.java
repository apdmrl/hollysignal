package com.enes.hollysignal.model.prediction;

import com.enes.hollysignal.model.Base;
import com.enes.hollysignal.model.Status;
import com.enes.hollysignal.model.auth.Account;
import com.enes.hollysignal.model.system.FileResource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Accessors(chain = true)
@Document(collection = "Predict")
@TypeAlias("Predict")
public class Predict extends Base {

    @Getter
    @Setter
    private CurrencyType currencyType;

    @Getter
    @Setter
    private Pair pair;

    @Getter
    @Setter
    private boolean isLongTerm;

    @Getter
    @Setter
    private String Title;

    @Getter
    @Setter
    private double targetPrice;

    @Getter
    @Setter
    private PredictType predictType;

    @Getter
    @Setter
    private String note;

//    @Getter
//    @Setter
//    private FileResource graph;

    @Getter
    @Setter
    private Status status;

    @Getter
    @Setter
    private int purchased;

    @Getter
    @Setter
    private double predictPrice;

    @Getter
    @Setter
    private boolean isPriceDown;

    @Getter
    @Setter
    private boolean doubleChecked = false;

    @Getter
    @Setter
    private boolean isApproved = false;

    @Getter
    @Setter
    private String accountId;

    public boolean isEnabled() {
        return status == Status.ENABLED;
    }

}
