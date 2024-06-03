import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

enum CallPriority {
    LOW,
    MEDIUM,
    HIGH
}

class Customer {
    private String name;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class Call {
    private Customer customer;
    private String issueDescription;
    private CallPriority priority;

    public Call(Customer customer, String issueDescription, CallPriority priority) {
        this.customer = customer;
        this.issueDescription = issueDescription;
        this.priority = priority;
    }

    // Getters
    public Customer getCustomer() {
        return customer;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public CallPriority getPriority() {
        return priority;
    }
}

class Agent {
    private String name;
    private boolean available;

    public Agent(String name) {
        this.name = name;
        this.available = true;
    }

    public void handleCall(Call call) {
        System.out.println("Agent " + name + " is handling a " + call.getPriority() + " priority call from " + call.getCustomer().getName() + ".");
        // Handle the call
        System.out.println("Issue resolved for customer: " + call.getIssueDescription());
        System.out.println("Call ended.");
        available = true;
    }

    // Getters and setters
    public boolean isAvailable() {
        return available;
    }
}

public class CallCenter {
    private List<Agent> agents;
    private Queue<Call> callsQueue;

    public CallCenter() {
        agents = new ArrayList<>();
        callsQueue = new LinkedList<>();
    }

    public void addAgent(Agent agent) {
        agents.add(agent);
    }

    public void routeCall(Call call) {
        if (call.getPriority() == CallPriority.HIGH) {
            Agent availableAgent = findAvailableAgent();
            if (availableAgent != null) {
                availableAgent.handleCall(call);
                return;
            }
        }
        System.out.println("Placing call in the queue.");
        callsQueue.add(call);
    }

    private Agent findAvailableAgent() {
        for (Agent agent : agents) {
            if (agent.isAvailable()) {
                return agent;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CallCenter callCenter = new CallCenter();

        // Create agents
        Agent agent1 = new Agent("Agent 1");
        Agent agent2 = new Agent("Agent 2");
        callCenter.addAgent(agent1);
        callCenter.addAgent(agent2);

        // Simulate incoming calls
        Customer customer1 = new Customer("John", "1234567890");
        Customer customer2 = new Customer("Alice", "0987654321");

        Call call1 = new Call(customer1, "Internet not working.", CallPriority.HIGH);
        Call call2 = new Call(customer2, "Billing issue.", CallPriority.MEDIUM);

        // Route calls
        callCenter.routeCall(call1);
        callCenter.routeCall(call2);
    }
}
