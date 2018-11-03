package ross.palmer.interstellar.ai.neuralnetwork.solver.genetic;

import ross.palmer.interstellar.ai.neuralnetwork.Neuron;
import ross.palmer.interstellar.ai.neuralnetwork.NeuronLayer;

import java.util.HashMap;
import java.util.Map;

public class GeneticCodeLayer {

    private final long layerId;
    private Map<Long, GenePair> genePairs;

    public GeneticCodeLayer(long layerId) {
        this.layerId = layerId;
        genePairs = new HashMap<>();
    }

    public void generateFromNeuronLayers(NeuronLayer parentLayerA, NeuronLayer parentLayerB) {

        parentLayerA.getNeuronMap().forEach((neuronId, neuronA) -> {

            Neuron neuronB = parentLayerB.getNeuronMap().get(neuronId);

            GenePair genePair = new GenePair();
            genePair.parentNeuronA = new GeneNeuron(neuronA, Math.random() < 0.5);
            genePair.parentNeuronB = new GeneNeuron(neuronB, Math.random() < 0.5);

            genePair.determineChildNeuron();

            genePairs.put(neuronId, genePair);

        });
    }

    public void generateFromGeneticCode(GeneticCodeLayer parentAGeneticCodeLayer, GeneticCodeLayer parentBGeneticCodeLayer) {

        parentAGeneticCodeLayer.genePairs.forEach((neuronId, pairParentA) -> {
            GenePair pairParentB = parentBGeneticCodeLayer.genePairs.get(neuronId);

            GenePair genePair = new GenePair();
            genePair.parentNeuronA = pairParentA.getRandomParentNeuron();
            genePair.parentNeuronB = pairParentB.getRandomParentNeuron();

            genePair.determineChildNeuron();

            genePairs.put(neuronId, genePair);

        });
    }

    public long getLayerId() {
        return layerId;
    }

    private class GenePair {

        private GeneNeuron childNeuron;

        private GeneNeuron parentNeuronA;
        private GeneNeuron parentNeuronB;

        public void determineChildNeuron() {

            if (parentNeuronA.isDominate() && !parentNeuronB.isDominate()) {
                childNeuron = parentNeuronA;

            } else if (parentNeuronB.isDominate() && !parentNeuronA.isDominate()) {
                childNeuron = parentNeuronB;

            } else {
                childNeuron = mergeParentNeurons();
            }
        }

        public GeneNeuron getRandomParentNeuron() {
            double randomNumber = Math.random();
            if (randomNumber > 0.5) {
                return parentNeuronA;
            } else {
                return parentNeuronB;
            }
        }

        public GeneNeuron getChildNeuron() {
            return childNeuron;
        }

        private GeneNeuron mergeParentNeurons() {

            boolean dominate = parentNeuronA.isDominate() && parentNeuronB.isDominate();

            GeneNeuron newNeuron = new GeneNeuron(parentNeuronA.getNeuronId(), dominate);

            parentNeuronA.getWeights().forEach((inputNeuronId, weightA) -> {
                double weightB = parentNeuronB.getWeights().get(inputNeuronId);
                double combinedWeight = (weightA + weightB) / 2;
                newNeuron.setWeight(inputNeuronId, combinedWeight);
            });

            double combinedBias = (parentNeuronA.getBias() + parentNeuronB.getBias()) / 2;
            newNeuron.setBias(combinedBias);

            return newNeuron;

        }
    }
}
