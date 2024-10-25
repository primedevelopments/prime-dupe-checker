package PDC;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import java.util.concurrent.*;

@Mod(modid = "PDC", version = "1.0")
public class PDCMain {

    private RootConfig config = new RootConfig();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Register the commands
        ClientCommandHandler.instance.registerCommand(new PDCCommand(config));
    }

    // RootConfig class with boolean variable
    public static class RootConfig {
        public boolean PDCEnabled = false;
    }

    // Command class for /PDC
    public static class PDCCommand extends CommandBase {
        private final RootConfig config;

        public PDCCommand(RootConfig config) {
            this.config = config;
        }

        @Override
        public String getCommandName() {
            return "pdc";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/pdc <on|off>";
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            if (args.length == 0) {
                sendChatMessage("§b§lPDC §8× §f§lInvalid command usage. Use /pdc <on|off>");
            } else if ("on".equalsIgnoreCase(args[0])) {
                if (!config.PDCEnabled) {
                    config.PDCEnabled = true;
                    try {
                        sendChatMessage("§b§lPDC §8× §f§lPDC is now §a§lenabled");




                    } catch (Exception e) {
                    }

                } else {
                    sendChatMessage("§c§l[§6§lSB§a§lDT§c§l] §f§lPDC is §e§lalready §a§lenabled§f§l!");
                }
            } else if ("off".equalsIgnoreCase(args[0])) {
                if (config.PDCEnabled) {
                    config.PDCEnabled = false;
                    sendChatMessage("§b§lPDC §8× §f§lPDC is now §c§ldisabled");
                } else {
                    sendChatMessage("§b§lPDC §8× §f§lPDC is §e§lalready §c§ldisabled§f§l!");
                }
            } else {
                sendChatMessage("§b§lPDC §8× §f§lInvalid command usage. Use /PDC <on|off>");
            }
        }

        @Override
        public int getRequiredPermissionLevel() {
            return 0;
        }

        private void sendChatMessage(String message) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(message));
        }
    }
}
