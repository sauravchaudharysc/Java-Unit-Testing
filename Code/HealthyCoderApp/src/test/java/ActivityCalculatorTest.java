package test.java;

import main.java.com.healthycoderapp.ActivityCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ActivityCalculatorTest {

    @Test
    void should_ReturnBad_When_AvgBelow20(){
        //Given
        int weeklyCardioMins = 40;
        int weeklyWorkouts=1;

        //When
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMins,weeklyWorkouts);

        //Then
        assertEquals("bad",actual);
    }

    @Test
    void should_ReturnAverage_When_AvgBetween20And40(){
        //Given
        int weeklyCardioMins = 40;
        int weeklyWorkouts=3;

        //When
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMins,weeklyWorkouts);

        //Then
        assertEquals("average",actual);
    }

    @Test
    void should_ReturnAverage_When_AvgAbove40(){
        //Given
        int weeklyCardioMins = 40;
        int weeklyWorkouts=7;

        //When
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMins,weeklyWorkouts);

        //Then
        assertEquals("good",actual);
    }

    @Test
    void should_ThrowException_When_InputBelowZero(){
        //Given
        int weeklyCardioMins = -40;
        int weeklyWorkouts=7;

        //When
        Executable executable = () -> ActivityCalculator.rateActivityLevel(weeklyCardioMins,weeklyWorkouts);

        //Then
        assertThrows(RuntimeException.class,executable);
    }
}