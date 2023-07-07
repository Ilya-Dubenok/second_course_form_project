package by.itacademy.servletproject.endpoints.telegram;

import by.itacademy.servletproject.core.BeansFactory;
import by.itacademy.servletproject.core.dto.ArtistDTO;
import by.itacademy.servletproject.daO.api.IArtistDao;
import by.itacademy.servletproject.service.api.IArtistService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScope;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeChat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.format.DateTimeFormatter;

public class Bot extends TelegramLongPollingBot {


    BotCommand command = new BotCommand();
    private IArtistService service = BeansFactory.getInstance().getBeanInstance(IArtistService.class);

    @Override
    public String getBotUsername() {
        return "my_third3_bot";
    }

    @Override
    public String getBotToken() {
        return "6070567132:AAG2Gl68lItWCI9mrecd7EHKxDLTYjaTAzQ";
    }

    @Override
    public void onUpdateReceived(Update update) {



        Message message = update.getMessage();

        Integer id = Integer.valueOf(update.getMessage().getText());
        ArtistDTO artistDTO = service.get(id);
        if (null == artistDTO) {
            return;
        }


        SendMessage sm = SendMessage.builder()
                .chatId(message.getChatId().toString()) //Who are we sending a message to
                .text(service.get(id).getName()).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }

//        System.out.println(
//                "User: " + message.getFrom().getUserName() +
//                        "\nПишет: " +
//                        message.getText());

    }
}
