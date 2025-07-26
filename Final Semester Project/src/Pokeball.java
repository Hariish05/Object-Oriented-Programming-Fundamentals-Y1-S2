
public class Pokeball {
	
	private String type;
	private int strength;
	
	public Pokeball() {
	}

	
	public Pokeball(String type, int strength) {
		this.type = type;
		this.strength = strength;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void attemptCatch() {
		
	}
	
	@Override
	public String toString() {
		return String.format("Pokeball [type=%s, strength=%s]", type, strength);
	}
	
	

}
