
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
public class LoginwhitmailGuiWindow extends ContainerScreen<LoginwhitmailGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = LoginwhitmailGui.guistate;
	TextFieldWidget password;
	TextFieldWidget mail;
	CheckboxButton remember;

	public LoginwhitmailGuiWindow(LoginwhitmailGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 346;
		this.ySize = 226;
	}

	private static final ResourceLocation texture = new ResourceLocation("arcadework:textures/screens/loginwhitmail.png");

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
		password.render(ms, mouseX, mouseY, partialTicks);
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
		this.blit(ms, this.guiLeft + 172, this.guiTop + 92, 0, 0, 160, 20, 160, 20);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("arcadework:textures/screens/midline.png"));
		this.blit(ms, this.guiLeft + 164, this.guiTop + 49, 0, 0, 3, 163, 3, 163);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("arcadework:textures/screens/facebook_login.png"));
		this.blit(ms, this.guiLeft + 172, this.guiTop + 125, 0, 0, 160, 20, 160, 20);

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
		if (mail.isFocused())
			return mail.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		password.tick();
		mail.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Iniciar con Google", 214, 96, -12829636);
		this.font.drawString(ms, "Bienvenido de nuevo", 122, 11, -10066330);
		this.font.drawString(ms, "Inicia sesi\u00F3n", 138, 24, -10066330);
		this.font.drawString(ms, "Iniciar con Facebook", 209, 130, -13421773);
		this.font.drawString(ms, "Ingresar contrase\u00F1a", 31, 50, -10066330);
		this.font.drawString(ms, "Ingresar correo", 42, 107, -10066330);
		this.font.drawString(ms, "Mantener Sesi\u00F3n", 53, 165, -10066330);
		this.font.drawString(ms, "coming soon", 172, 78, -12829636);
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
		password = new TextFieldWidget(this.font, this.guiLeft + 21, this.guiTop + 69, 120, 20, new StringTextComponent(""));
		guistate.put("text:password", password);
		password.setMaxStringLength(32767);
		this.children.add(this.password);
		this.addButton(new Button(this.guiLeft + 32, this.guiTop + 190, 98, 20, new StringTextComponent("Iniciar sesión"), e -> {
			if (true) {
				ArcadeworkMod.PACKET_HANDLER.sendToServer(new LoginwhitmailGui.ButtonPressedMessage(0, x, y, z));
				LoginwhitmailGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		mail = new TextFieldWidget(this.font, this.guiLeft + 21, this.guiTop + 125, 120, 20, new StringTextComponent(""));
		guistate.put("text:mail", mail);
		mail.setMaxStringLength(32767);
		this.children.add(this.mail);
		remember = new CheckboxButton(this.guiLeft + 30, this.guiTop + 160, 20, 20, new StringTextComponent(""), false);
		LoginwhitmailGui.guistate.put("checkbox:remember", remember);
		this.addButton(remember);
	}
}
