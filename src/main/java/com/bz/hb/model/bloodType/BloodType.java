package com.bz.hb.model.bloodType;

import com.bz.hb.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum BloodType {


    A_POSITIVE(1, "A+"),
    A_NEGATIVE(2, "A-"),
    B_POSITIVE(3, "B+"),
    B_NEGATIVE(4, "B-"),
    AB_POSITIVE(5, "AB+"),
    AB_NEGATIVE(6, "AB-"),
    O_POSITIVE(7, "O+"),
    O_NEGATIVE(8, "O-");

    private int id;
    private String name;

    public static BloodType getById(int id) {
        return Arrays.stream(values())
                .filter(bloodType -> bloodType.getId() == id)
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public static List<BloodType> all() {
        return Arrays.asList(values());
    }
}
