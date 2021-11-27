package com.bz.hb.model.relationType;

import com.bz.hb.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum RelationType {


    SPOUSE(1, "Sister"),
    CHILD(2, "Mother");

    private int id;
    private String name;

    public static RelationType getById(int id) {
        return Arrays.stream(values())
                .filter(relationType -> relationType.getId() == id)
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public static List<RelationType> all() {
        return Arrays.asList(values());
    }
}
