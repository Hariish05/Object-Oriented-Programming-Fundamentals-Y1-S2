
abstract class Pokemon {
	protected String nickname;
	protected boolean defeat;
	protected int level;
	protected int hp;
	protected int atk;
	protected int def;
	protected int speed;
    protected int spAtk;
    protected int spDef;
    protected String species;

	public Pokemon(String nickname, int hp, int atk, int def, int speed, int spAtk, int spDef, String species) {
		this.nickname = nickname;
		this.level = 1;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.speed = speed;
		this.spAtk = spAtk;
		this.spDef = spDef;
		this.species = species;
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
	public void setAtk(int atk) {
		this.atk = atk;
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
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getSpAtk() {
		return spAtk;
	}
	public void setSpAtk(int spAtk) {
		this.spAtk = spAtk;
	}
	public int getSpDef() {
		return spDef;
	}
	public void setSpDef(int spDef) {
		this.spDef = spDef;
	}
	public String getSpecies() {
		return species;
	}
	public abstract void useTypeMove();
	public abstract String getZMoveName();
	public abstract int getZMovePower();
	public abstract void useZMove();
	}
