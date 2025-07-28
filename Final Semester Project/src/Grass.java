class Grass extends Pokemon{
    private ZMoves ZMove;

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

    public Grass(String nickname, int hp, int atk, int def, int speed, String species, ZMoves ZMove){
        super(nickname, hp, atk, def, speed, species);
        this.ZMove = ZMove;
    }
    @Override
    public void useTypeMove(){
        System.out.printf("%s uses Grass type move!\n", getNickname());
    }
    public void grassBonus(){
        System.out.printf("%s calls upon the power of Grass!\n",getNickname());
    }
}
