package argumentor;

import org.junit.Test;
import static org.junit.Assert.*;

public class FunctionsTest {
    @Test public void testProcessDataAddsToStarters() {
        Functions func = new Functions();
        func.processData();
        assertTrue("List size should be > 0", func.starters.size() > 0);
    }
    
    @Test public void testProcessDataAddsToAllWords() {
        Functions func = new Functions();
        func.processData();
        assertTrue("List size should be > 0", func.allWords.size() > 0);
    }

    @Test public void testGenerateReturnsSentence() {
        Functions func = new Functions();
        func.processData();
        assertNotNull("Should return string",func.generate());
    }
}
