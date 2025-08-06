import java.util.Random;
class Pokeball {
	public static final Random RANDOM = new Random();
	private String ballName;
	private double strength;
	
	public Pokeball(String ballName, double strength) {
		this.ballName = ballName;
		this.strength = strength;
	}

	// Pokeball object creation

	public static final Pokeball POKEBALL = new Pokeball("Pokeball",0.4);
	public static final Pokeball GREATBALL = new Pokeball("Great ball",0.6); 
	public static final Pokeball ULTRABALL = new Pokeball("Ultra Ball",0.8);
	public static final Pokeball MASTERBALL = new Pokeball("Master ball",1.0); 

	public String getBallName() {
		return ballName;
	}
	public double getStrength() {
		return strength;
	}
	
	public static Pokeball getRandomPokeballByOdds() {
	   // Ranges from 1 to 100, to allow random odds for which pokeball is selected for capturing.
		int roll = RANDOM.nextInt(100) + 1;

	    if (roll <= 5) { //5 or lower is masterball
	        return MASTERBALL;
	    } else if (roll <= 20) { //20 or lower is ultraball
	        return ULTRABALL;
	    } else if (roll <= 50) { //50 or lower is greatball
	        return GREATBALL;
	    } else {
	        return POKEBALL; //if nothing hits, then it is pokeball
	    }
	}
	
	public static boolean attemptCatch(Pokeball pokeball){
		double catchRoll = RANDOM.nextDouble();
		boolean caught = catchRoll <= pokeball.getStrength();
		return  caught;
	}
	public static void placeCaughtPokemonInCollection(boolean caught,Pokemon pokemon,Player player) throws InterruptedException{
		if(caught){
			player.setCollection(pokemon);
			System.out.printf("\nYou have caught %s!\n", pokemon.getSpecies());
			Thread.sleep(1000);
			System.out.printf("%s has been added to your collection!\n",pokemon.getSpecies());
			Thread.sleep(2000);
			FileManager.removePokemonFromTxt(pokemon);
			FileManager.addPokemonToPlayerTxt(pokemon);

		} else {
			System.out.printf("\nYour attempt to catch %s has failed!\n", pokemon.getSpecies());
			Thread.sleep(2000);
		}
	}

	public String toString() {
		return String.format("Pokeball:\ntype=%s\nstrength=%f\n]", ballName, strength);
	}
}
