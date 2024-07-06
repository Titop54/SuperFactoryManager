package ca.teamdman.sfm.common.resourcetype.exclude;

import ca.teamdman.sfm.common.resourcetype.ResourceType;
import mekanism.api.Action;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.slurry.ISlurryHandler;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryStack;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.capabilities.CapabilityManager;
import net.neoforged.neoforge.common.capabilities.CapabilityToken;
import org.apache.commons.lang3.NotImplementedException;

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
    public SlurryStack getStackInSlot(ISlurryHandler handler, int slot) {
        return handler.getChemicalInTank(slot);
    }

    @Override
    public SlurryStack extract(ISlurryHandler handler, int slot, long amount, boolean simulate) {
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
    public long getMaxStackSize(ISlurryHandler handler, int slot) {
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
    public Registry<Slurry> getRegistry() {
        throw new NotImplementedException();
//        return MekanismAPI.slurryRegistry();
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
    protected SlurryStack setCount(SlurryStack stack, long amount) {
        stack.setAmount(amount);
        return stack;
    }
}