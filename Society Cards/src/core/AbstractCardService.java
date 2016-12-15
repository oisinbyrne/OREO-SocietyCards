package core;

import card.CardService;

public abstract class AbstractCardService implements CardService {
	private int counter = 1000;
	
	protected int generateReference() {
		return counter++;
	}
}
