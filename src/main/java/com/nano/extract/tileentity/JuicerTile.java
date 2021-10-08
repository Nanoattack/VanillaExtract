package com.nano.extract.tileentity;

import com.nano.extract.data.recipes.JuicerRecipe;
import com.nano.extract.data.recipes.ModRecipeTypes;
import com.nano.extract.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
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
import java.util.Optional;

public class JuicerTile extends TileEntity implements ITickableTileEntity {

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
                    case 1: return stack.getItem() == ModItems.BAMBOO_CUP.get() ||
                                   stack.getItem() == ModItems.CACTUS_FRUIT_JUICE.get() ||
                                   stack.getItem() == ModItems.APPLE_JUICE.get() ||
                                   stack.getItem() == ModItems.SWEET_BERRY_JUICE.get() ||
                                   stack.getItem() == ModItems.MELON_JUICE.get() ||
                                   stack.getItem() == ModItems.CHORUS_FRUIT_JUICE.get() ||
                                   stack.getItem() == ModItems.MILK_CUP.get() ||
                                   stack.getItem() == ModItems.CHOCOLATE_MILK.get() ||
                                   stack.getItem() == ModItems.CARROT_JUICE.get();

                    case 0: return true;

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
        //Top Slot Check
        boolean hasChorusInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == Items.CHORUS_FRUIT;
        boolean hasAppleInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == Items.APPLE;
        boolean hasMelonInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == Items.MELON_SLICE;
        boolean hasSweetBerryInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == Items.SWEET_BERRIES;
        boolean hasCarrotInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == Items.CARROT;
        boolean hasCocoaInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == Items.COCOA_BEANS;
        //Bottom Slot Check
        boolean hasCupInSecondSlot = this.itemHandler.getStackInSlot(1).getCount() > 0
                && this.itemHandler.getStackInSlot(1).getItem() == ModItems.BAMBOO_CUP.get();
        boolean hasMilkInSecondSlot = this.itemHandler.getStackInSlot(1).getCount() > 0
                && this.itemHandler.getStackInSlot(1).getItem() == ModItems.MILK_CUP.get();

        //Apple Juice

            if (hasCupInSecondSlot && hasAppleInFirstSlot)
        {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);

            this.itemHandler.insertItem(1, new ItemStack(ModItems.APPLE_JUICE.get()), false);
        }
        //Carrot Juice

            else if (hasCupInSecondSlot && hasCarrotInFirstSlot) {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);

            this.itemHandler.insertItem(1, new ItemStack(ModItems.CARROT_JUICE.get()), false);
        }
        //Sweet Berry Juice

            else if (hasCupInSecondSlot && hasSweetBerryInFirstSlot)
        {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);

            this.itemHandler.insertItem(1, new ItemStack(ModItems.SWEET_BERRY_JUICE.get()), false);
        }
        //Melon Juice

            else if (hasCupInSecondSlot && hasMelonInFirstSlot)
        {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);

            this.itemHandler.insertItem(1, new ItemStack(ModItems.MELON_JUICE.get()), false);
        }
        //Chorus Juice

        else if (hasCupInSecondSlot && hasChorusInFirstSlot)
        {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);

            this.itemHandler.insertItem(1, new ItemStack(ModItems.CHORUS_FRUIT_JUICE.get()), false);
        }
        //Chocolate Milk

            else if (hasMilkInSecondSlot && hasCocoaInFirstSlot)
        {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);

            this.itemHandler.insertItem(1, new ItemStack(ModItems.CHOCOLATE_MILK.get()), false);
        }
    }

    public void craft()
    {
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<JuicerRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(ModRecipeTypes.JUICER_RECIPE, inv, level);

        recipe.ifPresent(iRecipe -> {
            ItemStack output = iRecipe.getResultItem();

            craftTheItem(output);

            setChanged();
        });
    }

    private void craftTheItem(ItemStack output) {
        itemHandler.extractItem(0, 1, false);
        itemHandler.extractItem(1, 1, false);
        itemHandler.insertItem(1, output, false);
    }
    @Override
    public void tick() {
    if(level.isClientSide)
        return;;

        craft();
    }
}
