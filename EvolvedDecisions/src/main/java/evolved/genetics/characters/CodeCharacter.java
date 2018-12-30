package evolved.genetics.characters;

import evolved.genetics.Gene;

public abstract class CodeCharacter {

    private final Gene geneA;
    private final Gene geneB;

    public CodeCharacter(Gene geneA, Gene geneB) {

        this.geneA = geneA;
        this.geneB = geneB;
    }

    public Gene getGeneA() {
        return geneA;
    }

    public Gene getGeneB() {
        return geneB;
    }

    @Override
    public String toString() {
        return geneA.toString() + geneB.toString();
    }

}
