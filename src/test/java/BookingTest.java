import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    void bookingStoresUserId() {
        TimeSlot slot = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        Booking booking = new Booking("alice", "R1", slot);

        assertEquals("alice", booking.getUserId());
    }
    @Test
    void bookingStoresRoomId() {
        TimeSlot slot = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
       );

        Booking booking = new Booking("alice", "R1", slot);

        assertEquals("R1", booking.getRoomId());
}
    @Test
    void bookingStoresSlot() {
        TimeSlot slot = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        Booking booking = new Booking("alice", "R1", slot);

        assertEquals(slot, booking.getSlot());
    }
}