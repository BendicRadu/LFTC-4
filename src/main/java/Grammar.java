import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Grammar {

    private String startingSymbol;
    private List<String> terminals;
    private List<String> nonTerminals;
    private ProductionList productionList;
    private Production enchancedProduction;

    private static String fileContents;

    static  {
        try {
            fileContents = FileUtils.readFileToString(new File("D:\\Uni\\Lab4\\src\\main\\resources\\input.txt"), StandardCharsets.UTF_8)
                    .replaceAll("\n", " ")
                    .replaceAll("\r", " ")
                    .trim();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Grammar(boolean readFromFile){

        terminals = new ArrayList<>();
        nonTerminals = new ArrayList<>();
        productionList = new ProductionList();

        if (readFromFile){
            readFromFile();

            Production enhanced = new Production();
            enhanced.setSymbol(getStartingSymbol() + "'");
            enhanced.setSymbolsProduced(new ArrayList<String>(Collections.singleton(getStartingSymbol())));
            enhanced.setIndex(0);

            enchancedProduction = enhanced;
        }

    }

    private void readFromFile(){



        String[] splitContent = fileContents.split("\\s+");

        String nonTerminalsString = splitContent[0].split(":")[1].substring(1);
        nonTerminalsString = nonTerminalsString.substring(0, nonTerminalsString.length() - 1);

        nonTerminals.addAll(Arrays.asList(nonTerminalsString.split(",")));

        String terminalsString = splitContent[1].split(":")[1].substring(1);
        terminalsString = terminalsString.substring(0, terminalsString.length() - 1);

        terminals.addAll(Arrays.asList(terminalsString.split(",")));

        startingSymbol = splitContent[2].split(":")[1];

        for (int i = 4; i < splitContent.length; i++){

            String[] productionSplit = splitContent[i].split("->");
            String key = productionSplit[0];
            String value = productionSplit[1];

            String[] producedSplit = value.split("\\|");

            for (String produced : producedSplit){

                Production p = new Production();
                p.setSymbol(key);
                p.setIndex(0);

                List<String> symbolsProduced = new ArrayList<>();

                while (produced.length() != 0){

                    int endPos = findNextEndPos(produced);

                    String atom = produced.substring(0, endPos);
                    produced = produced.substring(endPos);

                    symbolsProduced.add(atom);
                }

                p.setSymbolsProduced(symbolsProduced);

                productionList.addProduction(p);

            }
        }
    }

    private int findNextEndPos(String produced){

        int i = 0;
        String atomCandidate = "";

        while (! isTerminal(atomCandidate) && ! isNonTerminal(atomCandidate) && i < produced.length()){
            i++;
            atomCandidate = produced.substring(0, i);
        }

        return i;

    }


    public boolean isNonTerminal(String atom){
        return nonTerminals.contains(atom);
    }

    public boolean isTerminal(String atom){
        return terminals.contains(atom);
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public void setStartingSymbol(String startingSymbol) {
        this.startingSymbol = startingSymbol;
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<String> terminals) {
        this.terminals = terminals;
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public void setNonTerminals(List<String> nonTerminals) {
        this.nonTerminals = nonTerminals;
    }

    public ProductionList getProductionList() {
        return productionList;
    }

    public void setProductionList(ProductionList productionList) {
        this.productionList = productionList;
    }

    public Production getEnchancedProduction() {
        return enchancedProduction;
    }

    public void setEnchancedProduction(Production enchancedProduction) {
        this.enchancedProduction = enchancedProduction;
    }

    public static String getFileContents() {
        return fileContents;
    }

    public static void setFileContents(String fileContents) {
        Grammar.fileContents = fileContents;
    }
}
