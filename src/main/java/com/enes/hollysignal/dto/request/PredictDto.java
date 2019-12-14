package com.enes.hollysignal.dto.request;

import com.enes.hollysignal.model.prediction.CurrencyType;
import com.enes.hollysignal.model.prediction.Pair;
import com.enes.hollysignal.model.prediction.PredictType;
import com.enes.hollysignal.model.system.FileResource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class PredictDto {
    @Getter
    @Setter
    private String id;

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
    private double predictPrice;

    @Getter
    @Setter
    private boolean isPriceDown;

    @Getter
    @Setter
    private boolean doubleChecked = false;

    @Getter
    @Setter
    private String accountId;
}
