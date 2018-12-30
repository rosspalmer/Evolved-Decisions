package evolved.genetics.graph.relationships;

import java.util.Objects;

public abstract class NodeRelationship {

    private final int relationshipId;
    private final NodeRelationshipType relationshipType;

    protected NodeRelationship(int relationshipId, NodeRelationshipType relationshipType) {
        this.relationshipId = relationshipId;
        this.relationshipType = relationshipType;
    }

    public NodeRelationshipType getRelationshipType() {
        return relationshipType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeRelationship that = (NodeRelationship) o;
        return relationshipId == that.relationshipId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(relationshipId);
    }
}
