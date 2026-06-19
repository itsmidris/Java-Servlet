import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Request Started");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request Finished");
    }
}
