package org.opusstudios.mods.blacksmithrpg.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.opusstudios.mods.blacksmithrpg.registry.BSRPGTileEntity;

public class HearthTile extends BlockEntity {
    public HearthTile(BlockPos pos, BlockState state) {
        super(BSRPGTileEntity.HEARTH_TILE.get(), pos, state);
    }
}