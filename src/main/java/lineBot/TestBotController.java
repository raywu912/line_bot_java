package lineBot;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@LineMessageHandler
public class TestBotController {
	@Autowired
	private LineMessagingClient lineMessagingClient;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String returntext; 
	
	//回覆訊息
	@EventMapping
	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT CONTENT");
		sql.append(" FROM EMAIL_INFO");
		sql.append(" WHERE ID='fc1d6589-3f12-4ba2-b3dd-ee06bc94ff50'");
		List<Map<String, Object>> list;
		Iterator<Map<String, Object>> itr;
		list = jdbcTemplate.queryForList(sql.toString());
		itr = list.iterator();
		while (itr.hasNext()) {
			Map itemMap = itr.next();
			returntext = itemMap.get("CONTENT").toString();
		}
//		switch(event.getMessage().getText()) {
//			case "1":
//				sql.append(" WHERE id=1");
//				list = jdbcTemplate.queryForList(sql.toString(),event.getMessage().getText());
//				itr = list.iterator();
//				while (itr.hasNext()) {
//					Map itemMap = itr.next();
//					returntext = itemMap.get("user_name").toString();
//				}
//				break;
//			case "2":
//				sql.append(" WHERE id=2");
//				list = jdbcTemplate.queryForList(sql.toString(),event.getMessage().getText());
//				itr = list.iterator();
//				while (itr.hasNext()) {
//					Map itemMap = itr.next();
//					returntext = itemMap.get("user_name").toString();
//				}
//				break;
//			default:
//				returntext = "沒資料唷";
//				break;
//		}
		return new TextMessage(returntext);
	}
	
	
}
