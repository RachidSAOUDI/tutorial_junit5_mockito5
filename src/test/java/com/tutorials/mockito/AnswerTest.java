package com.tutorials.mockito;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnswerTest {

    @Test
    public void testRetrieveAnswer() {
        Answer mockedAnswer = mock();
        String answer = "42 is the Answer to the Ultimate Question of Life, the Universe, and Everything";
        when(mockedAnswer.retrieveAnswer()).thenReturn(answer);
        assertEquals(answer, mockedAnswer.retrieveAnswer());
    }
}
