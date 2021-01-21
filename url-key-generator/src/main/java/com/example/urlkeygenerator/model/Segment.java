package com.example.urlkeygenerator.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Segment implements Serializable {
    private static final long serialVersionUID = -4582640947574414288L;
    int[] currentPermutation;

    public static Segment initialSegment(){
        Segment segment = new Segment();
        segment.setCurrentPermutation(new int[1]);
        return segment;
    }

}
