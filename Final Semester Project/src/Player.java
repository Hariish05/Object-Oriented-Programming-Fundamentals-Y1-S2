public class Player {
	
	
	private String name;
	private Pokemon collection;
	private int score;
	
	public Player() {
	}

	public Player(String name, Pokemon collection, int score) {
		this.name = name;
		this.collection = collection;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pokemon getCollection() {
		return collection;
	}

	public void setCollection(Pokemon collection) {
		this.collection = collection;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return String.format("Player [name=%s, collection=%s, score=%s]", name, collection, score);
	}

}
