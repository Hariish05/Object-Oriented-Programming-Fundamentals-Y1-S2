import java.util.*;

public class Battle {
	private Pokemon opponentPokemon;
	private Pokemon playerPokemon;
	private Random rand;
	private boolean zMoveUsed = false;
	private boolean opponentZMoveUsed = false;
	private int zMoveAttempts = 0;
	private int score;

	public Battle() {
	}

	public Battle(Pokemon opponentPokemon, Pokemon playerPokemon) {
		this.opponentPokemon = opponentPokemon;
		this.playerPokemon = playerPokemon;
		this.rand = new Random();
		this.score = 0;
	}

	public void startBattle(Player player) throws InterruptedException {
		zMoveUsed = false;
		opponentZMoveUsed = false;
		zMoveAttempts = 0;
		int choice = 0, tempChoice = 0, score = 0;
		Pokemon.restoreAllPokemonHp();

		System.out.println("A wild " + opponentPokemon.getNickname() + " appeared!");
		Thread.sleep(1000);

		// Randomize who goes first based on speed and some luck
		boolean playerTurn = determineFirstTurn();

		while (playerPokemon.getHp() > 0 && opponentPokemon.getHp() > 0) {
			if (playerTurn) {
				score += playerAttack();
			} else {
				opponentAttack();
			}
			playerTurn = !playerTurn;
		}

		if (playerPokemon.getHp() <= 0) {
			System.out.println("You lost the battle...");
			FileManager.addBattleScore(score);
		} else {
			System.out.println("You defeated " + opponentPokemon.getNickname() + "!");
			System.out.printf("Your battle score was %d!\n", score);
			FileManager.addBattleScore(score);
			Thread.sleep(1000);
			if (player.getCollection().size() >= 3) {
				System.out.printf("Despite defeating %s, you do not have space to attempt capturing it :(",
						opponentPokemon.getSpecies());
			} else {
				System.out.printf("Do you want to attempt capturing %s? (1 for YES, 2 for NO): ",
						opponentPokemon.getSpecies());
				while (true) {
					Scanner input = new Scanner(System.in);
					try {
						tempChoice = input.nextInt();
						if (tempChoice <= 0 || tempChoice > 2) {
							System.out.println("Invalid number. Try again.");
							Thread.sleep(1000);
						} else {
							choice = tempChoice;
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("Enter a number.");
						input.next();
					}
				}
				if (choice == 1) {
					tryCapture(player);
				} else {
					System.out.println(opponentPokemon.getNickname() + " has escaped!");
					Thread.sleep(2000);
				}

			}
		}
	}

	private void tryCapture(Player player) throws InterruptedException {
		System.out.println("A random Pokeball will be picked for you...");
		Thread.sleep(1000);
		Pokeball pokeball = Pokeball.getRandomPokeballByOdds();
		System.out.printf("You will attempt to catch %s with a %s...", opponentPokemon.getSpecies(),
				pokeball.getBallName());
		Thread.sleep(1000);

		boolean caught = Pokeball.attemptCatch(pokeball);
		Pokeball.placeCaughtPokemonInCollection(caught, opponentPokemon, player);
	}

	private boolean determineFirstTurn() throws InterruptedException{
		int playerRoll = playerPokemon.getSpeed() + rand.nextInt(10);
		int opponentRoll = opponentPokemon.getSpeed() + rand.nextInt(10);
		System.out.println("Determining turn order...");
		Thread.sleep(1000);
		return playerRoll >= opponentRoll;
	}

	public Pokemon getPlayerPokemon() {
		return playerPokemon;
	}

	public void setPlayerPokemon(Pokemon playerpokemon) {
		this.playerPokemon = playerpokemon;
	}

	private int playerAttack() throws InterruptedException{
	int score=0;
	if (!zMoveUsed && zMoveAttempts < 3) {
		int[] chances = { 75, 90, 100 };
		System.out.printf("Do you want to use a Z-Move? (1 for YES, 2 for NO) Current odds: %d%%\n",
				chances[zMoveAttempts]);
		int choice = getValidatedChoice(1, 2);

		if (choice == 1) {
			boolean hit = attemptZMoveHit();
			if (hit) {
				int damage = calculateZMoveDamage(playerPokemon.getZMove());
				opponentPokemon.reduceHp(damage);
				
				// Show effectiveness message
				double effectiveness = Pokemon.getTypeEffectiveness(playerPokemon, opponentPokemon);
				String effectivenessMsg = getEffectivenessText(effectiveness);

				System.out.printf("You used Z-Move %s! It hit and dealt %d damage!%s\n",
					playerPokemon.getZMove().getName(), damage, effectivenessMsg);

				Thread.sleep(1000);
				zMoveUsed = true;
				score = calculateScore(0,hit,damage);
				return score;
			} else {
				int damage = 0;
				System.out.println("Z-Move missed! You'll get another chance next turn.");
				zMoveAttempts++;
				Thread.sleep(1000);
				score = calculateScore(0,hit,damage);
			}
		}
	}

	// If ZMove fails
	double effectiveness = Pokemon.getTypeEffectiveness(playerPokemon, opponentPokemon);
	int damage = calculateDamage(playerPokemon.getAtk(), opponentPokemon.getDef(), effectiveness);
	opponentPokemon.reduceHp(damage);
	String effectivenessMsg = getEffectivenessText(effectiveness);
	System.out.println("You attacked " + opponentPokemon.getNickname() + " for " + damage + " damage!" + effectivenessMsg + " (" + opponentPokemon.getHp() + "/" + opponentPokemon.getMaxHp() + ")");
	Thread.sleep(1000);
	score += calculateScore(damage,false,0);
		return score;
	}

	private void opponentAttack() throws InterruptedException {
	if (!opponentZMoveUsed && opponentPokemon.getZMove() != null) {
		int damage = calculateZMoveDamage(opponentPokemon.getZMove());
		playerPokemon.reduceHp(damage);
		double effectiveness = Pokemon.getTypeEffectiveness(opponentPokemon, playerPokemon);
		String effectivenessMsg = getEffectivenessText(effectiveness);
		System.out.printf("%s used its Z-Move %s! It dealt %d damage to you!%s\n",
			opponentPokemon.getNickname(), opponentPokemon.getZMove().getName(), damage, effectivenessMsg);

		opponentZMoveUsed = true;
	} else {
		double effectiveness = Pokemon.getTypeEffectiveness(opponentPokemon, playerPokemon);
		int damage = calculateDamage(opponentPokemon.getAtk(), playerPokemon.getDef(), effectiveness);
		playerPokemon.reduceHp(damage);
		String effectivenessMsg = getEffectivenessText(effectiveness);
		System.out.println(opponentPokemon.getNickname() + " attacked you for " + damage + " damage!" +
		" (" + playerPokemon.getHp() + "/" + playerPokemon.getMaxHp() + ")" + effectivenessMsg);
	}
	Thread.sleep(1000);
}

	public static int calculateScore(int damage,boolean zMove, int zMoveDamage){
		int score=0;
		if (damage >20 ){
			score += 100;
		} else {
			score += 50;
		}
		if (zMove ==true && zMoveDamage > 30){
			score += 200;
		} else{
			score += 20; 
		}
		return score;
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

	private boolean attemptZMoveHit() {
		int[] chances = { 75, 90, 100 };
		int roll = rand.nextInt(100) + 1;
		return roll <= chances[zMoveAttempts];
	}

	private int calculateDamage(int attackerAtk, int defenderDef, double effectiveness) {
		int power = 10;
		double random = 0.85 + rand.nextDouble() * 0.15;
		if (defenderDef < 1) defenderDef = 1;
		double baseDamage = ((attackerAtk * power) / (double) defenderDef);
		double totalDamage = baseDamage * random * effectiveness;
		return Math.max(1, (int) totalDamage);
	}

	private int calculateZMoveDamage(ZMoves move) {
		double effectiveness = Pokemon.getTypeEffectiveness(playerPokemon, opponentPokemon);
		int ZMoveDamage = move.getBasePower();
		return (int) (ZMoveDamage * effectiveness / 6);
	}

	public static Pokemon playerSelectPokemonFromCollection(Player player) {
		Pokemon playerPokemon;
		List<Pokemon> playerPokemons = player.getCollection();
		String name,ZMoveName;
		int choice,atk,def,ZMoveAtk,count=1;
		int total = playerPokemons.size();
		int totalRows = 3;
		
		System.out.println();
	    for (int i = 0; i < total; i = i + totalRows) {

	        for (int j = 0; j < totalRows; j++) {
				if (i + j < total)
	            System.out.printf("%-40s", (i + j + 1) + ".");
	        } 
	        System.out.println();
	        
	        for (int j = 0; j < totalRows; j++) {
				if (i + j < total)
	            System.out.printf("%-40s", "Name: " + playerPokemons.get(i + j).getSpecies());
	        }
	        System.out.println();
	        
	        for(int j = 0; j< totalRows; j++) {
				if (i + j < total)
	        	System.out.printf("%-40s", "Attack: " + playerPokemons.get(i+j).getAtk());      	
	        }
	        System.out.println();
	        
	        for(int j = 0; j< totalRows; j++) {
				if (i + j < total)
        		System.out.printf("%-40s", "Defence: " + playerPokemons.get(i+j).getDef());      	
	        }
	        System.out.println();

	        for(int j = 0; j< totalRows; j++) {
				if (i + j < total)
        		System.out.printf("%-40s", "Speed: " + playerPokemons.get(i+j).getSpeed());      	
	        }
	        System.out.println();
	        
	        for(int j = 0; j< totalRows; j++) {
				if (i + j < total)
	        	System.out.printf("%-40s", "ZMove Name: " + playerPokemons.get(i+j).getZMove().getName());      	
	        }
	        System.out.println();     

	        for(int j = 0; j< totalRows; j++) {
				if (i + j < total)
        		System.out.printf("%-40s", "ZMove Attack: " + playerPokemons.get(i+j).getZMove().getBasePower());      	
	        }
	        System.out.println("\n");
			count++;

	    }

		System.out.printf("\nWhich Pokemon would you like to fight with? (1-%d): ",total);
		choice = getValidatedChoice(1, total);
		System.out.printf("\nYou will fight alongside %s!\n", playerPokemons.get(choice - 1).getSpecies());
		playerPokemon = Pokemon.getPokemonByName(playerPokemons.get(choice - 1).getSpecies());
		return playerPokemon;
	}

	private String getEffectivenessText(double effectiveness) {
	if (effectiveness > 1.0) {
		return " It's super effective!";
	} else if (effectiveness < 1.0) {
		return " It's not very effective...";
	}
	return ""; // Neutral effectiveness
}

	@Override
	public String toString() {
		return String.format("Battle [opponentPokemon=%s, playerPokemon=%s]", opponentPokemon, playerPokemon);
	}
}
