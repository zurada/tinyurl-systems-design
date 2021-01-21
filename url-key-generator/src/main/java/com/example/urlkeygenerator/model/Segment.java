package com.example.urlkeygenerator.model;

import lombok.Data;

@Data
public class Segment {
    int[] currentPermutation;

    public static Segment initialSegment(){
        Segment segment = new Segment();
        segment.setCurrentPermutation(new int[1]);
        return segment;
    }

}
