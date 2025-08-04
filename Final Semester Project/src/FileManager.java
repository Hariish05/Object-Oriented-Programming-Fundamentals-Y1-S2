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

    // Appends player data to playerData.txt without overwriting old data
    public void playerTextFile(Player player) {
        List<Object> playerDataFile = player.playerData();
        File playerFile = new File("playerData.txt");
        try (BufferedWriter playerFileWriter = new BufferedWriter(new FileWriter(playerFile, true))) {
            for (Object i : playerDataFile) {
                playerFileWriter.write(i.toString().trim());
                playerFileWriter.newLine();
            }
            // add a separator between players:
            playerFileWriter.write("-----");
            playerFileWriter.newLine();
        } catch (Exception e) {
            System.out.println("An error has occurred during file writing.");
            e.printStackTrace();
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
            while (allPokemonFileReader1.readLine() != null)
                lineCount++;
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
            while (allPokemonFileReader1.readLine() != null)
                lineCount++;
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
    // Another method will be created here for writing to the allPokemon.txt

}
