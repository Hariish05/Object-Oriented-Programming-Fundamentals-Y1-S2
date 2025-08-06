import java.util.*;
public class MainMenu {
    public static void main(String[] args) {
        FileManager.createAllPokemonTxt(); 
        Player player = FileManager.createNewPlayerOrUseExistingOne(); // THESE 2 LINES MUST RUN ALWAYS AT THE START
        int choice;
        Scanner input = new Scanner(System.in);
        boolean on = true;

        while (on){
            System.out.println("\n\t\tMain Menu\n-------------------------------------------\n1. Battle\n2. Catch wild pokemon\n3. Check collection\n4. Check top 5 scores of previous battles\n5. Check all obtainable Pokemon\n6. Release one of your Pokemons\n7. Exit");
            choice = Battle.getValidatedChoice(1, 7);
            switch(choice){
                case 1:
                    Pokemon playerPokemom = Battle.playerSelectPokemonFromCollection(player);
                    Pokemon opponentPokemon = Pokemon.getRandomPokemon();
                    Battle battle = new Battle(opponentPokemon,playerPokemom);
                    try {
                        battle.startBattle(player);
                    } catch (Exception e) {
                    }
                    break;
                case 2:
                    Player.catchWildPokemon(player);
                    break;
                case 3:
                System.out.println(FileManager.getPlayerPokemonFromTxt());
                    break;
                case 4: //incomplete 
                    break;
                case 5:
                    System.out.println(FileManager.getCurrentPokemonNameList());
                    break;
                case 6:
                    List<String> playerPokemons = FileManager.getPlayerPokemonFromTxt();
                    int count=1,releaseChoice;
                    for (String i: playerPokemons){
                        System.out.printf("%d.\n%s\n",count,i);
                        count++;
                    }
                    System.out.printf("Which Pokemon would you like to relaase back into the wild? (1-%d): ",count-1);
                    releaseChoice = Battle.getValidatedChoice(1, count-1);
                    FileManager.removePokemonFromPlayerTxtAndCollection(Pokemon.getPokemonByName(playerPokemons.get(releaseChoice-1)));
                    player.removeFromCollection(playerPokemons.get(releaseChoice-1));
                    break;
                case 7:
                    System.out.println("Shutting down...");
                    on = false;
                    break;
            }
        }
    }
}
