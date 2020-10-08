package lineBot;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@LineMessageHandler
public class TestBot {
	@EventMapping
	  public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
	    System.out.println("event: " + event);
	    return new TextMessage(event.getMessage().getText());
	  }

	  @EventMapping
	  public void handleDefaultMessageEvent(Event event) {
	    System.out.println("event: " + event);
	  }

}
