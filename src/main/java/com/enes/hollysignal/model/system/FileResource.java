package com.enes.hollysignal.model.system;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileResource {

    // Reserved for file name
    private String name;
    private ExtensionType extension;

    // URL to access file on disk
    private String path;
    private FileResourceType resourceType;

    // Base64 file content
    private String content;
}