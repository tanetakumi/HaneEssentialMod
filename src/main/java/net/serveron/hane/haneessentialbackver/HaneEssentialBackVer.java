package net.serveron.hane.haneessentialbackver;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

@Mod(
        modid = HaneEssentialBackVer.MOD_ID,
        name = HaneEssentialBackVer.MOD_NAME,
        version = HaneEssentialBackVer.VERSION
)
public class HaneEssentialBackVer {

    public static final String MOD_ID = "hane";
    public static final String MOD_NAME = "HaneEssentialBackVer";
    public static final String VERSION = "1.0-SNAPSHOT";

    private final Minecraft mc = Minecraft.getMinecraft();

    @Mod.Instance(MOD_ID)
    public static HaneEssentialBackVer INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent e) {
        if(e.getMessage().toString().trim().equalsIgnoreCase("Authentication")){
            if(mc.player!=null){
                mc.player.sendChatMessage("/auth "+getModList()+getResourcesList());
            }
        }
    }

    private String getModList(){
        StringBuilder result = new StringBuilder();
        String[] resourcesList = new File(mc.gameDir.getAbsolutePath(),"mods").list();
        if(resourcesList!=null){
            if(resourcesList.length!=0){
                for(String item : resourcesList){
                    String text = item.replaceAll("[0-9]", "")
                            .replace(" ","")
                            .replace(".","")
                            .replace("-","")
                            .replace("-","")
                            .replace("(","")
                            .replace(")","")
                            .replace("jar","");
                    result.append(text).append(":");
                }
            }
        }
        return result.toString();
    }

    private String getResourcesList(){
        StringBuilder result = new StringBuilder();
        String[] resourcesList = new File(mc.gameDir.getAbsolutePath(),"resourcepacks").list();
        if(resourcesList!=null){
            if(resourcesList.length!=0){
                for(String item : resourcesList){
                    String text = item.replaceAll("[0-9]", "")
                            .replace(" ","")
                            .replace(".","")
                            .replace("-","")
                            .replace("(","")
                            .replace(")","")
                            .replace("zip","");
                    result.append(text).append(":");
                }
            }
        }
        return result.toString();
    }
}
