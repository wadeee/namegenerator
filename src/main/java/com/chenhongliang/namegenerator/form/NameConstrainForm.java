package com.chenhongliang.namegenerator.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameConstrainForm implements Serializable {
    private String sex;
    private String lastname;
    private List<Integer> nameSize;
    private List<String> bannedPinyin;
    private List<String> bannedCharacter;
    private String generation;
    private List<String> wuxing;

    public NameConstrainForm(NameConstrainForm nameConstrainForm) {
        this(nameConstrainForm.getSex(), nameConstrainForm.getLastname(), nameConstrainForm.getNameSize(), nameConstrainForm.getBannedPinyin(), nameConstrainForm.getBannedCharacter(), nameConstrainForm.getGeneration(), nameConstrainForm.getWuxing());
    }
}
