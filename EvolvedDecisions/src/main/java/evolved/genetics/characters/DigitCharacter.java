package evolved.genetics.characters;

public class DigitCharacter implements GeneCharacter {

    private final int value;

    protected DigitCharacter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
