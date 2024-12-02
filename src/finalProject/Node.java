package finalProject;

public class Node {
	int time;
	int initialTime;
	int finalTime = 0;
	public int totalTime = 0;
	int waitTime = 0;
	int residualTime=0;
	float serviceIndex=0;
    Node next;
    Node prev;

    public Node(int time, int initialTime, Node nextNode, Node prevNode) {
        this.time = time;
        this.initialTime = initialTime;
        this.next = nextNode;
        this.prev = prevNode;
        this.residualTime = this.getTime();
    }
    
    public void setFinalTime(int finalTime) {
    	if (finalTime !=0) {
    		this.finalTime = finalTime + this.residualTime;
    		this.totalTime = this.finalTime - this.initialTime;
    		this.waitTime = this.totalTime - this.time;
    		float serviceIndex = (float) this.time / this.totalTime;
    		this.serviceIndex = Math.round(serviceIndex * 10000) / 10000f;
    	} else if (finalTime ==0) {
    		this.finalTime=0;
    		this.totalTime =0;
    		this.waitTime=0;
    		this.serviceIndex=0;
    		this.residualTime = this.getTime();
    	}
    }
    
    public int getTime() {
    	return this.time;
    }
    
    public int getResidualTime() {
    	return this.residualTime;
    }
    
    public void setResidualTime(int number) {
    	this.residualTime = number;
    }
    
    public int getFinalTime() {
    	return this.finalTime;
    }
    
    public Node getNext() {
    	return this.next;
    }
    
    public int getInitialTime() {
    	return this.initialTime;
    }
    
    public int[] getValues() {
    	int[] values = new int[5];
    	values[0] = this.time;
    	values[1] = this.initialTime;
    	values[2] = this.finalTime;
    	values[3] = this.totalTime;
    	values[4] = this.waitTime;
    	return values;
    }
    
    public float getServiceIndex() { return this.serviceIndex;}
}
