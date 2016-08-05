package mrcomputerghost.soulorbs;

import mrcomputerghost.soulorbs.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(name = "SoulOrbs", modid = "SoulOrbs", version = "1.0.0")
public class SoulOrbs {

    private static int maxOrbs = 1;

    @Mod.Instance
    private static SoulOrbs instance;

    @Mod.InstanceFactory
    public static SoulOrbs instance() {
        if (instance == null) instance = new SoulOrbs();
        return instance;
    }

    @SidedProxy(serverSide = "mrcomputerghost.soulorbs.proxy.CommonProxy", clientSide = "mrcomputerghost.soulorbs.client.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
        Configuration config = new Configuration(evt.getSuggestedConfigurationFile());
        config.load();
        maxOrbs = config.getInt("maxOrbs", "general", 1, -1, 256, "Number of deaths before orb disappears.");
        config.save();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent evt) {
        MinecraftForge.EVENT_BUS.register(instance());
    }

    @Mod.EventHandler
    public void init(FMLPostInitializationEvent evt) {

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void playerDeath(LivingDeathEvent evt) {
        if (evt.getEntity() instanceof EntityPlayer && !evt.getEntity().worldObj.isRemote) {
            EntityPlayer player = (EntityPlayer) evt.getEntity();
            NBTTagList list = new NBTTagList();
            player.inventory.writeToNBT(list);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH, receiveCanceled = true)
    public void playerDrop(PlayerDropsEvent evt) {
        evt.setCanceled(true);
    }

}
