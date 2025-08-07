import java.util.Arrays;
import java.util.List;

public class Normal extends Pokemon{
    
    //Normal pokemon contstructor
    public Normal(String nickname, int atk,int def, int speed, String species,int maxHp, ZMoves Zmove){
        super(nickname, maxHp, atk, def, speed, species,Zmove);
    }
    //Normal Pokemon object creation
    public static Pokemon pidgey = new Normal("Pidgey", 40, 40, 56, "Pidgey", 126, ZMoves.BREAKNECK_BLITZ_PIDGEY);
    public static Pokemon eevee = new Normal("Eevee", 90, 50, 55, "Eevee", 116, ZMoves.EXTREME_EVOBOOST);
    public static Pokemon munchlax = new Normal("Munchlax", 55, 45, 25, "Munchlax", 140, ZMoves.PULVERIZING_PANCAKE);

    public static List<Pokemon> getAllNormalPokemon(){
        return Arrays.asList(pidgey,eevee,munchlax);
    }

    @Override
    public void useTypeMove(){
        System.out.printf("%s uses a Normal type move!\n", getNickname());
    }
    public void grassBonus(){
        System.out.printf("%s calls upon the power of Normal!\n",getNickname());
    }
    @Override
    public String toString(){
        return String.format("Normal Type Pokemon\nNickname: %s\nSpecies: %s\nHealth points: %d/%d\nAttack: %d\nDefense: %d\nSpeed: %d\n%s",getNickname(),getSpecies(),getHp(),getMaxHp(),getAtk(),getDef(),getSpeed(),ZMove.toString());
    }
}
