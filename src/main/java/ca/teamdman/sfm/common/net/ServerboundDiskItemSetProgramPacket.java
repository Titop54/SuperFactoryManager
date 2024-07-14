package ca.teamdman.sfm.common.net;

import ca.teamdman.sfm.common.item.DiskItem;
import ca.teamdman.sfml.ast.Program;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.neoforged.neoforge.network.NetworkEvent;

import java.util.function.Supplier;

public record ServerboundDiskItemSetProgramPacket(
        String programString,
        InteractionHand hand
) {

    public static void encode(ServerboundDiskItemSetProgramPacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.programString, Program.MAX_PROGRAM_LENGTH);
        buf.writeEnum(msg.hand);
    }

    public static ServerboundDiskItemSetProgramPacket decode(
            FriendlyByteBuf buf
    ) {
        return new ServerboundDiskItemSetProgramPacket(
                buf.readUtf(Program.MAX_PROGRAM_LENGTH),
                buf.readEnum(InteractionHand.class)
        );
    }

    public static void handle(
            ServerboundDiskItemSetProgramPacket msg, NetworkEvent.Context context
    ) {
        context.enqueueWork(() -> {
            var sender = context.getSender();
            if (sender == null) {
                return;
            }
            var stack = sender.getItemInHand(msg.hand);
            if (stack.getItem() instanceof DiskItem) {
                DiskItem.setProgram(stack, msg.programString);
                DiskItem.compileAndUpdateErrorsAndWarnings(stack, null);
            }

        });
        context.setPacketHandled(true);
    }
}
