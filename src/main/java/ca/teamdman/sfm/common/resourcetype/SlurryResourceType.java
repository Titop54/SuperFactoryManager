package ca.teamdman.sfm.common.resourcetype;

import mekanism.api.Action;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.slurry.ISlurryHandler;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.stream.Stream;

public class SlurryResourceType extends ResourceType<SlurryStack, Slurry, ISlurryHandler> {
    public static final Capability<ISlurryHandler> CAP = CapabilityManager.get(new CapabilityToken<>() {
    });

    public SlurryResourceType() {
        super(CAP);
    }

    @Override
    public long getAmount(SlurryStack stack) {
        return stack.getAmount();
    }

    @Override
    public SlurryStack getStackInSlot(
            ISlurryHandler handler,
            int slot
    ) {
        return handler.getChemicalInTank(slot);
    }

    @Override
    public SlurryStack extract(
            ISlurryHandler handler,
            int slot,
            long amount,
            boolean simulate
    ) {
        return handler.extractChemical(slot, amount, simulate ? Action.SIMULATE : Action.EXECUTE);
    }

    @Override
    public int getSlots(ISlurryHandler handler) {
        return handler.getTanks();
    }

    @Override
    public long getMaxStackSize(SlurryStack stack) {
        return Long.MAX_VALUE;
    }

    @Override
    public long getMaxStackSizeForSlot(
            ISlurryHandler handler,
            int slot
    ) {
        return handler.getTankCapacity(slot);
    }

    @Override
    public SlurryStack insert(
            ISlurryHandler handler,
            int slot,
            SlurryStack stack,
            boolean simulate
    ) {
        return handler.insertChemical(slot, stack, simulate ? Action.SIMULATE : Action.EXECUTE);
    }

    @Override
    public boolean isEmpty(SlurryStack stack) {
        return stack.isEmpty();
    }

    @Override
    public SlurryStack getEmptyStack() {
        return SlurryStack.EMPTY;
    }

    @Override
    public boolean matchesStackType(Object o) {
        return o instanceof SlurryStack;
    }

    @Override
    public boolean matchesCapabilityType(Object o) {
        return o instanceof ISlurryHandler;
    }

    @Override
    public Stream<ResourceLocation> getTagsForStack(SlurryStack slurryStack) {
        return slurryStack.getType().getTags().map(TagKey::location);
    }

    @Override
    public IForgeRegistry<Slurry> getRegistry() {
        return MekanismAPI.slurryRegistry();
    }

    @Override
    public Slurry getItem(SlurryStack stack) {
        return stack.getType();
    }

    @Override
    public SlurryStack copy(SlurryStack stack) {
        return stack.copy();
    }

    @Override
    protected SlurryStack setCount(
            SlurryStack stack,
            long amount
    ) {
        stack.setAmount(amount);
        return stack;
    }
}
