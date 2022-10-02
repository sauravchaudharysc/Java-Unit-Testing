<center><h1>
    Mockito
    </h1></center>



#### Pre-Requisite

- Java Programming(Refer Java Class by DurgaSoft on Youtube)
- JUnit5

## JUnit5

JUnit is a simple testing framework to write and run repeatable tests. It provides a simple API to test our java application. The class or method we need to test is called subject under test. So to write a test we simply create a Java Test Class and use the Junit API to make some assumption  toward the behavior of subject under test. The JUnit verifies whether the assumption are correct or not. 

### Unit Testing

- Tests at the lowest level.
- They test single units/components of software.
- In Java, it checks a method.

```java
/*Method Under Test*/
double asCelsius(double temperatureFahrenheit){
	return ((temperatureFahrenheit-32)*5.0)/9.0;
}

/*Unit Test*/
@Test
void should_ReturnCorrectTemperatureAsCelsius(){
	assertEqual(asCelsius(41.0),5.0);
}
```

**Every good software must have unit test. Unit test increase confidence during development.**

### JUnit Architecture

![image-20220929074925610](C:\Users\hp\Desktop\Mockito\1)

On a high level JUnit5 is a combination of three different modules. 

**JUnit Platform** is the module which provides an API to launch the tests from either the Ide , buildtool or consoles.

**JUnit Jupiter ** is the module which provides an API to write our JUnit Test. For writing Unit Test using JUnit5 we use this module.

**JUnit Vintage ** is the module which provides backward compatibility for Unit Tests written with JUnit3 or JUnit4.

*JUnit platform module enables some 3rd Party Testing Frameworks to build their own API to write the tests and reuse the JUnit Platform to launch the tests.*

### Junit Test Lifecycle

So each Junit test when it is executed it will create a new instance of the test class and its follows different faces as part of the execution. Each face is represented with annotation in Junit5.

![image-20220929083732529](C:\Users\hp\Desktop\Mockito\2)

**@BeforeAll , @BeforeEach ** : Used to perform initialization tasks for tests.

**@AfterAll , @AfterEach **: Used to perform cleanup tasks for tests.

**@BeforeAll & @AfterAll are called only once for the entire test and the methods are usually marked as static.**

### JUnit 4 <> JUnit 5 

**Compatibility**

Running older JUnit 4 tests on the JUnit 5 platform --> possible

Running newer JUnit 5 tests on the JUnit 4 platform --> not possible

**Annotation name changes**

|   JUnit 5   |   JUnit 4    |
| :---------: | :----------: |
| @BeforeEach |   @Before    |
| @AfterEach  |    @After    |
| @BeforeEach | @BeforeClass |
|  @AfterAll  | @AfterClass  |
|  @Disabled  |   @Ignore    |

**Testing Exceptions**

To test exceptions in JUnit 5, we used assertThrows with an executable, that is a lambda expression.

In JUnit 4, however, we had to use @Test and inside the brackets we provided "expected=" and then the exception class.

**New in JUnit 5**

@Nested & @RepeatedTest not present in JUnit 4.

## Mockito 

