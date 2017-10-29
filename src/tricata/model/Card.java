package tricata.model;

import java.io.Serializable;

/**
 *
 */
public class Card implements Serializable {

	public enum Type {GIRL, DEMON, GENTLEMAN;}
	public enum Color {PURPLE, RED, GREEN;}

	private final byte number;
	private final Type type;
	private final Color color;

	public Card(byte number, Type type, Color color) {
		this.number = number;
		this.type = type;
		this.color = color;
	}

	public int getNumber() {
		return (int)this.number;
	}

	public Type getType() {
		return this.type;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Card) {
			Card o = (Card)other;
			return this.number == o.number && this.color == o.color && this.type == o.type;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (100 * type.ordinal()) + (10 * color.ordinal()) + number;
	}

	@Override
	public String toString() {
		return "Card(number:" + number + ", type:" + type.name() + ", color:" + color.name() + ")";
	}
}
