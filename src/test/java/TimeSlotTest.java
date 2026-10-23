import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalDateTime;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;


class TimeSlotTest {
    @Test
    void endBeforeStartIsRejected() {
        LocalDateTime ten = LocalDateTime.of(2027, 1, 11, 10, 0);
        LocalDateTime nine = LocalDateTime.of(2027, 1, 11, 9, 0);
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(ten, nine));
    }
    @Test
    void nullStartIsRejected() {
        LocalDateTime validTime = LocalDateTime.of(2026,10,23,10,0);
        assertThrows(IllegalArgumentException.class,
                () -> new TimeSlot(null, validTime));
    }
    @Test
    void nullEndIsRejected() {
        LocalDateTime validTime = LocalDateTime.of(2026,10,23,10,0);
        assertThrows(IllegalArgumentException.class,
                () -> new TimeSlot(validTime, null));
    }
    @Test
    void durationIsNinetyMinutesForTenToElevenThirty() {
        LocalDateTime ten = LocalDateTime.of(2026,10,23,10,0);
        LocalDateTime elevenThirty = LocalDateTime.of(2026,10,23,11,30);
        TimeSlot slot = new TimeSlot(ten, elevenThirty);
        assertEquals(Duration.ofMinutes(90), slot.duration());
    }
    private static TimeSlot slot(int startHour, int endHour) {
    return new TimeSlot(LocalDateTime.of(2027, 1, 11, startHour, 0),
            LocalDateTime.of(2027, 1, 11, endHour, 0));
    }

   @ParameterizedTest(name = "{0}-{1} vs {2}-{3} overlaps: {4}")
   @CsvSource({
        "10, 11, 11, 12, false",  
        "10, 12, 11, 13, true",
        "8, 9, 10, 11, false",
        "11, 12, 10, 13, true",
        "10, 11, 10, 11, true",
        "12, 13, 10, 11, false"
   })
   void overlapCasesA3(int aStart, int aEnd, int bStart, int bEnd, boolean expected) {
    assertEquals(expected, slot(aStart, aEnd).overlaps(slot(bStart, bEnd)));
}
}