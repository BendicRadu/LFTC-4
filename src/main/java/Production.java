import java.util.List;
import java.util.Objects;

public class Production {

    private String symbol;
    private List<String> symbolsProduced;
    private int index = 0;

    public boolean isDone;

    public String getSymbol() {
        return symbol;
    }

    public String getCurrent(){
        return symbolsProduced.get(index);
    }


    public boolean increment() throws Exception {

        index++;

        if (index < symbolsProduced.size()){
            return true;
        }

        isDone = true;
        return false;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<String> getSymbolsProduced() {
        return symbolsProduced;
    }

    public void setSymbolsProduced(List<String> symbolsProduced) {
        this.symbolsProduced = symbolsProduced;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        str.append(symbol).append("->");

        for (int i = 0; i < symbolsProduced.size(); i++){
            if (index == i && ! isDone){
                str.append(".");
            }
            str.append(symbolsProduced.get(i));
        }

        if(isDone){
            str.append(".");
        }

        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Production that = (Production) o;
        return getIndex() == that.getIndex() &&
                Objects.equals(getSymbol(), that.getSymbol()) &&
                Objects.equals(getSymbolsProduced(), that.getSymbolsProduced());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSymbol(), getSymbolsProduced(), getIndex());
    }
}


