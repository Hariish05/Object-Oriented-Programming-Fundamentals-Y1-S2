import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Pokemon {
	protected String nickname;
	protected boolean defeat;
	protected int level;
	protected int hp;
	protected int maxHp;
	protected int atk;
	protected int def;
	protected int speed;
    protected String species;
	protected ZMoves ZMove;

	public Pokemon(String nickname, int maxHp, int atk, int def, int speed, String species,ZMoves ZMove) {
		this.nickname = nickname;
		this.maxHp = maxHp;
		this.atk = atk;
		this.def = def;
		this.speed = speed;
		this.species = species;
		this.hp = this.maxHp;
		this.level = 1;
		this.defeat = false;
		this.ZMove = ZMove;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public boolean isDefeat() {
		return defeat;
	}
	public void setDefeat(boolean defeat) {
		this.defeat = defeat;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getSpeed() {
		return speed;
	}
	public String getSpecies() {
		return species;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public abstract void useTypeMove();
	public abstract String toString();

	public static double getTypeEffectiveness(Pokemon attacker, Pokemon defender){
		Class<?> attackerType = attacker.getClass();
		Class<?> defenderType = defender.getClass(); 
		if (attackerType ==Water.class && defenderType == Grass.class ){
			return 0.5;
		} else if (attackerType == Water.class && defenderType == Fire.class){
			return 2.0;
		} else if(attackerType == Water.class && defenderType == Water.class){
			return 0.5;
		} else if(attackerType == Water.class && defenderType == Electric.class){
			return 1.0;
		} else if (attackerType == Fire.class && defenderType == Fire.class){
        	return 0.5;
    	} else if (attackerType == Fire.class && defenderType == Water.class){
        	return 0.5;
    	} else if (attackerType == Fire.class && defenderType == Grass.class){
        	return 2.0;
    	} else if (attackerType == Fire.class && defenderType == Electric.class){
        	return 1.0;
    	} else if (attackerType == Grass.class && defenderType == Fire.class){
        	return 0.5;
		} else if (attackerType == Grass.class && defenderType == Water.class){
			return 2.0;
		} else if (attackerType == Grass.class && defenderType == Grass.class){
			return 0.5;
		} else if (attackerType == Grass.class && defenderType == Electric.class){
			return 1.0;
		}else if (attackerType == Electric.class && defenderType == Fire.class){
			return 1.0;
		} else if (attackerType == Electric.class && defenderType == Water.class){
			return 2.0;
		} else if (attackerType == Electric.class && defenderType == Grass.class){
			return 1.0;
		} else if (attackerType == Electric.class && defenderType == Electric.class){
			return 0.5;
		} else {
			return 1.0;
		}
	}
	public static List<Pokemon> getAllPokemon() {
        List<Pokemon> allPokemonList = new ArrayList<>();
        allPokemonList.addAll(Fire.getAllFirePokemon());
        allPokemonList.addAll(Water.getAllWaterPokemon());
        allPokemonList.addAll(Grass.getAllGrassPokemon());
        allPokemonList.addAll(Electric.getAllElectricPokemon());
        return allPokemonList;
    }
	public static Pokemon getPokemonByName(String name) {
        List<Pokemon> allPokemon = getAllPokemon();
        for (Pokemon pokemon : allPokemon) {
            if (pokemon.getSpecies().equalsIgnoreCase(name)) {
                return pokemon;
            }
        }
        return null; // Pokemon not found
    }
	public static Pokemon getRandomPokemon() {
        List<Pokemon> allPokemon = getAllPokemon();
        Random random = new Random();
        int randomIndex = random.nextInt(allPokemon.size());
        return allPokemon.get(randomIndex);
    }
	public static ArrayList<Pokemon> getThreeRandomPokemon(){
		List<Pokemon> allPokemon = getAllPokemon();
		ArrayList<Pokemon> selectedPokemon = new ArrayList<>();
		Random random = new Random();
		while (selectedPokemon.size() <3){
			int randomIndex = random.nextInt(allPokemon.size());
			Pokemon randomPokemon = allPokemon.get(randomIndex);
			if (!selectedPokemon.contains(randomPokemon)) {
                selectedPokemon.add(randomPokemon);
            } 
		}
		return selectedPokemon;
	}
	}
