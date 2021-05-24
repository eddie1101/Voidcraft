package erg.voidcraft.common.item;

import erg.voidcraft.common.init.VoidcraftPacketHandler;
import erg.voidcraft.common.network.PacketSpawnTestParticles;
import erg.voidcraft.common.particle.ParticleDataMiasma;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

import java.awt.*;
import java.util.Random;

public class ItemParticleTester extends Item {

    public ItemParticleTester() {
        super(new Item.Properties());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {

        if (world.isRemote) {
            VoidcraftPacketHandler.channel.send(PacketDistributor.DIMENSION.with((() -> world.getDimensionKey())), new PacketSpawnTestParticles(player.getPosition()));
        }
        return super.onItemRightClick(world, player, hand);
    }

}
