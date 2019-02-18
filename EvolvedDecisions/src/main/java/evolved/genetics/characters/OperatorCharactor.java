package evolved.genetics.characters;

import evolved.model.compute.Operator;

public abstract class OperatorCharactor implements GeneCharacter {

    private final Operator operator;

    public OperatorCharactor(Operator operator) {
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }

}
