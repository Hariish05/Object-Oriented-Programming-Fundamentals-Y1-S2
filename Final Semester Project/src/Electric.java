import java.util.Arrays;
import java.util.List;

public class Electric extends Pokemon{

    // Electric pokemon constructor
    public Electric(String nickname, int atk, int def, int speed, String species,int maxHp, ZMoves ZMove){
        super(nickname, maxHp, atk, def, speed, species,ZMove);
    }
    // Electric pokemon object creation (VALUES NOT FINAL)
    public static Pokemon elekid = new Electric("Elekid",63,37,95,"Elekid",45,ZMoves.GIGAVOLT_HAVOC_ELEKID);
    public static Pokemon pikachu = new Electric("Pikachu",71,40,93,"Pikachu",40,ZMoves.CATASTROPIKA);
    public static Pokemon shinx = new Electric("Shinx",65,34,45,"Shinx",45,ZMoves.GIGAVOLT_HAVOC_SHINX);

    public static List<Pokemon> getAllElectricPokemon(){
        return Arrays.asList(elekid,pikachu,shinx);
    }
    
    @Override
    public void useTypeMove(){
        System.out.printf("%s uses an Electricity type move!\n", getNickname());
    }
    public void electricBonus(){
        System.out.printf("%s calls upon the power of Electricity!\n",getNickname());
    }
    @Override
    public String toString(){
        return String.format("Electric Type Pokemon\nNickname: %s\nSpecies: %s\nHealth points: %d/%d\nAttack: %d\nDefense: %d\nSpeed: %d\n%s",getNickname(),getSpecies(),getHp(),getMaxHp(),getAtk(),getDef(),getSpeed(),ZMove.toString());
    }

}
