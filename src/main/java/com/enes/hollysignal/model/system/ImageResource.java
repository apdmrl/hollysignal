package com.enes.hollysignal.model.system;

import com.enes.hollysignal.model.Base;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Accessors(chain = true)
@Document(collection = "ImageResource")
@TypeAlias("ImageResource")
public class ImageResource extends Base {

    @Indexed
    @Getter
    @Setter
    private String firmId;

    @Getter
    @Setter
    private FileResource image;

}