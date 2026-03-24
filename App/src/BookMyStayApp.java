import java.util.*;

class Service {
    private String serviceName;
    private double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<Service>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, Service service) {
        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());
        servicesByReservation.get(reservationId).add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {
        List<Service> services = servicesByReservation.get(reservationId);
        if (services == null) {
            return 0.0;
        }
        double total = 0;
        for (Service s : services) {
            total += s.getCost();
        }
        return total;
    }

    public List<Service> getServicesForReservation(String reservationId) {
        return servicesByReservation.getOrDefault(reservationId, new ArrayList<>());
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();

        String resId1 = "RES-101";
        String resId2 = "RES-102";

        Service wifi = new Service("High-Speed WiFi", 15.0);
        Service breakfast = new Service("Buffet Breakfast", 25.0);
        Service spa = new Service("Spa Treatment", 50.0);

        manager.addService(resId1, wifi);
        manager.addService(resId1, breakfast);
        manager.addService(resId2, spa);

        System.out.println("Total Add-on Cost for " + resId1 + ": $" + manager.calculateTotalServiceCost(resId1));
        System.out.println("Total Add-on Cost for " + resId2 + ": $" + manager.calculateTotalServiceCost(resId2));

        System.out.println("\nServices for " + resId1 + ":");
        for (Service s : manager.getServicesForReservation(resId1)) {
            System.out.println("- " + s.getServiceName() + ": $" + s.getCost());
        }
    }
}