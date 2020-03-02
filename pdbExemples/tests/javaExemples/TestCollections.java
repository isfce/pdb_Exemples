package javaExemples;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

public class TestCollections {
	@Test
	public void f() {
		List<String> l = List.of("Just", "One");
		assertEquals(l.size(), 2);
	}
}
