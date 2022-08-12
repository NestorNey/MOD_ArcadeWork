
package arcadework.base.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

import arcadework.base.ArcadeworkMod;

@OnlyIn(Dist.CLIENT)
public class RegisterGuiWindow extends ContainerScreen<RegisterGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = RegisterGui.guistate;
	TextFieldWidget password;
	CheckboxButton news;
	TextFieldWidget confpassword;
	TextFieldWidget mail;

	public RegisterGuiWindow(RegisterGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 370;
		this.ySize = 236;
	}

	private static final ResourceLocation texture = new ResourceLocation("arcadework:textures/screens/register.png");

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
		password.render(ms, mouseX, mouseY, partialTicks);
		confpassword.render(ms, mouseX, mouseY, partialTicks);
		mail.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("arcadework:textures/screens/google_login.png"));
		this.blit(ms, this.guiLeft + 195, this.guiTop + 84, 0, 0, 160, 20, 160, 20);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("arcadework:textures/screens/facebook_login.png"));
		this.blit(ms, this.guiLeft + 195, this.guiTop + 114, 0, 0, 160, 20, 160, 20);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("arcadework:textures/screens/midline.png"));
		this.blit(ms, this.guiLeft + 185, this.guiTop + 44, 0, 0, 3, 163, 3, 163);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		if (password.isFocused())
			return password.keyPressed(key, b, c);
		if (confpassword.isFocused())
			return confpassword.keyPressed(key, b, c);
		if (mail.isFocused())
			return mail.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		password.tick();
		confpassword.tick();
		mail.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Registrarte con Google", 227, 88, -12829636);
		this.font.drawString(ms, "\u00A1Hola! Aqu\u00ED puedes registrarte...", 119, 4, -10066330);
		this.font.drawString(ms, "Reg\u00EDstrate usando el m\u00E9todo que mejor te convenga", 56, 16, -10066330);
		this.font.drawString(ms, "Registrarte con Facebook", 222, 118, -13421773);
		this.font.drawString(ms, "Crea una contrase\u00F1a", 43, 44, -10066330);
		this.font.drawString(ms, "Recibir promociones", 57, 164, -10066330);
		this.font.drawString(ms, "Confirma tu contrase\u00F1a", 35, 84, -10066330);
		this.font.drawString(ms, "Ingresar correo", 25, 124, -10066330);
		this.font.drawString(ms, " (Opcional)", 105, 124, -6710887);
		this.font.drawString(ms, "y noticias", 78, 174, -10066330);
		this.font.drawString(ms, "coming soon", 194, 69, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		password = new TextFieldWidget(this.font, this.guiLeft + 33, this.guiTop + 54, 120, 20, new StringTextComponent(""));
		guistate.put("text:password", password);
		password.setMaxStringLength(32767);
		this.children.add(this.password);
		this.addButton(new Button(this.guiLeft + 44, this.guiTop + 198, 98, 20, new StringTextComponent("Iniciar sesión"), e -> {
			if (true) {
				ArcadeworkMod.PACKET_HANDLER.sendToServer(new RegisterGui.ButtonPressedMessage(0, x, y, z));
				RegisterGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		news = new CheckboxButton(this.guiLeft + 30, this.guiTop + 164, 20, 20, new StringTextComponent(""), false);
		RegisterGui.guistate.put("checkbox:news", news);
		this.addButton(news);
		confpassword = new TextFieldWidget(this.font, this.guiLeft + 33, this.guiTop + 94, 120, 20, new StringTextComponent(""));
		guistate.put("text:confpassword", confpassword);
		confpassword.setMaxStringLength(32767);
		this.children.add(this.confpassword);
		mail = new TextFieldWidget(this.font, this.guiLeft + 33, this.guiTop + 134, 120, 20, new StringTextComponent(""));
		guistate.put("text:mail", mail);
		mail.setMaxStringLength(32767);
		this.children.add(this.mail);
	}
}
