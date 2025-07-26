abstract class Grass extends Pokemon{
    public Grass(String nickname, int hp, int atk, int def, int speed, int spAtk, int spDef, String species){
        super( nickname,  hp,  atk,  def,  speed,  spAtk, spDef,  species);
    }
    @Override
    public void useTypeMove(){
        System.out.printf("%s uses Grass type move!\n", getNickname());
    }
    public void grassBonus(){
        System.out.printf("%s calls upon the power of Grass!\n",getNickname());
    }
}
