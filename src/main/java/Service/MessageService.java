package Service;

import Model.Message;
import Model.Account;
import DAO.MessageDAO;

import java.util.List;

import DAO.AccountDAO;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO; 

    public MessageService() {
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO) {
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }

    public Message createMessage(Message message) {

        // first check if user actually exists in db. if not return null
        Account findUser = accountDAO.checkUserExists(message.getPosted_by());
        if (findUser == null) {
            return null;
        }

        String mssg = message.getMessage_text();
        // check if text meets length requirements. if not return null
        if (mssg.length() == 0 || mssg.length() > 254) {
            return null;
        }

        return messageDAO.insertMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageGivenMessageId(String messageId) {
        return messageDAO.getMessageGivenMessageId(Integer.parseInt(messageId));
    }

    public Message deleteMessageGivenId(String messageId) {

        int messageId_int = Integer.parseInt(messageId);
        // check if message exists in db. if not return null
        Message foundMessage = messageDAO.getMessageGivenMessageId(messageId_int);
        if (foundMessage == null) {
            return null;
        }

        return messageDAO.deleteMessageGivenId(foundMessage);
    }

    public Message updateMessage(String newText, int messageId) {

        // check if message exists in db. if not return null
        Message foundMessage = messageDAO.getMessageGivenMessageId(messageId);
        if (foundMessage == null) {
            return null;
        }

        // check if new text meets length requirements. if not return null
        if (newText.length() == 0 || newText.length() > 254) {
            return null;
        }

        return messageDAO.updateMessage(foundMessage, newText);
    }

    public List<Message> getMessagesForGivenUser(String account_id) {
        return messageDAO.allMessagesForGivenUser(Integer.parseInt(account_id));
    }
}
