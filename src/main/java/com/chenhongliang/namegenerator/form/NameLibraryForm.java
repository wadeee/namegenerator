package com.chenhongliang.namegenerator.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameLibraryForm implements Serializable {
    private String name;
    private String meaning;
    private String wuxing;
    private String source;
    private Boolean male;
    private Boolean female;
}
