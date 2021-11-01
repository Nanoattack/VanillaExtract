package com.nano.extract.tileentity;

import com.nano.extract.data.recipes.JuicerRecipe;
import com.nano.extract.data.recipes.ModRecipeTypes;
import com.nano.extract.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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
                                   stack.getItem() == ModItems.CARROT_JUICE.get() ||
                                   stack.getItem() == ModItems.CASHEW_MILK_CUP.get();

                    case 0: if(stack.getItem() == ModItems.BAMBOO_CUP.get())
                        return false;
                            else return true;

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

            level.playSound((PlayerEntity)null, getBlockPos(), SoundEvents.PISTON_EXTEND, SoundCategory.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            level.playSound((PlayerEntity)null, getBlockPos(), SoundEvents.COW_MILK, SoundCategory.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);

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
