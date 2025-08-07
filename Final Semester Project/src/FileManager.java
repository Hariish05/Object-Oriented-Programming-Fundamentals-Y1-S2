import java.io.*;
import java.util.*;

public class FileManager {
    private static final String SCORE_FILE = "battleScores.txt";
    private static final int MAX_SCORES = 5;

    public static void addBattleScore(int score) {
        List<Integer> scores = readScoresFromFile();
        
        scores.add(0, score);
        
        while (scores.size() > MAX_SCORES) {
            scores.remove(scores.size() - 1);
        }
        
        writeScoresToFile(scores);
        
        System.out.println("Battle score saved: " + score);
    }

     public static List<Integer> readScoresFromFile() {
        List<Integer> scores = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    try {
                        int score = Integer.parseInt(line);
                        scores.add(score);
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid score line: " + line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Score file not found. Creating new one...");
        } catch (IOException e) {
            System.err.println("Error reading score file: " + e.getMessage());
        }
        
        return scores;
    }

    public static void displayBattleScores() {
        List<Integer> scores = readScoresFromFile();
        
        if (scores.isEmpty()) {
            System.out.println("No battle scores recorded yet.");
            return;
        }
        
        System.out.println("\n === RECENT BATTLE SCORES ===");
        System.out.println("(Showing up to " + MAX_SCORES + " most recent scores)");
        System.out.println("─".repeat(30));
        
        for (int i = 0; i < scores.size(); i++) {
            System.out.printf("%d. %d points\n", (i + 1), scores.get(i));
        }
        System.out.println("─".repeat(30));
    }

    public static int getHighestScore() {
        List<Integer> scores = readScoresFromFile();
        
        if (scores.isEmpty()) {
            return 0;
        }
        
        int highestScore = 0;
        for (int score : scores) {
            highestScore = Math.max(highestScore, score);
        }
        
        return highestScore;
    }

    public static void writeScoresToFile(List<Integer> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            for (Integer score : scores) {
                writer.write(score.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to score file: " + e.getMessage());
        }
    }
    // Creates a new text file if one doesnt exist but doesnt overwrite it if it
    // does
    public static void createAllPokemonTxt() {
        List<Pokemon> allPokemonList = Pokemon.getAllPokemon();
        String[] names = Pokemon.getAllPokemonListNames(allPokemonList);
        try {
            File allPokemonFile = new File("allPokemon.txt");
            BufferedWriter allPokemonFileWriter = new BufferedWriter(new FileWriter(allPokemonFile, true));
            BufferedReader allPokemonFileReader = new BufferedReader(new FileReader(allPokemonFile));
            if (allPokemonFileReader.readLine() == null) {
                for (String i : names) {
                    allPokemonFileWriter.append(i);
                    allPokemonFileWriter.append("\n");
                }
                allPokemonFileWriter.close();
                allPokemonFileReader.close();
            }
        } catch (Exception e) {
            System.out.println("An error has occured during file writing.");
        }
    }

    // (REPLACED OLD METHOD) creates playerData text file and only allows one player to be stored
    public static void createPlayerDataFile(Player player) {
        String name = player.getName();
        List<Pokemon> playerPokemonList = player.getCollection();
        List<String> playerPokemonNameList= new ArrayList<>();
        int score = player.getScore();
        for (Pokemon i: playerPokemonList){
            playerPokemonNameList.add(i.getSpecies());
        }
        try {
            File playerDataFile = new File("playerData.txt");
            BufferedWriter playerDataFileWriter = new BufferedWriter(new FileWriter(playerDataFile,true));
            BufferedReader playerDataFileReader = new BufferedReader(new FileReader(playerDataFile));
            if (playerDataFileReader.readLine() == null){
                playerDataFileWriter.append(name);
                playerDataFileWriter.newLine();
                playerDataFileWriter.append(Integer.toString(score));
                playerDataFileWriter.newLine();
                for (String i: playerPokemonNameList){
                    playerDataFileWriter.append(i);
                    playerDataFileWriter.newLine();
                }
                playerDataFileReader.close();
                playerDataFileWriter.close();
            }
        } catch (Exception e) {
            System.out.println("An error has occurred during file writing.");
        }
    }
    public static List<Object> readPlayerDataFile(){
        int lineCount=0;
        List<Object> playerDataList = new ArrayList<>();
        Object temp = null;
        try {
            BufferedReader playerDataFileReader1 = new BufferedReader(new FileReader("playerData.txt"));
            while (playerDataFileReader1.readLine() != null) lineCount++;
            playerDataFileReader1.close();
            if (lineCount !=0){
                BufferedReader playerDataFileReader2 = new BufferedReader(new FileReader("playerData.txt"));
                for (int i =0; i<lineCount;i++){
                    temp = playerDataFileReader2.readLine();
                    playerDataList.add(temp);
                }
            } else {
                return null;
            }
            return playerDataList;
        } catch (Exception e) {
            return null;
        }
    }
    public static Player createNewPlayerOrUseExistingOne(){
        List<Object> playerDataList = readPlayerDataFile();
        List<String> playerPokemonNameList = getPlayerPokemonFromTxt();
        Scanner input = new Scanner(System.in);
        Pokemon pokemon;
        String playerName;
        int choice;
        if (playerDataList ==null || playerDataList.isEmpty()){
            System.out.println("Since you are a first-time user, you must create an account.\nEnter your player name: ");
            while (true) { 
                try {
                    playerName = input.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println("Error occured.");
                }
            }
            Player player = new Player(playerName);
            System.out.println("Account created successfully!\nNow you must pick a starter Pokemon...");
            Player.chooseStarter(player);
            System.out.println("You can now get started!");
            createPlayerDataFile(player);
            return player;
        } else{
            System.out.println("A saved player account has been found!\nWould you like to load the previous players account?\n(1 for YES, 2 for NO)");
            choice = Battle.getValidatedChoice(1,2);
            if (choice ==1){
                playerName = playerDataList.get(0).toString();
                Player player = new Player(playerName);
                for (String i: playerPokemonNameList){
                    pokemon = Pokemon.getPokemonByName(i);
                    player.setCollection(pokemon);
                }
                System.out.println("You can now get started!");
                return player;
            } else{
                try (BufferedWriter playerDataFileWriter = new BufferedWriter(new FileWriter("playerData.txt",false));){
                } catch (Exception e) {

                }
                System.out.println("Enter your player name: ");
                while (true) { 
                    try {
                        playerName = input.nextLine();
                        break;
                    } catch (Exception e) {
                        System.out.println("Error occured.");
                    }
                }
                Player player = new Player(playerName);
                System.out.println("Now you must pick your starter Pokemon...");
                Player.chooseStarter(player);
                System.out.println("You can now get started!");
                createPlayerDataFile(player);
                return player;
            }
        }
    }
    public static List<String> getPlayerPokemonFromTxt(){
        List<Object> playerDataList = readPlayerDataFile();
        List<String> playerPokemonList = new ArrayList<>();
        Object temp;
        int lineCount=0;
        try {
            BufferedReader playerDataFileReader1 = new BufferedReader(new FileReader("playerData.txt"));
            while (playerDataFileReader1.readLine() != null) lineCount++;
            playerDataFileReader1.close();
            for (int i = 2;i<lineCount;i++) {
                temp = playerDataList.get(i);
                playerPokemonList.add(temp.toString());
            }
            while (playerPokemonList.contains("")) playerPokemonList.remove("");
            return playerPokemonList;
        } catch (Exception e) {
            return null;
        }
    }
    public static void addPokemonToPlayerTxt(Pokemon pokemon){
        String pokemonName = pokemon.getSpecies();
        List<Object> playerDataList = readPlayerDataFile();
        List<String> playerPokemonList = getPlayerPokemonFromTxt();
        int playerDataIndex = playerDataList.size()+1; 
        int playerPokemonIndex = playerPokemonList.size();
        String temp;
        if (playerPokemonIndex >=3) {
            System.out.println("You cannot store anymore pokemon!");
        } else{
            playerDataList.add(pokemonName);
            try {
                BufferedWriter playerDataFileWriter = new BufferedWriter(new FileWriter("playerData.txt",false));
                for (int i =0;i<playerDataIndex;i++){
                    temp = playerDataList.get(i).toString();
                    playerDataFileWriter.append(temp);
                    playerDataFileWriter.newLine();
                }
                playerDataFileWriter.close();
            } catch (Exception e) {
            }
        }
    }
    public static boolean removePokemonFromPlayerTxtAndCollection(Pokemon pokemon){
        String pokemonName = pokemon.getSpecies();
        List<Object> playerDataList = readPlayerDataFile();
        List<String> playerPokemonList = getPlayerPokemonFromTxt();
        int playerDataIndex = playerDataList.size()-1; 
        int playerPokemonIndex = playerPokemonList.size();
        boolean removed = false;
        String temp;
        if (!playerDataList.contains(pokemonName) || playerPokemonIndex ==1) {
            System.out.println("Release failed! Either you do not have that Pokemon in your collection or it is your only Pokemon in your collection!");
            return removed;
        } else{
            playerDataList.remove(pokemonName);
            try {
                BufferedWriter playerDataFileWriter = new BufferedWriter(new FileWriter("playerData.txt",false));
                for (int i =0;i<playerDataIndex;i++){
                    temp = playerDataList.get(i).toString();
                    playerDataFileWriter.append(temp);
                    playerDataFileWriter.newLine();
                }
                addPokemonToTxt(pokemon);
                playerDataFileWriter.close();
                return removed =true;
            } catch (Exception e) {
                return true;
            }
        }
    }

    // reads the text file created and pulls the currently avaliable pokemon and
    // puts them into an arraylist
    public static List<Pokemon> getCurrentPokemonObjectList() {
        int lineCount = 0;
        List<Pokemon> currentPokemonObjectList = new ArrayList<>();
        List<String> names = new ArrayList<>();
        try {
            BufferedReader allPokemonFileReader1 = new BufferedReader(new FileReader("allPokemon.txt"));
            while (allPokemonFileReader1.readLine() != null) lineCount++;
            allPokemonFileReader1.close();
            BufferedReader allPokemonFileReader2 = new BufferedReader(new FileReader("allPokemon.txt"));
            for (int q = 0; q < lineCount; q++) {
                names.add(allPokemonFileReader2.readLine());
            }
            allPokemonFileReader2.close();
            while (names.contains(""))
                names.remove("");
            for (String i : names) {
                currentPokemonObjectList.add(Pokemon.getPokemonByName(i));
            }
            return currentPokemonObjectList;
        } catch (Exception e) {
            System.out.println("An error has occured during file reading.");
            return new ArrayList<>();
        }
    }

    // reads the text file just as the function above but returns the names of the
    // pokemon rather than the objects in a list
    public static List<String> getCurrentPokemonNameList() {
        int lineCount = 0;
        List<String> names = new ArrayList<>();
        try {
            BufferedReader allPokemonFileReader1 = new BufferedReader(new FileReader("allPokemon.txt"));
            while (allPokemonFileReader1.readLine() != null) lineCount++;
            allPokemonFileReader1.close();
            BufferedReader allPokemonFileReader2 = new BufferedReader(new FileReader("allPokemon.txt"));
            for (int q = 0; q < lineCount; q++) {
                names.add(allPokemonFileReader2.readLine());
            }
            allPokemonFileReader2.close();
            while (names.contains(""))
                names.remove("");
            return names;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    //method to remove desired pokemon from allPokemon.txt
    public static void removePokemonFromTxt(Pokemon pokemon){
        List<String> currentPokemonNameList = getCurrentPokemonNameList();
        String pokemonNameTORemove = pokemon.getSpecies();
        if (currentPokemonNameList.contains(pokemonNameTORemove)){
            currentPokemonNameList.remove(pokemonNameTORemove);
            writeToAllPokemonList(currentPokemonNameList);
        }else{
            System.out.println("error message (to be removed)");
        }
    }
    //method to add desired pokemon to allPokemon.txt
    public static void addPokemonToTxt(Pokemon pokemon){
        List<String> currentPokemonNameList = getCurrentPokemonNameList();
        String pokemonNameTORemove = pokemon.getSpecies();
        if (!currentPokemonNameList.contains(pokemonNameTORemove)){
            currentPokemonNameList.add(pokemonNameTORemove);
            writeToAllPokemonList(currentPokemonNameList);
        }else{
            System.out.println("error message (to be removed)");
        }
    }
    
    public static void writeToAllPokemonList(List<String> names){
        try {
            BufferedWriter allPokemonFileWriter = new BufferedWriter(new FileWriter("allPokemon.txt"));
            for (String i : names) {
                    allPokemonFileWriter.append(i);
                    allPokemonFileWriter.append("\n");
                }
                allPokemonFileWriter.close();
        } catch (Exception e) {
        }
    }
}
