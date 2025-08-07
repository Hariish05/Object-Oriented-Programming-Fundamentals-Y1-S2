import java.util.Arrays;
import java.util.List;

public class Fire extends Pokemon{
    //Fire pokemon constructor
    public Fire(String nickname, int atk, int def, int speed, String species,int maxHp, ZMoves ZMove){
        super(nickname, maxHp, atk, def, speed, species,ZMove);
    }
    //Fire Pokemon object creation (VALUES NOT FINAL)
    public static Pokemon charmander = new Fire("Charmander", 82, 43, 65, "Charmander", 112, ZMoves.INFERNO_OVERDRIVE_CHARMANDER);
    public static Pokemon torchic = new Fire("Torchic", 75, 40, 45, "Torchic", 120, ZMoves.INFERNO_OVERDRIVE_TORCHIC);
    public static Pokemon tepig = new Fire("Tepig", 88, 45, 40, "Tepig", 134, ZMoves.INFERNO_OVERDRIVE_TEPIG);

    public static List<Pokemon> getAllFirePokemon(){
        return Arrays.asList(charmander,torchic,tepig);
    }

    @Override
    public void useTypeMove(){
        System.out.printf("%s uses a Fire type move!\n", getNickname());
    }
    public void fireBonus(){
        System.out.printf("%s calls upon the power of Fire!\n",getNickname());
    }
    @Override
    public String toString(){
        return String.format("Fire Type Pokemon\nNickname: %s\nSpecies: %s\nHealth points: %d/%d\nAttack: %d\nDefense: %d\nSpeed: %d\n%s",getNickname(),getSpecies(),getHp(),getMaxHp(),getAtk(),getDef(),getSpeed(),ZMove.toString());
    }
}
