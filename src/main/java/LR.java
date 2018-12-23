import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class LR {

    private static Queue<Pair<ProductionList, String>> queue = new ArrayDeque<>();

    private static ProductionList closure(ProductionList allProductions, Production elem){


        ProductionList closure = new ProductionList();
        queue.add(new Pair<>(closure, elem.getCurrent()));

        closure.addProduction(elem);

        ProductionList prevClosure;

        do{
            prevClosure = closure;

            int i = 0;

            while (i < closure.getProductions().size()){

                Optional<Production> optionalProduction;

                if (!closure.getProductions().get(i).isDone) {
                    optionalProduction = allProductions.getSub(closure.getProductions().get(i).getCurrent());
                }else{
                    optionalProduction = Optional.empty();
                }

                if (optionalProduction.isPresent() && closure.doesNotContain(optionalProduction.get())){
                    try {
                        Production p2 = optionalProduction.get();

                        queue.add(new Pair<>(closure, p2.getCurrent()));
                        closure.addProduction(p2);
                        i = -1;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                i++;
            }


        }while (! closure.equals(prevClosure));

        return closure;
    }

    private static Optional<ProductionList> goTo(ProductionList productions, ProductionList state, String symbol) throws Exception {

        Optional<Production> productionOptional = state.getSubForGoTo(symbol);

        Production p;

        if (productionOptional.isPresent()){
            p = productionOptional.get();

            if (p.increment()){
                return Optional.of(closure(productions, p));
            }

            ProductionList productionList = new ProductionList();
            productionList.addProduction(p);
            return Optional.of(productionList);
        }

        return Optional.empty();
    }


    public static List<ProductionList> run(ProductionList productions, Production start) throws Exception {

        List<ProductionList> lrTableData = new ArrayList<>();

        ProductionList s0 = closure(productions, start);

        Integer state = 0;

        System.out.println("s" + state + " " + s0);


        lrTableData.add(s0);


        while (!queue.isEmpty()){

            Pair<ProductionList, String> pair = queue.remove();

            Optional<ProductionList> goToOptional = goTo(productions, pair.getKey(), pair.getValue());

            if (goToOptional.isPresent()){
                state++;
                lrTableData.add(goToOptional.get());
                System.out.println("s" + state + " " + goToOptional.get());

            }

        }

        return lrTableData;

    }

}
