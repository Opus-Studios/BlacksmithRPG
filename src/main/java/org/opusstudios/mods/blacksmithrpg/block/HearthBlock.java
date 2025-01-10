package org.opusstudios.mods.blacksmithrpg.block;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.ToIntFunction;
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class HearthBlock extends Block {
    public static final BooleanProperty LIT;
    public static final DirectionProperty FACING;
    public HearthBlock() {
        super(Properties.of().lightLevel(litBlockEmission()));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LIT, Boolean.FALSE)
                .setValue(FACING, Direction.NORTH)
        );
    }

    private static ToIntFunction<BlockState> litBlockEmission() {
        return (state) -> state.getValue(LIT) ? 15 : 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, FACING);
    }

    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack item = player.getMainHandItem();
        if (item.is(Items.FLINT_AND_STEEL)) {
            if (!state.getValue(LIT)) {
                item.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                level.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 0.3F, 1.0F);
                level.setBlock(pos,state.setValue(LIT,true),3);
                return ItemInteractionResult.SUCCESS;
            }
            return ItemInteractionResult.CONSUME;
        }
        if (level.setBlock(pos,state.setValue(LIT,false),3)) {
            level.playSound(player, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.3F, 2.0F);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.CONSUME;
    }

    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    static {
        LIT = BooleanProperty.create("lit");
        FACING = HorizontalDirectionalBlock.FACING;
    }
}
