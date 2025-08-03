import java.util.*;

public class Player {
	public static final Random RANDOM = new Random();
	private String name;
	private List<Pokemon> collection;
	private Pokemon pokemonTeam;
	private int score;
	private boolean turn;

	public Player(String name) {
		this.name = name;
		this.collection = new ArrayList<>();
		this.score = 0;
		this.pokemonTeam = null;
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

	public List<Pokemon> getCollection() {
		return collection;
	}

	/*
	 * public void setCollection(Pokemon collection) {
	 * this.collection = collection;
	 * }
	 */

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Pokemon getPokemonEquipped() {
		return pokemonTeam;
	}

	/*
	 * public void addPokemon(Pokemon pokemon){
	 * collection.add(pokemon);
	 * if (pokemonEquipped.size() < 2){
	 * pokemonEquipped.add(pokemon);
	 * }
	 * 
	 * }
	 * 
	 * public void addCurrentlyPokemon(Pokemon pokemon){
	 * if(collection.size() < 2 && collection.contains(pokemon)){
	 * if(pokemonEquipped.contains(pokemon)){
	 * pokemonEquipped.add(pokemon);
	 * }
	 * }
	 * }
	 * 
	 * public void removeCurrentPokemon(Pokemon pokemon){
	 * pokemonEquipped.remove(pokemon);
	 * }
	 */

	public void pokemonBattle(Pokemon pokemon) {
		if (Pokemon.getAllPokemon().contains(pokemon)) {
			pokemonTeam = pokemon;
		}

	}

	public static void Choose(Player player) {
		ArrayList<Pokemon> selectedPokemon = Pokemon.getThreeRandomPokemon();
		int count = 1;
		int choice, tempChoice;

		Scanner input = new Scanner(System.in);
		for (Pokemon i : selectedPokemon) {
			String name = i.getSpecies();
			int atk = i.getAtk();
			int def = i.getDef();
			int speed = i.getSpeed();
			ZMoves ZMove = i.getZMove();
			Class<?> temp = i.getClass();
			String pokemonType;

			if (temp == Water.class) {
				pokemonType = "Water";
			} else if (temp == Electric.class) {
				pokemonType = "Electric";
			} else if (temp == Grass.class) {
				pokemonType = "Grass";
			} else if (temp == Fire.class) {
				pokemonType = "Fire";
			} else {
				pokemonType = "Unknown";
			}

			System.out.printf("%d.\nType: %s\nName: %s\nAttack: %d\nDefense: %d\nSpeed: %d\n%s", count, pokemonType,
					name, atk, def, speed, ZMove.toString());
			count++;
		}
		System.out.println("Which pokemon would you like to take as a starter? (Enter 1,2 or 3): ");
		while (true) {
			try {
				tempChoice = input.nextInt();
				if (tempChoice <= 0 || tempChoice > 3) {
				} else {
					choice = tempChoice;
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter a number.");
				input.next();
			}
		}
		Pokemon finalPokemon = selectedPokemon.get(choice);
		player.setCollection(finalPokemon);
		System.out.println("Pokemon has been added to your collection!");

		input.close();
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

	public void setCollection(Pokemon pokemon) {
		this.collection.add(pokemon);
	}

	// This has to change to pull from collection
	public void setPokemonEquipped(Pokemon pokemonEquipped) {
		this.pokemonTeam = pokemonEquipped;
	}

}