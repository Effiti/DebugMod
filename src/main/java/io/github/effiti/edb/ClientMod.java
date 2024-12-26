package io.github.effiti.edb;

import io.github.effiti.lib.Events.EventListener;
import io.github.effiti.lib.Events.EventManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.scoreboard.ScoreboardEntry;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class ClientMod extends EventListener implements ClientModInitializer {
    public static final String MOD_NAME = "myclientmod";

    //public static final Identifier TEST_PACKET_ID = new Identifier("visualplugin", "test");
    //public static final Identifier SERVER_TO_CLIENT_PACKET_ID = new Identifier("visualplugin", "test");
    public static final MinecraftClient mc = MinecraftClient.getInstance();
    public static boolean botEnabled = false;
    public static boolean denyingTp = false;
    private static boolean joinMessages;
    private static boolean entityMessages;
    private static boolean packetMessages;
    private static boolean actionMessages;

    public static void sendMessage(String text) {
        ClientMod.mc.inGameHud.getChatHud().addMessage(Text.literal(text).setStyle(Style.EMPTY.withColor(0xF4_F4_F4).withBold(true)));
    }

    public static boolean shouldSendJoinMessages() {
        return joinMessages;
    }

    public static boolean shouldSendEntityMessages() {
        return entityMessages;
    }

    public static boolean shouldSendPacketSendMessages() {
        return packetMessages;
    }

    public static boolean shouldSendActionMessages() {
        return actionMessages;
    }

    @Override
    public void onInitializeClient() {
        EventManager manager = new EventManager();
        manager.register(this);
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal("-msg").then(literal("join").executes(ctx -> {
                joinMessages = !joinMessages;
                sendMessage("joinMessages => " + joinMessages);
                return 0;
            })).then(literal("entities").executes(ctx -> {
                entityMessages = !entityMessages;
                sendMessage("entityMessages => " + entityMessages);
                return 0;
            })).then(literal("send").executes(ctx -> {
                packetMessages = !packetMessages;
                sendMessage("packetSendMessages => " + packetMessages);
                return 0;
            })).then(literal("action").executes(ctx -> {
                actionMessages = !actionMessages;
                sendMessage("actionMessages=> " + actionMessages);
                return 0;
            })));
            dispatcher.register(literal("-dump").then(literal("scoreboard").executes(ctx -> {
                StringBuilder scoreBoard = new StringBuilder();
                for (ScoreboardObjective el : mc.player.getWorld().getScoreboard().getObjectives()) {
                    scoreBoard.append(el.getName()).append(": ").append(el.getDisplayName().getString());
                    for (ScoreboardEntry score : mc.player.getWorld().getScoreboard().getScoreboardEntries(el)) {
                        scoreBoard.append("---").append(score.owner()).append(": ").append(score.value());
                    }
                }
                sendMessage(scoreBoard.toString());
                return 0;
            })));
            dispatcher.register(literal("-teams").executes(ctx -> {
                for (Team team : mc.player.getWorld().getScoreboard().getTeams()) {
                    sendMessage(team.getName() + " - prefix: " + team.getPrefix() + " - members:" + String.join(", ", team.getPlayerList()));
                }
                return 0;
            }));
        });

        new EventManager();
        EventManager.INSTANCE.register(new MainListener());


    }
}
