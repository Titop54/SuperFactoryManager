/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package ca.teamdman.sfm.client.gui.flow.impl.util;

import ca.teamdman.sfm.client.gui.flow.core.FlowComponent;
import ca.teamdman.sfm.client.gui.flow.core.Size;
import ca.teamdman.sfm.common.flow.data.core.Position;

public abstract class FlowButton extends FlowComponent {

	private boolean clicking = false;

	public FlowButton(Position pos, Size size) {
		super(pos, size);
	}

	@Override
	public boolean mousePressed(int mx, int my, int button) {
		if (super.mousePressed(mx, my, button)) {
			return true;
		} else if (isInBounds(mx, my)) {
			clicking = true;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean mouseReleased(int mx, int my, int button) {
		if (super.mouseReleased(mx, my, button)) {
			return true;
		} else if (clicking) {
			clicking = false;
			if (isInBounds(mx, my)) {
				this.onClicked(mx, my, button);
				return true;
			}
		}
		return false;
	}

	public abstract void onClicked(int mx, int my, int button);
}