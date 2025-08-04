import java.util.Arrays;
import java.util.List;

public class Grass extends Pokemon{
    //Grass Pokemon Constructor
    public Grass(String nickname, int atk, int def, int speed, String species,int maxHp, ZMoves ZMove){
        super(nickname, maxHp, atk, def, speed, species,ZMove);
    }
    //Grass pokemon object creation (PLACEHOLDER VALUES)
    public static Pokemon bulbasuar = new Grass("Bulbasuar", 49,49,45, "Bulbasaur", 45, ZMoves.BLOOM_DOOM); 
    public static Pokemon snivy = new Grass("Snivy", 45,55,63, "Snivy", 45, ZMoves.BREAKNECK_BLITZ); 
    public static Pokemon turtwig = new Grass("Turtwig", 68,64	
,31, "Turtwig", 55, ZMoves.RAZOR_LEAF); 

    public static List<Pokemon> getAllGrassPokemon(){
        return Arrays.asList(bulbasuar,snivy,turtwig);
    }

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
