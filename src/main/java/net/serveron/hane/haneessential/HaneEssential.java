package net.serveron.hane.haneessential;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.resources.FolderPack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@OnlyIn(Dist.CLIENT)
@Mod("hane")
public class HaneEssential {
    private final Minecraft mc = Minecraft.getInstance();
    //private final ClientPlayerEntity player;


    public HaneEssential() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent e) {
        System.out.println(getModList()+getResourcesList());
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
