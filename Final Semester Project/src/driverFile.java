public class driverFile {
    public static void main(String[] args)  {
        //TESTS
        Grass.BULBASAUR.setHp(Grass.BULBASAUR.getMaxHp()-2);
        System.out.println(Grass.BULBASAUR.toString());
        boolean catchStatus = Pokeball.attemptCatch(Pokeball.POKEBALL);
        if (catchStatus) {
            System.out.println("Pokemon has been caught!");
        } else{
            System.out.println("Pokemon has slipped away!");
        }
    }
}
