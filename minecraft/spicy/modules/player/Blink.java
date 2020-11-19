package spicy.modules.player;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C03PacketPlayer;
import spicy.chatCommands.Command;
import spicy.events.Event;
import spicy.events.listeners.EventSendPacket;
import spicy.events.listeners.EventUpdate;
import spicy.modules.Module;
import spicy.notifications.Color;
import spicy.notifications.NotificationManager;
import spicy.notifications.Type;

public class Blink extends Module {
	
	public static ArrayList<Packet> packets = new ArrayList<Packet>();
	
	public Blink() {
		super("Blink", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onEnable() {
		packets.clear();
		
		if (mc.isSingleplayer()) {
			NotificationManager.getNotificationManager().createNotification("Don't use blink in singleplayer!", "", true, 5000, Type.WARNING, Color.RED);
			this.toggle();
		}
		
	}
	
	public void onDisable() {
		
		for (Packet p : packets) {
			
			if (mc.isSingleplayer()) {
				
			}else {
				mc.getNetHandler().addToSendQueue(p);
			}
			
		}
		packets.clear();
		
	}
	
	public void onEvent(Event e) {
		
		if (e instanceof EventSendPacket) {
			
			if (e.isPre()) {
				
				if (((EventSendPacket)e).packet instanceof C00PacketKeepAlive || ((EventSendPacket)e).packet instanceof C00Handshake || ((EventSendPacket)e).packet instanceof C00PacketLoginStart) {
					return;
				}
				
				EventSendPacket sendPacket = (EventSendPacket) e;
				packets.add(sendPacket.packet);
				sendPacket.setCanceled(true);
				
			}
			
		}
		
	}
	
}
