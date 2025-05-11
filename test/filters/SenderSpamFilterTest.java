package filters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SenderSpamFilterTest {
    @Test
    public void testSenderSpamFilterIsNull() {
        assertEquals(false, new SenderSpamFilter().isSpam(null));
    }
}