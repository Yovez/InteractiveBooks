package net.leomixer17.interactivebooks;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

final class Config {

    static void loadAll()
    {
        InteractiveBooks.getPlugin().saveDefaultConfig();
        final File f = new File(InteractiveBooks.getPlugin().getDataFolder(), "books");
        if (!f.exists())
        {
            try
            {
                if(!f.mkdirs())
                    throw new IOException();
                Files.copy(Objects.requireNonNull(InteractiveBooks.getPlugin().getResource("examplebook.yml")), new File(f, "examplebook.yml").toPath());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        loadBookConfigs();
    }

    private static void loadBookConfigs()
    {
        InteractiveBooks.getBooks().keySet().forEach(InteractiveBooks::unregisterBook);
        final File booksFolder = new File(InteractiveBooks.getPlugin().getDataFolder(), "books");
        for (final File f : Objects.requireNonNull(booksFolder.listFiles()))
            if (f.getName().endsWith(".yml"))
                InteractiveBooks.registerBook(new IBook(f.getName().substring(0, f.getName().length() - 4), YamlConfiguration.loadConfiguration(f)));
    }
}