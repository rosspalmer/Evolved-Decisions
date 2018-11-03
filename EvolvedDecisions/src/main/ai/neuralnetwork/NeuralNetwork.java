package ross.palmer.interstellar.ai.neuralnetwork;

import ross.palmer.interstellar.ai.decisions.DecisionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class NeuralNetwork implements DecisionModel {

    private final long networkId;
    private InputNeuronLayer inputLayer;
    private OutputNeuronLayer outputLayer;
    private List<NeuronLayer> hiddenNeuronLayers;

    private Function<Double, Double> activationFunction;
    private Function<Long, Neuron> neuronGenerator;

    public NeuralNetwork(long networkId) {
        this.networkId = networkId;
        hiddenNeuronLayers = new ArrayList<>();
    }

    public NeuralNetwork(long networkId, int numberOfInputs, int numberOfOutputs, List<Integer> hiddenLayerSizes) {
        this.networkId = networkId;
        hiddenNeuronLayers = new ArrayList<>();
        long layerIdCounter = 0;
        inputLayer = new InputNeuronLayer(layerIdCounter++, numberOfInputs);
        for (int layerSize : hiddenLayerSizes) {
            addHiddenNeuronLayer(generateNeuronLayer(layerIdCounter++, layerSize));
        }
        outputLayer = new OutputNeuronLayer(layerIdCounter, numberOfOutputs, neuronGenerator);
    }

    public NeuralNetwork(long networkId, NeuralNetwork networkToCopy) {
        this.networkId = networkId;
        inputLayer = new InputNeuronLayer(networkToCopy.getInputLayer().getLayerID(),
                networkToCopy.getInputLayer().getNumberOfNeurons());
        hiddenNeuronLayers = new ArrayList<>();
    }

    public void addHiddenNeuronLayer(NeuronLayer neuronLayer) {
        hiddenNeuronLayers.add(neuronLayer);
    }

    public NeuronLayer generateNeuronLayer(long layerId, int numberOfNeurons) {
        NeuronLayer newNeuronLayer = new NeuronLayer(layerId);
        for (int i = 0; i < numberOfNeurons; i++) {
            Neuron newNeuron = neuronGenerator.apply((long) i + 1);
            newNeuron.setActivationFunction(activationFunction);
            newNeuronLayer.addNeuron(newNeuron);
        }
        return newNeuronLayer;
    }

    public void feedForward() {

        if (hiddenNeuronLayers.size() > 0) {

            NeuronLayer firstHiddenLayer = hiddenNeuronLayers.get(0);
            firstHiddenLayer.updateActivationFunctionValues(inputLayer);

            if (hiddenNeuronLayers.size() > 1) {
                for (int i = 0; i < hiddenNeuronLayers.size(); i++) {
                    NeuronLayer previousLayer = hiddenNeuronLayers.get(i);
                    NeuronLayer nextLayer = hiddenNeuronLayers.get(i + 1);
                    nextLayer.updateActivationFunctionValues(previousLayer);
                }
            }

            NeuronLayer lastHiddenLayer = hiddenNeuronLayers.get(hiddenNeuronLayers.size() - 1);
            outputLayer.updateActivationFunctionValues(lastHiddenLayer);

        } else {
            outputLayer.updateActivationFunctionValues(inputLayer);
        }

    }

    @Override
    public Map<Long, Double> getOutputData(Map<Long, Double> inputValues) {
        getInputLayer().setInputValues(inputValues);
        feedForward();
        return getOutputLayer().getOutputActivationValues();
    }

    public void setActivationFunction(Function<Double, Double> activationFunction) {
        this.activationFunction = activationFunction;
    }

    public InputNeuronLayer getInputLayer() {
        return inputLayer;
    }

    public void setInputLayer(InputNeuronLayer inputLayer) {
        this.inputLayer = inputLayer;
    }

    public List<NeuronLayer> getHiddenNeuronLayers() {
        return hiddenNeuronLayers;
    }

    public long getNetworkId() {
        return networkId;
    }

    public void setNeuronGenerator(Function<Long, Neuron> neuronGenerator) {
        this.neuronGenerator = neuronGenerator;
    }

    public OutputNeuronLayer getOutputLayer() {
        return outputLayer;
    }

    public void setOutputLayer(OutputNeuronLayer outputLayer) {
        this.outputLayer = outputLayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NeuralNetwork that = (NeuralNetwork) o;
        return networkId == that.networkId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(networkId);
    }
}
