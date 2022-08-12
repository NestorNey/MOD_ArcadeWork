package arcadework.base.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.gui.widget.TextFieldWidget;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

import arcadework.base.ArcadeworkMod;

public class AuthLogProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ArcadeworkMod.LOGGER.warn("Failed to load dependency entity for procedure AuthLog!");
			return false;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				ArcadeworkMod.LOGGER.warn("Failed to load dependency guistate for procedure AuthLog!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		boolean rmb = false;
		String usPassXASS = "";
		String usMailAAkSD = "";
		File fileUserAKJJS = new File("");
		com.google.gson.JsonObject usOBAAS = new com.google.gson.JsonObject();
		usPassXASS = (new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:password");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText());
		rmb = (new Object() {
			public boolean getValue() {
				CheckboxButton checkbox = (CheckboxButton) guistate.get("checkbox:remember");
				if (checkbox != null) {
					return checkbox.isChecked();
				}
				return false;
			}
		}.getValue());
		fileUserAKJJS = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/auth/udb/"),
				File.separator + (entity.getUniqueID().toString() + ".JSON"));
		if (!fileUserAKJJS.exists()) {
			entity.getPersistentData().putString("logerror", "Ocurri\u00F3 un error inesperado, recon\u00E9ctese al servidor");
			entity.getPersistentData().putString("logerror", "y si el error persiste contacte a un administrador");
			entity.getPersistentData().putString("logerror", "por medio del servidor de discord");
			usOBAAS.addProperty("logg", "error_login");
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
			return false;
		}
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(fileUserAKJJS));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				usOBAAS = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if ((usPassXASS).equals(usOBAAS.get("pass").getAsString())) {
					if (!(usOBAAS.get("mail").getAsString()).equals("not")) {
						usMailAAkSD = (new Object() {
							public String getText() {
								TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:mail");
								if (_tf != null) {
									return _tf.getText();
								}
								return "";
							}
						}.getText());
						if ((usMailAAkSD).equals(usOBAAS.get("mail").getAsString())) {
							usOBAAS.addProperty("logg", "logged");
							entity.getPersistentData().putString("frzLogg", "logged");
						} else {
							entity.getPersistentData().putString("logerror", "Las credenciales dadas son invalidas");
							usOBAAS.addProperty("logg", "error_login");
						}
					} else {
						usOBAAS.addProperty("logg", "logged");
						entity.getPersistentData().putString("frzLogg", "logged");
					}
				} else {
					entity.getPersistentData().putString("logerror", "Las credenciales dadas son invalidas");
					usOBAAS.addProperty("logg", "error_login");
				}
				if (rmb == true) {
					usOBAAS.addProperty("rmb", (true));
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		{
			Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
			try {
				FileWriter fileWriter = new FileWriter(fileUserAKJJS);
				fileWriter.write(mainGSONBuilderVariable.toJson(usOBAAS));
				fileWriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
		return true;
	}
}
