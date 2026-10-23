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

    @Test
    void bookingsWithSameDetailsAreEqual() {
        TimeSlot slot1 = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        TimeSlot slot2 = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        Booking booking1 = new Booking("alice", "R1", slot1);
        Booking booking2 = new Booking("alice", "R1", slot2);

        assertEquals(booking1, booking2);
    }
    @Test
    void equalBookingsHaveSameHashCode() {
        TimeSlot slot1 = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        TimeSlot slot2 = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        Booking b1 = new Booking("alice", "R1", slot1);
        Booking b2 = new Booking("alice", "R1", slot2);

        assertEquals(b1.hashCode(), b2.hashCode());
    }
    @Test
    void bookingsWithDifferentUsersAreNotEqual() {
            TimeSlot slot = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        Booking b1 = new Booking("alice", "R1", slot);
        Booking b2 = new Booking("bob", "R1", slot);

        assertNotEquals(b1, b2);
    }
    @Test
    void bookingsWithDifferentRoomsAreNotEqual() {
        TimeSlot slot = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
        LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        Booking b1 = new Booking("alice", "R1", slot);
        Booking b2 = new Booking("alice", "R2", slot);

        assertNotEquals(b1, b2);
    }
}