package evolved.genetics;

import evolved.graph.Node;
import evolved.graph.NodeTag;

public class SpeciesNode extends Node {

    public SpeciesNode(int nodeId) {
        super(nodeId);
        addNodeTag(NodeTag.SPECIES);
    }

}
