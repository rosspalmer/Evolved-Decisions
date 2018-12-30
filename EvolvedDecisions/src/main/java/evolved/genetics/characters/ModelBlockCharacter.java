package evolved.genetics.characters;

import evolved.genetics.Gene;
import evolved.genetics.GeneOption;

import java.util.Set;

public abstract class ModelBlockCharacter extends CodeCharacter {

    private final ModelBlockType modelBlockType;

    public ModelBlockCharacter(GeneOption geneARequiredOption, Set<GeneOption> geneBSupported, Gene geneA, Gene geneB) {
        super(geneA, geneB);
        modelBlockType = determineModelBlockIndicator(geneB);
    }

    public ModelBlockType getModelBlockType() {
        return modelBlockType;
    }

    private ModelBlockType determineModelBlockIndicator(Gene geneB) {
        if (geneB.getGeneOption() == GeneOption.BASEA) {
            return ModelBlockType.BLOCK_START;
        } else if (geneB.getGeneOption() == GeneOption.BASEB) {
            return ModelBlockType.BLOCK_END;
        } else if (geneB.getGeneOption() == GeneOption.BASEC) {
            return ModelBlockType.SUB_START;
        } else if (geneB.getGeneOption() == GeneOption.BASED) {
            return ModelBlockType.SUB_END;
        } else if (geneB.getGeneOption() == GeneOption.BASEE) {
            return ModelBlockType.INPUT;
        } else if (geneB.getGeneOption() == GeneOption.BASEF) {
            return ModelBlockType.OUTPUT;
        } else {
            throw new RuntimeException("geneB Option is not supported");
        }
    }


}
