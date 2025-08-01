import java.util.ArrayList;
import java.util.Random;

public class Player {
	public static final Random RANDOM = new Random();
	private String name;
	private ArrayList<Pokemon> collection;
	private int score;
	
	public Player(String name, int score) {
		this.name = name;
		this.collection = null;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Pokemon> getCollection() {
		return collection;
	}
	public void setCollection(ArrayList<Pokemon> collection) {
		this.collection = collection;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void Choose() {
	}
	public void Catch() {
	}
	@Override
	public String toString() {
		return String.format("Player [name=%s, collection=%s, score=%s]", name, collection, score);
	}
}