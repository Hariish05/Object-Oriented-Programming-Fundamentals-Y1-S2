import java.util.*;

public class Battle {
	private Pokemon opponentPokemon;
	private Pokemon playerPokemon;
	private Random rand;
	private boolean zMoveUsed = false;
	private int zMoveAttempts = 0;

	public Battle() {
	}

	public Battle(Pokemon opponentPokemon, Pokemon playerPokemon) {
		this.opponentPokemon = opponentPokemon;
		this.playerPokemon = playerPokemon;
		this.rand = new Random();
	}

	public void startBattle(Player player) {
		zMoveUsed = false;
		zMoveAttempts = 0;
		int choice = 0, tempChoice = 0;

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

	private void tryCapture(Player player) {
		if (opponentPokemon.getHp() > 0) {
			System.out.println("You can only catch a Pokémon after it's fainted!");
			return;
		}
		System.out.println("A random Pokeball will be picked for you...");
		Pokeball pokeball = Pokeball.getRandomPokeballByOdds();
		System.out.printf("You will attempt to catch %s with a %s...", opponentPokemon.getSpecies(),
				pokeball.getBallName());

		boolean caught = Pokeball.attemptCatch(pokeball);
		Pokeball.placeCaughtPokemonInCollection(caught, opponentPokemon, player);
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
					zMoveUsed = true;
					return;
				} else {
					System.out.println("Z-Move missed! You'll get another chance next turn.");
					zMoveAttempts++;
				}
			}
		}

		// If ZMove fails
		int damage = calculateDamage(playerPokemon.getAtk());
		opponentPokemon.reduceHp(damage);
		System.out.println("You attacked " + opponentPokemon.getNickname() + " for " + damage + " damage!" +
				" (" + opponentPokemon.getHp() + "/" + opponentPokemon.getMaxHp() + ")");
	}

	private int getValidatedChoice(int min, int max) {
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

	private void opponentAttack() {
		int damage = calculateDamage(opponentPokemon.getAtk());
		playerPokemon.reduceHp(damage);
		System.out.println(opponentPokemon.getNickname() + " attacked you for " + damage + " damage!" + " ("
				+ playerPokemon.getHp() + "/" + playerPokemon.getMaxHp() + ")");
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

	@Override
	public String toString() {
		return String.format("Battle [opponentPokemon=%s, playerPokemon=%s]", opponentPokemon, playerPokemon);
	}
}
