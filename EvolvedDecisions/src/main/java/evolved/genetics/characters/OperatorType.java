package evolved.genetics.characters;

import evolved.genetics.GeneOption;

import static evolved.genetics.GeneOption.*;

public enum OperatorType {

    AND (BASE0), OR (BASE1), NOT (BASE2), XOR (BASE3),
    NAND (BASE4), NOR (BASE5), SUM (BASE6), DIFF (BASE7),
    PROD (BASE8), DIV (BASE9), MAX (BASEA), MIN (BASEB),
    CONS (BASEC), ABS (BASED);

    private GeneOption geneBOption;

    OperatorType(GeneOption geneBOption) {
        this.geneBOption = geneBOption;
    }

    public static OperatorType getByGeneOption(GeneOption geneOption) {
        OperatorType operatorType = null;
        for (OperatorType option : values()) {
            if (option.getGeneBOption() == geneOption) {
                operatorType = option;
                break;
            }
        }
        return operatorType;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public GeneOption getGeneBOption() {
        return geneBOption;
    }

}
