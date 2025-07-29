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
	
	public static boolean attemptCatch(Pokeball pokeball){
		double catchRoll = RANDOM.nextDouble();
		boolean caught = catchRoll <= pokeball.getStrength();
		return  caught;
	}

	public String toString() {
		return String.format("Pokeball:\ntype=%s\nstrength=%f\n]", ballName, strength);
	}
}