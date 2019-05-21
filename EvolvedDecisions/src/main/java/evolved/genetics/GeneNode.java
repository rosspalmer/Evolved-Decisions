package evolved.genetics;

import evolved.genetics.characters.GeneCharacter;
import evolved.graph.Node;

import java.util.Objects;

public class GeneNode extends Node {

    private final GeneCharacter geneCharacter;

    public GeneNode(int geneId, GeneCharacter geneCharacter) {
        super(geneId);
        this.geneCharacter = geneCharacter;
    }

    public int getGeneId() {
        return getNodeId();
    }

    public GeneCharacter getGeneCharacter() {
        return geneCharacter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneNode geneNode = (GeneNode) o;
        return getGeneId() == geneNode.getGeneId() &&
                getGeneCharacter() == geneNode.getGeneCharacter();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGeneId(), getGeneCharacter());
    }
}
