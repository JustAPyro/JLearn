package com.pyredevelopment.window;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlotTest {

    double input;
    double result;

    @Test
    @DisplayName("Testing Weird Round Function")
    void testRound() {

        // Set up
        input = 0.03;

        // Execute
        result = Plot.getBestIncrement(input);

        // Assert
        assertEquals(0.05, result);

    }

}