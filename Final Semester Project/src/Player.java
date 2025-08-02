import java.util.*;

public class Player {
	public static final Random RANDOM = new Random();
	private String name;
	private ArrayList<Pokemon> collection;
	private Pokemon pokemonEquipped;
	private int score;
	private boolean turn;

	
	public Player(String name, int score, ArrayList<Pokemon> collection) {
		this.name = name;
		this.collection = collection;
		this.score = score;
		this.pokemonEquipped= null;
		this.turn = false;
	}

	
	public boolean isTurn() {
		return turn;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
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

	/*public void setCollection(Pokemon collection) {
		this.collection = collection;
	}*/

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public Pokemon getPokemonEquipped(){
		return pokemonEquipped;
	}

	/*public void getPokemonEquipped(){
		this.pokemonEquipped = pokemonEquipped;
	}*/
/* 
	public void addPokemon(Pokemon pokemon){
		collection.add(pokemon);
		if (pokemonEquipped.size() < 2){
			pokemonEquipped.add(pokemon);
		}

	}

	public void addCurrentlyPokemon(Pokemon pokemon){
		if(collection.size() < 2 && collection.contains(pokemon)){
			if(pokemonEquipped.contains(pokemon)){
				pokemonEquipped.add(pokemon);
			}
		}
	}

	public void removeCurrentPokemon(Pokemon pokemon){
		pokemonEquipped.remove(pokemon);
	}*/

	public void battlePokemon(Player POKEMON_COLLECTION){
		


	}



	public void Choose() {
	}


	public void Catch() {
	}
	
	
	
	@Override
	public String toString() {
		return String.format("Player [name=%s, collection=%s, score=%s]", name, collection, score);
	}



	public static Random getRandom() {
		return RANDOM;
	}



	public void setCollection(ArrayList<Pokemon> collection) {
		this.collection = collection;
	}


// This has to change to pull from collection
	public void setPokemonEquipped(Pokemon pokemonEquipped) {
		this.pokemonEquipped = pokemonEquipped;
	}

}