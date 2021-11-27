package com.bz.hb.model.common;

/**
 * created by srana on 07/10/20 at 10.30 AM
 */

import com.bz.hb.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@Getter
@AllArgsConstructor
public enum UserType {
    ADMIN(1,"Admin");

    private int id;
    private String name;

    public static UserType getById(int id) {
        return Arrays.stream(values())
                .filter(type -> type.id == id)
                .findAny()
                .orElseThrow(NotFoundException::new);
    }
}
