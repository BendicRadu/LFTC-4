import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        ProductionList p = new ProductionList();
        Production prod = new Production();

        prod.setSymbol("S'");

        List<String> symbols = new ArrayList<>();
        symbols.add("S");

        prod.setSymbolsProduced(symbols);
        p.addProduction(prod);

        prod = new Production();
        prod.setSymbol("S");

        symbols = new ArrayList<>();
        symbols.add("A");
        symbols.add("B");

        prod.setSymbolsProduced(symbols);
        p.addProduction(prod);


        prod = new Production();

        prod.setSymbol("A");
        symbols = new ArrayList<>();
        symbols.add("a");

        prod.setSymbolsProduced(symbols);
        p.addProduction(prod);

        prod = new Production();
        prod.setSymbol("B");

        symbols = new ArrayList<>();
        symbols.add("b");

        prod.setSymbolsProduced(symbols);
        p.addProduction(prod);


        prod = new Production();
        prod.setSymbol("S'");

        symbols = new ArrayList<>();
        symbols.add("S");

        prod.setSymbolsProduced(symbols);

        LR.run(p, prod);
    }
}
