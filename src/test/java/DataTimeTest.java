import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.vicary.DataTime;

import java.time.OffsetDateTime;

public class DataTimeTest {
    DataTime dataTime = new DataTime();
    @Test
    public void now_ExpectTwoSameDateClasses() {
        OffsetDateTime dateExpected = OffsetDateTime.now();
        OffsetDateTime dateActual = dataTime.now();

        Assertions.assertSame(dateExpected.getClass(), dateActual.getClass());
        Assertions.assertNotEquals(dateExpected, dateActual);
    }
}
