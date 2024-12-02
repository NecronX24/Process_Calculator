package finalProject;

public enum DoubleList {
	queue, stack, rr;
	private Node head = null;
    private Node tail = null;
    private int[] clock;
    private int length = 0;
    private int quantum = 1;
    
    private float totalTimeAvg = 0;
    private float waitTimeAvg = 0;
    private float serviceIndexAvg = 0;
    
    public void setQuantum(int i) {
    	this.quantum=i;
    }

    public int getQuantum() {
    	return this.quantum;
    }
    
    public int getLength() {
    	return this.length;
    }
    
    public float[] getAvg() {
    	float[] avg = new float[3];
    	avg[0] = this.totalTimeAvg;
    	avg[1] = this.waitTimeAvg;
    	avg[2] = this.serviceIndexAvg;
    	return avg;
    }
    
    public Node getHead() {
    	return this.head;
    }
    
    public void setHeadNull() {
    	this.head=null;
    	this.tail=null;
    	this.length=0;
    	this.totalTimeAvg=0; 
    	this.waitTimeAvg=0;
    	this.serviceIndexAvg=0;
    	}

    private Node createNode(int time, int initialTime) {
        length++;
        return new Node(time, initialTime, null, null);
    }

    public void insertAtBeginning(int time, int initialTime) {
        Node newNode = createNode(time, initialTime);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void insertAtEnd(int time, int initialTime) {
    	Node newNode = createNode(time, initialTime);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public boolean isEmpty() {
    	return head == null;
    }
    
    public void setAvg() {
    	Node currentNode = head;
    	this.totalTimeAvg=0; 
    	this.waitTimeAvg=0;
    	this.serviceIndexAvg=0;
    	while(currentNode!=null) {
    		this.totalTimeAvg += currentNode.totalTime;
    		this.waitTimeAvg = this.waitTimeAvg + currentNode.waitTime;
    		this.serviceIndexAvg = this.serviceIndexAvg + currentNode.serviceIndex;
    		currentNode = currentNode.next;
    	}
    	float totalTime = (float) this.totalTimeAvg/this.length;
    	this.totalTimeAvg = Math.round(totalTime * 10000) / 10000f;
    	
    	float waitTime = (float) this.waitTimeAvg/this.length;
    	this.waitTimeAvg = Math.round(waitTime * 10000) / 10000f;
    	
    	float serviceIndex = (float) this.serviceIndexAvg/this.length;
    	this.serviceIndexAvg = Math.round(serviceIndex * 10000) / 10000f;
    	
    }
    
    public void setFinalTime(int finalTime, Node node) {
    	node.setFinalTime(finalTime);
    }
    
    public void calculateQueue() {
        if (this.isEmpty()) {
            return; // Manejo de lista vacía
        }
        
        if (this.head.getFinalTime()!=0) {
        	Node currentDeleting = this.head;
        	while (currentDeleting !=null) {
        		currentDeleting.setFinalTime(0);
        		currentDeleting=currentDeleting.next;
        	}
        }
        clock = new int[this.length];
        int currentClock = 0;
        int finalTimeAdded = 0;
        
        while (finalTimeAdded != this.length) {
            Node current = this.head;
            boolean finish = true; // Reiniciar finish en cada iteración
            
            while (current != null && finish) {
            	if (current.getFinalTime() == 0) {
            		if (current.initialTime <= currentClock) {
            			setFinalTime(currentClock, current);
            			currentClock = current.getFinalTime();
            			clock[finalTimeAdded] = currentClock;
            			finalTimeAdded++;
            			finish = false;
            		}
            	}
                current = current.next; // Avanzar al siguiente nodo
                if (current == null && finish) currentClock++; 
            }
        }
        setAvg();
    }
    
    public void calculateStack() {
        if (this.head == null) {
            return; // Manejo de lista vacía
        }
        if (this.head.getFinalTime()!=0) {
        	Node currentDeleting = this.head;
        	while (currentDeleting !=null) {
        		currentDeleting.setFinalTime(0);
        		currentDeleting=currentDeleting.next;
        	}
        }
        clock = new int[this.length];
        int currentClock = 0;
        int finalTimeAdded = 0;
        
        while (finalTimeAdded != this.length) {
            Node current = this.tail;
            boolean finish = true; // Reiniciar finish en cada iteración
            
            while (current != null && finish) {
            	if (current.getFinalTime() == 0) {
            		if (current.initialTime <= currentClock) {
            			setFinalTime(currentClock, current);
            			currentClock = current.getFinalTime();
            			clock[finalTimeAdded] = currentClock;
            			finalTimeAdded++;
            			finish = false;
            		}
            	}
                current = current.prev; // Avanzar al siguiente nodo
                if (current == null && finish) currentClock++; 
            }
        }
        setAvg();
    }
    
    public void calculateRr() {
        if (this.head == null) {
            return; // Manejo de lista vacía
        }
        if (this.head.getFinalTime()!=0) {
        	Node currentDeleting = this.head;
        	while (currentDeleting !=null) {
        		currentDeleting.setFinalTime(0);
        		currentDeleting=currentDeleting.next;
        	}
        }
        clock = new int[this.length];
        int currentClock = 0;
        int finalTimeAdded = 0;
        
        boolean voidCycle = true;
        
        Node current = this.head;
        while (finalTimeAdded != this.length) {
            	if (current.getFinalTime() == 0) {
            		
            		if (current.initialTime <= currentClock) {
            			if(current.getResidualTime()<= quantum) {
            				setFinalTime(currentClock, current);
            				currentClock = currentClock + current.getResidualTime();
            				clock[finalTimeAdded] = currentClock;
            				finalTimeAdded++;
            				current.setResidualTime(0);
            			} else {
            				current.setResidualTime(current.getResidualTime()-quantum);
            				currentClock = currentClock+quantum;
            			}
            			voidCycle = false;
            		}
            	}
                current = current.next; // Avanzar al siguiente nodo
                if (current == null) {
                	current = head;
                	if (voidCycle)currentClock++;
                }
        }
        setAvg();
    }

}
