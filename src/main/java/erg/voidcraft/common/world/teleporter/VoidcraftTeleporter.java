package erg.voidcraft.common.world.teleporter;

import net.minecraft.block.PortalInfo;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class VoidcraftTeleporter implements ITeleporter {

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
//        Entity newEntity = entity.getType().create(destWorld);
//        PortalInfo portalInfo = this.getPortalInfo(entity, destWorld, (ServerWorld) -> null);
//        if(portalInfo == null) {
//            return null;
//        }
//        if (newEntity != null) {
//            newEntity.copyDataFromOld(entity);
//            newEntity.setLocationAndAngles(portalInfo.pos.x, portalInfo.pos.y, portalInfo.pos.z, portalInfo.rotationYaw, newEntity.rotationPitch);
//            newEntity.setMotion(portalInfo.motion);
//            destWorld.addFromAnotherDimension(newEntity);
//        }
//        return newEntity;
        return repositionEntity.apply(false);
    }

    @Override
    public PortalInfo getPortalInfo(Entity entity, ServerWorld destWorld, Function<ServerWorld, PortalInfo> defaultPortalInfo) {
        return new PortalInfo(entity.position(), Vector3d.ZERO, entity.yRot, entity.xRot);
    }

    @Override
    public boolean isVanilla() {return false;}

}
