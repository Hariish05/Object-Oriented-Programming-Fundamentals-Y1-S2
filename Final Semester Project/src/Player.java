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
	public void removeFromCollection(String pokemon){
		collection.remove(Pokemon.getPokemonByName(pokemon));
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Pokemon getPokemonEquipped() {
		return pokemonTeam;
	}

	public void pokemonBattle(Pokemon pokemon) {
		if (Pokemon.getAllPokemon().contains(pokemon)) {
			pokemonTeam = pokemon;
		}

	}

	public boolean selectedPokemonforBattle(Scanner scanner) {
		if (collection.isEmpty()) {
			System.out.println("No pokemon available");
			return false;
		}

		List<Pokemon> availablePokemon = new ArrayList<>(); // creates a list to filter out pokemon thats not defeated
		for (Pokemon pokemon : collection) {
			if (!pokemon.isDefeat()) {
				availablePokemon.add(pokemon);
			}
		}

		if (availablePokemon.isEmpty()) {
			System.out.println("All of your pokemon are defeated");
			return false;
		}

		System.out.println("Select your pokemon for battle");

		for (int i = 0; i < availablePokemon.size(); i++) { // display pokemon thats available
			System.out.println((i + 1) + ". " + availablePokemon.get(i).toString());
		}

		while (true) {
			System.out.print("Choose your Pokemon 1-" + availablePokemon.size() + ": ");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

				if (choice > 1 && choice <= availablePokemon.size()) {
					pokemonTeam = availablePokemon.get(choice - 1); // sets pokemon to battle if the correctly input (-1
																	// is cause before i displayed in 1 2 3 but arryas
																	// start at 0 index so -1)
					System.out.println(pokemonTeam.getNickname() + " is ready");
					return true;
				} else {
					System.out.println("Invalid choice");
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
				scanner.nextLine();
			}
		}

	}

		// NEEDS IF STATEMENT TO PREVENT EXISTING PLAYER FROM SELECTING A STARTER EVERYTIME
	public static void chooseStarter(Player player) {
		ArrayList<Pokemon> selectedPokemon = Pokemon.getThreeRandomPokemon();
		List<String> names = new ArrayList<>();
		String name,ZMoveName;
		int choice,atk,def,speed,ZMoveAtk,tempChoice,count=1;
		int total = 3;
		int totalRows = 3;
		String pokemonType;

		Scanner input = new Scanner(System.in);

		for (int i = 0; i < total; i++) {
	        System.out.printf("%-40s", (i + 1) + ".");
		}
		System.out.println();

		for (int i = 0; i < total; i++) {
        	Pokemon p = selectedPokemon.get(i);
        	String type;
        	Class<?> temp = p.getClass();
			if (temp == Water.class) {
				pokemonType = "Water";
			} else if (temp == Electric.class) {
				pokemonType = "Electric";
			} else if (temp == Grass.class) {
				pokemonType = "Grass";
			} else if (temp == Fire.class) {
				pokemonType = "Fire";
			} else {
				pokemonType = "Normal";
			}
			System.out.printf("%-40s", "Type: " + pokemonType);
    		}

    		System.out.println();

	    	for (int i = 0; i < totalRows; i++) {
	        	System.out.printf("%-40s", "Name: " + selectedPokemon.get(i).getSpecies());
	        }
			System.out.println();
			
		    for(int i = 0; i< totalRows; i++) {
		    	System.out.printf("%-40s", "Attack: " + selectedPokemon.get(i).getAtk());      	
    	    }
		    System.out.println();
	        
		    for(int i = 0; i< totalRows; i++) {
    			System.out.printf("%-40s", "Defence: " + selectedPokemon.get(i).getDef());      	
    	    }
		    System.out.println();

    	    for(int i = 0; i< totalRows; i++) {
    			System.out.printf("%-40s", "Speed: " + selectedPokemon.get(i).getSpeed());      	
    	    }
    	    System.out.println();
	        
	        for(int i = 0; i< totalRows; i++) {
     		   	System.out.printf("%-40s", "ZMove Name: " + selectedPokemon.get(i).getZMove().getName());      	
     	    }
     	   System.out.println();     

			for(int i = 0; i< totalRows; i++) {
    			System.out.printf("%-40s", "ZMove Attack: " + selectedPokemon.get(i).getZMove().getBasePower());      	
        	}
			System.out.println("\n");   
			count++;

			System.out.println("Which pokemon would you like to take as a starter? (Enter 1, 2 or 3): ");
			while (true) {

				try {
					tempChoice = input.nextInt();
					if (tempChoice <= 0 || tempChoice > 3) {
						System.out.println("Invalid number. Try again.");
					} else {
						choice = tempChoice;
						break;
					}
				} catch (InputMismatchException e) {
					System.out.println("Enter a number.");
					input.next();
				}
			}

			// '-1' to match index
			Pokemon finalPokemon = selectedPokemon.get(choice - 1);
			player.setCollection(finalPokemon);
			FileManager.removePokemonFromTxt(finalPokemon);
			System.out.println(finalPokemon.getSpecies() + " has been added to your collection!");
			try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
	}

	public static void catchWildPokemon(Player player) {
		String name,ZMoveName;
		int choice,atk,def,speed,ZMoveAtk,tempChoice,count=1;
		int total = 3;
		int totalRows = 3;
		String pokemonType;

		if (player.getCollection().size() >=3){
			System.out.println("You do not have space to store anymore Pokemons!");
			try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
		}
		else{
			Scanner input = new Scanner(System.in);
			List<Pokemon> wildPokemonList = Pokemon.getThreeRandomPokemon();
			System.out.println();


			for (int i = 0; i < total; i++) {
	        	System.out.printf("%-40s", (i + 1) + ".");
			}
			System.out.println();

			for (int i = 0; i < total; i++) {
            	Pokemon p = wildPokemonList.get(i);
            	String type;
            	Class<?> temp = p.getClass();
				if (temp == Water.class) {
					pokemonType = "Water";
				} else if (temp == Electric.class) {
					pokemonType = "Electric";
				} else if (temp == Grass.class) {
					pokemonType = "Grass";
				} else if (temp == Fire.class) {
					pokemonType = "Fire";
				} else {
					pokemonType = "Normal";
				}

            	System.out.printf("%-40s", "Type: " + pokemonType);
    		}
    		System.out.println();


	    	for (int i = 0; i < totalRows; i++) {
	        	System.out.printf("%-40s", "Name: " + wildPokemonList.get(i).getSpecies());
	        }
			System.out.println();
			
		    for(int i = 0; i< totalRows; i++) {
		    	System.out.printf("%-40s", "Attack: " + wildPokemonList.get(i).getAtk());      	
    	    }
		    System.out.println();
	        
		    for(int i = 0; i< totalRows; i++) {
    			System.out.printf("%-40s", "Defence: " + wildPokemonList.get(i).getDef());      	
    	    }
		    System.out.println();

    	    for(int i = 0; i< totalRows; i++) {
    			System.out.printf("%-40s", "Speed: " + wildPokemonList.get(i).getSpeed());      	
    	    }
    	    System.out.println();
	        
	        for(int i = 0; i< totalRows; i++) {
     		   	System.out.printf("%-40s", "ZMove Name: " + wildPokemonList.get(i).getZMove().getName());      	
     	   }
     	   System.out.println();     

			for(int i = 0; i< totalRows; i++) {
    			System.out.printf("%-40s", "ZMove Attack: " + wildPokemonList.get(i).getZMove().getBasePower());      	
        	}
			System.out.println("\n");   
	    
			System.out.println("Which pokemon do you want to catch? (Enter 1, 2 or 3): ");
			while (true) {
				try {
					tempChoice = input.nextInt();
					if (tempChoice <= 0 || tempChoice > 3) {
						System.out.println("Invalid number. Try again.");
					} else {
						choice = tempChoice;
						break;
					}
				} catch (InputMismatchException e) {
					System.out.println("Enter a number.");
					input.next();
				}
			}
			Pokemon pokemonToAttemptCatch = wildPokemonList.get(choice-1);
			System.out.println("A random pokeball will be picked for you now...");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			Pokeball randomPokeball = Pokeball.getRandomPokeballByOdds();
			System.out.printf("You will attempt to catch this Pokemon with a %s!", randomPokeball.getBallName());
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			boolean caught = Pokeball.attemptCatch(randomPokeball);
			Pokeball.placeCaughtPokemonInCollection(caught, pokemonToAttemptCatch, player);
		}
	}

	@Override
	public String toString() {
		return String.format("Player name: %s, collection: %s, score: %s", name, collection, score);
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
