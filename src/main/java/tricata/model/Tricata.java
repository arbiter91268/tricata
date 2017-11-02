package tricata.model;

import java.util.*;

/**
 * Game model. Interacts with observer
 */
public class Tricata extends Observable {

	public enum Mode {
		NORMAL,
		UP_THE_ANTE,
		ROYAL_CHAOS;
	}

	private List<Player> players = new ArrayList<>();
	private Stack<Card> deck = new Stack<Card>();
	private Stack<Card> bin = new Stack<Card>();

	private int currentTurn = 0;

	private final Mode gamemode;

	public Tricata(Observer observer, int numplayers, Mode mode) {
		addObserver(observer);
		List<Card> cards = new ArrayList<>();
		this.gamemode = mode;
		for (Card.Type type : Card.Type.values()) {
			for (Card.Color color : Card.Color.values()) {
				for (byte i = 1; i <= 3; i++) {
					cards.add(new Card(i, type, color));
				}
			}
		}
		assert cards.size() == 27;
		Collections.shuffle(cards);
		Collections.shuffle(cards); // double shuffle for more randomness
		for (int i = 1; i <= numplayers; i++) {
			players.add(new Player("Player " + i));
		}
		deck.addAll(cards);
	}

	public Card pickupCard(boolean isFromDeck) {
		if (isFromDeck) {
			return deck.pop();
		}
		return bin.pop();
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	/**
	 * places card at location
	 * @param where -1 for the bin, otherwise (num / 3) to define player, and (num % 3) to define slot
	 */
	public void placeCard(Card toPlace, int where) {
		if (where == -1) {
			bin.push(toPlace);
			nextTurn();
			return;
		}
		int player = where / 3;
		int index = where % 3;
		Card dispose = getPlayer(player).setCard(index, toPlace);
		bin.push(dispose);
		nextTurn();
	}

	public void deal() {
		bin.push(deck.pop());
		for (int i = 0; i < 3; i++) {
			for (Player p : players) {
				p.setCard(i, deck.pop());
			}
		}
		setChanged();
		notifyObservers();
	}

	public Card peekNextCardInDeck() {
		return deck.peek();
	}

	public Card peekNextCardInBin() {
		if (bin.isEmpty()) {
			return null;
		}
		return bin.peek();
	}

	public Player getPlayer(int player) {
		return players.get(player);
	}

	public Player getCurrentPlayer() {
		return players.get(currentTurn);
	}

	private void nextTurn() {
		currentTurn++;
		if (currentTurn >= players.size()) {
			currentTurn = 0;
		}
		setChanged();
		notifyObservers();
		for (Player p : players) { // check if any player has won. may not detect on setting up the game
			if (p.hasWon()) {
				setChanged();
				notifyObservers(p.name);
			}
		}
	}
}
