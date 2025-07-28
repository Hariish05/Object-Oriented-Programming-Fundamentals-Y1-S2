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
    //ZMove Creation (BASE POWER VALUES NOT FINAL)
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
    public String toString(){
        return String.format("Z-Move name: %s\nZ-Move base power: %d",getName(),getBasePower());
    }
}
