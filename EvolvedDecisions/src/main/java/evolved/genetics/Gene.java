package evolved.genetics;

import evolved.genetics.characters.GeneCharacter;

import java.util.Objects;

public class Gene {

    private final int geneId;
    private final GeneCharacter geneCharacter;

    public Gene(int geneId, GeneCharacter geneCharacter) {
        this.geneId = geneId;
        this.geneCharacter = geneCharacter;
    }

    public int getGeneId() {
        return geneId;
    }

    public GeneCharacter getGeneCharacter() {
        return geneCharacter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gene gene = (Gene) o;
        return getGeneId() == gene.getGeneId() &&
                getGeneCharacter() == gene.getGeneCharacter();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGeneId(), getGeneCharacter());
    }
}
