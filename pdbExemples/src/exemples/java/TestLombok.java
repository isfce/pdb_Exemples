package exemples.java;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class TestLombok {

	private final int id;
	private String nom;

	/**
	 * @param id
	 * @param nom
	 */
	public TestLombok(int id, String nom) {
		this.id = id;
		this.nom = nom;
		log.info("Un Test est créé!!");
	}

	public static void main(String[] args) {
		TestLombok t1 = new TestLombok(1, "test1");
		System.out.println(t1);
	}

}
