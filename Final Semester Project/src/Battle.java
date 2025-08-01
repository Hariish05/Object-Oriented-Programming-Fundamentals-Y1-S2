
public class Battle {

	private Pokemon opponent_pokemon;
	private Pokemon player_pokemon;
	
	public Battle() {
	}

	public Battle(Pokemon opponent_pokemon, Pokemon player_pokemon) {
		this.opponent_pokemon = opponent_pokemon;
		this.player_pokemon = player_pokemon;
	}

	public Pokemon getOpponent_pokemon() {
		return opponent_pokemon;
	}

	public void setOpponent_pokemon(Pokemon opponent_pokemon) {
		this.opponent_pokemon = opponent_pokemon;
	}

	public Pokemon getPlayer_pokemon() {
		return player_pokemon;
	}

	public void setPlayer_pokemon(Pokemon player_pokemon) {
		this.player_pokemon = player_pokemon;
	}

	public void startBattle() {
		
	}
	
	public void Loser() {
		
	}
	
	@Override
	public String toString() {
		return String.format("Battle [opponent_pokemon=%s, player_pokemon=%s]", opponent_pokemon, player_pokemon);
	}
}
