package uk.jacobempire.ata.chair;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/** A wooden chair. Right-click to sit; sneak or jump off to get up. The back faces away from FACING. */
public class ChairBlock extends HorizontalFacingBlock {
    private static final VoxelShape[] SHAPES = new VoxelShape[4];

    static {
        for (Direction facing : Direction.Type.HORIZONTAL) {
            VoxelShape seat = Block.createCuboidShape(2, 0, 2, 14, 9, 14);
            // The back is on the side opposite where the sitter faces.
            Direction back = facing.getOpposite();
            double x0 = back == Direction.WEST ? 2 : back == Direction.EAST ? 12 : 2;
            double x1 = back == Direction.WEST ? 4 : back == Direction.EAST ? 14 : 14;
            double z0 = back == Direction.NORTH ? 2 : back == Direction.SOUTH ? 12 : 2;
            double z1 = back == Direction.NORTH ? 4 : back == Direction.SOUTH ? 14 : 14;
            SHAPES[facing.getHorizontal()] = VoxelShapes.union(seat, Block.createCuboidShape(x0, 9, z0, x1, 22, z1));
        }
    }

    public ChairBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // Faces the player who placed it, so you sit looking back towards where you were standing.
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES[state.get(FACING).getHorizontal()];
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.isSneaking() || player.hasVehicle()) return ActionResult.PASS;
        if (!world.getEntitiesByClass(SeatEntity.class, new Box(pos), e -> true).isEmpty()) return ActionResult.PASS;
        if (!world.isClient) {
            SeatEntity seat = new SeatEntity(world, pos, state.get(FACING));
            world.spawnEntity(seat);
            player.startRiding(seat);
        }
        return ActionResult.success(world.isClient);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
}
