package com.ldtteam.structurize.blocks.decorative;

import com.ldtteam.structurize.blocks.types.TimberFrameCentreType;
import com.ldtteam.structurize.blocks.types.TimberFrameType;
import com.ldtteam.structurize.blocks.types.WoodType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;

/**
 * Decorative block
 */
public class BlockTimberFrame extends DirectionalBlock
{
    /**
     * The hardness this block has.
     */
    private static final float                         BLOCK_HARDNESS = 3F;

    /**
     * The resistance this block has.
     */
    private static final float                         RESISTANCE     = 1F;

    /**
     * Fields defining the registered block's wood and face types, these are used by the Data Generators
     */
    private final TimberFrameType timberFrameType;
    private final WoodType        frameType;
    private final TimberFrameCentreType centreType;

    /**
     * Constructor for the TimberFrame
     */
    public BlockTimberFrame(final TimberFrameType timberFrameType, final WoodType frameType, final TimberFrameCentreType centreType)
    {
        super(Properties.create(Material.WOOD).hardnessAndResistance(BLOCK_HARDNESS, RESISTANCE).notSolid());
        this.timberFrameType = timberFrameType;
        this.frameType = frameType;
        this.centreType = centreType;
    }

    public static String getName(final TimberFrameType timberFrameType, final WoodType frameType, final TimberFrameCentreType centreType)
    {
        return String.format("%s_%s_%s_timber_frame", timberFrameType.getName(), frameType.getString(), centreType.getString());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getNearestLookingDirection().getOpposite());
    }

    /**
     * Get the registered TimberFrameType, used by the Data Generators
     *
     * @return the registered TimberFrameType
     */
    public TimberFrameType getTimberFrameType()
    {
        return this.timberFrameType;
    }

    /**
     * Get the associated wood, used for data gen
     *
     * @return the wood type
     */
    public WoodType getFrameType()
    {
        return this.frameType;
    }

    /**
     * Get the registered TimberFrameCentreType, used by the Data Generators
     *
     * @return the registered TimberFrameCentreType
     */
    public TimberFrameCentreType getCentreType()
    {
        return this.centreType;
    }
}
