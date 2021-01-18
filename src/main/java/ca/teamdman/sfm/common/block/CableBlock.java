/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package ca.teamdman.sfm.common.block;

import ca.teamdman.sfm.SFM;
import ca.teamdman.sfm.common.cablenetwork.CableNetworkManager;
import ca.teamdman.sfm.common.util.SFMUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CableBlock extends Block implements ICable {

	public CableBlock(final Properties props) {
		super(props);
	}

	@Override
	public void neighborChanged(
		BlockState state,
		World worldIn,
		BlockPos pos,
		Block blockIn,
		BlockPos fromPos,
		boolean isMoving
	) {
		CableNetworkManager.getOrRegisterNetwork(worldIn, pos).ifPresent(network -> {
			network.rebuildInventories(pos);
		});
	}

	@Override
	public void onBlockAdded(
		BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving
	) {
		super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
		CableNetworkManager.getOrRegisterNetwork(worldIn, pos);
		SFM.LOGGER.debug(
			SFMUtil.getMarker(getClass()),
			"{} networks now",
			CableNetworkManager.size()
		);
	}

	@Override
	public void onReplaced(
		BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving
	) {
		super.onReplaced(state, worldIn, pos, newState, isMoving);
		CableNetworkManager.unregister(worldIn, pos);
		SFM.LOGGER.debug(
			SFMUtil.getMarker(getClass()),
			"{} networks now",
			CableNetworkManager.size()
		);
	}
}
