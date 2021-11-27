package com.bz.hb.model.gender;

import com.bz.hb.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum  Gender {

    MALE(1, "MALE"),
    FEMALE(2, "FEMALE");

    private int id;
    private String name;

    public static Gender getById(int id) {
        return Arrays.stream(values())
                .filter(gender -> gender.getId() == id)
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public static List<Gender> all() {
        return Arrays.asList(values());
    }
}
