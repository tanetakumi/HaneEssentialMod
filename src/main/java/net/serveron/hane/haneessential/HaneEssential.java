package net.serveron.hane.haneessential;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

@OnlyIn(Dist.CLIENT)
@Mod("hane")
public class HaneEssential {
    private final Minecraft mc = Minecraft.getInstance();

    public HaneEssential() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent e) {
        if(e.getMessage().getString().trim().equalsIgnoreCase("Authentication")){
            if(mc.player!=null){
                mc.player.sendChatMessage("/auth "+getModList()+getResourcesList());
            }
        }
    }

    private String getModList(){
        StringBuilder result = new StringBuilder();
        String[] resourcesList = new File(mc.gameDir.getAbsolutePath(),"mods").list();
        if(resourcesList!=null){
            for(String item : resourcesList){
                String text = item.replaceAll("[0-9]", "")
                        .replace(" ","")
                        .replace(".","")
                        .replace("-","")
                        .replace("jar","");
                result.append(text).append(":");
            }
        } else {
            result.append("modsNotExists").append(":");
        }
        return result.toString();
    }

    private String getResourcesList(){
        StringBuilder result = new StringBuilder();
        String[] resourcesList = new File(mc.gameDir.getAbsolutePath(),"resourcepacks").list();
        if(resourcesList!=null){
            for(String item : resourcesList){
                String text = item.replaceAll("[0-9]", "")
                        .replace(" ","")
                        .replace(".","")
                        .replace("-","")
                        .replace("zip","");
                result.append(text).append(":");
            }
        } else {
            result.append("modsNotExists").append(":");
        }
        return result.toString();
    }

}
