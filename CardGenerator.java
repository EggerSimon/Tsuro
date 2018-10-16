package de.kvnsfr.university.tsuro;

import java.util.ArrayList;

public class CardGenerator {
	private static CardGenerator instance;
	private int[][]	connections;
	private int cardSides = 0;
	private int cardCount = 0;
	
	ArrayList<Card> cards = new ArrayList<>();
	
	public static CardGenerator getInstance() {
		if(instance == null)
			instance = new CardGenerator();
		return instance;
	}
	
	public int generateCards(int sides) 
	{		
		cardSides = sides;
		connections = new int[cardSides][2];
		CardVariants(1);
		
		System.out.println(cardCount);
		return cardCount;
	}
	
	private int CardVariants(int edge)
	{
		if(edge == cardSides)
		{
			for(int i = 0; i < cardSides * 2; i++) {
				if(CheckCards.getAvailability(connections, edge, 1, i)) {
					connections[edge - 1][1] = i;
				}
			}
			
			Card tempCard = new Card();
			for(int i = 0; i < cardSides; i++){
				tempCard.connectNodesByIndex(connections[i][0], connections[i][1]);
				System.out.print(connections[i][0] + "," + connections[i][1] + ",");
			}
			
			System.out.println();
					
			cards.add(tempCard);
			cardCount++;
									
			return 0;
		}
		else
		{
			for(int i = 0; i < cardSides * 2; i++) {
				if(CheckCards.getAvailability(connections, edge, 1, i)) {
					connections[edge - 1][1] = i;
					
					for(int j = 0; j < cardSides * 2; j++) {
						if(CheckCards.getAvailability(connections, edge, 0, j)) {
							connections[edge][0] = j;
						}
					}
					
					CardVariants(edge + 1);
				}
			}
		}
		
		return 0;
	}
	
	public int calculateUniqueCards() {
		ArrayList<Card> cardsToRemoved = new ArrayList<>();
				
		for(int i = 0; i < cards.size(); i++) {
			for(int y = 0; y < cards.size(); y++) {
				Card card = cards.get(y);
				Card compare = cards.get(i);
				if(card.equalsAfterRotation(compare) && i != y) {
					if(!cardsToRemoved.contains(card))
						cardsToRemoved.add(card);
				}
			}
		}
		
		for(Card remove: cardsToRemoved)
			cards.remove(remove);
		
		return cards.size();
	}
}
