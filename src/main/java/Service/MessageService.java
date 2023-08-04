package Service;

import Model.Message;
import Model.Account;
import DAO.MessageDAO;
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

        Account findUser = accountDAO.checkUserExists(message.getPosted_by());
        if (findUser == null) {
            return null;
        }

        return messageDAO.insertMessage(message);
    }
}
