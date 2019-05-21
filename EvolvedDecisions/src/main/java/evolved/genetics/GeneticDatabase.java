package evolved.genetics;

import evolved.graph.Node;
import evolved.graph.NodeDatabase;
import evolved.graph.NodeRelationship;
import evolved.graph.NodeSearch;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneticDatabase extends NodeDatabase {

    public void addGeneList(List<GeneNode> geneList) {

        Node previousNode = geneList.get(0);
        if (containsNode(previousNode)) {
            previousNode = getNode(previousNode.getNodeId());
        } else {
            addNode(previousNode);
        }

        for (int i=1; i < geneList.size(); i++) {

            Node nextNode = geneList.get(i);
            if (containsNode(nextNode)) {
                nextNode = getNode(nextNode.getNodeId());
            } else {
                addNode(nextNode);
            }

            previousNode.addRelatedNodes(NodeRelationship.PATH_TO, nextNode);

        }
    }

    public void addSpecies(SpeciesNode speciesNode, Set<GeneNode> geneNodeSet, GeneNode startGeneNode) {

        Set<Node> speciesGeneNodes = getNodes().stream()
                .filter(geneNodeSet::contains)
                .collect(Collectors.toSet());

        speciesGeneNodes.forEach(node -> speciesNode.addRelatedNodes(NodeRelationship.SPECIES_GENES, node));
        speciesNode.addRelatedNodes(NodeRelationship.SPECIES_START_GENE, startGeneNode);

        addNode(speciesNode);

    }

    public List<GeneNode> getSpeciesGeneList(SpeciesNode speciesNode) {

        Node startGeneNode = speciesNode.getRelatedNodes(NodeRelationship.SPECIES_START_GENE).stream().findAny().get();

        Set<Node> speciesGeneNodeSet = speciesNode.getRelatedNodes(NodeRelationship.SPECIES_GENES);
        List<GeneNode> orderGeneNodeList = NodeSearch.getNodePath(startGeneNode, speciesGeneNodeSet)
                .stream().map();



    }

}
