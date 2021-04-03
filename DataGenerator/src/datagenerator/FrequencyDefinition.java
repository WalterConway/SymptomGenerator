package datagenerator;

public class FrequencyDefinition {
	Frequency frequency;
	double percentage;
	
	
	public FrequencyDefinition(Frequency frequency, double percentage) {
		super();
		this.frequency = frequency;
		this.percentage = percentage;
	}
	
	public Frequency getFrequency() {
		return frequency;
	}
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "FrequencyDefinition [frequency=" + frequency + ", percentage=" + percentage + "]";
	}
	
	

}
