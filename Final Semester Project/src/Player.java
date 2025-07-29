
import java.util.*;

public class Player {
	
	
	private String name;
	private List<Pokemon> collection;
	private List<Pokemon> pokemonEquipped;
	private int score;

	
	public Player() {
	}

	public Player(String name, Pokemon collection, int score, Pokemon pokemonEquipped) {
		this.name = name;
		this.collection = new ArrayList<>();
		this.score = score;
		this.pokemonEquipped = new ArrayList<>();
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pokemon> getCollection() {
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

	public List<Pokemon> getPokemonEquipped(){
		return pokemonEquipped;
	}

	/*public void getPokemonEquipped(){
		this.pokemonEquipped = pokemonEquipped;
	}*/

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
