package mrcomputerghost.soulorbs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

import java.util.UUID;

public class EntitySoulOrb extends Entity {

    private static final DataParameter<String> OWNER = EntityDataManager.createKey(EntitySoulOrb.class, DataSerializers.STRING);

    private EntityPlayer owner;
    private UUID ownerUniqueId;

    public EntitySoulOrb(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

    }

    @Override
    protected void entityInit() {
        dataManager.register(OWNER, "");
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }

}
