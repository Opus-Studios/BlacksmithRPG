package org.opusstudios.mods.blacksmithrpg.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.opusstudios.mods.blacksmithrpg.registry.impl.TileEntityDeferredRegister;
import org.opusstudios.mods.blacksmithrpg.tile.HearthTile;

import java.util.function.Supplier;

public class BSRPGTileEntity {
    public static final TileEntityDeferredRegister INSTANCE = new TileEntityDeferredRegister();

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HearthTile>> HEARTH_TILE = register("furnace", HearthTile::new, BSRPGBlock.HEARTH_BLOCK);
    public static void register(IEventBus bus) {
        INSTANCE.register(bus);
    }
    @SuppressWarnings("DataFlowIssue")
    public static <BE extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<BE>> register(String name, BlockEntityType.BlockEntitySupplier<BE> BlockEntityWrap, Supplier<? extends Block> BlockWrap) {

        return INSTANCE.register(name, () -> BlockEntityType.Builder.of(BlockEntityWrap, BlockWrap.get()).build(null));
    }
}
