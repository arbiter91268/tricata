package tricata.model;

import java.util.*;

/**
 * Game model. Interacts with observer
 */
public class Tricata extends Observable {

	public enum Mode {
		NORMAL,
		UP_THE_ANTE,
		ROYAL_CHAOS,
		ALL;
	}

	public final String name;
	private final int maxRounds;
	private List<Player> players = new ArrayList<>();
	private Stack<Card> deck = new Stack<>();
	private Stack<Card> bin = new Stack<>();

	private int currentTurn = 0;
	private int currentRound = 0;

	private final Mode gamemode;

	public Tricata(Observer observer, int numplayers, Mode mode, int rounds, String name) {
		addObserver(observer);
		this.gamemode = mode;
		this.name = name;
		this.maxRounds = rounds;
		for (int i = 1; i <= numplayers; i++) {
			players.add(new Player("Player " + i));
		}
		createCards();
	}

	private void createCards() {
		deck.clear();
		bin.clear();
		List<Card> cards = new ArrayList<>();
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
		deck.addAll(cards);
		setChanged();
		notifyObservers();
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
				p.incrementScore();
				if (p.getScore() >= maxRounds) {
					setChanged();
					notifyObservers(p.name + "has won");
					return;
				}
				setChanged();
				notifyObservers(p.name);
				createCards();
			}
		}
	}
}
