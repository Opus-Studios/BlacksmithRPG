package org.opusstudios.mods.blacksmithrpg.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.opusstudios.mods.blacksmithrpg.block.HearthBlock;
import org.opusstudios.mods.blacksmithrpg.registry.impl.BlockItemDeferredRegister;

public class BSRPGBlock {

    public static final BlockItemDeferredRegister INSTANCE = new BlockItemDeferredRegister();
    public static final DeferredHolder<Block, HearthBlock> HEARTH_BLOCK = INSTANCE.register("hearth", HearthBlock::new, BlockItem::new);


    public static void register(IEventBus bus) {
        INSTANCE.register(bus);
    }
}
