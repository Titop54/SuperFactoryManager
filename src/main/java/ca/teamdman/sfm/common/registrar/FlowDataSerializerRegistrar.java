/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package ca.teamdman.sfm.common.registrar;

import ca.teamdman.sfm.SFM;
import ca.teamdman.sfm.common.flow.data.core.FlowDataSerializer;
import ca.teamdman.sfm.common.flow.data.impl.ItemStackComparerMatcherFlowData;
import ca.teamdman.sfm.common.flow.data.impl.ItemStackComparerMatcherFlowData.ItemStackComparerMatcherFlowDataSerializer;
import ca.teamdman.sfm.common.flow.data.impl.LineNodeFlowData;
import ca.teamdman.sfm.common.flow.data.impl.LineNodeFlowData.LineNodeFlowDataSerializer;
import ca.teamdman.sfm.common.flow.data.impl.RelationshipFlowData;
import ca.teamdman.sfm.common.flow.data.impl.RelationshipFlowData.FlowRelationshipDataSerializer;
import ca.teamdman.sfm.common.flow.data.impl.TileEntityItemStackRuleFlowData;
import ca.teamdman.sfm.common.flow.data.impl.TileEntityItemStackRuleFlowData.FlowTileEntityRuleDataSerializer;
import ca.teamdman.sfm.common.flow.data.impl.TileInputFlowData;
import ca.teamdman.sfm.common.flow.data.impl.TileInputFlowData.FlowInputDataSerializer;
import ca.teamdman.sfm.common.flow.data.impl.TileOutputFlowData;
import ca.teamdman.sfm.common.flow.data.impl.TileOutputFlowData.FlowOutputDataSerializer;
import ca.teamdman.sfm.common.flow.data.impl.TimerTriggerFlowData;
import ca.teamdman.sfm.common.flow.data.impl.TimerTriggerFlowData.FlowTimerTriggerDataSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = SFM.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FlowDataSerializerRegistrar {

	private static final FlowDataSerializer WAITING = null;

	@SubscribeEvent
	public static void onRegister(final RegistryEvent.Register<FlowDataSerializer<?>> e) {
		e.getRegistry().registerAll(
			new FlowInputDataSerializer(new ResourceLocation(SFM.MOD_ID, "input")),
			new FlowOutputDataSerializer(new ResourceLocation(SFM.MOD_ID, "output")),
			new FlowRelationshipDataSerializer(new ResourceLocation(SFM.MOD_ID, "relationship")),
			new LineNodeFlowDataSerializer(new ResourceLocation(SFM.MOD_ID, "line_node")),
			new FlowTimerTriggerDataSerializer(new ResourceLocation(SFM.MOD_ID, "timer_trigger")),
			new FlowTileEntityRuleDataSerializer(new ResourceLocation(SFM.MOD_ID, "tile_entity_rule")),
			new ItemStackComparerMatcherFlowDataSerializer(
				new ResourceLocation(SFM.MOD_ID, "itemstack_comparer_matcher"))
		);
	}

	@SubscribeEvent
	public static void onRegisterRegistry(final RegistryEvent.NewRegistry e) {
		MinecraftForge.EVENT_BUS.register(new FlowDataFactoryRegistry());
	}


	@ObjectHolder(SFM.MOD_ID)
	public static final class FlowDataSerializers {

		public static final FlowDataSerializer<TileInputFlowData> INPUT = WAITING;
		public static final FlowDataSerializer<TileOutputFlowData> OUTPUT = WAITING;
		public static final FlowDataSerializer<RelationshipFlowData> RELATIONSHIP = WAITING;
		public static final FlowDataSerializer<LineNodeFlowData> LINE_NODE = WAITING;
		public static final FlowDataSerializer<TimerTriggerFlowData> TIMER_TRIGGER = WAITING;
		public static final FlowDataSerializer<TileEntityItemStackRuleFlowData> TILE_ENTITY_RULE = WAITING;
		public static final FlowDataSerializer<ItemStackComparerMatcherFlowData> ITEM_STACK_COMPARER_MATCHER = WAITING;
	}

	public static class FlowDataFactoryRegistry {

		public FlowDataFactoryRegistry() {
			new RegistryBuilder<FlowDataSerializer>()
				.setName(new ResourceLocation(SFM.MOD_ID, "flow_data_serializer_registry"))
				.setType(FlowDataSerializer.class).create();
		}
	}
}
