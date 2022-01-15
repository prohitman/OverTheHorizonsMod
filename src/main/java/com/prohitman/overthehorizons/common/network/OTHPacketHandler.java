package com.prohitman.overthehorizons.common.network;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class OTHPacketHandler {
	private static final String PROTOCOL_VERSION = "1";

	private static int id = 0;

	public static SimpleChannel HANDLER;

	public static void init() {
		HANDLER = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "network"))
				.clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals)
				.networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

		register(MessageExtendedReachAttack.class, new MessageExtendedReachAttack());
		register(MessageBreakBlock.class, new MessageBreakBlock());
		register(MessageDecreaseBullets.class, new MessageDecreaseBullets());
		register(MessageReloadRifle.class, new MessageReloadRifle());
	}

	public static <MSG> void register(Class<MSG> classIn, IMessage<MSG> message) {
		HANDLER.registerMessage(id++, classIn, message::encode, message::decode, message::handle);

	}
}
