package net.BreachMC.Core;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.BreachMC.Core.Essentials.Broadcast;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by jesse.
 * Created at: 8-11-15, 18:53.
 */
public class Main extends JavaPlugin implements Listener{
    
    private String prefix;
    private String etcprefix;
    private String autosavecomplete;
    private String autosavemsg;
    private String noperms;
    private String ip;
    private String firstjoin;
    
    private static Plugin plugin;

    public void onEnable(){
        plugin = this;
        setup();
        
        getCommand("broadcast").setExecutor(new Broadcast());
        getCommand("bc").setExecutor(new Broadcast() );
        getCommand("ebroadcast").setExecutor(new Broadcast() );
        getCommand("ebc").setExecutor(new Broadcast() );
        getCommand("bcast").setExecutor(new Broadcast() );
        getCommand("ebcast").setExecutor(new Broadcast() );
        getCommand("resetmines").setExecutor(new ResetMines());
        getCommand("warp").setExecutor(new Warp() );
    }

    public void onDisable(){
        plugin = null;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static int getPing(Player p) {
        return ((CraftPlayer)p).getHandle().ping;
    }

    private void setup(){
    Save();
    Config();
    WorldDL();
    Bukkit.getPluginManager().registerEvents(new MineRewards(), this);
    Bukkit.getPluginManager().registerEvents(new GameClickEvent(), this);
    Bukkit.getPluginManager().registerEvents(new Warp(), this);
    Bukkit.getPluginManager().registerEvents(new Menus(), this);
    Bukkit.getPluginManager().registerEvents(this, this);
}


    private void Save(){


        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                Bukkit.broadcastMessage(Main.this.prefix + Main.this.autosavemsg);
                for (World w : Bukkit.getWorlds()) {
                    w.save();
                    w.setAutoSave(true);
                    w.setKeepSpawnInMemory(false);
                    System.gc();

                }
                Bukkit.broadcastMessage(Main.this.prefix + Main.this.autosavecomplete);
            }
        }, 0L, 1200 * getConfig().getInt("AutoSaveInterval"));



    }
    private void Prefixc(){
    getConfig().addDefault("Prefixes.Prefix", "&8[&c&lBreach &8&lPrison&8] &a");
    getConfig().addDefault("Prefixes.reload-completed", "&aThe config has been reloaded!");
    getConfig().addDefault("Prefixes.ETCPrefix", "&8&l>> &e");
    getConfig().addDefault("Prefixes.PrisonPrefix", "&3&lPrison &b>> ");
    getConfig().addDefault("Prefixes.Console", " &c&lCONSOLE ");
        Join();
}


    private void Warp(){
        getConfig().addDefault("Warp.Menu.Name", "&8Warps");
        WorldWarp();
        ARank();
        BRank();
        CRank();
        DRank();
        ERank();
        FRank();
        GRank();
        HRank();
        IRank();
        JRank();
        KRank();
        LRank();
        MRank();
        NRank();
        ORank();
        PRank();
        QRank();
        RRank();
        SRank();
        TRank();
        URank();
        VRank();
        WRank();
        XRank();
        YRank();
        ZRank();
        OtherWarps();
        getConfig().addDefault("Warp.tp", "&3&lPrison &b&l>> Teleporting you to ");

    }

    void WorldWarp(){
        getConfig().addDefault("Warp.World", "Mines");
    }

    private void Join(){
        getConfig().addDefault("Join.first", "&bHey everybody!\n &6I am new on this server!");
    }

    private void donors(){
        getConfig().addDefault("Donors.donormenu.name", "&bDonor Menu");
        getConfig().addDefault("Donors.donormenu.lore", "&bOpen the donor menu");
    }

    void ARank(){
        getConfig().addDefault("Warp.Ranks.A.Name", "&aA");
        getConfig().addDefault("Warp.Ranks.A.Lore", "&7Click here to go to the &aA &7Mine");
        getConfig().addDefault("Warp.Ranks.A.Command", "essentials:warp ");
    }
    void BRank(){
        getConfig().addDefault("Warp.Ranks.B.Name", "&eB");
        getConfig().addDefault("Warp.Ranks.B.Lore", "&7Click here to go to the &eB &7Mine");
        getConfig().addDefault("Warp.Ranks.B.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.B.Teleport", "Teleporting you to &eB");
    }
    void CRank(){
        getConfig().addDefault("Warp.Ranks.C.Name", "&eC");
        getConfig().addDefault("Warp.Ranks.C.Lore", "&7Click here to go to the &eC &7Mine");
        getConfig().addDefault("Warp.Ranks.C.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.C.Teleport", "Teleporting you to &eC");
    }
    void DRank(){
        getConfig().addDefault("Warp.Ranks.D.Name", "&eD");
        getConfig().addDefault("Warp.Ranks.D.Lore", "&7Click here to go to the &eD &7Mine");
        getConfig().addDefault("Warp.Ranks.D.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.D.Teleport", "Teleporting you to &eD");
    }

    void ERank(){
        getConfig().addDefault("Warp.Ranks.E.Name", "&6E");
        getConfig().addDefault("Warp.Ranks.E.Lore", "&7Click here to go to the &6E &7Mine");
        getConfig().addDefault("Warp.Ranks.E.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.E.Teleport", "Teleporting you to &6E");
    }
    void FRank(){
        getConfig().addDefault("Warp.Ranks.F.Name", "&6F");
        getConfig().addDefault("Warp.Ranks.F.Lore", "&7Click here to go to the &6E &7Mine");
        getConfig().addDefault("Warp.Ranks.F.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.F.Teleport", "Teleporting you to &6F");
    }
    void GRank(){
        getConfig().addDefault("Warp.Ranks.G.Name", "&6G");
        getConfig().addDefault("Warp.Ranks.G.Lore", "&7Click here to go to the &6G &7Mine");
        getConfig().addDefault("Warp.Ranks.G.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.G.Teleport", "Teleporting you to &6G");
    }
    void HRank(){
        getConfig().addDefault("Warp.Ranks.H.Name", "&6H");
        getConfig().addDefault("Warp.Ranks.H.Lore", "&7Click here to go to the &6H &7Mine");
        getConfig().addDefault("Warp.Ranks.H.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.H.Teleport", "Teleporting you to &6H");
    }
    void IRank(){
        getConfig().addDefault("Warp.Ranks.I.Name", "&6I");
        getConfig().addDefault("Warp.Ranks.I.Lore", "&7Click here to go to the &6I &7Mine");
        getConfig().addDefault("Warp.Ranks.I.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.I.Teleport", "Teleporting you to &6I");
    }
    void JRank(){
        getConfig().addDefault("Warp.Ranks.J.Name", "&cJ");
        getConfig().addDefault("Warp.Ranks.J.Lore", "&7Click here to go to the &cJ &7Mine");
        getConfig().addDefault("Warp.Ranks.J.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.J.Teleport", "Teleporting you to &cJ");
    }
    void KRank(){
        getConfig().addDefault("Warp.Ranks.K.Name", "&cK");
        getConfig().addDefault("Warp.Ranks.K.Lore", "&7Click here to go to the &cK &7Mine");
        getConfig().addDefault("Warp.Ranks.K.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.K.Teleport", "Teleporting you to &cK");
    }
    void LRank(){
        getConfig().addDefault("Warp.Ranks.L.Name", "&cL");
        getConfig().addDefault("Warp.Ranks.L.Lore", "&7Click here to go to the &cL &7Mine");
        getConfig().addDefault("Warp.Ranks.L.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.L.Teleport", "Teleporting you to &cL");
    }
    void MRank(){
        getConfig().addDefault("Warp.Ranks.M.Name", "&cM");
        getConfig().addDefault("Warp.Ranks.M.Lore", "&7Click here to go to the &cM &7Mine");
        getConfig().addDefault("Warp.Ranks.M.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.M.Teleport", "Teleporting you to &cM");
    }
    void NRank(){
        getConfig().addDefault("Warp.Ranks.N.Name", "&cN");
        getConfig().addDefault("Warp.Ranks.N.Lore", "&7Click here to go to the &cN &7Mine");
        getConfig().addDefault("Warp.Ranks.N.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.N.Teleport", "Teleporting you to &cN");
    }
    void ORank(){
        getConfig().addDefault("Warp.Ranks.O.Name", "&1O");
        getConfig().addDefault("Warp.Ranks.O.Lore", "&7Click here to go to the &1O &7Mine");
        getConfig().addDefault("Warp.Ranks.O.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.O.Teleport", "Teleporting you to &1O");
    }
    void PRank(){
        getConfig().addDefault("Warp.Ranks.P.Name", "&cP");
        getConfig().addDefault("Warp.Ranks.P.Lore", "&7Click here to go to the &cP &7Mine");
        getConfig().addDefault("Warp.Ranks.P.Command", "essentials:warp ");
        getConfig().addDefault("Warp.Ranks.P.Teleport", "Teleporting you to &1P");
    }
    void QRank(){
        getConfig().addDefault("Warp.Ranks.Q.Name", "&4Q");
        getConfig().addDefault("Warp.Ranks.Q.Lore", "&7Click here to go to the &4Q &7Mine");
        getConfig().addDefault("Warp.Ranks.Q.Command", "essentials:warp ");
    }
    void RRank(){
        getConfig().addDefault("Warp.Ranks.R.Name", "&4R");
        getConfig().addDefault("Warp.Ranks.R.Lore", "&7Click here to go to the &4R &7Mine");
        getConfig().addDefault("Warp.Ranks.R.Command", "essentials:warp ");
    }
    void SRank(){
        getConfig().addDefault("Warp.Ranks.S.Name", "&4S");
        getConfig().addDefault("Warp.Ranks.S.Lore", "&7Click here to go to the &4S &7Mine");
        getConfig().addDefault("Warp.Ranks.S.Command", "essentials:warp ");
    }
    void TRank(){
        getConfig().addDefault("Warp.Ranks.T.Name", "&4T");
        getConfig().addDefault("Warp.Ranks.T.Lore", "&7Click here to go to the &4T &7Mine");
        getConfig().addDefault("Warp.Ranks.T.Command", "essentials:warp ");
    }
    void URank(){
        getConfig().addDefault("Warp.Ranks.U.Name", "&4U");
        getConfig().addDefault("Warp.Ranks.U.Lore", "&7Click here to go to the &4U &7Mine");
        getConfig().addDefault("Warp.Ranks.U.Command", "essentials:warp ");
    }
    void VRank(){
        getConfig().addDefault("Warp.Ranks.V.Name", "&4V");
        getConfig().addDefault("Warp.Ranks.V.Lore", "&7Click here to go to the &4V &7Mine");
        getConfig().addDefault("Warp.Ranks.V.Command", "essentials:warp ");
    }
    void WRank(){
        getConfig().addDefault("Warp.Ranks.W.Name", "&4W");
        getConfig().addDefault("Warp.Ranks.W.Lore", "&7Click here to go to the &4W &7Mine");
        getConfig().addDefault("Warp.Ranks.W.Command", "essentials:warp ");
    }
    void XRank(){
        getConfig().addDefault("Warp.Ranks.X.Name", "&5X");
        getConfig().addDefault("Warp.Ranks.X.Lore", "&7Click here to go to the &5X &7Mine");
        getConfig().addDefault("Warp.Ranks.X.Command", "essentials:warp X");
    }
    void YRank(){
        getConfig().addDefault("Warp.Ranks.Y.Name", "&5Y");
        getConfig().addDefault("Warp.Ranks.Y.Lore", "&7Click here to go to the &5Y &7Mine");
        getConfig().addDefault("Warp.Ranks.Y.Command", "essentials:warp Y");
    }
    void ZRank(){
        getConfig().addDefault("Warp.Ranks.Z.Name", "&0Z");
        getConfig().addDefault("Warp.Ranks.Z.Lore", "&7Click here to go to the &0Z &7Mine");
        getConfig().addDefault("Warp.Ranks.Z.Command", "essentials:warp Z");
    }
    void HeroRank(){

    }
    void LegendRank(){

    }
    void TitanRank(){

    }

    void DonorWarps(){
        HeroRank();
        LegendRank();
        TitanRank();

    }
    void OtherWarps(){
        DSGwarp();
        Plotswarp();
        Crateswarp();
        PvPwarp();
        SpawnWarp();
        ServerTP();
    }
    void DSGwarp(){
        getConfig().addDefault("Warp.Warps.DSG.Name", "&aDSG");
        getConfig().addDefault("Warp.Warps.DSG.Lore", "&7Dirt, sand, gravel ...");
        getConfig().addDefault("Warp.Warps.DSG.Command", "essentials:warp ");
    }
    void Plotswarp(){
        getConfig().addDefault("Warp.Warps.Plots.Name", "&aPlots");
        getConfig().addDefault("Warp.Warps.Plots.Command", "essentials:warp ");
    }
    void Crateswarp(){
        getConfig().addDefault("Warp.Warps.Crates.Name", "&aCrates");
        getConfig().addDefault("Warp.Warps.Crates.Command", "essentials:warp ");
    }
    void PvPwarp(){
        getConfig().addDefault("Warp.Warps.PvP.Name", "&aPvP");
        getConfig().addDefault("Warp.Warps.PvP.Command", "essentials:warp ");
    }
    void SpawnWarp(){
        getConfig().addDefault("Warp.Warps.Spawn.Name", "&aSpawn");
        getConfig().addDefault("Warp.Warps.Spawn.Command", "spawn");
    }

    void ServerTP(){
        getConfig().addDefault("Warp.Menus.ServerTP.Name", "&aOpen /Server menu");
        getConfig().addDefault("Warp.Menus.ServerTP.Command", "server");
    }











    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {
            event.setKickMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Join.Whitelist")));
        }
    }

    void Config(){

        Prefixc();

        getConfig().addDefault("AutoSaveInterval", Integer.valueOf(1));
        getConfig().addDefault("IP", "BreachMC.xyz");
        getConfig().addDefault("Prefixes.headerandfooter", "&8&l&m+----------------------------------+");

        getConfig().addDefault("no-permission", "&4&lYou don`t have permission for this command!");
        getConfig().addDefault("Join.Whitelist", "\n&4Sorry but this server is currently under &b&lCONSTRUCTION!");
        getConfig().addDefault("Join.Join", "&e&lJOINED %player%");


        savec();
        votec();

        Warp();
donors();

        getConfig().options().copyDefaults(true);
        saveConfig();
        this.prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Prefixes.Prefix"));
        this.etcprefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Prefixes.ETCPrefix"));

        this.autosavecomplete = ChatColor.translateAlternateColorCodes('&', getConfig().getString("autosavecomplete"));
        this.autosavemsg = ChatColor.translateAlternateColorCodes('&', getConfig().getString("autosavemsg"));
        this.noperms = ChatColor.translateAlternateColorCodes('&', getConfig().getString("no-permission"));

        this.ip = ChatColor.translateAlternateColorCodes('&', getConfig().getString("IP"));

        this.firstjoin = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Join.first"));
    }




    void savec(){
        getConfig().addDefault("autosavemsg", "&7Saving worlds and player data");
        getConfig().addDefault("autosavecomplete", "&a&lSuccessfully saved all worlds and player data");
    }








    void votec(){
        getConfig().addDefault("Vote.Vote1", "&bNot availible :(");
        getConfig().addDefault("Vote.Vote2", "&bNot availible :(");
        getConfig().addDefault("Vote.Vote3", "&bNot availible :(");
        getConfig().addDefault("Vote.Vote4", "&bNot availible :(");
        getConfig().addDefault("Vote.Vote5", "&bNot availible :(");
        getConfig().addDefault("Vote.Vote6", "&bNot availible :(");
        getConfig().addDefault("Vote.Vote7", "&bNot availible :(");
        getConfig().addDefault("Vote.Vote8", "&bNot availible :(");
    }



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){




        if (label.equalsIgnoreCase("rlconfig")) {
            if (!sender.hasPermission("breach.cmds.bc")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Prefixes.PrisonPrefix") + Main.plugin.getConfig().getString("no-permission")));
                return false;
            }
            reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Prefixes.PrisonPrefix") + getConfig().getString("Prefixes.reload-completed")));
            getLogger().warning("The plugin config has been reloaded!!!");
            return true;



        }

        if(label.equalsIgnoreCase("ip")){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Prefixes.PrisonPrefix") + "Our server IP: " + Main.plugin.getConfig().getString("IP")));
        }



        if(label.equalsIgnoreCase("save")){
            if(!sender.hasPermission("breach.cmds.save")){
                sender.sendMessage(this.noperms);
                return false;
            }



            Bukkit.broadcastMessage(Main.this.prefix + Main.this.autosavemsg);
            for (World w : Bukkit.getWorlds()) {
                w.save();
                w.setAutoSave(true);

            }
            Bukkit.broadcastMessage(Main.this.prefix + Main.this.autosavecomplete);
            return true;

        }

        if(label.equalsIgnoreCase("vote")){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Vote.Vote1")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Vote.Vote2")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Vote.Vote3")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Vote.Vote4")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Vote.Vote5")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Vote.Vote6")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Vote.Vote7")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Vote.Vote8")));
            return true;


        }





        if(label.equalsIgnoreCase("ip")){

            Player p = (Player)sender;
                p.sendMessage(this.ip);
                return true;


        }






        if(!(sender instanceof Player)){
            sender.sendMessage("This can be run only as player!");
            return true;
        }
        if ((cmd.getName().equalsIgnoreCase("ping"))){
            if (args.length == 0)
            {
                Player p = (Player)sender;
                int ping = ((CraftPlayer)p).getHandle().ping;
                if (ping < 50)
                {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lPING&7: &fYour current ping to 'Breach MC' is &a&l" + ping + "&fms!"));
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10.0F, 10.0F);
                    return true;
                }
                if ((ping >= 51) && (ping < 100))
                {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lPING&7: &fYour current ping to 'Breach MC' is &6&l" + ping + "&fms!"));
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10.0F, 5.0F);
                    return true;
                }
                if ((ping >= 101) && (ping < 349))
                {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lPING&7: &fYour current ping to 'Breach MC' is &c&l" + ping + "&fms!"));
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 10.0F, 10.0F);
                    return true;
                }
                if (ping >= 350)
                {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lPING&7: &fYour current ping to 'Breach MC' is &4&l" + ping + "&fms!"));
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 10.0F, 4.0F);
                    return true;
                }
            }
            else if (args.length == 1)
            {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null)
                {
                    int ping = ((CraftPlayer)target).getHandle().ping;
                    if (ping <= 50)
                    {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lPING&7: &f" + target.getName() + "'s current ping to 'Breach MC' is &a&l" + ping + "&fms!"));
                        return true;
                    }
                    if ((ping >= 51) && (ping < 100))
                    {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lPING&7: &f" + target.getName() + "'s current ping to 'Breach MC' is &6&l" + ping + "&fms!"));
                        return true;
                    }
                    if ((ping >= 101) && (ping < 349))
                    {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lPING&7: &f" + target.getName() + "'s current ping to 'Breach MC' is &c&l" + ping + "&fms!"));
                        return true;
                    }
                    if (ping >= 350)
                    {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lPING&7: &f" + target.getName() + "'s current ping to 'Breach MC' is &4&l" + ping + "&fms!"));
                        return true;
                    }
                }
            }
        }


        return false;
    }














    @EventHandler
    public void onPlayerLogin(final PlayerLoginEvent e) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                if (e.getPlayer().hasPlayedBefore()) return;

                Firework f = (Firework) e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);

                FireworkMeta fm = f.getFireworkMeta();
                fm.addEffect(FireworkEffect.builder()
                        .flicker(false)
                        .trail(true)
                        .with(FireworkEffect.Type.CREEPER)
                        .withColor(Color.GREEN)
                        .withFade(Color.BLUE)
                        .build());
                fm.setPower(3);
                f.setFireworkMeta(fm);

                e.getPlayer().chat(ChatColor.translateAlternateColorCodes('&', firstjoin));
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.chat("Welcome "+  e.getPlayer().getName() + " on &c&lBreach &8&lPrison!");
                }
            }
        }, 20);
    }










    void WorldDL(){
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "WDL|CONTROL");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "WDL|INIT", (s, player, bytes) -> {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeInt(1);
            out.writeBoolean(false);
            out.writeInt(1);
            out.writeBoolean(false);
            out.writeBoolean(false);
            out.writeBoolean(false);
            out.writeBoolean(false);
            getLogger().info("Blocking WorldDownloader for " + player.getDisplayName());
            player.sendPluginMessage(this, "WDL|CONTROL", out.toByteArray());
            getServer().dispatchCommand(Bukkit.getConsoleSender(), "kick " + player.getName() + " World download client!");
            getServer().dispatchCommand(Bukkit.getConsoleSender(), "warn " + player.getName() + " World Download client!");


        });
    }
}
