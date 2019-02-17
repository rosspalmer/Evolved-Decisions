package evolved.genetics.characters;

public abstract class OperatorCharactor implements GeneCharacter {

    private final OperatorType operatorType;

    public OperatorCharactor(OperatorType operatorType) {
        this.operatorType = operatorType;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

}
