package com.dsh105.holoapi.util;

import com.dsh105.holoapi.HoloAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Lang {

    //PREFIX("prefix", "&e[&9HoloAPI&e] &r••• "),

    UPDATE_NOT_AVAILABLE("update_not_available", "&3An update is not available."),
    NO_PERMISSION("no_permission", "&3You are not permitted to do that."),
    COMMAND_ERROR("cmd_error", "&3Error for input string: &b%cmd%&3. Use &b/" + HoloAPI.getInstance().getCommandLabel() + " help &3for help."),
    HELP_INDEX_TOO_BIG("help_index_too_big", "&3Page &b%index% &3does not exist."),
    IN_GAME_ONLY("in_game_only", "&3Please log in to do that."),
    STRING_ERROR("string_error", "&3Error parsing &b%string%&3. Please revise command arguments."),
    NULL_PLAYER("null_player", "&b%player% &3is not online. Please try a different Player."),
    INT_ONLY("int_only", "&b%string% &3needs to be an integer."),
    INT_ONLY_WITH_ARGS("int_only_with_args", "&b%string% &3[Argument &b%argNum%&3] needs to be an integer."),
    WHUPS("whups", "&3Whups. Something bad happened."),

    PROMPT_INPUT("prompt_input", "&3Enter the desired lines of the new hologram. Enter &bDone &3when finished."),
    PROMPT_INPUT_FAILED("prompt_input_failed", "&3Hologram lines cannot be empty and must not exceed 32 characters. Retry or enter &bExit &3 to cancel."),
    PROMPT_INPUT_SUCCESS("prompt_input_success", "&3Hologram created of ID &b%id%&3."),
    ;

    private String path;
    private String def;
    private String[] desc;

    Lang(String path, String def, String... desc) {
        this.path = path;
        this.def = def;
        this.desc = desc;
    }

    public String[] getDescription() {
        return this.desc;
    }

    public String getPath() {
        return this.path;
    }

    public String getDefault() {
        return def;
    }

    public static void sendTo(CommandSender sender, String msg) {
        if (msg != null || !msg.equalsIgnoreCase("") && !msg.equalsIgnoreCase(" ") && !msg.equalsIgnoreCase("none")) {
            sender.sendMessage(HoloAPI.getInstance().getPrefix() + msg);
        }
    }

    public static void sendTo(Player p, String msg) {
        if (msg != null && !msg.equalsIgnoreCase("") && !msg.equalsIgnoreCase(" ") && !(msg.equalsIgnoreCase("none"))) {
            p.sendMessage(HoloAPI.getInstance().getPrefix() + msg);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getValue() {
        String result = HoloAPI.getInstance().getConfig(HoloAPI.ConfigType.LANG).getString(this.path, this.def);
        if (result != null && result != "" && result != "none") {
            return ChatColor.translateAlternateColorCodes('&', HoloAPI.getInstance().getConfig(HoloAPI.ConfigType.LANG).getString(this.path, this.def));
        } else {
            return "";
        }
    }

    public String getRaw() {
        return HoloAPI.getInstance().getConfig(HoloAPI.ConfigType.LANG).getString(this.path, this.def);
    }
}