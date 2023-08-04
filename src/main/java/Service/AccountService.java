package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account registerAccount(Account account) {

        // check if username is empty
        if (account.getUsername().length() == 0) {
            return null;
        }

        // check if password is at least 4 characters long
        if (account.getPassword().length() < 4) {
            return null;
        }

        return accountDAO.insertAccount(account);
    }

    public Account loginAccount(Account account) {
        return accountDAO.loginAccount(account);
    }
}
