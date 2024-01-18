package su.nightexpress.sunlight.module.chat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import su.nexmedia.engine.api.manager.AbstractListener;
import su.nightexpress.sunlight.SunLight;
import su.nightexpress.sunlight.api.event.PlayerPrivateMessageEvent;
import su.nightexpress.sunlight.module.chat.ChatModule;
import su.nightexpress.sunlight.module.chat.config.ChatConfig;
import su.nightexpress.sunlight.module.chat.event.AsyncSunlightPlayerChatEvent;
import su.nightexpress.sunlight.module.chat.util.ChatUtils;

public class ChatListener extends AbstractListener<SunLight> {

    private final ChatModule module;

    public ChatListener(@NotNull ChatModule module) {
        super(module.plugin());
        this.module = module;
    }

    @EventHandler(priority=EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.module.getChannelManager().autoJoinChannels(player);
    }

    @EventHandler(priority=EventPriority.NORMAL)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ChatUtils.clear(player);
        this.module.getChannelManager().leaveAllChannels(player);
    }

    @EventHandler(priority=EventPriority.LOWEST, ignoreCancelled=true)
    public void onChatProcessMessageLowest(AsyncPlayerChatEvent event) {
        if (ChatConfig.CHAT_EVENT_PRIORITY.get() != EventPriority.LOWEST) {
            return;
        }
        this.module.handleChatEvent(event);
    }

    @EventHandler(priority=EventPriority.LOW, ignoreCancelled=true)
    public void onChatProcessMessageLow(AsyncPlayerChatEvent event) {
        if (ChatConfig.CHAT_EVENT_PRIORITY.get() != EventPriority.LOW) {
            return;
        }
        this.module.handleChatEvent(event);
    }

    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onChatProcessMessageNormal(AsyncPlayerChatEvent event) {
        if (ChatConfig.CHAT_EVENT_PRIORITY.get() != EventPriority.NORMAL) {
            return;
        }
        this.module.handleChatEvent(event);
    }

    @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
    public void onChatProcessMessageHigh(AsyncPlayerChatEvent event) {
        if (ChatConfig.CHAT_EVENT_PRIORITY.get() != EventPriority.HIGH) {
            return;
        }
        this.module.handleChatEvent(event);
    }

    @EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
    public void onChatProcessMessageHighest(AsyncPlayerChatEvent event) {
        if (ChatConfig.CHAT_EVENT_PRIORITY.get() != EventPriority.HIGHEST) {
            return;
        }
        this.module.handleChatEvent(event);
    }

    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onChatProcessCommand(PlayerCommandPreprocessEvent event) {
        this.module.handleCommandEvent(event);
    }

    @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
    public void onSpyChat(AsyncSunlightPlayerChatEvent event) {
        if (!ChatConfig.SPY_ENABLED.get()) {
            return;
        }
        this.module.handleSpyMode(event);
    }

    @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
    public void onSpyCommand(PlayerCommandPreprocessEvent event) {
        if (!ChatConfig.SPY_ENABLED.get()) {
            return;
        }
        this.module.handleSpyMode(event);
    }

    @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
    public void onSpySocial(PlayerPrivateMessageEvent event) {
        if (!ChatConfig.SPY_ENABLED.get()) {
            return;
        }
        this.module.handleSpyMode(event);
    }
}

