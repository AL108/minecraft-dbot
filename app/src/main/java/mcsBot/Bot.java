
package mcsBot;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.lang.StackWalker.Option;
import java.sql.Time;
import java.util.Arrays;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.io.IOException;
import java.lang.Runtime;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FilePermission;
import java.lang.ProcessBuilder;

public class Bot extends ListenerAdapter
{
    public static void main(String[] args) throws LoginException
    {
        /* if (args.length != 1) {
            System.out.println("please specify cmd args and try running again.");
            return;
        } */
        final String SERVER_PATH = "/home/alien/Desktop/mc-server/server.jar";
        final String TOKEN = "OTEwODE3MzMyMDk0NDU1ODI4.YZYWvg.hah9DfkJYSesun1-BBuXIfkR9Lg";
        // We only need 2 intents in this bot. We only respond to messages in guilds and private channels.
        // All other events will be disabled.
        JDA jda  = JDABuilder.createLight(TOKEN, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
            .addEventListeners(new Bot())
            .build();
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //jda.upsertCommand("start", "start a 3 hour minecraft session").queue(); // This can take up to 1 hour to show up in the client
        jda.getGuildById("848849465850462281").upsertCommand("start", "start a 3 hour minecraft session").queue(); // WAIT TILL DISCORD API IS UPDATED TO SUPPORT ATTACHMENTS IN SLASH COMMAND OPTIONS
        jda.getGuildById("814349764249780234").upsertCommand("start", "start a 3 hour minecraft session").queue(); // WAIT TILL DISCORD API IS UPDATED TO SUPPORT ATTACHMENTS IN SLASH COMMAND OPTIONS
        
    }
    
    /* @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        
        User user = event.getAuthor();
        Guild server = event.getGuild();
        //System.out.println(msg.getContentDisplay());
        //System.out.println(msg.getContentRaw().matches("(.*)/track(.*)"));
        if (msg.getContentRaw().matches("(.*)/track(.*)") && !msg.getAuthor().isBot())
        {
            
            //System.out.println(msg.getAuthor());
            //System.out.println("ok");
            MessageChannel channel = event.getChannel();
            //System.out.println(channel);
            //long time = System.currentTimeMillis();
            channel.addReactionById(event.getMessageId(), "minn:245267426227388416");
            channel.sendMessage(String.format("Hi %s, your workout has been tracked.",user.getName())).complete();
            //System.out.println("A");
            //System.out.println(server.getSelfMember().hasPermission(Permission.MESSAGE_WRITE));
        }
    }  */

    public void startSession(SlashCommandEvent event) {
        System.out.println("starting...");
        Runtime runtime = Runtime.getRuntime();
        ProcessBuilder x = new ProcessBuilder("/bin/bash","/home/alien/Desktop/my-code/minecraft-dbot/app/src/main/java/mcsBot/mc-run.sh");
        Process process;
        try {
            process = x.start();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        event.getChannel().sendMessage(String.format("Hi %s, your session is complete. For another, use the start command.",event.getUser().getAsMention())).complete();
        System.out.println("A session has completed.");

    }

    @Override
    public void onSlashCommand(SlashCommandEvent event)
    {
        String username = event.getUser().getAsMention();
        if (!event.getName().equals("start")) return; // make sure we handle the right command
        //long time = System.currentTimeMillis();
        //System.out.println(event.getOption("days").);
        try {
            event.reply(String.format("Hi %s, have fun playing minecraft. This session has 1 hour left.\nJoin at 112:213:170:34:25565",username))
            .queue();
        } catch (Exception e) {e.printStackTrace();}
        this.startSession(event);
        //System.out.println(event.getCommandString());
        //System.out.println(Arrays.toString(event.getOptions().toArray()));
    }
    
}
