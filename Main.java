package de.kvnsfr.university.tsuro;

public class Main {
	
	public static void main(String[] args) {
		CardGenerator gc = CardGenerator.getInstance();
		gc.generateCards(4);
		System.out.println("No. unquie cards: " + gc.calculateUniqueCards());
	}
}
