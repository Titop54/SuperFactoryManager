package ca.teamdman.sfm.client.gui.flow.impl.manager.flowdataholder;

import ca.teamdman.sfm.client.gui.flow.core.BaseScreen;
import ca.teamdman.sfm.client.gui.flow.core.Colour3f.CONST;
import ca.teamdman.sfm.client.gui.flow.core.FlowComponent;
import ca.teamdman.sfm.client.gui.flow.core.Size;
import ca.teamdman.sfm.client.gui.flow.impl.manager.core.ManagerFlowController;
import ca.teamdman.sfm.client.gui.flow.impl.util.FlowContainer;
import ca.teamdman.sfm.client.gui.flow.impl.util.ItemStackFlowComponent;
import ca.teamdman.sfm.client.gui.flow.impl.util.TextAreaFlowComponent;
import ca.teamdman.sfm.common.flow.core.FlowDataHolder;
import ca.teamdman.sfm.common.flow.core.Position;
import ca.teamdman.sfm.common.flow.data.ItemStackComparerMatcherFlowData;
import ca.teamdman.sfm.common.flow.holder.FlowDataHolderObserver;
import com.mojang.blaze3d.matrix.MatrixStack;

public class ItemStackComparerMatcherFlowDataDrawerItemFlowComponent extends
	FlowContainer implements
	FlowDataHolder<ItemStackComparerMatcherFlowData> {

	private final ItemStackFlowComponent STACK;
	private final TextAreaFlowComponent QUANTITY_INPUT;
	private ItemStackComparerMatcherFlowData data;

	public ItemStackComparerMatcherFlowDataDrawerItemFlowComponent(
		ManagerFlowController CONTROLLER,
		ItemStackComparerMatcherFlowData data
	) {
		super(new Position(0, 0), new Size(100,100));
		this.data = data;

		// Quantity input box
		this.QUANTITY_INPUT = new TextAreaFlowComponent(
			CONTROLLER.SCREEN,
			Integer.toString(data.quantity),
			"#",
			new Position(4, 4),
			new Size(38, 16)
		);
		QUANTITY_INPUT.setResponder(next -> {
			try {
				int nextVal = Integer.parseInt(next);
				if (nextVal != data.quantity) {
					data.quantity = nextVal;
					CONTROLLER.SCREEN.sendFlowDataToServer(data);
				}
			} catch (NumberFormatException ignored) {
			}
		});
		addChild(QUANTITY_INPUT);

		// "x" separator
		addChild(new FlowComponent(45, 8, 0, 0) {
			@Override
			public void draw(
				BaseScreen screen, MatrixStack matrixStack, int mx, int my, float deltaTime
			) {
				screen.drawString(
					matrixStack,
					"x",
					getPosition().getX(),
					getPosition().getY(),
					CONST.TEXT_DARK
				);
			}
		});

		// Display itemstack
		this.STACK = new ItemStackFlowComponent(data.stack, new Position(54, 2));
		addChild(STACK);

		// Add change listener
		CONTROLLER.SCREEN.getFlowDataContainer().addObserver(new FlowDataHolderObserver<>(
			this,
			ItemStackComparerMatcherFlowData.class
		));
	}

	@Override
	public void draw(BaseScreen screen, MatrixStack matrixStack, int mx, int my, float deltaTime) {
		screen.drawRect(
			matrixStack,
			getPosition().getX(),
			getPosition().getY(),
			getSize().getWidth(),
			getSize().getHeight(),
			CONST.PANEL_BACKGROUND_LIGHT
		);
		super.draw(screen, matrixStack, mx, my, deltaTime);
	}

	@Override
	public ItemStackComparerMatcherFlowData getData() {
		return data;
	}

	@Override
	public void setData(ItemStackComparerMatcherFlowData data) {
		this.data = data;
		STACK.setItemStack(data.stack);
		QUANTITY_INPUT.setContent(Integer.toString(data.quantity));
	}
}
