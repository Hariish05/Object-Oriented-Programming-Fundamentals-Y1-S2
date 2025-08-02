import java.util.Arrays;
import java.util.List;
public class Water extends Pokemon{
    // Water pokemon constructor 
    public Water(String nickname, int atk, int def, int speed, String species,int maxHp, ZMoves ZMove){
        super(nickname, maxHp, atk, def, speed, species,ZMove);
    }

    // Water Pokemon object creation (VALUES NOT FINAL)
    public static Pokemon squirtle = new Water("yur",0,0,0,"Squirtle",100,ZMoves.HYDRO_VORTEX_SQUIRTLE);
    public static Pokemon oshawott = new Water("yurr",0,0,0,"Oshawott",100,ZMoves.HYDRO_VORTEX_OSHAWOTT);
    public static Pokemon piplup = new Water("yurrr",0,0,0,"Piplup",100,ZMoves.HYDRO_VORTEX_PIPLUP);

    public static List<Pokemon> getAllWaterPokemon(){
        return Arrays.asList(squirtle,oshawott,piplup);
    }
    
    @Override
    public void useTypeMove(){
        System.out.printf("%s uses a Water type move!\n", getNickname());
    }
    public void waterBonus(){
        System.out.printf("%s calls upon the power of Water!\n",getNickname());
    }
    @Override
    public String toString(){
        return String.format("Water Type Pokemon\nNickname: %s\nSpecies: %s\nHealth points: %d/%d\nAttack: %d\nDefense: %d\nSpeed: %d\n%s",getNickname(),getSpecies(),getHp(),getMaxHp(),getAtk(),getDef(),getSpeed(),ZMove.toString());
    }
}
