import java.lang.*;
public class Battle {

	private Pokemon opponentPokemon;
	private Pokemon playerPokemon;
	private int pokemonNumber;
	

	public Battle() {
	}

	public Battle(Pokemon opponentPokemon, Pokemon playerPokemon) {
		this.opponentPokemon = opponentPokemon;
		this.playerPokemon = playerPokemon;
	}

	// public Pokemon getOpponent_pokemon() {
	// 	return opponent_pokemon;
	// }

	// public void setOpponent_pokemon(Pokemon opponent_pokemon) {
	// 	this.opponent_pokemon = opponent_pokemon;
	// }

	public Pokemon getPlayerPokemon() {
		return playerPokemon;
	}

	public void setPlayerPokemon(Pokemon playerpokemon) {
		this.playerPokemon = playerpokemon;
	}

	public void startBattle(Pokemon playerPokemon) {
		opponentPokemon = Pokemon.getRandomPokemon();
		while (playerPokemon.getHp() == 0 || opponentPokemon.getHp() == 0){
			
		}

		// if( player.getspeed > opponentPokemon.getSpeed)
		// 	playerTurn = true;
		// 	break SKETCH LOGIC FOR WHO GOES FIRST
		// else {
		// 	firstP =computer

		// }
	}
	
	public void Loser() {
		
	}
	
	@Override
	public String toString() {
		return String.format("Battle [opponentPokemon=%s, playerPokemon=%s]", opponentPokemon, playerPokemon);
	}
}
