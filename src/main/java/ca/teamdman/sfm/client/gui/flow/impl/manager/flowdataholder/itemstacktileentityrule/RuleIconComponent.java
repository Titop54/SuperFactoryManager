package ca.teamdman.sfm.client.gui.flow.impl.manager.flowdataholder.itemstacktileentityrule;

import ca.teamdman.sfm.client.gui.flow.core.BaseScreen;
import ca.teamdman.sfm.client.gui.flow.core.Colour3f.CONST;
import ca.teamdman.sfm.client.gui.flow.impl.util.FlowContainer;
import ca.teamdman.sfm.client.gui.flow.impl.util.FlowItemStackPicker;
import ca.teamdman.sfm.client.gui.flow.impl.util.ItemStackFlowComponent;
import ca.teamdman.sfm.common.flow.core.Position;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.item.ItemStack;

class RuleIconComponent extends FlowContainer {

	private final ItemStackTileEntityRuleFlowComponent PARENT;
	private final FlowItemStackPicker PICKER;
	protected final ItemStackFlowComponent BUTTON;

	public RuleIconComponent(
		ItemStackTileEntityRuleFlowComponent parent,
		Position pos
	) {
		super(pos);
		this.PARENT = parent;
		this.BUTTON = new MyButton();
		this.PICKER = new MyPicker();
		PICKER.setVisible(false);
		PICKER.setEnabled(false);
		addChild(PICKER);
		addChild(BUTTON);
	}

	private class MyButton extends ItemStackFlowComponent {

		public MyButton() {
			super(PARENT.getData().icon, new Position());
		}

		@Override
		public void onClicked(int mx, int my, int button) {
			PICKER.toggleVisibilityAndEnabled();
		}

		@Override
		public void draw(
			BaseScreen screen,
			MatrixStack matrixStack,
			int mx,
			int my,
			float deltaTime
		) {
			drawBackground(screen, matrixStack, CONST.PANEL_BACKGROUND_LIGHT);
			super.draw(screen, matrixStack, mx, my, deltaTime);
		}
	}

	private class MyPicker extends FlowItemStackPicker {

		public MyPicker() {
			super(
				PARENT.CONTROLLER,
				new Position(BUTTON.getSize().getWidth() + 5,0)
			);
		}

		@Override
		public void onItemStackChanged(ItemStack stack) {
			PARENT.getData().icon = stack;
			PARENT.CONTROLLER.SCREEN.sendFlowDataToServer(PARENT.getData());
		}
	}
}
