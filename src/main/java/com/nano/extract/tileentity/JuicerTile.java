package com.nano.extract.tileentity;

import com.nano.extract.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class JuicerTile extends TileEntity {

    private final ItemStackHandler itemHandler =  createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(()-> itemHandler);

    public JuicerTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public JuicerTile() {
        this(ModTileEntities.JUICER_TILE.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.save(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2)
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                setChanged();
            }
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot){
                    //bottle/juice go here
                    case 1: return stack.getItem() == Items.GLASS_BOTTLE ||
                                   stack.getItem() == ModItems.CACTUS_FRUIT_JUICE.get() ||
                                   stack.getItem() == ModItems.APPLE_JUICE.get() ||
                                   stack.getItem() == ModItems.CARROT_JUICE.get();
                    //thing to be juiced goes here
                    case 0: return stack.getItem() == ModItems.CACTUS_FRUIT.get() ||
                            stack.getItem() == Items.APPLE ||
                            stack.getItem() == Items.MELON_SLICE ||
                            stack.getItem() == Items.SWEET_BERRIES ||
                            stack.getItem() == Items.CHORUS_FRUIT ||
                            stack.getItem() == Items.CARROT ||
                            stack.getItem() == Items.COCOA_BEANS;

                    default:
                        return false;
                }
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    public void AfterJuiceSound() {
        boolean hasCactusInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == ModItems.CACTUS_FRUIT.get();
        boolean hasAppleInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == Items.APPLE;
        boolean hasCarrotInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == Items.CARROT;
        boolean hasBottleInSecondSlot = this.itemHandler.getStackInSlot(1).getCount() > 0
                && this.itemHandler.getStackInSlot(1).getItem() == Items.GLASS_BOTTLE;

        if(hasBottleInSecondSlot && hasCactusInFirstSlot)
        {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);

            this.itemHandler.insertItem(1, new ItemStack(ModItems.CACTUS_FRUIT_JUICE.get()), false);
        }
        else if (hasBottleInSecondSlot && hasAppleInFirstSlot)
        {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);

            this.itemHandler.insertItem(1, new ItemStack(ModItems.APPLE_JUICE.get()), false);
        }
        else if (hasBottleInSecondSlot && hasCarrotInFirstSlot)
        {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);

            this.itemHandler.insertItem(1, new ItemStack(ModItems.CARROT_JUICE.get()), false);
        }
    }
}
