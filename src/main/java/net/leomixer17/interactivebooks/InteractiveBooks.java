package net.leomixer17.interactivebooks;

import org.bstats.bukkit.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class InteractiveBooks extends JavaPlugin {

    private static InteractiveBooks plugin;
    private static final Map<String, IBook> books = new HashMap<>();

    @Override
    public void onEnable()
    {
        plugin = this;
        Config.loadAll();
        this.getCommand("ibooks").setExecutor(new CommandIBooks());
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        new MetricsLite(this);
    }

    @Override
    public void onDisable()
    {
        plugin = null;
    }

    public static InteractiveBooks getPlugin()
    {
        return plugin;
    }

    public static Map<String, IBook> getBooks()
    {
        return new HashMap<>(books);
    }

    public static IBook getBook(final String id)
    {
        return books.get(id);
    }

    public static void registerBook(final IBook book)
    {
        books.put(book.getId(), book);
    }

    public static void unregisterBook(final String id)
    {
        books.remove(id);
    }

}