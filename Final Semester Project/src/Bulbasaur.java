public class Bulbasaur extends Grass{
    
    public Bulbasaur(String nickname){
        super( nickname, 100,100,100,100,100,100,"Bulbasaur"); //placeholder values
    }
    @Override
    public void useZMove(){
        grassBonus();
        System.out.printf("a");
        System.out.println("Massive vines erupt from the ground, dealing " + getZMovePower() + " damage!");
        System.out.println("The battlefield is covered in blooming flowers!");
    }
}
