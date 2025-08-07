import java.util.*;

public class Player {
	public static final Random RANDOM = new Random();
	private String name;
	private List<Pokemon> collection;

	public Player(String name) {
		this.name = name;
		this.collection = new ArrayList<>();
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

	public static void chooseStarter(Player player) {
		ArrayList<Pokemon> selectedPokemon = Pokemon.getThreeRandomPokemon();
		int choice,tempChoice;
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
        	Class<?> temp = p.getClass(); //Creates a variable that holds the name of the class in an unspecfic variable type
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
     	    System.out.println("\n");      

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
		int choice,tempChoice;
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

	public static int getValidatedChoice(int min, int max) {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		while (true) {
			try {
				choice = input.nextInt();
				if (choice >= min && choice <= max)
					break;
				else
					System.out.println("Invalid choice. Try again.");
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number.");
				input.next(); // clear invalid input
			}
		}
		return choice;
	}

	@Override
	public String toString() {
		return String.format("Player name: %s, collection: %s", name, collection);
	}
	
	public void setCollection(Pokemon pokemon) {
		this.collection.add(pokemon);
	}
}
