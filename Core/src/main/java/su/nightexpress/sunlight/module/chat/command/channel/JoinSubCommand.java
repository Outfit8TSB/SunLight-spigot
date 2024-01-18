package su.nightexpress.sunlight.module.chat.command.channel;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.sunlight.module.chat.ChatChannel;
import su.nightexpress.sunlight.module.chat.ChatModule;
import su.nightexpress.sunlight.module.chat.config.ChatPerms;
import su.nightexpress.sunlight.module.chat.config.ChatLang;

class JoinSubCommand extends AbstractChannelSubCommand {

    public static final String NAME = "join";

    public JoinSubCommand(@NotNull ChatModule chatModule) {
        super(chatModule, new String[]{NAME}, ChatPerms.COMMAND_CHATCHANNEL);
        this.setDescription(plugin.getMessage(ChatLang.COMMAND_CHANNEL_JOIN_DESC));
        this.setUsage(plugin.getMessage(ChatLang.COMMAND_CHANNEL_JOIN_USAGE));
    }

    @Override
    protected void perform(@NotNull Player player, @NotNull ChatChannel channel) {
        this.module.getChannelManager().joinChannel(player, channel);
    }
}
