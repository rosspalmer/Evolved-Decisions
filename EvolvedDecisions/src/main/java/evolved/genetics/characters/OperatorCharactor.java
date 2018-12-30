package evolved.genetics.characters;

import evolved.genetics.Gene;

public abstract class OperatorCharactor extends CodeCharacter {

    private final OperatorType operatorType;

    public OperatorCharactor(OperatorType operatorType, Gene geneA, Gene geneB) {
        super(geneA, geneB);
        this.operatorType = operatorType;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

}
