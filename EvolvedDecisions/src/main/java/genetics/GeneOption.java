package genetics;

public enum GeneOption {

    BASE1, BASE2, BASE3, BASE4, BASE5, BASE6,
    BASE7, BASE8, BASE9, BASE0, BASEA, BASEB,
    BASEC, BASED, BASEE, BASEF;

    @Override
    public String toString() {
        String baseString = super.toString();
        return baseString.substring(4);
    }

    public int getCount() {
        return GeneOption.values().length;
    }

}
