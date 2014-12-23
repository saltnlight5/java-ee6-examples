/*
 *  Zemian Deng 2014
 */

package zemian.service.logging;

/**
 * A message data to capture all the information that will be logged by service.
 * 
 * <p>A message string may contains parameter holders and then later by calling
 * getFormatedMessage() to actually bind it together. The syntax of the message
 * format is same as the java.lang.String.format(). Separating message and the
 * parameters allow message to be construct lazily. This can save some 
 * performance in long run, and it could make the message string more readable.
 * 
 * @author zedeng
 */
public class Message {
    private String message; 
    private Object[] messageParameters;
    private Exception cause;
    
    public static Message msg(String message, Object ... parameters) {
        return msg(null, message, parameters);
    }
    
    public static Message msg(Exception cause, String message, Object ... parameters) {
        Message result = new Message();
        result.setCause(cause);
        result.setMessage(message);
        result.setMessageParameters(parameters);
        return result;
    }
    
    public String getFormatedMessage() {
        return String.format(message, messageParameters);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getMessageParameters() {
        return messageParameters;
    }

    public void setMessageParameters(Object[] messageParameters) {
        this.messageParameters = messageParameters;
    }

    public Exception getCause() {
        return cause;
    }

    public void setCause(Exception cause) {
        this.cause = cause;
    }
    
    
}
