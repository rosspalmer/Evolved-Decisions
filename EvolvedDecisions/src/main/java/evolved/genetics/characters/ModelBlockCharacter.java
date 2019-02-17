package evolved.genetics.characters;

public abstract class ModelBlockCharacter implements GeneCharacter {

    private final ModelBlockType modelBlockType;

    public ModelBlockCharacter(ModelBlockType modelBlockType) {
        this.modelBlockType = modelBlockType;
    }

    public ModelBlockType getModelBlockType() {
        return modelBlockType;
    }

}
