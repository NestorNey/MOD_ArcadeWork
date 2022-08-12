package arcadework.base.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.gui.widget.TextFieldWidget;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

import arcadework.base.ArcadeworkMod;

public class AuthRegProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ArcadeworkMod.LOGGER.warn("Failed to load dependency entity for procedure AuthReg!");
			return false;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				ArcadeworkMod.LOGGER.warn("Failed to load dependency guistate for procedure AuthReg!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		File ufalsd344FFS = new File("");
		com.google.gson.JsonObject ubAAGKFAA55 = new com.google.gson.JsonObject();
		String usPassUYYU5F = "";
		String usMailGFGF35rg = "";
		String usConfPssSKFDFJKSA56 = "";
		boolean promynot = false;
		ufalsd344FFS = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/auth/udb/"),
				File.separator + (entity.getUniqueID().toString() + ".JSON"));
		if (!ufalsd344FFS.exists()) {
			entity.getPersistentData().putString("logerror", "Ocurri\u00F3 un error inesperado, recon\u00E9ctese al servidor");
			entity.getPersistentData().putString("logerror2", "y si el error persiste contacte a un administrador");
			entity.getPersistentData().putString("logerror3", "por medio del servidor de discord");
			ubAAGKFAA55.addProperty("logg", "error_regis");
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
			return false;
		}
		if (!(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:password");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText()).equals(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:confpassword");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText())) {
			entity.getPersistentData().putString("logerror", "Las contrase\u00F1as no son iguales");
			ubAAGKFAA55.addProperty("logg", "error_regis");
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
			return false;
		}
		usPassUYYU5F = (new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:password");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText());
		usMailGFGF35rg = (new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:mail");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText());
		promynot = (new Object() {
			public boolean getValue() {
				CheckboxButton checkbox = (CheckboxButton) guistate.get("checkbox:news");
				if (checkbox != null) {
					return checkbox.isChecked();
				}
				return false;
			}
		}.getValue());
		if (!(usMailGFGF35rg).equals("")) {
			if (usMailGFGF35rg.contains("@gamil.com")) {
				usMailGFGF35rg = (usPassUYYU5F.replace("@gamil.com", ""));
			} else if (usMailGFGF35rg.contains("@hotmail.com")) {
				usMailGFGF35rg = (usPassUYYU5F.replace("@hotmail.com", ""));
			} else if (usMailGFGF35rg.contains("@outlook.com")) {
				usMailGFGF35rg = (usPassUYYU5F.replace("@outlook.com", ""));
			} else {
				entity.getPersistentData().putString("logerror", "La direcci\u00F3n de correo tiene que ser de");
				entity.getPersistentData().putString("logerror2", "@gmail.com, @hotmail.com O @outlook.com");
				ubAAGKFAA55.addProperty("logg", "error_regis");
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).closeScreen();
				return false;
			}
			if (!usMailGFGF35rg.contains(" ")) {
				usMailGFGF35rg = (new Object() {
					public String getText() {
						TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:mail");
						if (_tf != null) {
							return _tf.getText();
						}
						return "";
					}
				}.getText());
			} else {
				entity.getPersistentData().putString("logerror", "La direcci\u00F3n de correo contiene espacios");
				ubAAGKFAA55.addProperty("logg", "error_regis");
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).closeScreen();
				return false;
			}
		} else {
			usMailGFGF35rg = "not";
		}
		if ((usPassUYYU5F).length() < 8) {
			entity.getPersistentData().putString("logerror", "La contrase\u00F1a tiene que ser m\u00EDnimo de 8 caracteres");
			ubAAGKFAA55.addProperty("logg", "error_regis");
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
			return false;
		} else if ((usPassUYYU5F).length() > 50) {
			entity.getPersistentData().putString("logerror", "La contrase\u00F1a tiene que ser m\u00E1ximo de 50 caracteres");
			ubAAGKFAA55.addProperty("logg", "error_regis");
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
			return false;
		}
		ubAAGKFAA55.addProperty("pass", usPassUYYU5F);
		ubAAGKFAA55.addProperty("mail", usMailGFGF35rg);
		ubAAGKFAA55.addProperty("rmb", (false));
		if (promynot == true) {
			ubAAGKFAA55.addProperty("news", (true));
		} else {
			ubAAGKFAA55.addProperty("news", (false));
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
						" \u00A7e[ALERTA] \u00A76\u00A7lRubert \u00A7c>> \u00A77No activaste la notificaci\u00F3n de promociones y noticias v\u00EDa correo, puedes cambiar esto en la configuraci\u00F3n de tu perfil."),
						(false));
			}
		}
		ubAAGKFAA55.addProperty("logg", "logged");
		entity.getPersistentData().putString("frzLogg", "logged");
		{
			Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
			try {
				FileWriter fileWriter = new FileWriter(ufalsd344FFS);
				fileWriter.write(mainGSONBuilderVariable.toJson(ubAAGKFAA55));
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
