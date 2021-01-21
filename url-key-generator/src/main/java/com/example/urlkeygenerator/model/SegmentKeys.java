package com.example.urlkeygenerator.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class SegmentKeys {
    Segment startingSegment;
    Set<String> createdKeys = new HashSet<>();
    Segment endingSegment;
    char[] endingValue;
}
