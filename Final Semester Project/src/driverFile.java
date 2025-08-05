

public class driverFile {
    public static void main(String[] args) {
        // THIS HAS TO BE DONE AT THE START EVERYTIME
        FileManager.createAllPokemonTxt();
        // TESTS
        Grass.bulbasuar.setHp(Grass.bulbasuar.getMaxHp() - 2);
        System.out.println(Grass.bulbasuar.toString());

        Player dummy = new Player("Test_Player");
        Player.chooseStarter(dummy);
        FileManager.createPlayerDataFile(dummy);

        FileManager.removePokemonFromPlayerTxt(Electric.elekid);

        Pokemon playerPokemon = dummy.getCollection().get(0);
        Pokemon wildPokemon = Pokemon.getRandomPokemon();

        // Initiate Battle
        Battle battle = new Battle(wildPokemon, playerPokemon);
        try {
            battle.startBattle(dummy);
        } catch (Exception e) {
        }
    }
}
