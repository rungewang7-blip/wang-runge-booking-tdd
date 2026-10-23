import java.time.*;
import java.util.List;

public class BookingService {

    public static final Duration MAX_SLOT = Duration.ofHours(3);

    private final IRoomRepository rooms;
    private final IBookingRepository bookings;
    private final Clock clock;

    public BookingService(
        IRoomRepository rooms,
        IBookingRepository bookings,
        Clock clock
    ) {
        this.rooms = rooms;
        this.bookings = bookings;
        this.clock = clock;
    }

    /** Book a room for a user. Returns the new booking. */
   public Booking book(String userId, String roomId, TimeSlot slot) {

    checkRoomExists(roomId);
    checkFutureStart(slot);
    checkDuration(slot);
    checkRoomOverlap(roomId, slot);
    checkUserOverlap(userId, slot);

    Booking booking = new Booking(userId, roomId, slot);
    bookings.save(booking);
    return booking;
}
// R1: Room must exist
private void checkRoomExists(String roomId) {
    if (rooms.findById(roomId) == null) {
        throw new IllegalArgumentException();
    }
}

// R2: Booking must start in the future
private void checkFutureStart(TimeSlot slot) {
    if (!slot.getStart().isAfter(LocalDateTime.now(clock))) {
        throw new IllegalArgumentException();
    }
}

// R3: Booking duration must not exceed 3 hours
private void checkDuration(TimeSlot slot) {
    if (slot.duration().compareTo(MAX_SLOT) > 0) {
        throw new IllegalArgumentException();
    }
}

// R4: No overlapping bookings for the same room
private void checkRoomOverlap(String roomId, TimeSlot slot) {
    for (Booking existing : bookings.findByRoom(roomId)) {
        if (existing.getSlot().overlaps(slot)) {
            throw new IllegalStateException();
        }
    }
}

// R5: No overlapping bookings for the same user
private void checkUserOverlap(String userId, TimeSlot slot) {
    for (Booking existing : bookings.findByUser(userId)) {
        if (existing.getSlot().overlaps(slot)) {
            throw new IllegalStateException();
        }
    }
}

    /** Cancel a user's booking. */
    public void cancel(String userId, String roomId, TimeSlot slot) {
        Booking booking = new Booking(userId, roomId, slot);

        if (!bookings.findByUser(userId).contains(booking)) {
            throw new IllegalArgumentException();
        }

        bookings.delete(booking);
    }

    public List<Booking> bookingsFor(String userId) {
        return bookings.findByUser(userId).stream()
            .sorted((a, b) ->
                a.getSlot().getStart().compareTo(b.getSlot().getStart()))
            .toList();
    }
}