package arcadework.base.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.AbstractMap;

import java.io.File;

import arcadework.base.ArcadeworkMod;

public class FrzProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ArcadeworkMod.LOGGER.warn("Failed to load dependency world for procedure Frz!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ArcadeworkMod.LOGGER.warn("Failed to load dependency x for procedure Frz!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ArcadeworkMod.LOGGER.warn("Failed to load dependency y for procedure Frz!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ArcadeworkMod.LOGGER.warn("Failed to load dependency z for procedure Frz!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ArcadeworkMod.LOGGER.warn("Failed to load dependency entity for procedure Frz!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		File fileJKSADFWIT = new File("");
		com.google.gson.JsonObject ObjectIQESDFN = new com.google.gson.JsonObject();
		String sessionGJrsdf = "";
		if ((entity.getPersistentData().getString("frzLogg")).equals("logged")) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).clearActivePotions();
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						("title " + entity.getUniqueID().toString() + " times 20 100 20"));
			}
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						("title " + entity.getUniqueID().toString()
								+ " subtitle {\"text\":\"Recuerda usar /menu o la tecla M\",\"color\":\"gray\"}"));
			}
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						("title " + entity.getUniqueID().toString()
								+ " title [\"\",{\"text\":\"ii\",\"bold\":true,\"obfuscated\":true,\"color\":\"black\"},{\"text\":\"Bienvenido a\",\"bold\":true,\"color\":\"dark_green\"},{\"text\":\" Arcade\",\"bold\":true,\"color\":\"white\"},{\"text\":\"Work\",\"bold\":true,\"color\":\"dark_purple\"},{\"text\":\"ii\",\"bold\":true,\"obfuscated\":true,\"color\":\"black\"}]"));
			}
		} else {
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate(0.5, 31, 0.5);
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation(0.5, 31, 0.5, _ent.rotationYaw, _ent.rotationPitch,
							Collections.emptySet());
				}
			}
			FrzProcedure.executeProcedure(Stream
					.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
							new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
					.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
		}
	}
}
