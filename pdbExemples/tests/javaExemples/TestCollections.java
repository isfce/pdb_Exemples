package javaExemples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TestCollections {
	@Test
	public void f() {
		List<String> l = List.of("Just", "One");
		assertEquals(l.size(), 2);
	}
}
