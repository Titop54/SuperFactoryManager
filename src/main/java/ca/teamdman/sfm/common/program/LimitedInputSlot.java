package ca.teamdman.sfm.common.program;

import ca.teamdman.sfm.SFM;
import ca.teamdman.sfml.ast.InputStatement;
import net.minecraftforge.items.IItemHandler;

public class LimitedInputSlot extends LimitedSlot<InputItemMatcher> {

    private final InputStatement STATEMENT;

    public LimitedInputSlot(
            InputStatement statement,
            IItemHandler handler,
            int slot,
            InputItemMatcher matcher
    ) {
        super(handler, slot, matcher);
        this.STATEMENT = statement;
    }

    public InputStatement getStatement() {
        return STATEMENT;
    }

    public void moveTo(LimitedOutputSlot other) {
        var potential = this.extract(Integer.MAX_VALUE, true);
        if (potential.isEmpty()) {
            setDone();
            return;
        }
        if (!MATCHER.test(potential)) return;
        if (!other.MATCHER.test(potential)) return;
        var remainder = other.insert(potential, true);

        // how many can we move unrestrained
        var toMove = potential.getCount() - remainder.getCount();
        if (toMove == 0) return;

        // how many have we promised to leave in this slot
        toMove -= this.MATCHER.getExistingPromise(SLOT);

        // how many more need to be promised
        var toPromise = this.MATCHER.getRemainingPromise();
        toPromise = Math.min(toMove, toPromise);
        toMove -= toPromise;

        // track the promise
        this.MATCHER.track(SLOT, 0, toPromise);

        // if whole slot has been promised, mark done
        if (toMove == 0) {
            setDone();
            return;
        }

        // how many are we allowed to put in the other inventory
        toMove = Math.min(toMove, other.MATCHER.getMaxTransferable());

        // how many can we move at once
        toMove = Math.min(toMove, this.MATCHER.getMaxTransferable());
        if (toMove <= 0) return;

        // extract item for real
        var extracted = this.HANDLER.extractItem(SLOT, toMove, false);
        // insert item for real
        remainder = other.HANDLER.insertItem(other.SLOT, extracted, false);
        // track transfer amounts
        this.MATCHER.trackTransfer(toMove);
        other.MATCHER.trackTransfer(toMove);

        // if remainder exists, someone lied.
        if (!remainder.isEmpty()) {
            SFM.LOGGER.error(
                    "Failed to move all promised items, took {} but had {} left over after insertion.",
                    extracted,
                    remainder
            );
        }
    }
}