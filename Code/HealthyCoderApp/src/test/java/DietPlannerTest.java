package test.java;

import main.java.com.healthycoderapp.Coder;
import main.java.com.healthycoderapp.DietPlan;
import main.java.com.healthycoderapp.DietPlanner;
import main.java.com.healthycoderapp.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {
    //Since the DietPlanner method wasn't static
    //We create an object of DietPlanner Class
    private DietPlanner dietPlanner;
    /*This isn't initialized because unit test must be independent of each other
    and we want to have a brand new instance of DietPlanner.*/

    //This function will invoke before each unit test
    @BeforeEach
    void setup() {
        this.dietPlanner = new DietPlanner(20, 30, 50);
    }

    @AfterEach
    void AfterEach() {
        System.out.println("A unit test was finished");
    }
    @Test
    void should_ReturnCorrectDietPlan_When_CorrectCoder(){
        //Given
        Coder coder = new Coder(1.82,75.0,26, Gender.MALE);
        DietPlan expected = new DietPlan(2202,110,73,275);

        //When
        DietPlan actual = dietPlanner.calculateDiet(coder);

        //Then
        assertAll(
                () -> assertEquals(expected.getCalories(),actual.getCalories()),
                () -> assertEquals(expected.getProtein(),actual.getProtein()),
                () -> assertEquals(expected.getFat(),actual.getFat()),
                () -> assertEquals(expected.getCarbohydrate(),actual.getCarbohydrate())
        );
    }

    /*To run the same tests multiple times.
    Each repeated test is treated a seperate unit test.
    It is useless to repeat the test if you dont use any random number
    inside a method or method doesnt change it state in any way.
     */
    @RepeatedTest(10)
    void should_RTReturnCorrectDietPlan_When_CorrectCoder(){
        //Given
        Coder coder = new Coder(1.82,75.0,26, Gender.MALE);
        DietPlan expected = new DietPlan(2202,110,73,275);

        //When
        DietPlan actual = dietPlanner.calculateDiet(coder);

        //Then
        assertAll(
                () -> assertEquals(expected.getCalories(),actual.getCalories()),
                () -> assertEquals(expected.getProtein(),actual.getProtein()),
                () -> assertEquals(expected.getFat(),actual.getFat()),
                () -> assertEquals(expected.getCarbohydrate(),actual.getCarbohydrate())
        );
    }

    @RepeatedTest(value = 10,name = RepeatedTest.LONG_DISPLAY_NAME)
    void should_RTEReturnCorrectDietPlan_When_CorrectCoder(){
        //Given
        Coder coder = new Coder(1.82,75.0,26, Gender.MALE);
        DietPlan expected = new DietPlan(2202,110,73,275);

        //When
        DietPlan actual = dietPlanner.calculateDiet(coder);

        //Then
        assertAll(
                () -> assertEquals(expected.getCalories(),actual.getCalories()),
                () -> assertEquals(expected.getProtein(),actual.getProtein()),
                () -> assertEquals(expected.getFat(),actual.getFat()),
                () -> assertEquals(expected.getCarbohydrate(),actual.getCarbohydrate())
        );
    }
}