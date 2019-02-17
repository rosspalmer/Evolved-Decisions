package evolved.model.operators;

class OperatorFactory {

    static Operator generate(OperatorType operatorType) {

        Operator operator = null;

        if (operatorType == OperatorType.AND) {
            operator = new AndOperator();
        } else if (operatorType == OperatorType.OR) {
            operator = new OrOperator();
        } else if (operatorType == OperatorType.NOT) {
            operator = new NotOperator();

        } else if (operatorType == OperatorType.SUM) {
            operator = new SumOperator();
        } else if (operatorType == OperatorType.PROD) {
            operator = new ProductOperator();
        } else if (operatorType == OperatorType.INV) {
            operator = new InverseOperator();

        } else if (operatorType == OperatorType.MAX) {
            operator = new MaxOperator();
        } else if (operatorType == OperatorType.MIN) {
            operator = new MinOperator();
        } else if (operatorType == OperatorType.ABS) {
            operator = new AbsoluteOperator();
        }

        if (operator == null)
            throw new RuntimeException("OperatorType is not supported");

        return operator;
    }
}
