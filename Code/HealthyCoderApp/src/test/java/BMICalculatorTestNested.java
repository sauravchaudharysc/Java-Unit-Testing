package test.java;

import main.java.com.healthycoderapp.BMICalculator;
import main.java.com.healthycoderapp.Coder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTestNested {
    private String environment ="prod";

    //Function can be any name but Must be static
    @BeforeAll
    static void beforeAll(){
        System.out.println("Run exactly once before all unit test");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Run exactly once after all unit test");
    }

    @Nested
    class isDietRecommended{
        @Test
        void should_ReturnTrue_When_DietRecommended(){
            //Given
            double weight=89.0;
            double height=1.72;

            //When
            boolean recommended = BMICalculator.isDietRecommended(weight,height);

            //Then
            assertTrue(recommended);
        }

        @Test
        void should_ReturnFalse_When_DietRecommended(){
            //Given
            double weight=50.0;
            double height=1.92;

            //When
            boolean recommended = BMICalculator.isDietRecommended(weight,height);

            //Then
            assertFalse(recommended);
        }

        @Test
        void should_ThrowArithmeticException_When_DietRecommended(){
            //Given
            double weight=50.0;
            double height=0;

            //When
            Executable executable = () -> BMICalculator.isDietRecommended(weight,height);

            //Then
            assertThrows(ArithmeticException.class,executable);
        }

    }

    //Disable is used to skip a test
    @Nested
    @DisplayName("Customized display name")
    class findCoderWithWorstBMI{
        @Test
        @DisplayName(">>>sample method display name")
        @Disabled
        void should_ReturnCoderWithWorstBMI(){
            //Given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80,60.0));
            coders.add(new Coder(1.82,98.0));
            coders.add(new Coder(1.82,64.5));

            //When
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

            //Then
        /*If the first assertion fails second doesn't work so to
        avoid that we use assertAll */
            assertAll(
                    () -> assertEquals(1.82,coderWorstBMI.getHeight()),
                    () -> assertEquals(98.0,coderWorstBMI.getWeight())
            );
        }

        @Test
        @DisabledOnOs(OS.WINDOWS)
        void should_ReturnNull_CoderListNull(){
            //Given
            List<Coder> coders = new ArrayList<>();

            //When
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

            //Then
            assertNull(coderWorstBMI);
        }
    }

    @Nested
    class getBMIScoresTest{
        @Test
        @DisabledOnOs(OS.LINUX)
        void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty(){
            //Given
            assumeTrue(BMICalculatorTestNested.this.environment.equals("prod"));
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80,60.0));
            coders.add(new Coder(1.82,98.0));
            coders.add(new Coder(1.82,64.7));
            double[] expected = {18.52,29.59,19.53};

            //When
            double[] bmiScores = BMICalculator.getBMIScores(coders);

            //Then
        /*In case of array comparison never use assertEquals. It
        will throw error though output is same.*/
            assertArrayEquals(expected,bmiScores);
        }
    }
}