import java.util.*;
public class Battle {

	public static final Scanner SCANNER = new Scanner(System.in);
	
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
			tryCapture(player);
		}
	}
	
	private void tryCapture(Player player) {
	    if (opponentPokemon.getHp() > 0) {
	        System.out.println("You can only catch a Pokémon after it's fainted!");
	        return;
	    }

	    Pokeball pokeball = Pokeball.getRandomPokeballByOdds();
	    System.out.println("You threw a " + pokeball.getBallName() + "!");

	    boolean caught = Pokeball.attemptCatch(pokeball);

	    if (caught) {
	        System.out.println("Congratulations! You caught " + opponentPokemon.getNickname() + "!");
	        player.getCollection().add(opponentPokemon); // Add to player's collection
	    } else {
	        System.out.println(opponentPokemon.getNickname() + " broke free!");
	    }
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
		int damage = ((2*1+10)/250 * playerPokemon.getAtk() / playerPokemon.getDef() + 2);
		opponentPokemon.reduceHp(damage);
		System.out.println("You attacked " + opponentPokemon.getNickname() + " for " + damage + " damage!");
	}
	
	private void opponentAttack() {
		int damage = ((2*1+10)/250 * opponentPokemon.getAtk() / opponentPokemon.getDef() + 2);
		playerPokemon.reduceHp(damage);
		System.out.println(opponentPokemon.getNickname() + " attacked you for " + damage + " damage!");
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