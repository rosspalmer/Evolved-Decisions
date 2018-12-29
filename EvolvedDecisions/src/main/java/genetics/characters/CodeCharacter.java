package genetics.characters;

import genetics.Gene;
import genetics.GeneOption;

import java.util.Set;

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
