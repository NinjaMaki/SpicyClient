package spicy.notifications;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import spicy.chatCommands.Command;

public class NotificationManager {
	
	public CopyOnWriteArrayList<Notification> notifications = new CopyOnWriteArrayList<Notification>();
	
	private int defaultTargetX = 0, defaultTargetY = 0, defaultStartingX = 0, defaultStartingY = 0, defaultSpeed = 2;
	
	public void createNotification(String title, String text, boolean showTimer, long timeOnScreen, Type type, Color color) {
		
		notifications.add(new Notification(title, text, showTimer, timeOnScreen, type, color, defaultTargetX, defaultTargetY, defaultStartingX, defaultStartingY, defaultSpeed));
		
	}
	
	public void createNotification(Notification notification) {
		
		notifications.add(notification);
		
	}
	
	public void onRender() {
		
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer fr = mc.fontRendererObj;
		ScaledResolution sr = new ScaledResolution(mc);
		
		defaultStartingX = sr.getScaledWidth();
		defaultStartingY = sr.getScaledHeight();
		defaultTargetX = sr.getScaledWidth() - 180;
		defaultTargetY = 0;
		defaultTargetY = sr.getScaledHeight() - 54 - (54 * notifications.size());
		
		for (Notification not : notifications) {
			not.onRender();
		}
		
	}
	
	public static NotificationManager notificationManager = null;
	
	public static NotificationManager getNotificationManager() {
		
		if (notificationManager == null) {
			notificationManager = new NotificationManager();
		}
		
		return notificationManager;
		
	}

	public double getDefaultTargetX() {
		return defaultTargetX;
	}

	public void setDefaultTargetX(int defaultTargetX) {
		this.defaultTargetX = defaultTargetX;
	}

	public double getDefaultTargetY() {
		return defaultTargetY;
	}

	public void setDefaultTargetY(int defaultTargetY) {
		this.defaultTargetY = defaultTargetY;
	}

	public double getDefaultStartingX() {
		return defaultStartingX;
	}

	public void setDefaultStartingX(int defaultStartingX) {
		this.defaultStartingX = defaultStartingX;
	}

	public double getDefaultStartingY() {
		return defaultStartingY;
	}

	public void setDefaultStartingY(int defaultStartingY) {
		this.defaultStartingY = defaultStartingY;
	}

	public double getDefaultSpeed() {
		return defaultSpeed;
	}

	public void setDefaultSpeed(int defaultSpeed) {
		this.defaultSpeed = defaultSpeed;
	}
	
}