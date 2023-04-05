package sqa.example.ptit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PtitApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void twoPlusShouldEqualsFour() {
		assertEquals(4, 2 + 2);
		assertNull(null);
	}

}
