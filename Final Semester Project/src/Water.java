import java.util.Arrays;
import java.util.List;
public class Water extends Pokemon{
    // Water pokemon constructor 
    public Water(String nickname, int atk, int def, int speed, String species,int maxHp, ZMoves ZMove){
        super(nickname, maxHp, atk, def, speed, species,ZMove);
    }

    // Water Pokemon object creation (VALUES NOT FINAL)
    public static Pokemon squirtle = new Water("Squirtle",48,65,43,"Squirtle",44,ZMoves.HYDRO_VORTEX_SQUIRTLE);
    public static Pokemon oshawott = new Water("Oshawott",55,45,45,"Oshawott",55,ZMoves.HYDRO_VORTEX_OSHAWOTT);
    public static Pokemon piplup = new Water("Piplup",51,53,40,"Piplup",53,ZMoves.HYDRO_VORTEX_PIPLUP);

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
