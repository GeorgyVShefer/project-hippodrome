
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import java.util.concurrent.TimeUnit;


public class MainTest {
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled(value = "не будет занимать лишнего времени")
    public void main(){

    }
}