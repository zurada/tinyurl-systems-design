package com.example.urlkeygenerator.generator;

import com.example.urlkeygenerator.model.Segment;
import com.example.urlkeygenerator.model.SegmentKeys;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KeyGeneratorTest {
    private final int EXPECTED_SIZE = 100000;

    @Test
    void generate() {

        //given
        KeyGenerator keyGenerator = new KeyGenerator(EXPECTED_SIZE);

        Segment segment = Segment.initialSegment();
        assertThat(segment.getCurrentPermutation()).isEqualTo(new int[1]);

        //when
        SegmentKeys state = keyGenerator.generate(segment);

        //then
        assertThat(state.getCreatedKeys().size()).isEqualTo(EXPECTED_SIZE);
        assertThat(state.getEndingSegment().getCurrentPermutation()).containsSequence(24, 61, 56);
        assertThat(state.getCreatedKeys()).doesNotHaveDuplicates();

        //when
        state = keyGenerator.generate(state.getEndingSegment());

        //then
        assertThat(state.getCreatedKeys().size()).isEqualTo(EXPECTED_SIZE);
        assertThat(state.getEndingSegment().getCurrentPermutation()).containsSequence(51, 0, 50);
        assertThat(state.getCreatedKeys()).doesNotHaveDuplicates();


    }
}