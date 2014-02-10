package vswe.stevesfactory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vswe.stevesfactory.StevesFactoryManager;


public class BlockCableCluster extends BlockContainer {
    protected BlockCableCluster(int id) {
        super(id, Material.iron);
        setCreativeTab(Blocks.creativeTab);
        setStepSound(soundMetalFootstep);
        setUnlocalizedName(StevesFactoryManager.UNLOCALIZED_START + Blocks.CABLE_CLUSTER_UNLOCALIZED_NAME);
        setHardness(2F);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
        blockIcon = register.registerIcon(StevesFactoryManager.RESOURCE_LOCATION + ":cable_cluster");
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityCluster();
    }

    private TileEntityCluster getTe(IBlockAccess world, int x, int y, int z) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null && te instanceof  TileEntityCluster) {
            return (TileEntityCluster)te;
        }
        return null;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        TileEntityCluster cluster = getTe(world, x, y, z);

        if (cluster != null) {
            cluster.loadElements(itemStack);

            cluster.onBlockPlacedBy(entity, itemStack);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
        TileEntityCluster cluster = getTe(world, x, y, z);

        if (cluster != null) {
            cluster.onNeighborBlockChange(id);
        }
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityCluster cluster = getTe(world, x, y, z);

        if (cluster != null) {
            return cluster.canConnectRedstone(side);
        }

        return false;
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        TileEntityCluster cluster = getTe(world, x, y, z);

        if (cluster != null) {
            cluster.onBlockAdded();
        }
    }

    @Override
    public boolean shouldCheckWeakPower(World world, int x, int y, int z, int side) {
        TileEntityCluster cluster = getTe(world, x, y, z);

        if (cluster != null) {
            return cluster.shouldCheckWeakPower(side);
        }

        return false;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityCluster cluster = getTe(world, x, y, z);

        if (cluster != null) {
            return cluster.isProvidingWeakPower(side);
        }

        return 0;
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityCluster cluster = getTe(world, x, y, z);

        if (cluster != null) {
            return cluster.isProvidingStrongPower(side);
        }

        return 0;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileEntityCluster cluster = getTe(world, x, y, z);

        if (cluster != null) {
            return cluster.onBlockActivated(player, side, hitX, hitY, hitZ);
        }

        return false;
    }


}