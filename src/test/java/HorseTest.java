import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;


//test{Method}_Should{Do}_When{Condition}
public class HorseTest {

    final static String NAME_IS_BLANK = "Name cannot be blank.";
    final static String SPEED_IS_NEGATIVE = "Speed cannot be negative";
    final static String DISTANCE_IS_NEGATIVE = "Distance cannot be negative";
    String name = "Bucephalus";
    double speed = 10;
    double distance = 10;


    @Test
    public void testConstructor_ShouldThrowIAEx_WhenArgsNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));
    }

    @Test
    public void testConstructor_ShouldThrowExTextMessage_WhenArgsNameIsNull() {
        String expectedExceptionMessage = "Name cannot be null.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    /* @ParameterizedTest
     @ValueSource(strings = {" ", "\n","","\t","\r"})
    public void nameIsBlank(){
         IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                 new Horse(name, speed, distance));
         assertEquals(NAME_IS_BLANK, exception.getMessage());
     }*/
   /* @Test
    public void negativeSpeedValue(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, -1, distance));
        assertEquals(SPEED_IS_NEGATIVE, exception.getMessage());
    }*/
    /*@Test
    public void negativeDistanceValue(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, speed, -1));
        assertEquals(DISTANCE_IS_NEGATIVE, exception.getMessage());
    }*/
    @Test
    public void testGetName_ShouldReturnName_WhenArgsConstructorIsName() {
        assertEquals(name, new Horse(name, speed).getName());
    }

    @Test
    @SneakyThrows
    public void testGetName_ShouldReturnName_WhenArgsConstructorIsNameReflection() {
        String expectedName = "Bucephalus";
        Horse horse = new Horse(name, speed);

        Field field = Horse.class.getDeclaredField("name");
        field.setAccessible(true);
        String actualName = (String) field.get(horse);
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetSpeed_ShouldReturnSpeed_WhenArgsConstructorIsSpeed() {
        assertEquals(speed, new Horse(name, speed).getSpeed(), 0);
    }

    @Test
    @SneakyThrows
    public void testGetSpeed_ShouldReturnSpeed_WhenArgsConstructorIsSpeedReflection() {
        double expectedSpeed = 10.0;
        Horse horse = new Horse(name, speed);

        Field field = Horse.class.getDeclaredField("speed");
        field.setAccessible(true);
        Double actualSpeed = (Double) field.get(horse);

        assertEquals(expectedSpeed, actualSpeed, 0);

    }

    @Test
    public void testGetDistance_ShouldReturnDistance_WhenArgsConstructorIsDistance() {
        assertEquals(distance, new Horse(name, speed, distance).getDistance(), 0);
    }

    @Test
    public void testGetDistance_ShouldReturnDistance_WhenArgsConstructorIsNot() {
        double dis = 0;
        assertEquals(dis, new Horse(name, speed).getDistance());
    }

    @Test
    @SneakyThrows
    public void testGetDistance_ShouldReturnDistance_WhenArgsConstructorIsDistanceReflection() {
        double expectedDistance = 10;
        Horse horse = new Horse(name, speed, distance);

        Field field = Horse.class.getDeclaredField("distance");
        field.setAccessible(true);

        double actualDistance = (Double) field.get(horse);

        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    @SneakyThrows
    public void testGetDistance_ShouldReturnDistance_WhenArgsConstructorIsNotReflection() {
        double expectedDistance = 0;
        Horse horse = new Horse(name, speed);

        Field field = Horse.class.getDeclaredField("distance");
        field.setAccessible(true);

        double actualDistance = (Double) field.get(horse);

        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    public void move() {
        Horse horse = new Horse(name, speed, distance);
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble()));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.2, 0.5, 0.7, 1.0, 10.0, 100})
    public void testMove(double fakeValue) {
        Horse horse = new Horse(name,speed,distance);
        double expectedDistance = horse.getDistance()+horse.getSpeed() * fakeValue;

       try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
           mockedStatic.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(fakeValue);

           horse.move();
       }
        assertEquals(expectedDistance,horse.getDistance());

    }
}