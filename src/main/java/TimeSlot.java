import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Objects;

public final class TimeSlot {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
         if (start == null) {
            throw new IllegalArgumentException("start must not be null");
         }
         if (end == null) {
            throw new IllegalArgumentException("end must not be null");
         }
         if (!end.isAfter(start)) {
            throw new IllegalArgumentException("end must be after start");
         }
         this.start = start;
         this.end = end;
    }
    public Duration duration() {
    return Duration.between(start, end);
    }
    public boolean overlaps(TimeSlot other) {
        return !((this.end.isBefore(other.start) || this.end.isEqual(other.start))
        || (other.end.isBefore(this.start) || other.end.isEqual(this.start)));
    }
    @Override
    public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       TimeSlot timeSlot = (TimeSlot) o;
       return Objects.equals(start, timeSlot.start) && Objects.equals(end, timeSlot.end);
    }

   @Override
   public int hashCode() {
    return Objects.hash(start, end);
   }
}
