package ross.palmer.interstellar.ai.neuralnetwork.solver.genetic;

import ross.palmer.interstellar.ai.neuralnetwork.Neuron;

public class GeneNeuron extends Neuron {

    private final boolean dominate;

    public GeneNeuron(long neuronId, boolean dominate) {
        super(neuronId);
        this.dominate = dominate;
    }

    public GeneNeuron(Neuron neuron, boolean dominate) {
        super(neuron.getNeuronId(), neuron);
        this.dominate = dominate;
    }

    public boolean isDominate() {
        return dominate;
    }
}
