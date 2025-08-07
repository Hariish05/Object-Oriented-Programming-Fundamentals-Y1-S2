public class ZMoves {
    private String name;
    private String type;
    private String desc; 

    public ZMoves(String name, String type, String desc) {
        this.name = name;
        this.type = type;
        this.desc = desc;
    }
    //ZMove Creation GRASS
    public static final ZMoves BLOOM_DOOM = new ZMoves(
    "Bloom Doom",
    "Grass",
    "Bulbasaur's signature Z-Move"
    );
    public static final ZMoves BREAKNECK_BLITZ =new ZMoves(
    "Breakneck Blitz",
    "Grass",
    "Snivy's signature Z-Moves"
    );
    public static final ZMoves RAZOR_LEAF = new ZMoves(
    "Razor Leaf",
    "Grass",
    "Turtwig's Z-Move"
    );
    //ZMove Creation NORMAL
    public static final ZMoves BREAKNECK_BLITZ_PIDGEY = new ZMoves(
    "Breakneck Blitz",
    "Normal",
    "Pidgey's Z-Move"
    );
    public static final ZMoves EXTREME_EVOBOOST = new ZMoves(
    "Extreme Evoboost",
    "Normal",
    "Eevee's Signature Z-Move"
    );
    public static final ZMoves PULVERIZING_PANCAKE = new ZMoves(
    "Pulverizing Pancake",
    "Normal",
    "Munchlax's Z-Move"
    );
    //ZMove Creation WATER
    public static final ZMoves HYDRO_VORTEX_SQUIRTLE = new ZMoves(
    "Hydro Vortex",
    "Water",
    "Squirtle's Z-Move"
    );
    public static final ZMoves HYDRO_VORTEX_OSHAWOTT = new ZMoves(
    "Hydro Vortex",
    "Water",
    "Oshawotts's Z-Move"
    );
    public static final ZMoves HYDRO_VORTEX_PIPLUP = new ZMoves(
    "Hydro Vortex",
    "Water",
    "Piplup's Z-Move"
    );
    //ZMove Creation ELECTRIC
    public static final ZMoves GIGAVOLT_HAVOC_ELEKID = new ZMoves(
    "Gigavolt Havoc",
    "Electric",
    "Elekid's Z-Move"
    );
    public static final ZMoves CATASTROPIKA = new ZMoves(
    "Catastropika",
    "Electric",
    "Pikachu's Signature Z-Move"
    );
    public static final ZMoves GIGAVOLT_HAVOC_SHINX = new ZMoves(
    "Gigavolt Havoc",
    "Electric",
    "Shinx's Z-Move"
    );
    //ZMove Creation FIRE
    public static final ZMoves INFERNO_OVERDRIVE_CHARMANDER = new ZMoves(
    "Inferno Overdrive",
    "Fire",
    "Charmander's Z-Move"
    );
    public static final ZMoves INFERNO_OVERDRIVE_TORCHIC = new ZMoves(
    "Inferno Overdrive",
    "Fire",
    "Torchic's Z-Move"
    );
    public static final ZMoves INFERNO_OVERDRIVE_TEPIG = new ZMoves(
    "Inferno Overdrive",
    "Fire",
    "Tepig's Z-Move"
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

    @Override
    public String toString(){
        return String.format("Z-Move name: %s\n",getName());
    }
}
