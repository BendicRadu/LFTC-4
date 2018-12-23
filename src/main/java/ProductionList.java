import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ProductionList {

    private List<Production> productions = new ArrayList<>();

    public boolean doesNotContain(Production production){

        return productions.stream()
                .noneMatch(x -> x.equals(production));

    }

    public void addProduction(Production production){
        productions.add(production);
    }


    public Optional<Production> getSubForGoTo(String symbol){

        for (Production p : productions){
            if (!p.isDone && p.getCurrent().equals(symbol)){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }


    public Optional<Production> getSub(String symbol){

        //TODO replace with terminal/non-terminal check
        if (symbol.equals(symbol.toLowerCase())){

            for (Production p : productions){
                for (String s : p.getSymbolsProduced()){
                    if (s.equals(symbol)){
                        return Optional.of(p);
                    }
                }
            }
        }

        return productions.stream()
                .filter(x -> x.getSymbol().equals(symbol))
                .findFirst();

    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }


    public Stream<Production> stream(){
        return this.productions.stream();
    }

    @Override
    public String toString() {
        return productions.toString();
    }
}
