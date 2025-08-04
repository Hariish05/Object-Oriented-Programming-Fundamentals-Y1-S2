public class ZMoves {
    private String name;
    private String type;
    private boolean status; // Indicates whether the move has been used or not
    private String desc; 
    private int basePower;

    public ZMoves(String name, String type, String desc, int basePower) {
        this.name = name;
        this.type = type;
        this.status = false;
        this.desc = desc;
        this.basePower = basePower;
    }
    //ZMove Creation GRASS (BASE POWER VALUES NOT FINAL)
    public static final ZMoves BLOOM_DOOM = new ZMoves(
    "Bloom Doom",
    "Grass",
    "Bulbasaur's signature Z-Move",
    140
    );
    public static final ZMoves BREAKNECK_BLITZ =new ZMoves(
    "Breakneck Blitz",
    "Grass",
    "Snivy's signature Z-Moves",
    130
    );
    public static final ZMoves RAZOR_LEAF = new ZMoves(
    "Razor Leaf",
    "Grass",
    "Turtwig's Z-Move",
    100
    );
    //ZMove Creation NORMAL (BASE POWER VALUES NOT FINAL)
    public static final ZMoves BREAKNECK_BLITZ_PIDGEY = new ZMoves(
    "Breakneck Blitz",
    "Normal",
    "Pidgey's Z-Move",
    110
    );
    public static final ZMoves EXTREME_EVOBOOST = new ZMoves(
    "Extreme Evoboost",
    "Normal",
    "Eevee's Signature Z-Move",
    100
    );
    public static final ZMoves PULVERIZING_PANCAKE = new ZMoves(
    "Pulverizing Pancake",
    "Normal",
    "Munchlax's Z-Move",
    150
    );
    //ZMove Creation WATER (BASE POWER VALUES NOT FINAL)
    public static final ZMoves HYDRO_VORTEX_SQUIRTLE = new ZMoves(
    "Hydro Vortex",
    "Water",
    "Squirtle's Z-Move",
    120
    );
    public static final ZMoves HYDRO_VORTEX_OSHAWOTT = new ZMoves(
    "Hydro Vortex",
    "Water",
    "Oshawotts's Z-Move",
    100
    );
    public static final ZMoves HYDRO_VORTEX_PIPLUP = new ZMoves(
    "Hydro Vortex",
    "Water",
    "Piplup's Z-Move",
    110
    );
    //ZMove Creation ELECTRIC (BASE POWER VALUES NOT FINAL)
    public static final ZMoves GIGAVOLT_HAVOC_ELEKID = new ZMoves(
    "Gigavolt Havoc",
    "Electric",
    "Elekid's Z-Move",
    140
    );
    public static final ZMoves CATASTROPIKA = new ZMoves(
    "Catastropika",
    "Electric",
    "Pikachu's Signature Z-Move",
    210
    );
    public static final ZMoves GIGAVOLT_HAVOC_SHINX = new ZMoves(
    "Gigavolt Havoc",
    "Electric",
    "Shinx's Z-Move",
    120
    );
    //ZMove Creation FIRE (BASE POWER VALUES NOT FINAL)
    public static final ZMoves INFERNO_OVERDRIVE_CHARMANDER = new ZMoves(
    "Inferno Overdrive",
    "Fire",
    "Charmander's Z-Move",
    140
    );
    public static final ZMoves INFERNO_OVERDRIVE_TORCHIC = new ZMoves(
    "Inferno Overdrive",
    "Fire",
    "Torchic's Z-Move",
    120
    );
    public static final ZMoves INFERNO_OVERDRIVE_TEPIG = new ZMoves(
    "Inferno Overdrive",
    "Fire",
    "Tepig's Z-Move",
    100
    );

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public int getBasePower() {
        return basePower;
    }
    @Override
    public String toString(){
        return String.format("Z-Move name: %s\nZ-Move base power: %d\n",getName(),getBasePower());
    }
}
