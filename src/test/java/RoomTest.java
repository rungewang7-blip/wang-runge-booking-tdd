import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    @Test
    void roomStoresItsId() {
        Room room = new Room("R1");
        assertEquals("R1", room.getId());
    }
}