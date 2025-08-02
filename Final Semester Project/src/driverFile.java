public class driverFile {
    public static void main(String[] args)  {
        //TESTS
        Grass.bulbasuar.setHp(Grass.bulbasuar.getMaxHp()-2);
        System.out.println(Grass.bulbasuar.toString());
        boolean catchStatus = Pokeball.attemptCatch(Pokeball.POKEBALL);
        if (catchStatus) {
            System.out.println("Pokemon has been caught!");
        } else{
            System.out.println("Pokemon has slipped away!");
        }
        Player dummy = new Player("yur");
        Player.Choose(dummy);
    }
}