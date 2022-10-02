package test.java;

import main.java.com.healthycoderapp.BMICalculator;
import main.java.com.healthycoderapp.Coder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @BeforeEach
    void beforeEach(){
        System.out.println("A unit test was performed");
    }

    @AfterEach
    void afterEach(){
        System.out.println("A unit test was completed");
    }

    //Function can be any name but Must be static
    @BeforeAll
    static void beforeAll(){
        System.out.println("Run exactly once before all unit test");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Run exactly once after all unit test");
    }

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

    @Test
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
    void should_ReturnNull_CoderListNull(){
        //Given
        List<Coder> coders = new ArrayList<>();

        //When
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        //Then
        assertNull(coderWorstBMI);
    }

    @Test
    void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty(){
        //Given
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

    /*To check our method for multiple values we could
    create further similar unit test case with different values
    but not a good practice. So we use Parameterized Test*/
    @ParameterizedTest
    @ValueSource(doubles={89.0,95.0,110.0})
    void should_SPReturnTrue_When_DietRecommended(Double coderWeight){
        //Given
        double weight=coderWeight;
        double height=1.72;

        //When
        boolean recommended = BMICalculator.isDietRecommended(weight,height);

        //Then
        assertTrue(recommended);
    }

    /*Multiple Values at same time
    MP in function name means Multiple Parameter
    MPDF means developer friendly way*/
    @ParameterizedTest
    @CsvSource(value={"89.0,1.72","95.0,1.75","110.0,1.78"})
    void should_MPReturnTrue_When_DietRecommended(Double coderWeight,Double coderHeight){
        //Given
        double weight=coderWeight;
        double height=coderHeight;

        //When
        boolean recommended = BMICalculator.isDietRecommended(weight,height);

        //Then
        assertTrue(recommended);
    }

    @ParameterizedTest(name="weight {0},height {1}")
    @CsvSource(value={"89.0,1.72","95.0,1.75","110.0,1.78"})
    void should_MPDFReturnTrue_When_DietRecommended(Double coderWeight,Double coderHeight){
        //Given
        double weight=coderWeight;
        double height=coderHeight;

        //When
        boolean recommended = BMICalculator.isDietRecommended(weight,height);

        //Then
        assertTrue(recommended);
    }

    @ParameterizedTest(name="weight {0},height {1}")
    @CsvFileSource(resources="../resources/diet-recommended-input-data.csv",numLinesToSkip = 1)
    void should_MPDFFromCSVFileReturnTrue_When_DietRecommended(Double coderWeight,Double coderHeight){
        //Given
        double weight=coderWeight;
        double height=coderHeight;

        //When
        boolean recommended = BMICalculator.isDietRecommended(weight,height);

        //Then
        assertTrue(recommended);
    }

    /*Sometime we need to run a certain test within a given time limit.
    With JUnit5, we can check the Performance simply.
    */
    @Test
    void should_ReturnCoderWithWorstBMIIn1Ms_When_CoderListHas10000Elements(){
        //Given
        List<Coder> coders = new ArrayList<>();
        for(int i=0;i<10000;i++){
            coders.add(new Coder(1.0+i,10.0+i));
        }

        //When
        Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

        //Then
        assertTimeout(Duration.ofMillis(7),executable);
    }
}