package Controller;

import Model.Account;
import Model.Message;

import Service.AccountService;
import Service.MessageService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;
    public SocialMediaController() {
        accountService = new AccountService();
        messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessagesHandler);
        app.get("/messages", this::getMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageGivenMessageId);
        app.delete("/messages/{message_id}", this::deleteMessageGivenMessageId);
        app.patch("/messages/{message_id}", this::updateMessageGivenMessageId);
        app.get("/accounts/{account_id}/messages", this::getMessagesForGivenUser);

        return app;
    }

    /**
     * POST Handler to register a new account
     * If accountService returns a null account (meaning posting a user was unsuccessful, the API will return a 400
     * message (client error).
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.post method.
     * @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
     */
    private void postRegisterHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account registeredAccount = accountService.registerAccount(account);
        if (registeredAccount==null) {
            ctx.status(400);
        } else {
            ctx.json(mapper.writeValueAsString(registeredAccount)).status(200);
        }
    }

    /**
     * POST Handler to login a user
     * If accountService returns a null account (meaning a user login was unsuccessful, the API will return a 400
     * message (client error).
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.post method.
     * @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
     */
    private void postLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account logedInAccount = accountService.loginAccount(account);
        if (logedInAccount==null) {
            ctx.status(400);
        } else {
            ctx.json(mapper.writeValueAsString(logedInAccount)).status(200);
        }
    }

    /**
     * POST Handler to create a new message
     * If messageService returns a null message (meaning creating message was unsuccessful, the API will return a 400
     * message (client error).
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.post method.
     * @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
     */
    private void postMessagesHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message newMessage = messageService.createMessage(message);
        if (newMessage==null) {
            ctx.status(400);
        } else {
            ctx.json(mapper.writeValueAsString(newMessage)).status(200);
        }
    }

    private void getMessagesHandler(Context ctx) {
        
    }

    private void getMessageGivenMessageId(Context ctx) {

    }

    private void deleteMessageGivenMessageId(Context ctx) {

    }

    private void updateMessageGivenMessageId(Context ctx) throws JsonProcessingException {

    }

    private void getMessagesForGivenUser(Context ctx) {

    }

}