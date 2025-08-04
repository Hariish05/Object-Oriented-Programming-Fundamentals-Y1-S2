public class driverFile {
    public static void main(String[] args)  {
        //THIS HAS TO BE DONE AT THE START EVERYTIME
        FileManager.createAllPokemonTxt();
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
        Player.chooseStarter(dummy);
        
        Pokemon playerPokemon = dummy.getCollection().get(0);
        Pokemon wildPokemon = Pokemon.getRandomPokemon();
        System.out.println("\nA wild " + wildPokemon.getSpecies() + " appeared!");

        // Initiate Battle
        Battle battle = new Battle(wildPokemon, playerPokemon);
        battle.startBattle();
    }
}
