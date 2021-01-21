package com.example.urlkeygenerator.generator;

import com.example.urlkeygenerator.model.Segment;
import com.example.urlkeygenerator.model.SegmentKeys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KeyGenerator {
    private final Integer size;

    public KeyGenerator(@Value("${generated.segment.size}") Integer size) {
        this.size = size;
    }

    public SegmentKeys generate(Segment startSegment) {
        if(startSegment == null) {
            startSegment = Segment.initialSegment();
        }

        String dictionary = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SegmentKeys segmentKeys = new SegmentKeys();
        segmentKeys.setStartingSegment(startSegment);

        int[] currentPermutation = startSegment.getCurrentPermutation();
        int stateLen = currentPermutation.length;

        while (segmentKeys.getCreatedKeys().size() < size) {
            permutate(segmentKeys, new char[stateLen], currentPermutation, 0, dictionary);
            if (segmentKeys.getCreatedKeys().size() < size) {
                currentPermutation = new int[++stateLen];
                permutate(segmentKeys, new char[stateLen], currentPermutation, 0, dictionary);
            }
        }

        segmentKeys.setEndingSegment(getEndingSegment(segmentKeys, dictionary));
        return segmentKeys;
    }

    private Segment getEndingSegment(SegmentKeys createdKeysSegment, String dictionary) {
        Segment endingSegment = new Segment();
        char[] endingPermutation = createdKeysSegment.getEndingValue();
        endingSegment.setCurrentPermutation(new int[endingPermutation.length]);
        int[] currentPermutation = endingSegment.getCurrentPermutation();
        for (int i = 0; i < endingPermutation.length; i++) {
            char lastCharAt = endingPermutation[i];
            int indexInDict = dictionary.indexOf(lastCharAt);
            currentPermutation[i] = indexInDict;
        }

        //increment ending permutation
        int dictLen = dictionary.length();
        boolean wasIncremented = false;
        for (int i = currentPermutation.length - 1; i >= 0; i--) {
            int curr = currentPermutation[i];
            if (curr + 1 >= dictLen) {
                currentPermutation[i] = 0;
            } else {
                wasIncremented = true;
                currentPermutation[i]++;
                break;
            }
        }
        if (!wasIncremented) {
            currentPermutation = new int[currentPermutation.length + 1];
            endingSegment.setCurrentPermutation(currentPermutation);
        }

        return endingSegment;
    }

    private void permutate(SegmentKeys results, char[] perm, int[] permPositions, int pos, String dictionary) {
        if (results.getCreatedKeys().size() >= size) {
            return;
        }
        if (pos == perm.length) {
            results.getCreatedKeys().add(new String(perm));
            results.setEndingValue(perm);
        } else {

            for (int i = permPositions[pos]; i < dictionary.length(); i++) {
                if (results.getCreatedKeys().size() >= size) {
                    break;
                }
                perm[pos] = dictionary.charAt(i);
                permPositions[pos] = 0;
                permutate(results, perm, permPositions, pos + 1, dictionary);
            }
        }

    }

}
