import java.io.*;
import java.util.*;

public class FileManager {
    //Creates a new text file if one doesnt exist but doesnt overwrite it if it does
    public static void createAllPokemonTxt(){
        List<Pokemon> allPokemonList = Pokemon.getAllPokemon();
        String[] names = Pokemon.getAllPokemonListNames(allPokemonList);
        try {
            File allPokemonFile = new File("allPokemon.txt");
            BufferedWriter allPokemonFileWriter = new BufferedWriter(new FileWriter(allPokemonFile,true));
            BufferedReader allPokemonFileReader = new BufferedReader(new FileReader("allPokemon.txt"));
            if (allPokemonFileReader.readLine() == null){
                for(String i: names){
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
    // reads the text file created and pulls the currently avaliable pokemon and puts them into an arraylist
    public static List<Pokemon> getCurrentPokemonList(){
        int lineCount = 0;
        try {
            List<Pokemon> currentPokemon = new ArrayList<>();
            List<String> names = new ArrayList<>();
            BufferedReader allPokemonFileReader1 = new BufferedReader(new FileReader("allPokemon.txt"));
            while(allPokemonFileReader1.readLine() !=null) lineCount++;
            allPokemonFileReader1.close();
            BufferedReader allPokemonFileReader2 = new BufferedReader(new FileReader("allPokemon.txt"));
            for (int q = 0 ; q<lineCount ;q++){
                names.add(allPokemonFileReader2.readLine());
            }
            allPokemonFileReader2.close();
            while (names.contains("")) names.remove("");
            for(String i: names){
                currentPokemon.add(Pokemon.getPokemonByName(i));
            }
            System.out.println(currentPokemon);
            return currentPokemon;
        } catch (Exception e) {
            System.out.println("An error has occured during file reading.");
            return new ArrayList<>();
        }
    }

}
