import java.io.*;
import java.util.*;

public class FileManager {
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
                System.out.println("no value!"); //test output, will be removed in final
                return null;
            }
            return playerDataList;
        } catch (Exception e) {
            return null;
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
            System.out.println(playerPokemonList);
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
        System.out.println(playerDataList);
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
    public static void removePokemonFromPlayerTxt(Pokemon pokemon){
        String pokemonName = pokemon.getSpecies();
        List<Object> playerDataList = readPlayerDataFile();
        List<String> playerPokemonList = getPlayerPokemonFromTxt();
        int playerDataIndex = playerDataList.size()-1; 
        int playerPokemonIndex = playerPokemonList.size();
        String temp;
        System.out.println(playerDataList);
        if (!playerDataList.contains(pokemonName) || playerPokemonIndex ==1) {
            System.out.println("You dont have that pokemon in your collection!\n Or you only have 1 pokemon in your collection!");
        } else{
            playerDataList.remove(pokemonName);
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
            System.out.println(currentPokemonObjectList);
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