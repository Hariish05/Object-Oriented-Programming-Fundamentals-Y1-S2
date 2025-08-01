
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

	public Pokemon(String nickname, int maxHp, int atk, int def, int speed, String species) {
		this.nickname = nickname;
		this.maxHp = maxHp;
		this.atk = atk;
		this.def = def;
		this.speed = speed;
		this.species = species;
		this.hp = this.maxHp;
		this.level = 1;
		this.defeat = false;
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
	}
