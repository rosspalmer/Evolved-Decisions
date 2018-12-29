package genetics;

import java.util.Objects;

public class Gene {

    private final int geneId;
    private final GeneOption geneOption;

    public Gene(int geneId, GeneOption geneOption) {
        this.geneId = geneId;
        this.geneOption = geneOption;
    }

    public int getGeneId() {
        return geneId;
    }

    public GeneOption getGeneOption() {
        return geneOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gene gene = (Gene) o;
        return getGeneId() == gene.getGeneId() &&
                getGeneOption() == gene.getGeneOption();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGeneId(), getGeneOption());
    }
}
