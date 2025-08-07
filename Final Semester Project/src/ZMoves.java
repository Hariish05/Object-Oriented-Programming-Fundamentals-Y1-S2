public class ZMoves {
    private String name;
    private String type;
    private String desc;
    private int basePower;

    public ZMoves(String name, String type, String desc,int basePower) {
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.basePower = basePower;
    }
    //ZMove Creation GRASS
    public static final ZMoves BLOOM_DOOM = new ZMoves(
    "Bloom Doom",
    "Grass",
    "Bulbasaur's signature Z-Move",
    42
    );
    public static final ZMoves BREAKNECK_BLITZ =new ZMoves(
    "Breakneck Blitz",
    "Grass",
    "Snivy's signature Z-Moves",
    58
    );
    public static final ZMoves RAZOR_LEAF = new ZMoves(
    "Razor Leaf",
    "Grass",
    "Turtwig's Z-Move",
    63
    );
    //ZMove Creation NORMAL
    public static final ZMoves BREAKNECK_BLITZ_PIDGEY = new ZMoves(
    "Breakneck Blitz",
    "Normal",
    "Pidgey's Z-Move",
    71
    );
    public static final ZMoves EXTREME_EVOBOOST = new ZMoves(
    "Extreme Evoboost",
    "Normal",
    "Eevee's Signature Z-Move",
    48
    );
    public static final ZMoves PULVERIZING_PANCAKE = new ZMoves(
    "Pulverizing Pancake",
    "Normal",
    "Munchlax's Z-Move",
    55
    );
    //ZMove Creation WATER
    public static final ZMoves HYDRO_VORTEX_SQUIRTLE = new ZMoves(
    "Hydro Vortex",
    "Water",
    "Squirtle's Z-Move",
    69
    );
    public static final ZMoves HYDRO_VORTEX_OSHAWOTT = new ZMoves(
    "Hydro Vortex",
    "Water",
    "Oshawotts's Z-Move",
    77
    );
    public static final ZMoves HYDRO_VORTEX_PIPLUP = new ZMoves(
    "Hydro Vortex",
    "Water",
    "Piplup's Z-Move",
    45
    );
    //ZMove Creation ELECTRIC
    public static final ZMoves GIGAVOLT_HAVOC_ELEKID = new ZMoves(
    "Gigavolt Havoc",
    "Electric",
    "Elekid's Z-Move",
    52
    );
    public static final ZMoves CATASTROPIKA = new ZMoves(
    "Catastropika",
    "Electric",
    "Pikachu's Signature Z-Move",
    66
    );
    public static final ZMoves GIGAVOLT_HAVOC_SHINX = new ZMoves(
    "Gigavolt Havoc",
    "Electric",
    "Shinx's Z-Move",
    74
    );
    //ZMove Creation FIRE
    public static final ZMoves INFERNO_OVERDRIVE_CHARMANDER = new ZMoves(
    "Inferno Overdrive",
    "Fire",
    "Charmander's Z-Move",
    49
    );
    public static final ZMoves INFERNO_OVERDRIVE_TORCHIC = new ZMoves(
    "Inferno Overdrive",
    "Fire",
    "Torchic's Z-Move",
    59
    );
    public static final ZMoves INFERNO_OVERDRIVE_TEPIG = new ZMoves(
    "Inferno Overdrive",
    "Fire",
    "Tepig's Z-Move",
    72
    );

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
    
    public int getBasePower(){
        return basePower;
    }

    public String toString(){
        return String.format("Z-Move name: %s\nZ-Move Power: %d",getName(),getBasePower());
    }
}
