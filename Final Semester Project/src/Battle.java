import java.util.*;

public class Battle {
	private Pokemon opponentPokemon;
	private Pokemon playerPokemon;
	private Random rand;
	private boolean zMoveUsed = false;
	private boolean opponentZMoveUsed = false;
	private int zMoveAttempts = 0;

	public Battle() {
	}

	public Battle(Pokemon opponentPokemon, Pokemon playerPokemon) {
		this.opponentPokemon = opponentPokemon;
		this.playerPokemon = playerPokemon;
		this.rand = new Random();
	}

	public void startBattle(Player player) throws InterruptedException {
		zMoveUsed = false;
		opponentZMoveUsed = false;
		zMoveAttempts = 0;
		int choice = 0, tempChoice = 0;

		System.out.println("A wild " + opponentPokemon.getNickname() + " appeared!");
		Thread.sleep(1000);

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
				}

			}
		}
	}

	private void tryCapture(Player player) throws InterruptedException {
		if (opponentPokemon.getHp() > 0) {
			System.out.println("You can only catch a Pokémon after it's fainted!");
			return;
		}
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

	private void playerAttack() throws InterruptedException{
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
					System.out.printf("You used Z-Move %s! It hit and dealt %d damage!\n",
							playerPokemon.getZMove().getName(), damage);
					Thread.sleep(1000);
					zMoveUsed = true;
					return;
				} else {
					System.out.println("Z-Move missed! You'll get another chance next turn.");
					zMoveAttempts++;
					Thread.sleep(1000);
				}
			}
		}

		// If ZMove fails
		int damage = calculateDamage(playerPokemon.getAtk());
		opponentPokemon.reduceHp(damage);
		System.out.println("You attacked " + opponentPokemon.getNickname() + " for " + damage + " damage!" +
				" (" + opponentPokemon.getHp() + "/" + opponentPokemon.getMaxHp() + ")");
		Thread.sleep(1000);
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

	private void opponentAttack() throws InterruptedException {
		if (!opponentZMoveUsed && opponentPokemon.getZMove() != null) {
			// Uses ZMove on its first turn
			int damage = calculateZMoveDamage(opponentPokemon.getZMove());
			playerPokemon.reduceHp(damage);
			System.out.printf("%s used its Z-Move %s! It dealt %d damage to you!\n",
					opponentPokemon.getNickname(), opponentPokemon.getZMove().getName(), damage);
			opponentZMoveUsed = true;
		} else {
			int damage = calculateDamage(opponentPokemon.getAtk());
			playerPokemon.reduceHp(damage);
			System.out.println(opponentPokemon.getNickname() + " attacked you for " + damage + " damage!" +
					" (" + playerPokemon.getHp() + "/" + playerPokemon.getMaxHp() + ")");
		}
		Thread.sleep(1000);
	}

	private int calculateDamage(int baseAttack) {
		// RNG between 20% to 70% of Damage
		return baseAttack * (10 + rand.nextInt(51)) / 100;
	}

	private int calculateZMoveDamage(ZMoves move) {
		double effectiveness = Pokemon.getTypeEffectiveness(playerPokemon, opponentPokemon);
		int ZMoveDamage = move.getBasePower();
		return (int) (ZMoveDamage * effectiveness / 18);
	}
	public static Pokemon playerSelectPokemonFromCollection(Player player) {
		Pokemon playerPokemon;
		List<Pokemon> playerPokemons = player.getCollection();
		String name,ZMoveName;
		int choice,atk,def,ZMoveAtk,count=1;

		for(Pokemon i:playerPokemons){
			name = i.getSpecies();
			ZMoveName = i.getZMove().getName();
			ZMoveAtk = i.getZMove().getBasePower();
			atk = i.getAtk();
			def=i.getDef();
			System.out.printf("%d.\nName: %s\nAttack: %d\nDefense: %d\nZMove Name: %s\nZMove Attack: %d",count,name,atk,def,ZMoveName,ZMoveAtk);
			count++;
		}
		count--;
		System.out.printf("\nWhich Pokemon would you like to fight with? (1-%d): ",count);
		choice = getValidatedChoice(1, count);
		System.out.printf("\nYou will fight alongside %s!\n",playerPokemons.get(count-1).getSpecies());
		playerPokemon = Pokemon.getPokemonByName(playerPokemons.get(count-1).getSpecies());
		return playerPokemon;
	}

	@Override
	public String toString() {
		return String.format("Battle [opponentPokemon=%s, playerPokemon=%s]", opponentPokemon, playerPokemon);
	}
}
