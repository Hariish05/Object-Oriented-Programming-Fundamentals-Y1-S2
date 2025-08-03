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
	
	public void startBattle() {
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
			//tryCapture();
		}
	}

	//public void startBattle(Pokemon playerPokemon) {
	//	opponentPokemon = Pokemon.getRandomPokemon();
	//	while (playerPokemon.getHp() == 0 || opponentPokemon.getHp() == 0){
			
	//	}

		// if( player.getspeed > opponentPokemon.getSpeed)
		// 	playerTurn = true;
		// 	break SKETCH LOGIC FOR WHO GOES FIRST
		// else {
		// 	firstP =computer

		// }
	//}
	
	// public Pokemon getOpponent_pokemon() {
	// 	return opponent_pokemon;
	// }

	// public void setOpponent_pokemon(Pokemon opponent_pokemon) {
	// 	this.opponent_pokemon = opponent_pokemon;
	// }
	
	
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
		int damage = calculateDamage(playerPokemon.getAtk());
		opponentPokemon.reduceHp(damage);
		System.out.println("You attacked " + opponentPokemon.getNickname() + " for " + damage + " damage!");
	}
	
	private void opponentAttack() {
		int damage = calculateDamage(opponentPokemon.getAtk());
		playerPokemon.reduceHp(damage);
		System.out.println(opponentPokemon.getNickname() + " attacked you for " + damage + " damage!");
	}

	private int calculateDamage(int baseAttack) {
		// RNG between 80% to 100% of Damage
		return baseAttack * (80 + rand.nextInt(31)) / 100;
	}
	
	//private void tryCapture() {
	//	System.out.println("Trying to capture " + opponentPokemon.getNickname() + "...");
	//	int chance = 50 - opponentPokemon.getHp(); // Lower HP = better chance
	//	if (rand.nextInt(100) < chance) {
	//		System.out.println("Gotcha! You caught " + opponentPokemon.getNickname() + "!");
	//	} else {
	//		System.out.println(opponentPokemon.getNickname() + " escaped...");
	//	}
	//}
	
	@Override
	public String toString() {
		return String.format("Battle [opponentPokemon=%s, playerPokemon=%s]", opponentPokemon, playerPokemon);
	}
}
