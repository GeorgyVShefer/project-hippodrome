

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {

    final static String HORSE_IS_EMPTY = "Horses cannot be empty.";
    @Test
    public void nameIsNull(){
        final String HORSE_IS_NULL = "Horses cannot be null.";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals(HORSE_IS_NULL,exception.getMessage());
    }
    @Test
    public void nameIsEmpty(){
        List<Horse> horses = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(horses);
        });
        assertEquals(HORSE_IS_EMPTY,exception.getMessage());
    }
    @Test
    public void getHorses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            horses.add(new Horse("Horse " + i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        List<Horse> horsess = hippodrome.getHorses();

        assertEquals(horses, horsess);
    }
    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();

        for (int i =0 ; i < 50; i++){
            horses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses){
            verify(horse,times(1)).move();
        }
    }
    @Test
    public void getWinner(){
        Horse horse1 = new Horse("firstHorse", 60, 200);
        Horse horse2 = new Horse("secondHorse", 80, 220);
        Horse horse3 = new Horse("thirthHorse", 45, 290);

        List<Horse> horses = Arrays.asList(horse1, horse2, horse3);

        Hippodrome hippodrome = new Hippodrome(horses);

        assertSame(horse3, hippodrome.getWinner());
    }
}