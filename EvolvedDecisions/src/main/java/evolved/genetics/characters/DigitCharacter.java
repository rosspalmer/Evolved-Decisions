package evolved.genetics.characters;

import evolved.genetics.Gene;

public class DigitCharacter extends CodeCharacter {

    private final int value;

    protected DigitCharacter(Gene geneA, Gene geneB) {
        super(geneA, geneB);
        value = Integer.getInteger(geneB.toString());
    }

    public int getValue() {
        return value;
    }

}
