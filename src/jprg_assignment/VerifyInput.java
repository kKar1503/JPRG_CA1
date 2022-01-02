
// Class : DIT / FT / 1B / 04
// Admission Number : P2123181
// Name : Yam Kar Lok

package jprg_assignment;

public class VerifyInput {
    
    public static boolean[] isInt(String strNum) {
        // First array value indicates if there's input
        // Second array value indicates if the input isInt
        boolean[] returnBool = {false, false};
        if (strNum.isEmpty()) {
            UserActivityLogger.errLog("userInput is null.", new Throwable("Missing Input"));
            return returnBool; // {false, false}
        }
        returnBool[0] = true;
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            UserActivityLogger.errLog("userInput is not int.", nfe);
            return returnBool; // {true, false}
        }
        returnBool[1] = true;
        UserActivityLogger.infoLog("userInput verified as integer. User Input: " + strNum);
        return returnBool; // {true, true}
    }
    
    public static boolean[] isDouble(String strNum) {
        // First array value indicates if there's input
        // Second array value indicates if the input isInt
        boolean[] returnBool = {false, false};
        if (strNum.isEmpty()) {
            UserActivityLogger.errLog("userInput is null.", new Throwable("Missing Input"));
            return returnBool; // {false, false}
        }
        returnBool[0] = true;
        try {
            double i = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            UserActivityLogger.errLog("userInput is not double.", nfe);
            return returnBool; // {true, false}
        }
        returnBool[1] = true;
        UserActivityLogger.infoLog("userInput verified as double. User Input: " + strNum);
        return returnBool; // {true, true}
    }
}
