class Grass extends Pokemon{
    //Grass Pokemon Constructor
    public Grass(String nickname, int atk, int def, int speed, String species,int maxHp, ZMoves ZMove){
        super(nickname, maxHp, atk, def, speed, species,ZMove);
    }

    //Grass pokemon object creation (PLACEHOLDER VALUES)
    public static Pokemon bulbasuar = new Grass("yur", 0, 0, 0, "Bulbasaur", 100, ZMoves.BLOOM_DOOM); 
    public static Pokemon snivy = new Grass("yurr", 0, 0, 0, "Snivy", 200, ZMoves.BREAKNECK_BLITZ); 
    public static Pokemon turtwig = new Grass("yurrr", 0, 0, 0, "Turtwig", 140, ZMoves.RAZOR_LEAF); 

    @Override
    public void useTypeMove(){
        System.out.printf("%s uses a Grass type move!\n", getNickname());
    }
    public void grassBonus(){
        System.out.printf("%s calls upon the power of Grass!\n",getNickname());
    }
    @Override
    public String toString(){
        return String.format("Grass Type Pokemon\nNickname: %s\nSpecies: %s\nHealth points: %d/%d\nAttack: %d\nDefense: %d\nSpeed: %d\n%s",getNickname(),getSpecies(),getHp(),getMaxHp(),getAtk(),getDef(),getSpeed(),ZMove.toString());
    }
}
