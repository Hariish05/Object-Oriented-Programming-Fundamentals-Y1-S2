import java.util.*;
public class Battle {
	private Pokemon opponentPokemon;
	private Pokemon playerPokemon;
	private Random rand;
	

	public Battle() {
	}

	public Battle(Pokemon opponentPokemon, Pokemon playerPokemon) {
		this.opponentPokemon = opponentPokemon;
		this.playerPokemon = playerPokemon;
		this.rand = new Random();
	}
	
	public void startBattle(Player player) {
		int choice=0,tempChoice=0;

		System.out.println("A wild " + opponentPokemon.getNickname() + " appeared!");

		// Randomize who goes first based on speed and some luck
		boolean playerTurn = determineFirstTurn();

		while (playerPokemon.getHp() > 0 && opponentPokemon.getHp() > 0) {
			if (playerTurn) {
				playerAttack();
			} else {
				opponentAttack();
			}
			playerTurn = !playerTurn;
		}

		if (playerPokemon.getHp() <= 0) {
			System.out.println("You lost the battle...");
		} else {
			System.out.println("You defeated " + opponentPokemon.getNickname() + "!");
			if (player.getCollection().size() >=3){
				System.out.printf("Despite defeating %s, you do not have space to attempt capturing it :(", opponentPokemon.getSpecies());
			}else{
				System.out.printf("Do you want to attempt capturing %s? (1 for YES, 2 for NO): ",opponentPokemon.getSpecies());
				while (true) {
					Scanner input = new Scanner(System.in);
					try {
						tempChoice = input.nextInt();
						if (tempChoice <= 0 || tempChoice > 2) {
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
				if (choice == 1){
					tryCapture(player);
				}else{
					System.out.println("Returning...");
				}
				
			}
		}
	}
	
	private void tryCapture(Player player) {
	    if (opponentPokemon.getHp() > 0) {
	        System.out.println("You can only catch a Pokémon after it's fainted!");
	        return;
	    }
		System.out.println("A random Pokeball will be picked for you...");
	    Pokeball pokeball = Pokeball.getRandomPokeballByOdds();
		System.out.printf("You will attempt to catch %s with a %s...",opponentPokemon.getSpecies(),pokeball.getBallName());

	    boolean caught = Pokeball.attemptCatch(pokeball);
		Pokeball.placeCaughtPokemonInCollection(caught, opponentPokemon,player);
	}	
	
	private boolean determineFirstTurn() {
		int playerRoll = playerPokemon.getSpeed() + rand.nextInt(10);
		int opponentRoll = opponentPokemon.getSpeed() + rand.nextInt(10);
		System.out.println("Determining turn order...");
		return playerRoll >= opponentRoll;
	}

	public Pokemon getPlayerPokemon() {
		return playerPokemon;
	}

	public void setPlayerPokemon(Pokemon playerpokemon) {
		this.playerPokemon = playerpokemon;
	}
	
	private void playerAttack() {
		int damage = calculateDamage((2*1+10)/250 * playerPokemon.getAtk() / playerPokemon.getDef() + 2);
		opponentPokemon.reduceHp(damage);
		System.out.println("You attacked " + opponentPokemon.getNickname() + " for " + damage + " damage!" +  " (" + opponentPokemon.getHp() + "/" + opponentPokemon.getMaxHp() + ")");
	}
	
	private void opponentAttack() {
		int damage = calculateDamage((2*1+10)/250 * opponentPokemon.getAtk() / opponentPokemon.getDef() + 2);
		playerPokemon.reduceHp(damage);
		System.out.println(opponentPokemon.getNickname() + " attacked you for " + damage + " damage!" +  " (" + playerPokemon.getHp() + "/" + playerPokemon.getMaxHp() + ")");
	}
	
	private int calculateDamage(int baseAttack) {
		// RNG between 80% to 100% of Damage
		return baseAttack * (80 + rand.nextInt(31)) / 100;
	}

	@Override
	public String toString() {
		return String.format("Battle [opponentPokemon=%s, playerPokemon=%s]", opponentPokemon, playerPokemon);
	}
}
