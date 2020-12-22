/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package ca.teamdman.sfm.common.net.packet.manager;

import ca.teamdman.sfm.SFM;
import ca.teamdman.sfm.SFMUtil;
import ca.teamdman.sfm.common.flow.data.core.FlowData;
import ca.teamdman.sfm.common.flow.data.core.Position;
import ca.teamdman.sfm.common.flow.data.impl.FlowTileEntityRuleData;
import ca.teamdman.sfm.common.tile.ManagerTileEntity;
import java.util.UUID;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;

public class ManagerCreateTileEntityRulePacketC2S extends C2SManagerPacket {

	private final Position ELEMENT_POSITION;
	private final UUID OWNER_ID;

	public ManagerCreateTileEntityRulePacketC2S(int windowId, BlockPos pos, UUID owner, Position elementPosition) {
		super(windowId, pos);
		this.OWNER_ID = owner;
		this.ELEMENT_POSITION = elementPosition;
	}

	public static class Handler extends C2SHandler<ManagerCreateTileEntityRulePacketC2S> {

		@Override
		public void finishEncode(
			ManagerCreateTileEntityRulePacketC2S msg,
			PacketBuffer buf
		) {
			buf.writeString(msg.OWNER_ID.toString());
			buf.writeLong(msg.ELEMENT_POSITION.toLong());
		}

		@Override
		public ManagerCreateTileEntityRulePacketC2S finishDecode(
			int windowId, BlockPos tilePos,
			PacketBuffer buf
		) {
			return new ManagerCreateTileEntityRulePacketC2S(
				windowId,
				tilePos,
				SFMUtil.readUUID(buf),
				Position.fromLong(buf.readLong())
			);
		}

		@Override
		public void handleDetailed(ManagerCreateTileEntityRulePacketC2S msg, ManagerTileEntity manager) {
			FlowData data = new FlowTileEntityRuleData(
				UUID.randomUUID(),
				msg.OWNER_ID,
				msg.ELEMENT_POSITION
			);

			SFM.LOGGER.debug(
				SFMUtil.getMarker(getClass()),
				"C2S received, creating TileEntityRule at position {} with id {}",
				msg.ELEMENT_POSITION,
				data.getId()
			);

			manager.addData(data);
			manager.markAndNotify();
			manager.sendPacketToListeners(new ManagerCreateTileEntityRulePacketS2C(
				msg.WINDOW_ID,
				data.getId(),
				msg.OWNER_ID,
				msg.ELEMENT_POSITION
			));
		}
	}
}