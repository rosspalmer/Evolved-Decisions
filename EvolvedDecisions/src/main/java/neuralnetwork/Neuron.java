package neuralnetwork;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Neuron {

    private final long neuronId;
    private Map<Long, Double> weights;
    private double bias;
    private double activateFunctionValue;

    private Function<Double, Double> activationFunction;
    private Supplier<Double> newWeightGenerator;

    public Neuron(long neuronId) {
        this.neuronId = neuronId;
        weights = new HashMap<>();
    }

    public Neuron(long neuronId, Neuron neuron) {
        this.neuronId = neuronId;
        weights = new HashMap<>();
        neuron.getWeights().forEach((key, value) -> weights.put(key, value));
        setBias(neuron.getBias());
        setActivationFunction(neuron.getActivationFunction());
        setNewWeightGenerator(neuron.getNewWeightGenerator());
    }

    public void calculateActivationFunctionValue(NeuronLayer neuronLayer) {

        Collection<Neuron> inputNeurons = neuronLayer.getNeuronMap().values();

        double weightInputProductSum = inputNeurons.stream()
                .mapToDouble(inputNeuron -> {

            // Weights for input neurons are generated if they did not previously exist
            double weight;
            if (weights.containsKey(inputNeuron.getNeuronId())) {
                weight = weights.get(inputNeuron.getNeuronId());
            } else {
                weight = newWeightGenerator.get();
                weights.put(inputNeuron.getNeuronId(), weight);
            }

            return weight * inputNeuron.getActivateFunctionValue();

        }).sum();

        double activationFunctionX = weightInputProductSum + getBias();

        setActivateFunctionValue(activationFunction.apply(activationFunctionX));

    }

    public double getActivateFunctionValue() {
        return activateFunctionValue;
    }

    public void setActivateFunctionValue(double activateFunctionValue) {
        this.activateFunctionValue = activateFunctionValue;
    }

    private Function<Double, Double> getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(Function<Double, Double> activationFunction) {
        this.activationFunction = activationFunction;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public long getNeuronId() {
        return neuronId;
    }

    private Supplier<Double> getNewWeightGenerator() {
        return newWeightGenerator;
    }

    public void setNewWeightGenerator(Supplier<Double> newWeightGenerator) {
        this.newWeightGenerator = newWeightGenerator;
    }

    public void getWeight(long inputNeuronId) {
        weights.get(inputNeuronId);
    }

    public Map<Long, Double> getWeights() {
        return weights;
    }

    public void setWeight(long inputNeuronId, double weight) {
        weights.put(inputNeuronId, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neuron neuron = (Neuron) o;
        return neuronId == neuron.neuronId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(neuronId);
    }
}
