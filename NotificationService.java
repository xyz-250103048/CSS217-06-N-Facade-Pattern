
import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private List<String> notifications;

    public NotificationService() {
        notifications = new ArrayList<>();
    }

    public void sendNotification(String message) {
        notifications.add(message);
    }

    public int getNotificationCount() {
        return notifications.size();
    }

    public List<String> getNotifications() {
        return List.copyOf(notifications);
    }
}
