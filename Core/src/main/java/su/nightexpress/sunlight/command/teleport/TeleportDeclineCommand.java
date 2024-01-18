package su.nightexpress.sunlight.command.teleport;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import su.nexmedia.engine.api.command.AbstractCommand;
import su.nexmedia.engine.api.command.CommandResult;
import su.nightexpress.sunlight.Perms;
import su.nightexpress.sunlight.SunLight;
import su.nightexpress.sunlight.command.teleport.impl.TeleportRequest;
import su.nightexpress.sunlight.config.Lang;

import java.util.List;
import java.util.UUID;

public class TeleportDeclineCommand extends AbstractCommand<SunLight> {

    public static final String NAME = "decline";

    public TeleportDeclineCommand(@NotNull SunLight plugin) {
        super(plugin, new String[]{NAME}, Perms.COMMAND_TELEPORT_DECLINE);
        this.setDescription(plugin.getMessage(Lang.COMMAND_TELEPORT_DECLINE_DESC));
        this.setUsage(plugin.getMessage(Lang.COMMAND_TELEPORT_DECLINE_USAGE));
        this.setPlayerOnly(true);
    }

    @NotNull
    public List<String> getTab(@NotNull Player player, int arg, @NotNull String[] args) {
        if (arg == 1) {
            return TeleportRequest.getRequests(player.getUniqueId()).stream().map(TeleportRequest::getSender).toList();
        }
        return super.getTab(player, arg, args);
    }

    protected void onExecute(@NotNull CommandSender sender, @NotNull CommandResult result) {
        Player player = (Player)sender;
        UUID playerId = player.getUniqueId();
        TeleportRequest request = result.length() < 2 ? TeleportRequest.getLatest(playerId) : TeleportRequest.getFrom(playerId, result.getArg(1));
        if (request == null) {
            this.plugin.getMessage(Lang.COMMAND_TELEPORT_DECLINE_ERROR_NOTHING).send(sender);
            return;
        }
        request.decline(true);
    }
}

